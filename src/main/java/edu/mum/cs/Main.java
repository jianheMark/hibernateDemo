package edu.mum.cs;
import edu.mum.cs.domain.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        //Persistence unitname in the xml file.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("edu.mum.cs");
//      https://docs.jboss.org/hibernate/orm/3.5/javadocs/org/hibernate/impl/SessionFactoryImpl.html
/*Concrete implementation of the SessionFactory interface. Has the following responsibilites
caches configuration settings (immutably)
 */
        System.out.println("emf"+ emf);
        System.out.println("emf.isOpen()"+ emf.isOpen());
        EntityManager entityManager = emf.createEntityManager();
        Person p = new Person("jian", "he", "test@test.com", LocalDate.now());
        entityManager.getTransaction().begin();
        System.out.println("1. Transaction().begin---------------");
        entityManager.persist(p);//move Person object to Persistence Context.
        System.out.println(p);

        System.out.println("2---------------");

        p.setFirstName("lawrence"); //In the persistence context change firstName.

        //entityManager.setFirstName("test");
        System.out.println("3---------------");
        entityManager.getTransaction().commit();// after commit, then that's it. Nothing can change.
        System.out.println("4---------------");
        entityManager.close();
        emf.close();
        System.out.println("5---------------");



    }
}
