/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import static finalproject.MainController.customerlist;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Vikram Prashar
 */
public class OwnerCustomerController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    ObservableList<Customer> clist;
    @FXML
    private TableView<Customer> tableView;

    @FXML
    private TableColumn<Customer, String> t_username;

    @FXML
    private TableColumn<Customer, String> t_password;

    @FXML
    private TableColumn<Customer, Integer> t_points;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_password;
    @FXML
    private Text error;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clist = FXCollections.observableArrayList(customerlist);
        t_username.setCellValueFactory(new PropertyValueFactory("username"));
        t_password.setCellValueFactory(new PropertyValueFactory("password"));
        t_points.setCellValueFactory(new PropertyValueFactory("points"));
        tableView.setItems(clist);
    }

    public void sownerstart(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("OwnerStart.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void add(ActionEvent e) throws IOException {
        boolean check = false;
        for (Customer c : customerlist) {
            if ((tf_username.getText().equals(c.getUsername())) && (tf_password.getText().equals(c.getPassword()))) {
                check = true;
            }
        }
        if (!check) {
            error.setText("");
            customerlist.add(new Customer(tf_username.getText(), tf_password.getText(), 0));
            clist = FXCollections.observableArrayList(customerlist);
            tableView.setItems(clist);
        }
        else{
            error.setText("Customer already exists");
        }

    }

    @FXML
    public void delete(ActionEvent e) throws IOException {
        customerlist.remove(tableView.getSelectionModel().getSelectedItem());
        clist = FXCollections.observableArrayList(customerlist);
        tableView.setItems(clist);
    }

}
