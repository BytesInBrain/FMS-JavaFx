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


public class ExpenditureController implements Initializable {
    @FXML private TextField eexpenditure;
    @FXML private ChoiceBox eaccount;
    @FXML private ChoiceBox ecategory;
    public void insertexpdata(String filename,String accountname,double spend,String category){
        Databases db = new Databases();
        db.connectToDatabase(filename);
        db.insertdataintolog(filename,accountname,spend,category);
        System.out.println("A Log is created.");
    }
    public void submitexpenditure(ActionEvent e)throws IOException
    {
        Double spend = Double.parseDouble(eexpenditure.getText().toString());
        String category = ecategory.getValue().toString();
        String accountname = eaccount.getValue().toString();
        Databases db = new Databases();
        Double existingbal = db.getrequiredBalance("Logs.db",accountname);
        Double finalbal = existingbal - spend;
        db.updatebankbalance("Logs.db",accountname,finalbal);
        insertexpdata("Logs.db",accountname,spend,category);
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Account.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    public void gotoLogs(javafx.scene.input.MouseEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Logs.fxml"));
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
    public void gotoAddAccount(javafx.scene.input.MouseEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AddAccount.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
        Databases db = new Databases();
        System.out.println(db.getaccountnames("Logs.db"));
        db.getaccountnames("Logs.db").forEach((elem)->{
            eaccount.getItems().add((String)elem);
        });
        ecategory.getItems().addAll("Food","Sports","Blah","Meh","Lol","Hnm","Hnm1","Hnm2");
        ecategory.setValue("Food");


    }
}
