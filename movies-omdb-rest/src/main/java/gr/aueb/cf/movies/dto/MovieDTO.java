package gr.aueb.cf.movies.dto;

import java.util.ArrayList;
import java.util.List;

public class MovieDTO {

    private Long id;
    private String title;
    private String genre;
    private List<UserDTO> users = new ArrayList<>();

    public MovieDTO() {}

    public MovieDTO(Long id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}

