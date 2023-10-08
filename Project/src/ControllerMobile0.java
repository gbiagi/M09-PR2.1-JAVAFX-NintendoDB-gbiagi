import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMobile0 implements Initializable{

        @FXML
        private ListView<String> lista = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList("Consoles","Jocs","Personatges");
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                lista.setItems(items);
                lista.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue != null) {
                                try {
                                        ControllerMobile1 control1 = new ControllerMobile1();
                                        UtilsViews.setView("ListaMovil");
                                        control1.loadListMobile(newValue);
                                } catch (Exception e) {
                                        System.out.println("Error al cargar la lista.");;
                                }
                        }
                });
        }

}
