package UI;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import Database.Databases;
import POJO.Logs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
public class LogsController implements Initializable {
    @FXML
    private TableView<Logs> tableViewl;
    @FXML
    private TableColumn<Logs, Integer> idColumnl;
    @FXML
    private TableColumn<Logs, String> accountNameColumnl;
    @FXML
    private TableColumn<Logs, String> categoryColumnl;
    @FXML
    private TableColumn<Logs, Double> spendColumnl;
    public void gotoLogs(javafx.scene.input.MouseEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Logs.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void gotoAddAccount(javafx.scene.input.MouseEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AddAccount.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void gotoAccount(javafx.scene.input.MouseEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Account.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void gotoExpenditure(javafx.scene.input.MouseEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Expenditure.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void gotoSpendAnalysis(javafx.scene.input.MouseEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Spend.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumnl.setCellValueFactory(new PropertyValueFactory<>("id"));
        accountNameColumnl.setCellValueFactory(new PropertyValueFactory<>("accountname"));
        spendColumnl.setCellValueFactory(new PropertyValueFactory<>("spend"));
        categoryColumnl.setCellValueFactory(new PropertyValueFactory<>("category"));
        buildBankData();
    }

    public void buildBankData() {
        ObservableList<Logs> data = FXCollections.observableArrayList();
        Databases db = new Databases();
        try {
            String sql = "SELECT id,accountname,spend,category FROM log";
            Connection conn = db.connectToDatabase("Logs.db");
            Statement sate = conn.createStatement();
            ResultSet result = sate.executeQuery(sql);
            while (result.next()) {
                Logs log = new Logs();
                log.userId.set(result.getInt("id"));
                log.accountName.set(result.getString("accountname"));
                log.spend.set(result.getDouble("spend"));
                log.category.set(result.getString("category"));
                data.add(log);
            }
            tableViewl.setItems(data);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error building Data");
        }
    }

}
