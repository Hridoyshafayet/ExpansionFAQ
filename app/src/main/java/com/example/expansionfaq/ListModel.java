package com.example.expansionfaq;

import android.graphics.drawable.Drawable;

public class ListModel {

    public String questions;
    public String answers;
    public boolean expanded = false;
    public boolean parent = false;

    // flag when item swiped
    public boolean swiped = false;

    public ListModel() {
    }

    public ListModel(String questions, String answers) {
        this.questions = questions;
        this.answers = answers;
    }
}
