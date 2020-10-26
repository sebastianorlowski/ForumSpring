package pl.orlowski.sebastian.forumspring.dto;

public class InscriptionDto {
    String text;

    public InscriptionDto(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
