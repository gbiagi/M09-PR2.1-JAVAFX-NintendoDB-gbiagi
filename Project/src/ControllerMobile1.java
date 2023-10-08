import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMobile1 {
    @FXML
    private VBox yPane;
    public void loadListMobile(String opcio) {
        // Obtenir una referència a AppData que gestiona les dades
        AppData appData = AppData.getInstance();
        // Demanar les dades
        appData.load(opcio, (result) -> {
            if (result == null) {
                System.out.println("ControllerMobile: Error loading data.");
            } else {
                try {
                    showListMobile(opcio);
                } catch (Exception e) {
                    System.out.println("ControllerMobile: Error showing list.");
                }
            }
        });
    }
    public void showListMobile(String opcioSeleccionada) throws Exception {
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
                // Defineix el callback que s'executarà quan l'usuari seleccioni un element
                // (cal passar final perquè es pugui accedir des del callback)
                final String type = opcioSeleccionada;
                final int index = i;
//                itemTemplate.setOnMouseClicked(event -> {
//                    showInfo(type, index);
//                });
                yPane.getChildren().add(itemTemplate);
            }
        }
    }
}