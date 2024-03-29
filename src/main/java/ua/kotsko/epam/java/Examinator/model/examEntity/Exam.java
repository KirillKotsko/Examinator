package ua.kotsko.epam.java.Examinator.model.examEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @Column(name = "exam_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "* name is empty!")
    @Column(name = "exam_name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    @Column(name = "exam_difficulty")
    @Enumerated(EnumType.STRING)
    private Difficulty level;

    @Column(name = "exam_subject")
    @Enumerated(EnumType.STRING)
    private Subject subject;

    @Column(name = "exam_shuffle")
    private boolean shuffle;

    @Column(name = "exam_duration")
    private int durationMinute;

    public Exam() {
        questions = new ArrayList<>();
    }

    public Exam(String name, Difficulty level, Subject subject, boolean isShuffle, int durationMinute) {
        this.name = name;
        this.level = level;
        this.subject = subject;
        this.shuffle = isShuffle;
        this.durationMinute = durationMinute;
        questions = new ArrayList<>();
    }
    
    public void updateEntityOnQuestions() {
    	questions.stream().forEach(question -> { question.setExam(this); 
    											 question.updateEntityOnAnswers();
    											 } );
    }

    public void addQuestion(Question question) {
        question.setExam(this);
        questions.add(question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Difficulty getLevel() {
        return level;
    }

    public void setLevel(Difficulty level) {
        this.level = level;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public boolean isShuffle() {
        return shuffle;
    }

    public void setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
    }

    public int getDurationMinute() {
        return durationMinute;
    }

    public void setDurationMinute(int durationMinute) {
        this.durationMinute = durationMinute;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", questions=" + questions +
                ", level=" + level +
                ", subject=" + subject +
                ", shuffle=" + shuffle +
                ", durationMinute=" + durationMinute +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return id == exam.id && shuffle == exam.shuffle && durationMinute == exam.durationMinute && Objects.equals(name, exam.name) && Objects.equals(questions, exam.questions) && level == exam.level && subject == exam.subject;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, questions, level, subject, shuffle, durationMinute);
    }

	public void addQuestionAndAnswerPair() {
		Question questionAdd = new Question();
		questionAdd.addAnswer(new Answer());
		this.addQuestion(questionAdd);	
	}
}
