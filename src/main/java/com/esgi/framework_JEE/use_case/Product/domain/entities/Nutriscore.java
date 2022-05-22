package com.esgi.framework_JEE.use_case.Product.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Nutriscore {
    private int finalScore;
    private int negativePointsSum;
    private int positivePointsSum;
    private String color;
    private String letter;

    public Nutriscore(int finalScore, int negativePointsSum, int positivePointsSum, String color, String letter) {
        this.finalScore = finalScore;
        this.negativePointsSum = negativePointsSum;
        this.positivePointsSum = positivePointsSum;
        this.color = color;
        this.letter = letter;
    }
}
