import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.application.Platform;

public class AppData {

    private static AppData instance = null;
    public boolean readyConsoles = false;
    public boolean readyJocs = false;
    public boolean readyPersonatges = false;
    public JSONArray dataConsoles = null;
    public JSONArray dataJocs = null;
    public JSONArray dataPersonatges = null;
    private AppData() { }

    public static AppData getInstance() {
        if (instance == null) {
            instance = new AppData();
        }
        return instance;
    }

    public void loadData(String dataFile, Consumer<String> callBack) {

        // Use a thread to avoid blocking the UI
        new Thread(() -> {
            try {
                // Wait a second to simulate a long loading time
                Thread.sleep(1000);  
    
                // Load the data from the assets file
                InputStream is = getClass().getResourceAsStream(dataFile);
                Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
                StringBuilder content = new StringBuilder();
                char[] buffer = new char[1024];
                int bytesRead;
                while ((bytesRead = reader.read(buffer)) != -1) {
                    content.append(buffer, 0, bytesRead);
                }

                // Call the callback function on the UI thread (Only works on JavaFX)
                Platform.runLater(()->{
                    callBack.accept(content.toString());
                });

            } catch (InterruptedException e) {
                callBack.accept(null);
                e.printStackTrace();
            } catch (IOException e) {
                callBack.accept(null);
                e.printStackTrace();
            }
        }).start();
    }
}
