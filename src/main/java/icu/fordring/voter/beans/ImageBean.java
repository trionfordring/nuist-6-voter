package icu.fordring.voter.beans;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity
public class ImageBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String path;
    @Column
    private String name;
    @Column
    private String type;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "uid")
    private User author;
    @ManyToMany(mappedBy = "images")
    private Set<User> users = new HashSet<>();

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
