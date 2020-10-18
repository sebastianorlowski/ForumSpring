package pl.orlowski.sebastian.forumspring.topic;

import pl.orlowski.sebastian.forumspring.user.User;

import java.util.Date;

public class Inscription {

    private Long id;
    private String text;
    private Date createdAt;
    private User user;
    private Topic topic;

    public Inscription(Long id, String text, Date createdAt, User user, Topic topic) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.user = user;
        this.topic = topic;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
}
