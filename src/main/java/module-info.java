module org.example.catalogomelodias {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.catalogomelodias to javafx.fxml;
    exports org.example.catalogomelodias;
}