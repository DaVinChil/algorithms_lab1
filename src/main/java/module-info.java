module ru.ns.alg_lab {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.ns.alg_lab to javafx.fxml;
    exports ru.ns.alg_lab;
}