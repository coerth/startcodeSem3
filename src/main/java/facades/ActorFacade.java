package facades;

import dtos.ActorDTO;
import dtos.ActorDTO;
import entities.Actor;
import entities.Actor;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

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

    public Actor create(Actor actor){
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            actor.getMovies().forEach(movie -> {
                em.persist(movie);
            });
            em.persist(actor);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return actor;
    }

    public List<ActorDTO> getAll()
    {
        EntityManager em = getEntityManager();

        List<ActorDTO> actors = new ArrayList<>();

        try
        {
            TypedQuery<Actor> query = em.createQuery("SELECT a FROM Actor a", Actor.class);
            actors = ActorDTO.getDtos(query.getResultList());

        }
        finally {
            em.close();
        }

        return actors;

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


