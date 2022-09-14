package dtos;

import entities.Actor;
import entities.Movie;

import java.util.LinkedHashSet;
import java.util.Set;

public class MovieDTO
{
    private Integer id;
    private Integer year;
    private String title;
    private String genre;
    private Set<ActorDTO> actors = new LinkedHashSet<>();

    public MovieDTO(Movie movie)
    {
        if(movie.getId() != null)
        {
            this.id = movie.getId();
        }
        this.year = movie.getYear();
        this.title = movie.getTitle();
        this.genre = movie.getGenre();

        if(!movie.getActors().isEmpty())
        {
            for(Actor a : movie.getActors())
            {
                actors.add(new ActorDTO(a));
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Set<ActorDTO> getActors() {
        return actors;
    }

    public void setActors(Set<ActorDTO> actors) {
        this.actors = actors;
    }
}
