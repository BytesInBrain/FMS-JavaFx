package UI;
import Database.Databases;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class AddAccountController implements Initializable {
    @FXML private TextField bankbalanceadd;
    @FXML private TextField accountnameadd;
    @FXML private ChoiceBox<String> banknamebox;

    public void insertbankdata(String filename,String accountname,double balance,String bankname){
        Databases db = new Databases();
        db.connectToDatabase(filename);
        db.insertdataintobank(filename,accountname,balance,bankname);
    }
    public void gotoLogs(javafx.scene.input.MouseEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Logs.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
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
    public void submitaddaction(ActionEvent event) throws IOException
    {
        String accountname = accountnameadd.getText();
        double bankbalance = Double.parseDouble(bankbalanceadd.getText());
        String bankname = banknamebox.getValue().toString();
        insertbankdata("Logs.db",accountname,bankbalance,bankname);
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Account.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    public void gotoAccount(javafx.scene.input.MouseEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Account.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    public void gotoExpenditure(javafx.scene.input.MouseEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Expenditure.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    public void gotoSpendAnalysis(javafx.scene.input.MouseEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Spend.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
         banknamebox.getItems().add("SBI");
         banknamebox.getItems().addAll("Indian Bank","Axis Bank","Kotak Bank","HDFC bank","PNB","OBC");
         banknamebox.setValue("SBI");

    }
}
