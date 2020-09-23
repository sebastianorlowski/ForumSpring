package pl.orlowski.sebastian.forumspring.user;

public enum Gender {
    MALE("M"),
    FEMALE("F"),
    NONE("N");

    private String gender;

    Gender (String gender) {
        this.gender = gender;
    }


}
