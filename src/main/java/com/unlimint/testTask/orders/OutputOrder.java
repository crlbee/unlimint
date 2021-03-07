package com.unlimint.testTask.orders;


import java.util.Objects;

public class OutputOrder {
    double amount;
    String comment;
    String filename;
    int line;
    String result;

    public OutputOrder(String filename, int line, String result) {
        this.filename = filename;
        this.line = line;
        this.result = result;
    }

    public OutputOrder(double amount, String comment, String filename, int line, String result) {
        this.amount = amount;
        this.comment = comment;
        this.filename = filename;
        this.line = line;
        this.result = result;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutputOrder that = (OutputOrder) o;
        return Double.compare(that.amount, amount) == 0 && line == that.line && Objects.equals(comment, that.comment) && Objects.equals(filename, that.filename) && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, comment, filename, line, result);
    }
}
