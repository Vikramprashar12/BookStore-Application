/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import static finalproject.MainController.booklist;
import static finalproject.MainController.main;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Vikram Prashar
 */
public class CustomerViewController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    Text custview;

    @FXML
    private TableView<Book> custTable;

    @FXML
    private TableColumn<Book, String> bookname;

    @FXML
    private TableColumn<Book, Double> price;

    @FXML
    private TableColumn<Book, CheckBox> checkbox;

    ObservableList<Book> blist;
    
    private double totalcost;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        blist = FXCollections.observableArrayList(booklist);
        bookname.setCellValueFactory(new PropertyValueFactory("bookName"));
        price.setCellValueFactory(new PropertyValueFactory("bookPrice"));
        checkbox.setCellValueFactory(new PropertyValueFactory("cb"));
        custTable.setItems(blist);
    }

    public void changet(String s) {
        custview.setText(s);
    }

    @FXML
    public void scustcost(ActionEvent event, double cost, int points, String s) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerCost.fxml"));
        root = loader.load();
        CustomerCostController s2 = loader.getController();
        s2.changecustcost("Total Cost: " + cost + "\n Points: " + points + "\n Status: " + s);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void buy(ActionEvent e) throws Exception {
        totalcost = 0;
        int pointsEarned;
        ArrayList<Book> temp = new ArrayList();
        for (Book b : booklist) {
            if (b.getCb().isSelected()) {
                temp.add(b);
            }
        }
        for (int i = 0; i < temp.size(); i++) {
            totalcost += temp.get(i).getBookPrice();
        }

        pointsEarned = (int) totalcost * 10;
        main.setPoints((main.getPoints() + pointsEarned));

        for (int i = 0; i < temp.size(); i++) {
            booklist.remove(temp.get(i));
        }
        scustcost(e, totalcost, main.getPoints(), main.myStatusName());
    }

    @FXML
    public void buyr(ActionEvent e) throws Exception {
        //Resetting total cost
        totalcost = 0;
        int pointsEarned;
        int pointsToCAD;
        double originalCost;

        //For every book called selected, in sub array)
        ArrayList<Book> temp = new ArrayList();
        for (Book b : booklist) {
            if (b.getCb().isSelected()) {
                temp.add(b);
            }
        }
        for (int i = 0; i < temp.size(); i++) {
            totalcost += temp.get(i).getBookPrice();
        }

        originalCost = totalcost;

        //Finding final cost
        pointsToCAD = main.getPoints() / 100;

        if ((totalcost - pointsToCAD) < 0) {
            totalcost = 0;
        } else {
            totalcost = totalcost - pointsToCAD;
        }
        int deductPoints = (int) (originalCost - totalcost) * 100;
        main.setPoints((main.getPoints() - deductPoints));
        pointsEarned = (int) totalcost * 10;
        main.setPoints((main.getPoints() + pointsEarned));
        for (int i = 0; i < temp.size(); i++) {
            booklist.remove(temp.get(i));
        }
        scustcost(e, totalcost, main.getPoints(), main.myStatusName());
    }

}
