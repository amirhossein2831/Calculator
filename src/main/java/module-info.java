module com.example.calculator {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.calculator to javafx.fxml;
    exports com.example.calculator;
    exports com.example.calculator.Controller;
    opens com.example.calculator.Controller to javafx.fxml;
}