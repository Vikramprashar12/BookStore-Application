/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;


import static finalproject.MainController.booklist;
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
public class OwnerBookController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    ObservableList<Book> blist;

    @FXML
    private TableView<Book> o_book;

    @FXML
    private TableColumn<Book, String> o_bookname;

    @FXML
    private TableColumn<Book, Double> o_bookprice;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_price;
    @FXML
    private Text custcost;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        blist = FXCollections.observableArrayList(booklist);
        o_bookname.setCellValueFactory(new PropertyValueFactory("bookName"));
        o_bookprice.setCellValueFactory(new PropertyValueFactory("bookPrice"));
        o_book.setItems(blist);
    }

    @FXML
    public void sownerstart(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("OwnerStart.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void add(ActionEvent e) throws IOException {
       try{ booklist.add(new Book(tf_name.getText(), Double.parseDouble(tf_price.getText())));
        blist = FXCollections.observableArrayList(booklist);
        o_book.setItems(blist);}catch(Exception ex){}
    }

    @FXML
    public void delete(ActionEvent e) throws IOException {
        booklist.remove(o_book.getSelectionModel().getSelectedItem());
        blist = FXCollections.observableArrayList(booklist);
        o_book.setItems(blist);
    }

}
