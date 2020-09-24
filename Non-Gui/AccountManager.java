import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

interface Manager{
    String locationofacc = "Accounts.txt";
    String locationofbacc = "BackupAccounts.txt";
    String locationoflogs = "logs.txt";
    void writelogs(String str);
    void showLogs();
    HashMap<String,Double> getHashMapFromfile();
    void writeHashMap(HashMap<String,Double> h,boolean b);
    void updateBal(String s,Double d);
}
interface Accounts{
   String getAccount();
   Double getBalance();
   void setAccountName(String name);
   void setBalance(Double bal);
}
public class AccountManager implements Manager{
    static HashMap<String,Double> manage = new HashMap<>();
    AccountManager(){}
    class Account implements Accounts{
        String AccountName;
        Double Balance;
        Account() {
            this.AccountName = "";
            this.Balance = 0.0;
        }
        Account(String accname, double balance) {
            this.AccountName = accname;
            this.Balance = balance;
        }
        public String getAccount() { return this.AccountName; }
        public Double getBalance(){
            return this.Balance;
        }
        public void setAccountName(String name){
            this.AccountName = name;
        }
        public void setBalance(Double bal){
            this.Balance = bal;
        }
    }
    public void writelogs(String str){
        BufferedWriter bw = null;
        try{
            File file = new File(locationoflogs);
            if (file.createNewFile()) {}
            bw = new BufferedWriter(new FileWriter(file,true));
            bw.write(str+" on "+java.time.LocalDateTime.now());
            bw.newLine();
            bw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                bw.close();
            }catch (Exception e){}
        }
    }
    public void showLogs(){
        BufferedReader br = null;
        try{
            File file = new File(locationoflogs);
            br = new BufferedReader( new FileReader(file) );
            String line = null;
            while ( (line = br.readLine()) != null ){
                System.out.println(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(br != null){
                try {
                    br.close();
                }catch(Exception e){};
            }
        }
    }
    public HashMap<String,Double> getHashMapFromfile(){
        HashMap<String,Double> maps = new HashMap<String,Double>();
        BufferedReader br = null;
        try{
            File file = new File(locationofacc);
            if (file.createNewFile()){}
            br = new BufferedReader( new FileReader(file) );
            String line = null;
            while ( (line = br.readLine()) != null ){
                String[] parts = line.split(":");
                String accname = parts[0].trim();
                Double bal = Double.parseDouble( parts[1].trim());
                if( !accname.equals("") && !bal.equals("") )
                    maps.put(accname, bal);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(br != null){
                try {
                    br.close();
                }catch(Exception e){};
            }
        }
        return maps;
    }
    public void writeHashMap(String s,Double d){
        File file = new File(locationofacc);
        BufferedWriter bw = null;
        try{
            bw = new BufferedWriter(new FileWriter(file,true));
                bw.write(s+":"+d);
                bw.newLine();
                bw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                bw.close();
            }catch (Exception e){}
        }
    }
    public void writeHashMap(HashMap<String,Double> h,boolean b){
        File file = null;
        if(b){
        file = new File(locationofbacc);
        }else{
        file = new File(locationofacc);
        }
        BufferedWriter bw = null;
        try{
            bw = new BufferedWriter(new FileWriter(file,true));
            for(Map.Entry<String,Double> entry :h.entrySet()){
                bw.write(entry.getKey()+":"+entry.getValue());
                bw.newLine();
            }bw.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                bw.close();
            }catch (Exception e){}
        }
    }
    public void remrep(String out,String inp)throws IOException{
        PrintWriter pw = new PrintWriter(out);
        BufferedReader br1 = new BufferedReader(new FileReader(inp));
        String line1 = br1.readLine();
        while(line1 != null)
        {
            boolean flag = false;
            BufferedReader br2 = new BufferedReader(new FileReader(out));
            String line2 = br2.readLine();
            while(line2 != null)
            {
                if(line1.equals(line2))
                {
                    flag = true;
                    break;
                }
                line2 = br2.readLine();
            }
            if(!flag){
                pw.println(line1);
                pw.flush();
            }
            line1 = br1.readLine();
        }
        br1.close();
        pw.close();
        System.out.println("File operation performed successfully");
    }
    public void removefile(String s){
        File file = new File(s);
        file.delete();
    }
    public void remrep(String s) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(s));
        Set<String> lines = new HashSet<String>(10000);
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter(s));
        for (String unique : lines) {
            writer.write(unique);
            writer.newLine();
        }
        writer.close();
    }
    public void updateBal(String s,Double d){ manage.replace(s,d);}
    public void addAccount(Account a){ manage.put(a.AccountName,a.Balance);}
}


