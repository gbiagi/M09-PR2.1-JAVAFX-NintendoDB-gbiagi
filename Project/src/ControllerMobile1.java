import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URL;

public class ControllerMobile1 {
    @FXML
    ListView<String> listaObjetos = new ListView<>();
    ObservableList<String> items = FXCollections.observableArrayList("a");
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
                    e.printStackTrace();
                    System.out.println("ControllerMobile: Error showing list.");
                }
            }
        });
    }
    public void showListMobile(String opcioSeleccionada) throws Exception {
        // Obtenir una referència a l'ojecte AppData que gestiona les dades
        AppData appData = AppData.getInstance();
        System.out.println("E2");
        // Obtenir les dades de l'opció seleccionada
        JSONArray dades = appData.getData(opcioSeleccionada);
        System.out.println("E4");
        // Carregar la llista amb les dades
        int j = 5;
        for (int i = 0; i < dades.length(); i++) {
            j++;
            JSONObject consoleObject = dades.getJSONObject(i);
            if (consoleObject.has("nom")) {
                String nom = consoleObject.getString("nom");
                System.out.println(nom);
                items.addAll(nom);
            }
        }
        listaObjetos.setItems(items);
        System.out.println("aa****");
        System.out.println(listaObjetos);
    }
}
