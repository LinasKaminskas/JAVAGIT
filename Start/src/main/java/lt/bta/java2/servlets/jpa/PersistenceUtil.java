package lt.bta.java2.servlets.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.function.Function;

public class PersistenceUtil {

    private static final EntityManagerFactory emf; //vienas duomenu bazei

    static {                        //gaudome klaidas
        try {
            emf = Persistence.createEntityManagerFactory("employees-persistence-unit");

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial EntityManagerFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public static EntityManager createEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    public static <R> R executeInsideTransaction(Function<EntityManager, R> action) { //vykdome transakcija
        EntityManager entityManager = createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            R res = action.apply(entityManager);
            transaction.commit();
            return res;
        }
        catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

}