package UI;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import Database.Databases;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class SpendController implements Initializable {
    @FXML PieChart piechart;
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
    private ObservableList<PieChart.Data> data;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        buildpiedata();
        piechart.getData().addAll(data);
    }
    public void buildpiedata(){
    Databases db = new Databases();
    data = FXCollections.observableArrayList();
        Map<String,Double> mdata = new HashMap<>();
    String sql = "SELECT spend,category FROM log";
    try{
        Connection conn = db.connectToDatabase("Logs.db");
        ResultSet result = conn.createStatement().executeQuery(sql);
        while(result.next()){
            mdata.merge(result.getString("category"),result.getDouble("spend"),Double::sum);
        }
        data = mdata.entrySet().stream().map(var -> new PieChart.Data(var.getKey(),var.getValue())).collect(Collectors.toCollection(()->FXCollections.observableArrayList()));
    }catch (Exception e){
        System.out.println(e.getMessage());
    }
}

}
