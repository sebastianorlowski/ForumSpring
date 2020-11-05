package pl.orlowski.sebastian.forumspring.inscription;

import pl.orlowski.sebastian.forumspring.topic.Topic;
import pl.orlowski.sebastian.forumspring.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inscription")
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

//    Many inscriptions to one user
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private User user;

    // Many inscriptions to one topic
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Topic topic;

    private Date createdAt;

    public Inscription(Long id, String text, User user, Topic topic, Date createdAt) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.topic = topic;
        this.createdAt = createdAt;
    }

    public Inscription() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = new Date();
    }
}
