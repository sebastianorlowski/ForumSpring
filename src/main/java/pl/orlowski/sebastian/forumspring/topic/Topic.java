package pl.orlowski.sebastian.forumspring.topic;

import java.util.Date;

public class Topic {

    private Long id;
    private String title;
    private String text;
    private Date createdAt;
    private Set<Inscription> inscription;

    public Topic(Long id, String title, String text, Date createdAt, Set<Inscription> inscription) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.createdAt = createdAt;
        this.inscription = inscription;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Inscription> getInscription() {
        return inscription;
    }

    public void setInscription(Set<Inscription> inscription) {
        this.inscription = inscription;
    }
}
