package UI;
import POJO.Bank;
import Database.Databases;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn.CellEditEvent;
public class AccountController implements Initializable {
    @FXML
    private TableView<Bank> tableView;
    @FXML
    private TableColumn<Bank, Integer> idColumn;
    @FXML
    private TableColumn<Bank, String> accountNameColumn;
    @FXML
    private TableColumn<Bank, String> banknameColumn;
    @FXML
    private TableColumn<Bank, Double> balanceColumn;

    public void changebalance(CellEditEvent edittedCell)
    {
        System.out.println(tableView.getSelectionModel().getSelectedItem());

    }
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
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        accountNameColumn.setCellValueFactory(new PropertyValueFactory<>("accountname"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
        banknameColumn.setCellValueFactory(new PropertyValueFactory<>("bankName"));
        buildBankData();
    }

    public void buildBankData() {
        tableView.getSelectionModel().setCellSelectionEnabled(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<Bank> data = FXCollections.observableArrayList();
        Databases db = new Databases();
        try {
            String sql = "SELECT id,accountname,balance,bankname FROM bank";
            Connection conn = db.connectToDatabase("Logs.db");
            Statement sate = conn.createStatement();
            ResultSet result = sate.executeQuery(sql);
            while (result.next()) {
                Bank bank = new Bank();
                bank.userId.set(result.getInt("id"));
                bank.accountName.set(result.getString("accountname"));
                bank.balance.set(result.getDouble("balance"));
                bank.bankName.set(result.getString("bankname"));
                data.add(bank);
            }
            tableView.setItems(data);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error building Data");
        }
    }

}
