package edu.mum.cs;
import edu.mum.cs.domain.Book;
import edu.mum.cs.domain.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.Period;

public class MergeTestMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("edu.mum.cs");

    private void persist() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Person p = new Person("jian", "he", "test@test.com", LocalDate.now());
        em.persist(p);
        em.getTransaction().commit();
        em.close();
    }

    private void merge() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Person p1 = entityManager.find(Person.class, 1L);

        //move p1 outside of persistence context. But p1 still refer to p1.
        System.out.println("1..................");
        entityManager.detach(p1);

//        https://youtu.be/NRPsWRNnrpQ?t=342
        Person p2 = entityManager.find(Person.class, 1L);

        p1.setFirstName("parton");
        System.out.println("2..................");

        //This way does not make p1 in to persist context.
        entityManager.merge(p1); //only copy the updated value. Will not copy the entire object.


        p1 = entityManager.merge(p1);  //This way will move p1 into persist context. using varaiable hold p1.
        p1.setFirstName("Josh");

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void merge2() {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Person p1 = new Person("jian", "he", "test@test.com", LocalDate.now());

        p1 = entityManager.merge(p1);

        Book b1 = new Book("11-11-22-33-6", "Hibernate");
        b1 = entityManager.merge(b1); //https://youtu.be/ixWNB0q2XOk?t=493

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private void mergeVSpersist() {
        //For understand the diff between persist and merge. persist intention is saving.
        //while merge intention is for update and merge.
        //A different object with the same identifier value was already associated with the session : [edu.mum.cs.domain.Book#1133-6]
//        persist will not allow same primary key execute twice. So using persist, all the records will be unique.

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

//        Book b1 = new Book("1133-6","Hibernate");
//        entityManager.persist(b1);
//        Book b2 = new Book("1133-6","Hibern");
//        entityManager.persist(b2);

        Book b1 = new Book("1133-6", "Hibernate");
        System.out.println("Merging.......");
        entityManager.merge(b1);
        Book b2 = new Book("1133-6", "Spring");
        System.out.println("Updating........");
        entityManager.merge(b2);


        entityManager.getTransaction().commit();
        entityManager.close();

    }


    public static void main(String[] args) {
        MergeTestMain main = new MergeTestMain();
//        main.persist();
//        main.merge();
        main.mergeVSpersist();
    }
}
