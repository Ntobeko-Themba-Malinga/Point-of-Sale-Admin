package org.pos.controller;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class HomeController {


    public void cashiers(ActionEvent event){
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

        try {
//            Parent root_ = FXMLLoader.load(getClass().getResource("Home.fxml"));

            //Used for testing
            Pane root = new Pane(); //

            Text text = new Text(); //
            text.setFill(Color.BLACK); //
            text.setText("THis is the cashiers scene"); //
            text.setX(100); //
            text.setY(100); //
            root.getChildren().add(text); //

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void sales(ActionEvent event){
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

        try {
//            Parent root_ = FXMLLoader.load(getClass().getResource("Home.fxml"));

            //Used for testing
            Pane root = new Pane(); //

            Text text = new Text(); //
            text.setFill(Color.BLACK); //
            text.setText("THis is the sales scene"); //
            text.setX(100); //
            text.setY(100); //
            root.getChildren().add(text); //

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public void department(ActionEvent event){
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

        try {
//            Parent root_ = FXMLLoader.load(getClass().getResource("Home.fxml"));

            //Used for testing
            Pane root = new Pane(); //

            Text text = new Text(); //
            text.setFill(Color.BLACK); //
            text.setText("This is the department scene"); //
            text.setX(100); //
            text.setY(100); //
            root.getChildren().add(text); //

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };





}
