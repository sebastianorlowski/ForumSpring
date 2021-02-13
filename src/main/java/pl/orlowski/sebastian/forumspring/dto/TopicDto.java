package pl.orlowski.sebastian.forumspring.dto;

import pl.orlowski.sebastian.forumspring.user.User;

import javax.validation.constraints.Size;

public class TopicDto {

    private Long id;
    private String title;

    @Size(min = 10, max = 10000, message = "Text must have 10 - 10000 letters!")
    private String text;
    private User user;

    public TopicDto(Long id, String title, String text, User user) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.user = user;
    }

    public TopicDto() {
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
}
