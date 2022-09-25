package facades;

import dtos.ActorDTO;
import dtos.MovieDTO;
import dtos.MovieDTO;
import entities.Actor;
import entities.Movie;
import entities.Movie;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.LinkedHashSet;
import java.util.Set;

public class MovieFacade 
{
    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    private static ActorFacade actorFacade;

    //Private Constructor to ensure Singleton
    private MovieFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
            actorFacade = ActorFacade.getInstance(emf);
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public MovieDTO create(MovieDTO movieDTO){
        EntityManager em = getEntityManager();
        Movie movie = new Movie(movieDTO);
        try {
            em.getTransaction().begin();
                 movieDTO.getActors().forEach(actor ->
                 {
                     if(actor.getId() != null)
                     {
                         actor = new ActorDTO(em.find(Actor.class, actor.getId()));
                        em.merge(actor);
                     }
                     else
                     {
                        em.persist(actor);
                     }
                 });
            em.persist(movie);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new MovieDTO(movie);
    }

    /*public MovieDTO create(MovieDTO movieDTO){
        Movie Movie = new Movie(movieDTO.getYear(), movieDTO.getTitle(), movieDTO.getGenre());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(Movie);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new MovieDTO(Movie);
    }*/



    public MovieDTO update(MovieDTO movieDTO)
    {
        EntityManager em = getEntityManager();
        //MovieDTO movieFromDB = new MovieDTO( em.find(Movie.class, movieDTO.getId()));

        /*if(movieDTO.equals(movieDTO))
        {
            return movieFromDB;
        }*/

        movieDTO.getActors().forEach(actor -> {
            if(actor.getId() == null)
            {
                actor = ActorFacade.getInstance(emf).create(actor);
            }
            else
            {
                actor = ActorFacade.getInstance(emf).update(actor);
            }
        });

        /*for(Actor a : movieDTO.getActors())
        {
            if(a.getId() == null)
            {
                a = ActorFacade.getInstance(emf).create(a);
            }
            else
            {
               a = ActorFacade.getInstance(emf).update(a);
            }
        }*/

        Movie movie = new Movie(movieDTO);


        try {
            em.getTransaction().begin();
            em.merge(movie);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new MovieDTO(movie);
    }
    public MovieDTO getById(long id)
    {
        EntityManager em = emf.createEntityManager();
        Movie Movie = em.find(Movie.class, id);

        return new MovieDTO(Movie);
    }
}
