package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Databases {
    private String setUrl(String name){
        String url = "Folder to store database"+name;
        return url;
    }
    public void createNewDatabase(String filename){
        String createurl = setUrl(filename);
        try(Connection conn = DriverManager.getConnection(createurl)){
            if(conn!=null){
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is "+meta.getDriverName());
                System.out.println("A new database has been created");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public Connection connectToDatabase(String filename){
        Connection conn = null;
        String connecturl = setUrl(filename);
        try{
            conn = DriverManager.getConnection(connecturl);
        }catch (SQLException e){
            System.out.println(e.getMessage());
//        }finally {
//            try{
//                if(conn!=null){
////                    conn.close();
//                }
//            }catch (SQLException ex){
//                System.out.println(ex.getMessage());
//            }
        }return conn;
    }
    public void createBankTable(String filename){
        String cretaburl = setUrl(filename);
        String sqlt = "CREATE TABLE IF NOT EXISTS bank(id integer PRIMARY KEY,accountname text NOT NULL,balance real NOT NULL,bankname text NOT NULL )";
        try(Connection conn = DriverManager.getConnection(cretaburl);
            Statement stat = conn.createStatement()){
                    stat.execute(sqlt);
                    conn.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void createLogTable(String filename){
        String crelogurl = setUrl(filename);
        String sqll = "CREATE TABLE IF NOT EXISTS log(id integer PRIMARY KEY,accountname text NOT NULL,spend real NOT NULL,category text NOT NULL)";
        try(Connection conn = DriverManager.getConnection(crelogurl);
            Statement stat = conn.createStatement()){
            stat.execute(sqll);
            conn.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void insertdataintobank(String filename,String accountname,double balance,String bankname){
        String sql = "INSERT INTO bank(accountname,balance,bankname) VALUES(?,?,?)";
        try(Connection conn = this.connectToDatabase(filename);
            PreparedStatement sate = conn.prepareStatement(sql)){
                sate.setString(1,accountname);
                sate.setDouble(2,balance);
                sate.setString(3,bankname);
                sate.executeUpdate();
                conn.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void insertdataintolog(String filename,String name,double spend,String cate){
        String sql = "INSERT INTO log(accountname,spend,category) VALUES(?,?,?)";
        try(Connection conn = this.connectToDatabase(filename);
            PreparedStatement sate = conn.prepareStatement(sql)){
            sate.setString(1, name);
            sate.setDouble(2, spend);
            sate.setString(3, cate);
            sate.executeUpdate();
            conn.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public Double getrequiredBalance(String filename,String accountnamelol){
        String sql = "SELECT balance FROM bank WHERE accountname LIKE ?";
        Double d = null;
        try (Connection conn = this.connectToDatabase(filename);
             PreparedStatement sate  = conn.prepareStatement(sql);){
            sate.setString(1,accountnamelol);
            ResultSet result  = sate.executeQuery();
            while (result.next()) {
//                System.out.println(result.getDouble("balance"));
                d = result.getDouble("balance");
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }return d;
    }
    public List getaccountnames(String filename){
        String sql = "SELECT accountname FROM bank";
        List acn = new ArrayList();
        try (Connection conn = this.connectToDatabase(filename);
             PreparedStatement sate  = conn.prepareStatement(sql)){
            ResultSet result  = sate.executeQuery();
            while (result.next()) {
                acn.add(result.getString("accountname"));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }return acn;
    }
    public void updatebankbalance(String filename,String accountname,Double balance){
        String sql =  "UPDATE bank SET balance = ? WHERE accountname=?";
        try(Connection conn = this.connectToDatabase(filename);
            PreparedStatement sate = conn.prepareStatement(sql)){
            sate.setString(2,accountname);
            sate.setDouble(1,balance);
            sate.executeUpdate();
            conn.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public List getbankdata(String filename){
        String sql = "SELECT id,accountname,balance,bankname FROM bank";
        List<List<String>> data = new ArrayList<>();
        try(Connection conn = this.connectToDatabase(filename);
            Statement sate = conn.createStatement();
            ResultSet result = sate.executeQuery(sql)){
            List<String> list1 = new ArrayList<>();
            while(result.next()){
                list1.clear();
                list1.add(Integer.toString(result.getInt("id")));
                list1.add(result.getString("accountname"));
                list1.add(Double.toString(result.getDouble("balance")));
                list1.add(result.getString("bankname"));
                data.add(list1);
//                System.out.println(result.getInt("id") +  "\t" +
//                        result.getString("accountname") + "\t" +
//                        result.getDouble("balance")+ "\t" +
//                        result.getString("bankname"));
                System.out.println(list1);
            }
            conn.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }return data;
    }
    public List getlogdata(String filename){
        String sql = "SELECT id,name,spend,category FROM log";
        List<List<String>> data = new ArrayList<>();
        try(Connection conn = this.connectToDatabase(filename);
            Statement sate = conn.createStatement();
            ResultSet result = sate.executeQuery(sql)){
            List<String> list1 = new ArrayList<>();
            while(result.next()){
                list1.clear();
                list1.add(Integer.toString(result.getInt("id")));
                list1.add(result.getString("accountname"));
                list1.add(Double.toString(result.getDouble("balance")));
                list1.add(result.getString("bankname"));
                data.add(list1);
//                System.out.println(result.getInt("id") +  "\t" +
//                        result.getString("accountname") + "\t" +
//                        result.getDouble("balance")+ "\t" +
//                        result.getString("bankname"));
                System.out.println(list1);
            }
            conn.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }return data;
    }
    public void delete(String filename,int id) {
        String sql = "DELETE FROM logs WHERE id = ?";
        try (Connection conn = this.connectToDatabase(filename);
             PreparedStatement sate = conn.prepareStatement(sql)) {
            sate.setInt(1, id);
            sate.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
