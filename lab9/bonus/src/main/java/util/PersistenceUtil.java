package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {

    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    private PersistenceUtil() {
    }

    public static EntityManagerFactory getFactory() {
        if(factory == null) {
            factory = Persistence.createEntityManagerFactory("MusicAlbumsPU");
        }
        return factory;
    }

    public static EntityManager getEntityManager() {
        if(em == null) {
            em = getFactory().createEntityManager();
        }
        return em;
    }

}
