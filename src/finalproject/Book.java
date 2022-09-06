/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import javafx.scene.control.CheckBox;

/**
 *
 * @author Vikram Prashar
 */
public class Book {

    private String bookName;
    private double bookPrice;
    private CheckBox cb;

    public Book(String name, double price) {
        this.bookName = name;
        this.bookPrice = price;
        this.cb = new CheckBox();
    }

    public String getBookName() {
        return bookName;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public CheckBox getCb() {
        return cb;
    }
}
