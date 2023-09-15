package org.pos.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.pos.model.Department;
import org.pos.model.Item;
import org.pos.repository.DepartmentRepository;
import org.pos.repository.IDepartmentRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentController implements Initializable{
    @FXML
    private Label departmentLabel;
    @FXML
    private ListView<Department> departmentsList;
    @FXML
    private TextField departmentName;
    @FXML
    private Button updateButton;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;

    private Department selectedDepar;
    private final IDepartmentRepository deparRepo;



    public DepartmentController(){
        this.deparRepo = new DepartmentRepository();
    }

    private void updateButtonStatus(boolean updateButton, boolean addButton, boolean deleteButton){
        this.updateButton.setDisable(updateButton);
        this.addButton.setDisable(addButton);
        this.deleteButton.setDisable(deleteButton);
    }


    private void updateField(){
        if(selectedDepar != null){
            this.departmentName.setText(selectedDepar.getName());
        }else{
            this.departmentName.setText("");
        }
    }

    private  void updateTheeListView(){
        departmentsList.getItems().clear();
        departmentsList.getItems().addAll(this.deparRepo.getDepartments());
    }


    public void create(){
        try {
            String deparName = departmentName.getText();

            Department depar = new Department();
            depar.setName(deparName);

            this.deparRepo.saveDepartment(depar);
            this.departmentsList.getItems().add(depar);
        }catch (Exception e){
            System.out.println("Oops!!! Something's wrong");
        }
    }

    public void update(){
        try {
            String deparName = departmentName.getText();

            selectedDepar.setName(deparName);

            this.deparRepo.updateDepartment(selectedDepar);
            this.updateTheeListView();
        }catch (Exception e){
            System.out.println("Oops!!! Something's wrong");
        }
        this.selectedDepar = null;
        this.updateButtonStatus(true, false, true);
        this.updateField();
    }

    public void deleteDepartment(){
        Alert confirmDepart = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDepart.setTitle("Delete Department?");
        confirmDepart.setContentText("Are you sure that you want to permanently remove'"+ selectedDepar +"'?");

        if (confirmDepart.showAndWait().get() == ButtonType.OK){
            try{
                this.deparRepo.deleteDepartment(selectedDepar);
                this.departmentsList.getItems().remove(selectedDepar);
            }catch (Exception e){
                System.out.println("Oops!!! Something's wrong");
            }
            this.selectedDepar = null;
            this.updateButtonStatus(true, false, true);
            updateField();
        }
    }

    public void setDepartments(Department department){
        this.selectedDepar = department;
        departmentLabel.setText(this.selectedDepar.getName());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.updateButtonStatus(true, false, true);
        this.departmentsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedDepar = departmentsList.getSelectionModel().getSelectedItem();
            this.updateButtonStatus(false, true, false);
            updateField();
        });
    }

}
