package com.example.bmicalculator;

// import semua modul yang dibutuhkan
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class BMICalculator extends Application {
    @Override
    public void start(Stage primaryStage) {
        // ------------- TAMPILAN -------------
        // instansiasi gridpane untuk mengatur tata letak elemen BMI Calculator
        GridPane root = new GridPane();
        root.setHgap(70); // set horizontal gap antarelemen
        root.setVgap(20); // set vertikal gap antarelemen
        root.setPadding(new Insets(10, 10, 10, 10)); // set padding gridpane

        // ------------- ELEMEN -------------
        // set semua elemen yang ada di dalam BMICalculator
        Label welcome = new Label("Welcome to BMI Calculator");
        welcome.setFont(Font.font("Constantia", FontWeight.BOLD,20));
        Label genderLabel = new Label("Gender");
        String[] genderList = {"Laki-Laki", "Perempuan"};
        ChoiceBox<String> genderChoice = new ChoiceBox<String>(FXCollections.observableArrayList(genderList));
        Label weightLabel = new Label("Berat Badan (kg)");
        TextField weightField = new TextField();
        Label heightLabel = new Label("Tinggi Badan (cm)");
        TextField heightField = new TextField();
        Label hasilLabel = new Label("--------------- Hasil ---------------");
        hasilLabel.setFont(Font.font("Constantia", FontWeight.BOLD, 20));
        Label idealBBLabel = new Label("Berat Badan Ideal");
        Label idealBBField = new Label(); // label untuk menampung hasil berat badan ideal
        Label BMILabel = new Label("Index Massa Tubuh (BMI)");
        Label BMIField = new Label(); // label untuk menampung hasil BMI
        Label klasifikasiLabel = new Label("Klasifikasi");
        Label klasifikasiField = new Label(); // label untuk menampung hasil klasifikasi
        Button computeButton = new Button("Compute!");
        computeButton.setFont(Font.font("Constantia", FontWeight.BOLD, 15));
        Button exitButton = new Button("Exit");
        exitButton.setFont(Font.font("Constantia", FontWeight.BOLD, 15));

        // ------------- ATUR TATA LETAK -------------
        // atur tata letak semua elemen di dalam gridpane sesuai keinginan
        root.add(welcome, 0, 0, 2, 1);
        root.add(genderLabel, 0, 1);
        root.add(genderChoice, 1, 1);
        root.add(weightLabel, 0, 2);
        root.add(weightField, 1, 2);
        root.add(heightLabel, 0, 3);
        root.add(heightField, 1, 3);
        root.add(hasilLabel, 0, 4, 2, 1);
        root.add(idealBBLabel, 0, 5);
        root.add(idealBBField, 1, 5);
        root.add(BMILabel, 0, 6, 2, 1);
        root.add(BMIField, 1, 6);
        root.add(klasifikasiLabel, 0, 7);
        root.add(klasifikasiField, 1, 7);
        root.add(computeButton, 1, 8);
        root.add(exitButton, 1, 9);

        // ------------- SET HANDLER EVENT -------------
        computeButton.setOnAction(event -> { // handler event computeButton
            try {
                double weight = Double.parseDouble(weightField.getText());
                double height = Double.parseDouble(heightField.getText());
                String gender = genderChoice.getValue();
                // instansiasi BMIImplementation sesuai dengan input user
                BMIImplementation bmi = new BMIImplementation(gender, weight, height);
                // set text idealBBField dengan bmi.getIdealBB() pada class BMIImplementation
                idealBBField.setText(String.format("%.2f", bmi.getIdealBB()));
                // set text BMIField dengan bmi.calculateBMI() pada class BMIImplementation
                BMIField.setText(String.format("%.2f", bmi.calculateBMI()));
                // set text klasifikasiField dengan bmi.getClassification() pada class BMIImplementation
                klasifikasiField.setText(bmi.getClassification());
                // cek klasifikasi BMI dengan warna yang sesuai
                if (bmi.getClassification().equals("Underweight")) {
                    klasifikasiField.setTextFill(Color.BLUE);
                } else if (bmi.getClassification().equals("Normal")) {
                    klasifikasiField.setTextFill(Color.GREEN);
                } else if (bmi.getClassification().equals("Overweight")) {
                    klasifikasiField.setTextFill(Color.YELLOW);
                } else {
                    klasifikasiField.setTextFill(Color.RED);
                }
            }
            catch (Exception e) {
                // catch exception saat terjadi error
                klasifikasiField.setTextFill(Color.BLACK);
                idealBBField.setText("Invalid input!");
                BMIField.setText("Invalid input!");
                klasifikasiField.setText("Invalid input!");
            }
        });

        exitButton.setOnAction(event -> System.exit(0)); // handler event exit button

        // ------------- SET PRIMARY STAGE -------------
        primaryStage.setScene(new Scene(root, 300, 250)); // set primary stage
        BackgroundFill backgroundFill = new BackgroundFill(Color.BEIGE, null, null); // set backgoundfill root
        Background background = new Background(backgroundFill); // set background
        root.setBackground(background); // Set background root GridPane
        primaryStage.setTitle("BMI Calculator"); // set title primaryStage
        primaryStage.setHeight(500); // set height primaryStage
        primaryStage.setWidth(350); // set width primaryStage
        primaryStage.setResizable(false); // set primaryStage tidak resizable
        primaryStage.show(); // show primary stage
    }
}