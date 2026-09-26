interface Question {
    void displayQuestion();
    boolean checkAnswer(String answer);
}

class MultipleChoiceQuestion implements Question {
    private String question;
    private String answer;
    private int marks;

    public MultipleChoiceQuestion(String question, String answer, int marks) {
        this.question = question;
        this.answer = answer;
        this.marks = marks;
    }

    public void displayQuestion() {
        System.out.println(question);
    }

    public boolean checkAnswer(String answer) {
        return this.answer.equalsIgnoreCase(answer);
    }

    public int getMarks() {
        return marks;
    }
}

class TrueFalseQuestion implements Question {
    private String question;
    private String answer;
    private int marks;

    public TrueFalseQuestion(String question, String answer, int marks) {
        this.question = question;
        this.answer = answer;
        this.marks = marks;
    }

    public void displayQuestion() {
        System.out.println(question);
    }

    public boolean checkAnswer(String answer) {
        return this.answer.equalsIgnoreCase(answer);
    }

    public int getMarks() {
        return marks;
    }
}

class ShortAnswerQuestion implements Question {
    private String question;
    private String answer;
    private int marks;

    public ShortAnswerQuestion(String question, String answer, int marks) {
        this.question = question;
        this.answer = answer;
        this.marks = marks;
    }

    public void displayQuestion() {
        System.out.println(question);
    }

    public boolean checkAnswer(String answer) {
        return this.answer.equalsIgnoreCase(answer);
    }

    public int getMarks() {
        return marks;
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Examination {
    private String name;
    private java.util.ArrayList<Question> questions;

    public Examination(String name) {
        this.name = name;
        questions = new java.util.ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public String getName() {
        return name;
    }

    public java.util.ArrayList<Question> getQuestions() {
        return questions;
    }
}

class Attempt {
    private Student student;
    private Examination examination;
    private java.util.ArrayList<String> answers;
    private boolean submitted;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
        answers = new java.util.ArrayList<>();
        submitted = false;
    }

    public void start() {
        System.out.println(examination.getName() + " started by "
                + student.getName() + ".");
    }

    public void answerQuestion(String answer) {
        if (submitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }

        answers.add(answer);
        System.out.println("Answer recorded.");
    }

    public void submit() {
        submitted = true;
        System.out.println("Examination submitted.");
    }

    public void displayResult() {
        int score = 0;

        for (int i = 0; i < answers.size()
                && i < examination.getQuestions().size(); i++) {

            Question question = examination.getQuestions().get(i);

            if (question.checkAnswer(answers.get(i))) {
                if (question instanceof MultipleChoiceQuestion) {
                    score += ((MultipleChoiceQuestion) question).getMarks();
                } else if (question instanceof TrueFalseQuestion) {
                    score += ((TrueFalseQuestion) question).getMarks();
                } else if (question instanceof ShortAnswerQuestion) {
                    score += ((ShortAnswerQuestion) question).getMarks();
                }
            }
        }

        System.out.println("Student: " + student.getName());
        System.out.println("Score: " + score);
    }
}

public class P3_OnlineExamination {
    public static void main(String[] args) {
        Student student = new Student("Student 1");

        Examination exam = new Examination("Exam A");

        exam.addQuestion(new MultipleChoiceQuestion(
                "What is 2 + 2?", "4", 2));

        exam.addQuestion(new TrueFalseQuestion(
                "Java is an object-oriented language.", "true", 2));

        exam.addQuestion(new ShortAnswerQuestion(
                "What is the capital of India?", "Delhi", 2));

        Attempt attempt = new Attempt(student, exam);

        attempt.start();

        attempt.answerQuestion("4");
        attempt.answerQuestion("true");
        attempt.answerQuestion("Delhi");

        attempt.submit();

        attempt.displayResult();

        attempt.answerQuestion("Mumbai");
    }
}