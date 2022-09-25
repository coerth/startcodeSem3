package entities;

import dtos.MovieDTO;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @Column(name = "genre", nullable = false, length = 45)
    private String genre;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "movie_has_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Actor> actors = new LinkedHashSet<>();

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

    public Movie() {
    }

    public Movie(Integer year, String title, String genre) {
        this.year = year;
        this.title = title;
        this.genre = genre;
    }

    public Movie(MovieDTO movieDTO)
    {
        this.id = movieDTO.getId();
        this.year = movieDTO.getYear();
        this.title = movieDTO.getTitle();
        this.genre = movieDTO.getGenre();

       movieDTO.getActors().forEach(actorDTO -> actors.add(new Actor(actorDTO)));


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return getId().equals(movie.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
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

    public void addActor(Actor actor)
    {
        actors.add(actor);
     /*   if(!actor.getMovies().contains(this))
        {
            actor.addMovie(this);
        }*/
    }

    public static Set<Movie> getEntities(Set<MovieDTO> movieDTOS)
    {
        Set<Movie> movies = new LinkedHashSet<>();
        //movieDTOS.forEach(movieDTO->movies.add(new Movie(movieDTO)));

        for(MovieDTO mdto : movieDTOS)
        {
            movies.add(new Movie(mdto));
        }

        return movies;
    }
}