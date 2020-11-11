package pl.orlowski.sebastian.forumspring.dto;

import pl.orlowski.sebastian.forumspring.user.User;

public class TopicDto {

    private String title;
    private String text;
    private User user;

    public TopicDto(String title, String text, User user) {
        this.title = title;
        this.text = text;
        this.user = user;
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
