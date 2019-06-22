package com.easylang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Question {
    private Dictionary data;
    private List<String> cases = new ArrayList<>();

    public Dictionary getData() {
        return data;
    }

    public void setData(Dictionary data) {
        this.data = data;
    }

    public List<String> getCases() {
        return cases;
    }

    public void setCases(List<String> cases) {
        this.cases = cases;
    }

    public void addCase(String c) {
        cases.add(c);
    }

    public void shuffle() {
        Collections.shuffle(cases);
    }
}
