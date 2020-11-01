package pl.orlowski.sebastian.forumspring.dto;

import pl.orlowski.sebastian.forumspring.topic.Topic;
import pl.orlowski.sebastian.forumspring.user.User;

import javax.persistence.*;
import java.util.Date;

public class InscriptionDto {

    private Long id;
    private String text;
    private User user;
    private Topic topic;
    private Date createdAt;

    public InscriptionDto(Long id, String text, User user, Topic topic, Date createdAt) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.topic = topic;
        this.createdAt = createdAt;
    }

    public InscriptionDto() {

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

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
