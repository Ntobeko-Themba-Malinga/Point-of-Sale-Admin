package org.pos.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.pos.model.Department;
import org.pos.model.Item;
import org.pos.repository.ItemRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemsController implements Initializable {
    @FXML
    private Label departmentLabel;
    @FXML
    private ListView<Item> items;
    @FXML
    private TextField name;
    @FXML
    private TextField qty;
    @FXML
    private TextField price;
    @FXML
    private Button updateButton;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;

    private Department department;
    private Item selectedItem;
    private final ItemRepository itemRepo;

    public ItemsController() {
        this.itemRepo = new ItemRepository();
    }

    /**
     * Changes the state of buttons between enabled and disabled.
     * @param updateButton if true the update button is disabled else it is enabled.
     * @param addButton if true the add button is disabled else it is enabled.
     * @param deleteButton if true the delete button is disabled else it is enabled.
     */
    private void updateButtonState(boolean updateButton, boolean addButton, boolean deleteButton) {
        this.updateButton.setDisable(updateButton);
        this.addButton.setDisable(addButton);
        this.deleteButton.setDisable(deleteButton);
    }

    /**
     * Update the text fields content
     * if no item is selected the text field are set to empty
     */
    private void updateFields() {
        if (selectedItem != null) {
            this.name.setText(selectedItem.getName());
            this.qty.setText("" + selectedItem.getQuantity());
            this.price.setText("" + selectedItem.getPrice());
        } else {
            this.name.setText("");
            this.qty.setText("");
            this.price.setText("");
        }
    }

    /**
     * Updates the ListView of items to show changes that have been made.
     */
    private void updateListViewItems() {
        items.getItems().clear();
        items.getItems().addAll(this.department.getItems());
    }

    /**
     * Called when the add button is clicked.
     * Adds an item to the database.
     */
    public void create() {
        try {
            String itemName = name.getText();
            int itemQty = Integer.parseInt(qty.getText());
            double itemPrice = Double.parseDouble(price.getText());

            Item item = new Item();
            item.setName(itemName);
            item.setQuantity(itemQty);
            item.setPrice(itemPrice);

            this.itemRepo.saveItem(item);
            this.department.getItems().add(item);
            items.getItems().clear();
            items.getItems().addAll(this.department.getItems());
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }

    /**
     * Called when the update button is clicked.
     * Updates an item on the database.
     */
    public void update() {
        try {
            String itemName = name.getText();
            int itemQty = Integer.parseInt(qty.getText());
            double itemPrice = Double.parseDouble(price.getText());

            selectedItem.setName(itemName);
            selectedItem.setQuantity(itemQty);
            selectedItem.setPrice(itemPrice);

            this.itemRepo.updateItem(selectedItem);
            this.updateListViewItems();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        }
        this.selectedItem = null;
        this.updateButtonState(true, false, true);
        this.updateFields();
    }

    /**
     * Called when the delete button is clicked.
     * deletes an item from the database.
     */
    public void delete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete item?");
        alert.setContentText("Are you sure you want to delete '" + selectedItem + "'?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                this.itemRepo.deleteItem(selectedItem);
                this.department.getItems().remove(selectedItem);
                this.updateListViewItems();
            } catch (Exception e) {
                System.out.println("Something went wrong!");
            }
            this.selectedItem = null;
            this.updateButtonState(true, false, true);
            updateFields();
        }
    }

    public void setDepartment(Department department) {
        this.department = department;
        departmentLabel.setText(this.department.getName());
        items.getItems().addAll(this.department.getItems());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.updateButtonState(true, false, true);
        this.items.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedItem = items.getSelectionModel().getSelectedItem();
            this.updateButtonState(false, true, false);
            updateFields();
        });
    }
}
