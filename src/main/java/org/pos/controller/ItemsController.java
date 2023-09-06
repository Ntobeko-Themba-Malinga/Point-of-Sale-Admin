package org.pos.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.pos.model.Department;

public class ItemsController {
    @FXML
    private Label departmentLabel;
    @FXML
    private ListView items;
    @FXML
    private TextField name;
    @FXML
    private TextField qty;
    @FXML
    private TextField price;

    private Department department;

    public void create() {
        System.out.println(name.getText() + " " + qty.getText() + " " + price.getText());
    }

    public void update() {

    }

    public void delete() {

    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
