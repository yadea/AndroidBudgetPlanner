package com.yadea.budgetplanner.model;

import java.util.Date;

public class Cashbook {

    public int _id;
    public boolean Expense;
    public String Title;
    public String Category;
    public Date Date;
    public double Amount;


    public Cashbook(boolean expense, String title, String category, java.util.Date date,double amount) {
        Expense = expense;
        Amount = amount;
        Title = title;
        Category = category;
        Date = date;
    }

}
