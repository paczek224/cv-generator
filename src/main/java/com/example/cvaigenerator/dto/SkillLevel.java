package com.example.cvaigenerator.dto;

public enum SkillLevel {
    BEGINNER(1),
    INTERMEDIATE(2),
    ADVANCED(3),
    EXPERT(4),
    MASTER(5);

    private final int value;

    SkillLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
