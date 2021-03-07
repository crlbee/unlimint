package com.unlimint.testTask.orders;

import com.opencsv.bean.CsvBindByPosition;

import java.util.Objects;

public class InputOrder {
    @CsvBindByPosition(position = 0)
    int orderId;

    @CsvBindByPosition(position = 1)
    double amount;

    @CsvBindByPosition(position = 2)
    String currency;

    @CsvBindByPosition(position = 3)
    String comment;

    public InputOrder(){
    }

    public InputOrder(int orderId, double amount, String currency, String comment) {
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputOrder that = (InputOrder) o;
        return orderId == that.orderId && Double.compare(that.amount, amount) == 0 && Objects.equals(currency, that.currency) && Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, amount, currency, comment);
    }
}
