package dtos;

import entities.Actor;
import entities.Movie;

import java.util.LinkedHashSet;
import java.util.Set;

public class MovieDTO
{
    private Long id;
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
                ActorDTO newActor = new ActorDTO(a);
                if(actors.contains(newActor))
                {
                    continue;
                }
                actors.add(newActor);
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public static Set<MovieDTO> getDtos(Set<Movie> movies)
    {
        Set<MovieDTO> movieDTOS = new LinkedHashSet<>();
        movies.forEach(movie->movieDTOS.add(new MovieDTO(movie)));
        return movieDTOS;
    }

    @Override
    public String toString() {

        String actorsInMovie = "";
                for(ActorDTO a : actors)
                {
                    actorsInMovie += a.getName()+'\n';
                }

        return "MovieDTO{" +
                "id=" + id +
                ", year=" + year +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                actorsInMovie + '\'' +
                '}';
    }

    public void addActor(ActorDTO actorDTO)
    {
        actors.add(actorDTO);
    }
}
