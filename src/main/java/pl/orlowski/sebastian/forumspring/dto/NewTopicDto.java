package pl.orlowski.sebastian.forumspring.dto;

public class NewTopicDto {
    private String title;
    private String text;

    public NewTopicDto(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public NewTopicDto() {
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
