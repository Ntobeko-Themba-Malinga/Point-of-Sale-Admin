module pos {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;


    exports org.pos.controller;
    exports org.pos.model;
    exports org.pos.repository;

    opens org.pos;
    opens org.pos.controller;
    opens org.pos.model;
    opens org.pos.repository;
}