module com.alexsoft.converter.webptopng {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.alexsoft.converter.webptopng to javafx.fxml;
    exports com.alexsoft.converter.webptopng;
}