package facades;

import dtos.MovieDTO;
import dtos.MovieDTO;
import entities.Movie;
import entities.Movie;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class MovieFacade 
{
    private static MovieFacade instance;
    private static EntityManagerFactory emf;

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
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public MovieDTO create(MovieDTO movieDTO){
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
    }

    public MovieDTO update(MovieDTO movieDTO)
    {
        EntityManager em = getEntityManager();
        Movie MovieFromDB = em.find(Movie.class, movieDTO.getId());


        Movie Movie = new Movie(movieDTO.getYear(), movieDTO.getTitle(), movieDTO.getGenre());


        try {
            em.getTransaction().begin();
            em.merge(Movie);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new MovieDTO(Movie);
    }
    public MovieDTO getById(long id)
    {
        EntityManager em = emf.createEntityManager();
        Movie Movie = em.find(Movie.class, id);

        return new MovieDTO(Movie);
    }
}
