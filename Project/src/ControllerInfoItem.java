import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.awt.*;

public class ControllerInfoItem {
    @FXML
    private ImageView img;
    @FXML
    private Label title = new Label();
    @FXML
    private Label text1 = new Label();
    @FXML
    private Label text2 = new Label();
    @FXML
    private Label text3 = new Label();
    @FXML
    private Rectangle cuadradoColor = new Rectangle();

    public void setCuadradoColor(Color color){
        cuadradoColor.setWidth(30);
        cuadradoColor.setHeight(30);
        cuadradoColor.setFill(color);
        cuadradoColor.setStroke(Color.BLACK);
        cuadradoColor.setVisible(true);
    }

    public void setImage(String resourceName) {

        // Obté una referència al recurs dins del .jar
        ClassLoader classLoader = getClass().getClassLoader();
        Image image = new Image(classLoader.getResourceAsStream(resourceName));

        // Estableix la imatge a l'ImageView
        img.setImage(image);
    }
    public void setTitle(String text) {

        // Estableix el contingut del Label
        this.title.setText(text);
    }
    public void setText1(String text) {

        // Estableix el contingut del Label
        this.text1.setText(text);
    }
    public void setText2(String text) {

        // Estableix el contingut del Label
        this.text2.setText(text);
    }
    public void setText3(String text) {

        // Estableix el contingut del Label
        this.text3.setText(text);
    }
}
