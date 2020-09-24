package POJO;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
public class Bank {
        public SimpleIntegerProperty userId = new SimpleIntegerProperty();
        public SimpleStringProperty accountName = new SimpleStringProperty();
        public SimpleDoubleProperty balance = new SimpleDoubleProperty();
        public SimpleStringProperty bankName = new SimpleStringProperty();
        public Integer getId() {
            return userId.get();
        }
        public String getAccountname() {
            return accountName.get();
        }
        public String getBankName() {
            return bankName.get();
        }
        public Double getBalance(){
            return balance.get();
        }
}
