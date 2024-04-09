package com.example.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;

public class LesothoTriviaGame extends Application {

    private final String[] questions = {
            "What is the capital city of Lesotho?",
            "What is the highest mountain in Lesotho?",
            "What is the highest waterfall in Lesotho?"
    };

    private final String[][] options = {
            {"Maseru", "Gaborone", "WindHoek", "Mohales'Hoek"},
            {"Kilimanjaro", "Mount Everest", "Thabana Ntlenyane", "Thaba-Bosiu"},
            {"Maletsunyane Falls", "Victoria Falls", "Tugela Falls", "Kalambo Falls"}
    };

    private static final int[] correctAnswers = {0, 2, 0};

    private int currentQuestionIndex = 0;
    private int score = 0;

    private VBox layout;
    private Label questionLabel;
    private Button[] optionButtons;
    private Label[] answerLabels;
    private Label resultLabel;

    @Override
    public void start(Stage primaryStage) {
        layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.BOTTOM_CENTER);
        layout.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));

        questionLabel = new Label(questions[currentQuestionIndex]);
        questionLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333;");
        layout.getChildren().add(questionLabel);

        optionButtons = new Button[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new Button(options[currentQuestionIndex][i]);
            int finalI = i;
            optionButtons[i].setOnAction(event -> handleAnswer(finalI));
            optionButtons[i].setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-size: 20px;");
        }
        VBox.setMargin(optionButtons[0], new Insets(0, 0, 10, 0));
        VBox.setMargin(optionButtons[1], new Insets(0, 0, 10, 0));
        VBox.setMargin(optionButtons[2], new Insets(0, 0, 10, 0));
        VBox.setMargin(optionButtons[3], new Insets(0, 0, 10, 0));
        layout.getChildren().addAll(optionButtons);

        answerLabels = new Label[4];
        for (int i = 0; i < 4; i++) {
            answerLabels[i] = new Label();
            layout.getChildren().add(answerLabels[i]);
        }

        resultLabel = new Label();
        layout.getChildren().add(resultLabel);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lesotho Trivia Game");
        primaryStage.show();
    }

    private void handleAnswer(int selectedOption) {
        if (selectedOption == correctAnswers[currentQuestionIndex]) {
            displayCorrectMedia();
            score++;
        } else {
            answerLabels[selectedOption].setText("Your answer: " + options[currentQuestionIndex][selectedOption]);
        }

        answerLabels[correctAnswers[currentQuestionIndex]].setText("Correct answer: " +
                options[currentQuestionIndex][correctAnswers[currentQuestionIndex]]);

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            updateQuestion();
        } else {
            showResults();
        }
    }

    private void updateQuestion() {
        questionLabel.setText(questions[currentQuestionIndex]);
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[currentQuestionIndex][i]);
            answerLabels[i].setText(""); // Clear previous answer labels
        }
        resultLabel.setText(""); // Clear result label
    }

    private void showResults() {
        layout.getChildren().removeAll(optionButtons); // Remove option buttons
        resultLabel.setText("Game Over! Your final score is: " + score + "/" + questions.length);
    }

    private void displayCorrectMedia() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("lesotho.jpg")));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        layout.getChildren().add(imageView);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
