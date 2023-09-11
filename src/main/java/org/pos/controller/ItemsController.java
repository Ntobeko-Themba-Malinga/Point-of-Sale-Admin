package org.pos.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

    private Department department;
    private Item selectedItem;
    private final ItemRepository itemRepo;

    public ItemsController() {
        this.itemRepo = new ItemRepository();
    }

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

    public void update() {
        try {
            String itemName = name.getText();
            int itemQty = Integer.parseInt(qty.getText());
            double itemPrice = Double.parseDouble(price.getText());

            selectedItem.setName(itemName);
            selectedItem.setQuantity(itemQty);
            selectedItem.setPrice(itemPrice);

            this.itemRepo.updateItem(selectedItem);
            items.getItems().clear();
            items.getItems().addAll(this.department.getItems());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        }
        this.selectedItem = null;
        this.updateButton.setDisable(true);
        this.addButton.setDisable(false);
        name.setText("");
        qty.setText("");
        price.setText("");
    }

    public void delete() {

    }

    public void setDepartment(Department department) {
        this.department = department;
        departmentLabel.setText(this.department.getName());
        items.getItems().addAll(this.department.getItems());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.updateButton.setDisable(true);
        this.items.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Item> observable, Item oldValue, Item newValue) {
                selectedItem = items.getSelectionModel().getSelectedItem();
                updateButton.setDisable(false);
                addButton.setDisable(true);
                name.setText(selectedItem.getName());
                qty.setText("" + selectedItem.getQuantity());
                price.setText("" + selectedItem.getPrice());
            }
        });
    }
}
