package gr.aueb.cf.movies.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Movie {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "TITLE", length = 100, nullable = false)
    private String title;

    @Column(name = "YEAR", length = 50, nullable = false)
    private String year;

    @ManyToMany
    @JoinTable(name = "MOVIES_USERS",
    joinColumns = @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"))
    private List<User> users = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movies = (Movie) o;

        if (!id.equals(movies.id)) return false;
        if (!title.equals(movies.title)) return false;
        return year.equals(movies.year);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + year.hashCode();
        return result;
    }

    protected List<User> getUsers() {
        return users;
    }

    protected void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getAllUsers() {
        return Collections.unmodifiableList(getUsers());
    }

    public void addUser(User user) {
        this.users.add(user);
        for (Movie movie : user.getMovies()) {
            if (movie == this) {
                return;
            }
        }
        user.addFavoriteMovie(this);
    }

    public void deleteUser(User user) {
        boolean found = false;
        this.users.remove(user);

        for (Movie movie : user.getMovies()) {
            if (movie == this) {
                found = true;
                break;
            }
        }
        if (found) user.deleteMovie(this);
    }
}
