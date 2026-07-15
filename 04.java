import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class QuizQuestion {
    String questionText;
    String[] options;
    int correctAnswer;

    public QuizQuestion(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class QuizApplication {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<QuizQuestion> quiz = new ArrayList<>();

        quiz.add(new QuizQuestion(
            "Which data type is used to create a variable that should store text in Java?",
            new String[]{"1. string", "2. String", "3. txt", "4. text"},
            2
        ));
        quiz.add(new QuizQuestion(
            "What is the correct way to create an object called myObj of MyClass?",
            new String[]{"1. class MyClass = new myObj();", "2. MyClass myObj = new MyClass();", "3. myObj = new MyClass();", "4. new MyClass myObj = MyClass();"},
            2
        ));
        quiz.add(new QuizQuestion(
            "Which method can be used to find the length of a string in Java?",
            new String[]{"1. length()", "2. getSize()", "3. len()", "4. getLength()"},
            1
        ));

        int score = 0;
        List<Boolean> summary = new ArrayList<>();

        System.out.println("===================================");
        System.out.println("     WELCOME TO THE JAVA QUIZ      ");
        System.out.println("===================================");
        System.out.println("You have 10 seconds for each question.\n");

        for (int i = 0; i < quiz.size(); i++) {
            QuizQuestion currentQuestion = quiz.get(i);
            System.out.println("Question " + (i + 1) + ": " + currentQuestion.questionText);
            for (String option : currentQuestion.options) {
                System.out.println(option);
            }
            System.out.print("Your answer (1-4): ");

            long startTime = System.currentTimeMillis();
            String input = "";
            boolean timeUp = true;

            while ((System.currentTimeMillis() - startTime) < 10000) {
                if (reader.ready()) {
                    input = reader.readLine();
                    timeUp = false;
                    break;
                }
                Thread.sleep(100);
            }

            if (timeUp) {
                System.out.println("\nTime's up!");
                summary.add(false);
            } else {
                try {
                    int chosenOption = Integer.parseInt(input.trim());
                    if (chosenOption == currentQuestion.correctAnswer) {
                        System.out.println("Correct!\n");
                        score++;
                        summary.add(true);
                    } else {
                        System.out.println("Incorrect. The correct answer was option " + currentQuestion.correctAnswer + ".\n");
                        summary.add(false);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input format. Counted as incorrect.\n");
                    summary.add(false);
                }
            }
        }

        System.out.println("===================================");
        System.out.println("           QUIZ RESULTS            ");
        System.out.println("===================================");
        System.out.println("Final Score: " + score + " / " + quiz.size());
        System.out.println("\n--- Question Summary ---");
        for (int i = 0; i < summary.size(); i++) {
            String status = summary.get(i) ? "Correct" : "Incorrect / Timed out";
            System.out.println("Question " + (i + 1) + ": " + status);
        }
        System.out.println("===================================");
    }
}
