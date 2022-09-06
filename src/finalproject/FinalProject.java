/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import static finalproject.MainController.booklist;
import static finalproject.MainController.customerlist;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Vikram Prashar
 */
public class FinalProject extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("BookStore Application");
        stage.show();
        loadData();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void end() throws IOException {
        File custs = new File("customers.txt");
        FileWriter fw = new FileWriter(custs);
        for (Customer c : customerlist) {
            if (c != null) {
                fw.write(c.username + "\n" + c.password + "\n" + c.getPoints() + "\n");
            }
        }
        fw.close();
        File books = new File("books.txt");
        FileWriter fw2 = new FileWriter(books);
        for (Book b : booklist) {
            fw2.write(b.getBookName() + "\n" + b.getBookPrice() + "\n");
        }
        fw2.close();
        Platform.exit();
    }

    public void loadData() {
        ArrayList<Book> loadedBooks = new ArrayList<Book>();
        ArrayList<Customer> loadedCustomers = new ArrayList<Customer>();

        try {
            BufferedReader bReader = new BufferedReader(new FileReader("books.txt"));
            String bTitle = "";
            double bPrice = 0;
            String line = null;
            int count = 0;
            while ((line = bReader.readLine()) != null) {
                if ((count % 2) == 0) {
                    bTitle = line;
                    count++;
                } else {
                    bPrice = Double.parseDouble(line);
                    loadedBooks.add(new Book(bTitle, bPrice));
                    count++;
                }
            }
            bReader.close();
        } catch (FileNotFoundException e) {
            // Do nothing
        } catch (IOException e) {
            throw new RuntimeException("Failed to load data somehow!");
        }
        try {
            BufferedReader cReader = new BufferedReader(new FileReader("customers.txt"));
            String cUser = "";
            String cPass = "";
            int cPoints = 0;
            String line = null;
            int count = 0;
            while ((line = cReader.readLine()) != null) { // Read alternating lines to get customer data
                if ((count % 3) == 0) {
                    cUser = line;
                    count++;
                } else if ((count % 3) == 1) {
                    cPass = line;
                    count++;
                } else {
                    cPoints = Integer.parseInt(line);
                    loadedCustomers.add(new Customer(cUser, cPass, cPoints));
                    count++;
                }
            }
            cReader.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

            throw new RuntimeException("Failed to load data somehow!");
        }
        booklist = loadedBooks;
        customerlist = loadedCustomers;
    }
}
