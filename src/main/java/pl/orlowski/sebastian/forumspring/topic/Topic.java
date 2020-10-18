package pl.orlowski.sebastian.forumspring.topic;

import pl.orlowski.sebastian.forumspring.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;

//    Many topics to one user
    @ManyToOne
    @JoinColumn
    private User user;
    private Date createdAt;

    public Topic(Long id, String title, String text, User user, Date createdAt) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.user = user;
        this.createdAt = createdAt;
    }

    public Topic() {

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createAt) {
        this.createdAt = createAt;
    }
}
