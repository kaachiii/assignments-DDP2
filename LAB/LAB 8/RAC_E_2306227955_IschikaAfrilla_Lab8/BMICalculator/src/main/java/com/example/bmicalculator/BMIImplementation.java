package com.example.bmicalculator;

public class BMIImplementation {
    // inisiasi atribut yang dibutuhkan untuk BMICalculator
    private final String gender;
    private final double weight;
    private final double height;

    /*
     * Method ini digunakan sebagai constructor BMIImplementation
     */
    public BMIImplementation(String gender, double weight, double height){
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    /*
     * Method ini digunakan untuk get ideal berat badan
     * @return double berat badan
     */
    public double getIdealBB() {
        // cek gender dari user
        if (this.gender.equals("Laki-Laki")) {
            return (this.height - 100)*(1-0.1);
        }
        return (this.height - 100)*(1-0.15);
    }

    /*
     * Method ini digunakan untuk menghitung BMI
     * @return double BMI
     */
    public double calculateBMI() {
        return this.weight/(this.height*this.height)*10000;
    }

    /*
     * Method ini digunakan untuk mengklasifikasikan BMI
     * @return String klasifikasi BMI
     */
    public String getClassification() {
        double bmi = calculateBMI();
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi <= 24.9) {
            return "Normal";
        } else if (bmi <= 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}