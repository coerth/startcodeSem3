package dtos;

import entities.Actor;
import entities.Movie;

import java.util.LinkedHashSet;
import java.util.Set;

public class ActorDTO
{
    private int id;
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
        if(!actor.getMovies().isEmpty())
        {
            for(Movie m : actor.getMovies())
            {
                movies.add(new MovieDTO(m));
            }
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
