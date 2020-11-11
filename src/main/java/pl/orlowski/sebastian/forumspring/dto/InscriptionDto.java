package pl.orlowski.sebastian.forumspring.dto;

import pl.orlowski.sebastian.forumspring.topic.Topic;
import pl.orlowski.sebastian.forumspring.user.User;

import javax.persistence.*;
import java.util.Date;

public class InscriptionDto {

    private String text;
    private User user;
    private Topic topic;

    public InscriptionDto(String text, User user, Topic topic) {
        this.text = text;
        this.user = user;
        this.topic = topic;
    }

    public InscriptionDto() {

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
