import Database.Databases;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UI/Account.fxml"));
        Scene scene = new Scene(root,800,500);
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("Applogo.png")));
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args){
        Databases db = new Databases();
        db.createNewDatabase("Logs.db");
        db.connectToDatabase("Logs.db");
        db.createBankTable("Logs.db");
        db.createLogTable("Logs.db");
        launch(args);

    }
}