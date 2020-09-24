import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        AccountManager manager = new AccountManager();
        boolean bool = true;
        AccountManager.manage = manager.getHashMapFromfile();
        try{
            manager.remrep(AccountManager.locationofacc);
        }catch (IOException e){
            e.printStackTrace();
        }
        while(bool) {
            System.out.println("1.Add an Account");
            System.out.println("2.View All Account and Balance");
            System.out.println("3.Enter Expense");
            System.out.println("4.View Logs");
            System.out.println("5.Deposit Balance");
            System.out.println("6.Save and exit");
            int x = sc.nextInt();
            sc.nextLine();
            if (x == 1) {
                AccountManager.Account ac = manager.new Account();
                System.out.print("Enter the Accountname :- ");
                String s = sc.nextLine();
                ac.setAccountName(s);
                System.out.print("Enter the Balance :- ");
                Double d = sc.nextDouble();
                sc.nextLine();
                ac.setBalance(d);
                manager.addAccount(ac);
                manager.writeHashMap(s,d);
                System.out.println("Account Has Been Added!");

            } else if (x == 2) {
                AccountManager.manage.forEach((accn,bal)->System.out.println("The Account "+accn+" has "+bal+" balance"));
            } else if (x == 3) {
                System.out.print("Enter the name of the account in which you spent money :-");
                String s = sc.nextLine();
                System.out.print("Enter the amount of money spent :- ");
                Double spen = sc.nextDouble();
                Double existing = AccountManager.manage.get(s);
                Double up = existing - spen;
                manager.updateBal(s, up);
                System.out.println("The Balance in " + s + " is :-" + up);
                manager.writelogs("The Balance in " + s + " is :-" + up);
            } else if (x == 4) {
                manager.showLogs();
            } else if (x == 5) {
                System.out.print("Enter the name of the account in which you want to add money :-");
                String s = sc.nextLine();
                System.out.print("Enter the amount of money to add :- ");
                Double spen = sc.nextDouble();
                Double existing = AccountManager.manage.get(s);
                Double up = existing+spen;
                manager.updateBal(s, up);
                System.out.println("The Balance in " + s + " is :-" + up);
                manager.writelogs("The Balance in " + s + " is :-" + up);
            }else if(x==6){
                manager.writeHashMap(AccountManager.manage,true);
                manager.removefile(AccountManager.locationofacc);
                manager.writeHashMap(AccountManager.manage,false);
                try{
                    manager.remrep(AccountManager.locationofbacc);
                }catch (IOException e){
                    e.printStackTrace();
                }
                System.out.println("Saving and Exiting.........");
                bool = false;
                break;
            } else {
                System.out.println("Please Select the correct option(number)");
            }
        }
    }
}
