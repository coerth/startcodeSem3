package dtos;

import entities.Actor;
import entities.Movie;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieDTO
{
    private Long id;
    private Integer year;
    private String title;
    private String genre;
    private Set<Actor> actors = new LinkedHashSet<>();

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
        this.actors = movie.getActors().stream().map(actor -> new Actor(actor)).collect(Collectors.toSet());
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

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public static Set<MovieDTO> getDtos(Set<Movie> movies)
    {
        Set<MovieDTO> movieDTOS = new LinkedHashSet<>();
        movies.forEach(movie->movieDTOS.add(new MovieDTO(movie)));
        return movieDTOS;
    }

    class Actor{
        private long id;
        private String name;

        public Actor (entities.Actor actor) {
            if(actor.getId() != null)
            {
            this.id = actor.getId();
            }
            this.name = actor.getName();
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Actor{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "id=" + id +
                ", year=" + year +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", actors=" + actors +
                '}';
    }
}
