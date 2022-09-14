package facades;

import dtos.ActorDTO;
import entities.Actor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ActorFacade
{
    private static ActorFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private ActorFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static ActorFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ActorFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ActorDTO create(ActorDTO actorDTO){
        Actor Actor = new Actor(actorDTO.getName());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(Actor);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new ActorDTO(Actor);
    }
}
