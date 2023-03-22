package gr.aueb.cf.movies.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USERNAME", length = 50, nullable = false)
    private String username;

    @Column(name = "PASSWORD", length = 50, nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name = "USERS_MOVIES",
    joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "MOVIE_ID",referencedColumnName = "ID"))
    private List<Movie> movies = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    protected List<Movie> getMovies() {
        return movies;
    }

    protected void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getAllMovies() {
        return Collections.unmodifiableList(getMovies());
    }

    public void addFavoriteMovie(Movie movie) {
        this.movies.add(movie);
        for (User user : movie.getUsers()) {
            if (user == this) {
                return;
            }
        }
        movie.addUser(this);
    }

    public void deleteMovie(Movie movie) {
        boolean found = false;
        this.movies.remove(movie);

        for (User user : movie.getUsers()) {
            if (user == this) {
                found = true;
                break;
            }
        }
        if (found) movie.deleteUser(this);
    }
}
