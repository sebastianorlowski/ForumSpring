package pl.orlowski.sebastian.forumspring.user;

public enum Role {
    USER ("user"),
    ADMIN ("admin");

    private String name;

    Role(String name) {
        this.name = name;
    }
}
