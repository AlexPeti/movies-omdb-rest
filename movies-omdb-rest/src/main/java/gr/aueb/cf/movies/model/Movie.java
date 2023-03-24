package gr.aueb.cf.movies.model;

import jakarta.persistence.*;

@Embeddable
public class Movie {

    @Column(name = "TITLE", length = 256, nullable = false)
    private String title;

    public Movie() {}

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return title.equals(movie.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
