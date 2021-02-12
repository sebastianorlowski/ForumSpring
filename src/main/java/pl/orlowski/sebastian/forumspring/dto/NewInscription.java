package pl.orlowski.sebastian.forumspring.dto;

import pl.orlowski.sebastian.forumspring.topic.Topic;
import pl.orlowski.sebastian.forumspring.user.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class NewInscription {

    private Long id;

    @Size(min = 10, max = 10000, message = "Text must have 10 - 10000 letters!")
    private String text;
    private User user;
    private Topic topic;

    public NewInscription(Long id, String text, User user, Topic topic) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.topic = topic;
    }

    public NewInscription() {
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
}
