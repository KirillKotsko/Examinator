package ua.kotsko.epam.java.Examinator.model.examEntity;

public enum Difficulty {
	LOW("Low"),MEDIUM("Medium"),HIGH("High");

    private String level;

    Difficulty(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}
