package dtos;

import entities.Actor;
import entities.Employee;
import entities.Movie;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ActorDTO
{
    private Long id;
    private String name;
    private Set<Movie> movies = new LinkedHashSet<>();

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
            this.movies = actor.getMovies().stream().map(movie -> new Movie(movie)).collect(Collectors.toSet());
        }
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

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public static List<ActorDTO> getDtos(List<Actor> actors){
        List<ActorDTO> actorsDTOS = new ArrayList<>();
        actors.forEach(actor->actorsDTOS.add(new ActorDTO(actor)));
        return actorsDTOS;
    }

    @Override
    public String toString() {
        return "ActorDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", movies=" + movies +
                '}';
    }

    class Movie {
        private long id;
        private Integer year;
        private String title;
        private String genre;


        public Movie(entities.Movie movie)
        {
            if(movie.getId() != null)
            {
            this.id = movie.getId();
            }
            this.year = movie.getYear();
            this.title = movie.getTitle();
            this.genre = movie.getGenre();
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
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

        @Override
        public String toString() {
            return "Movie{" +
                    "id=" + id +
                    ", year=" + year +
                    ", title='" + title + '\'' +
                    ", genre='" + genre + '\'' +
                    '}';
        }
    }

}
