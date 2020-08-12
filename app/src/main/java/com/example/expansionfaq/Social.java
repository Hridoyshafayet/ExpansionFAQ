package com.example.expansionfaq;

import android.graphics.drawable.Drawable;

public class Social {

    public String questions;
    public String answers;
    public boolean expanded = false;
    public boolean parent = false;

    // flag when item swiped
    public boolean swiped = false;

    public Social() {
    }

    public Social(String questions, String answers) {
        this.questions = questions;
        this.answers = answers;
    }
}
