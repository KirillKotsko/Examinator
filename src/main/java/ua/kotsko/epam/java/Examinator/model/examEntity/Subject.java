package ua.kotsko.epam.java.Examinator.model.examEntity;

public enum Subject {
	MATH("Math"),
    SCIENCE("Science"),
    NATURE("Nature"),
    HISTORY("History"),
    CULTURE("Culture");

    private String title;

    Subject(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
