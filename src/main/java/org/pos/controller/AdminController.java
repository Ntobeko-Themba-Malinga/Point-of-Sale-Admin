package org.pos.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.pos.model.User;
import org.pos.repository.IUserRepository;
import org.pos.repository.UserRepository;

import java.io.IOException;

public class AdminController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    public Label errorLabel;

    private IUserRepository userRepository;


    public AdminController() {
        this.userRepository = new UserRepository();
    }

    /**
     * Logins in the admin.
     * @param event Event from the login button.
     */
    public void login(ActionEvent event) {
        String usernameText = username.getText().trim();
        String passwordText = password.getText().trim();

        //Used only for testing.
        userRepository.registerUser("TestUser", "testpass123", "ADMIN"); //

        User user = userRepository.getUser(usernameText, passwordText);

        if (user != null) {
            loadHomeScene(event);
        } else {
            errorLabel.setText("* Invalid username or password!");
        }
    }

    /**
     * Changes the scene to the next home scene.
     * @param event  Event from the login button. Used to get the stage.
     */
    private void loadHomeScene(ActionEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
