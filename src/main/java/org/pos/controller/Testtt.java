package org.pos.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.pos.model.Department;
import org.pos.model.Item;
import org.pos.repository.DepartmentRepository;
import org.pos.repository.IDepartmentRepository;
import org.pos.repository.IItemRepository;
import org.pos.repository.ItemRepository;

public class Testtt extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        IDepartmentRepository departmentRepository = new DepartmentRepository();
        IItemRepository itemRepository = new ItemRepository();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Items.fxml"));

        Parent root = loader.load();
        ItemsController controller = loader.getController();
        Department department = new Department();
        department.setName("Test Department");
        departmentRepository.saveDepartment(department);

        Item item = new Item();
        item.setName("Test item 1");
        item.setQuantity(10);
        item.setPrice(25);
        itemRepository.saveItem(item);
        department.getItems().add(item);

        Item item2 = new Item();
        item2.setName("Test item 2");
        item2.setQuantity(20);
        item2.setPrice(250);
        itemRepository.saveItem(item2);
        department.getItems().add(item2);
        controller.setDepartment(department);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
