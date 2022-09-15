package dtos;

import entities.Actor;
import entities.Employee;
import entities.Movie;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ActorDTO
{
    private Long id;
    private String name;
    private Set<MovieDTO> movies = new LinkedHashSet<>();

    public ActorDTO(Actor actor)
    {
        if(actor.getId() == null)
        {
            this.id = actor.getId();
        }
        this.id = actor.getId();
        this.name = actor.getName();
        /*if(!actor.getMovies().isEmpty())
        {
            for(Movie m : actor.getMovies())
            {
                MovieDTO newMovie = new MovieDTO(m);
                if(movies.contains(newMovie))
                {
                    continue;
                }
                movies.add(newMovie);
            }
        }*/

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MovieDTO> getMovies() {
        return movies;
    }

    public void setMovies(Set<MovieDTO> movies) {
        this.movies = movies;
    }

    public static List<ActorDTO> getDtos(List<Actor> actors){
        List<ActorDTO> actorsDTOS = new ArrayList<>();
        actors.forEach(actor->actorsDTOS.add(new ActorDTO(actor)));
        return actorsDTOS;
    }
}
