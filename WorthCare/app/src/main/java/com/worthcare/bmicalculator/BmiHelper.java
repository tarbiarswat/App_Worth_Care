package com.worthcare.bmicalculator;

import java.text.DecimalFormat;

public class BmiHelper {

    private double format ( double value) {
        if ( value != 0){
            DecimalFormat df = new DecimalFormat("###.#");
            return Double.valueOf(df.format(value));
        } else {
            return -1;
        }
    }

    public double lbToKgConverter(double lb) {
        return format(lb * 0.45359237 );
    }

    public double kgToLbConverter(double kg) {
        return format(kg * 2.20462262);
    }

    public double cmToFeetConverter(double cm) {
        return format(cm * 0.032808399 );
    }

    public double feetToCmConverter(double feet) {
        return format(feet * 30.48 );
    }

    public double feetInchToCmConverter(double feet, double inch) {
        return format( (feet * 30.48)+(inch*2.54) );
    }

    public double getBMIKg (double height, double weight) {
        double meters = height/100;
        return format( weight / Math.pow(meters,2));
    }

    public double getBMILb (double height, double heightInch, double weight) {
        int inch = (int)((height *12)+heightInch);
        return format((weight*703) / Math.pow(inch, 2));
    }

    public String getBMIClassification (double bmi) {

        if (bmi <= 0) return "unknown";
        String classification;

        if (bmi < 18.5) {
            classification = "underweight";
        } else if (bmi < 25) {
            classification = "normal";
        } else if (bmi < 30) {
            classification = "overweight";
        } else {
            classification = "obese";
        }

        return classification;
    }

}
