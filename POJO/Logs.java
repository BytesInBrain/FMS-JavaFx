package POJO;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
public class Logs{
    public SimpleIntegerProperty userId = new SimpleIntegerProperty();
    public SimpleStringProperty accountName = new SimpleStringProperty();
    public SimpleDoubleProperty spend = new SimpleDoubleProperty();
    public SimpleStringProperty category = new SimpleStringProperty();
    public Integer getId() {
        return userId.get();
    }
    public String getAccountname() {
        return accountName.get();
    }
    public Double getSpend() {
        return spend.get();
    }
    public String getCategory(){
        return category.get();
    }
}
