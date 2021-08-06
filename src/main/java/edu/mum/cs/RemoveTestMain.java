package edu.mum.cs;

import edu.mum.cs.domain.Book;
import edu.mum.cs.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.Period;

public class RemoveTestMain {
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("edu.mum.cs");

    private void removeTransientObject() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        System.out.println("Transaction begins-----------------");

//      1. removeTransientObject---assigned primary key, not naturally generated key.

        Book b1 = new Book("11-11-22-33-6", "Hibernate");
        System.out.println("------before remove for natural primary key-------------");
        entityManager.remove(b1);
        System.out.println("------after remove for natural primary key-------------");
//       2. remove auto generated primary key
        Person p1 = new Person("jian", "he", "test@test.com", LocalDate.now());
        System.out.println("------before remove for generated primary key-------------");
        entityManager.remove(p1);
        System.out.println("------after remove for generated primary key-------------");

        entityManager.getTransaction().commit();
        System.out.println("4-------------");
        entityManager.close();
    }

    private void removeDeatchedObject() {

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Person p1 = new Person("jian", "he", "test@test.com", LocalDate.now());
        System.out.println("... Begin persist process");
        entityManager.persist(p1); //Now p1 is in the persist context.

        System.out.println("... Begin detach process");
        entityManager.detach(p1); //p1 remove out from persist context.
        System.out.println("... Begin remove process");
//        .IllegalArgumentException: Removing a detached instance edu.mum.cs.domain.Person#1
        //Basically, you cannot removing detached objects. Otherwise, It will throw exceptions.
        entityManager.remove(p1);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private void removeManagedObject() {
        persist();
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("-------------find from the database.");
        Person p = entityManager.find(Person.class,1L);

//        getReference won't hit to the database.
//        Person p = entityManager.getReference(Person.class,1L);

        System.out.println("------------delete from database.");
        entityManager.remove(p);
//        System.out.println("1-------------");
//        Book b2 = entityManager.getReference(Book.class, "11-11-22-33-6");
//        System.out.println("2-------------");
//
//        entityManager.remove(b2);
//        System.out.println("3-------------");


        entityManager.getTransaction().commit();
        System.out.println("4-------------");
        entityManager.close();
    }

    private void persist() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Person p1 = new Person("jian", "he", "test@test.com", LocalDate.now());
        Book b1 = new Book("11-11-22-33-6", "Hibernate");
        entityManager.persist(b1);
        entityManager.persist(p1); //Now p1 is in the persist context.
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    public static void main(String[] args) throws Exception {
        RemoveTestMain main = new RemoveTestMain();
//        main.removeTransientObject();
//        main.removeDeatchedObject();
        main.removeManagedObject();

    }
}
