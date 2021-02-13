package pl.orlowski.sebastian.forumspring.topic;

import javax.validation.constraints.Size;

public class NewTopic {

    @Size(min = 10, max = 30, message = "Title must have 10-30 letters!")
    private String title;

    @Size(min = 10, max = 10000, message = "Text must have 10 - 10000 letters!")
    private String text;

    public NewTopic(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public NewTopic() {
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
}
