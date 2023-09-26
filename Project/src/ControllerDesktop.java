import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

import java.awt.*;

public class ControllerDesktop implements Initializable {
    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private VBox yPane;

    @FXML
    private AnchorPane info;

    String[] opcions = {"Personatges", "Jocs", "Consoles"};
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Añadir opciones al ChoiceBox
        choiceBox.getItems().addAll(opcions);
        // Seleccionar primera opcion
        choiceBox.setValue(opcions[0]);
        // Callback que se ejecuta cuando el usuario escoge una opcion
        choiceBox.setOnAction((event) -> {loadList(); });
        // Cargar aytinatucanebte kis datis de "Personajes"
        loadList();
    }
    public void loadList() {
        String opcio = choiceBox.getValue();
        System.out.println(opcio);
    }
}