package pl.orlowski.sebastian.forumspring.topic;

public class NewTopic {
    private String title;
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
