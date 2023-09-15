package org.pos.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class HomeController {


    public void cashiers(ActionEvent event){
        System.out.println("Loads cash scene");

    }


    public void sales(ActionEvent event){
        System.out.println("Loads sales scene");
    }

    public void department(ActionEvent event){
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("Department.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };





}
