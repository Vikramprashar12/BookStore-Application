/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Vikram Prashar
 */
public class MainController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public static ArrayList<Book> booklist = new ArrayList<Book>();
    public static ArrayList<Customer> customerlist = new ArrayList<Customer>();
    
    
    public static Customer main;
    
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Text wrong;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }

    public void sownerstart(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("OwnerStart.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void scustview(ActionEvent e, String name, int points, String s) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerView.fxml"));
        root = loader.load();
        CustomerViewController s2 = loader.getController();
        s2.changet("Welcome " + name + "! You have " + points + " points. Your status is " + s);
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void login(ActionEvent e) throws IOException {

        if ((username.getText().equals("admin")) && (password.getText().equals("admin"))) {
            sownerstart(e);
        } else {
            for (Customer c : customerlist) {
                if ((username.getText().equals(c.username)) && (password.getText().equals(c.password))) {
                    main = c;
                    scustview(e, c.username, c.getPoints(), c.myStatusName());
                }
            }
            wrong.setText("Wrong username or password");

        }

    }

}
