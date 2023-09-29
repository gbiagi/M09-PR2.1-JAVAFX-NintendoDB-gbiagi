import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.fxml.Initializable;
import org.json.JSONArray;
import org.json.JSONObject;

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
        // Obtenir l'opció seleccionada
        String opcio = choiceBox.getValue();
        // Obtenir una referència a AppData que gestiona les dades
        AppData appData = AppData.getInstance();
        // Mostrar el missatge de càrrega
        showLoading();
        // Demanar les dades
        appData.load(opcio, (result) -> {
            if (result == null) {
                System.out.println("ControllerDesktop: Error loading data.");
            } else {
                try {
                    showList();
                } catch (Exception e) {
                    System.out.println("ControllerDesktop: Error showing list.");
                }
            }
        });
    }
    public void showList() throws Exception {
        String opcioSeleccionada = choiceBox.getValue();
        // Carregar la plantilla
        URL resource = this.getClass().getResource("assets/template_list_item.fxml");
        // Obtenir una referència a l'ojecte AppData que gestiona les dades
        AppData appData = AppData.getInstance();
        // Obtenir les dades de l'opció seleccionada
        JSONArray dades = appData.getData(opcioSeleccionada);
        // Esborrar la llista actual
        yPane.getChildren().clear();
        // Carregar la llista amb les dades
        for (int i = 0; i < dades.length(); i++) {
            JSONObject consoleObject = dades.getJSONObject(i);
            if (consoleObject.has("nom")) {
                String nom = consoleObject.getString("nom");
                String imatge = "assets/images/" + consoleObject.getString("imatge");
                FXMLLoader loader = new FXMLLoader(resource);
                Parent itemTemplate = loader.load();
                ControllerListItem itemController = loader.getController();
                itemController.setText(nom);
                itemController.setImage(imatge);
                yPane.getChildren().add(itemTemplate);
            }
        }
    }
    // Funció ‘showLoading’, mostrar una càrrega
    public void showLoading() {
    // Esborrar la llista actual
        yPane.getChildren().clear();
    // Afegeix un indicador de progrés com a primer element de la llista
        ProgressIndicator progressIndicator = new ProgressIndicator();
        yPane.getChildren().add(progressIndicator);
    }
}