package facades;

import dtos.ActorDTO;
import dtos.ActorDTO;
import entities.Actor;
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
        Actor actor = new Actor(actorDTO.getName());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(actor);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new ActorDTO(actor);
    }

    public ActorDTO update(ActorDTO actorDTO)
    {
        EntityManager em = getEntityManager();
        Actor actorFromDB = em.find(Actor.class, actorDTO.getId());


        Actor actor = new Actor(actorDTO.getName());


        try {
            em.getTransaction().begin();
            em.merge(actor);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new ActorDTO(actor);
    }
    public ActorDTO getById(long id)
    {
        EntityManager em = emf.createEntityManager();
        Actor actor = em.find(Actor.class, id);

        return new ActorDTO(actor);
    }
}


