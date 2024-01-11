import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name");
        String name = scanner.nextLine();

        //asking the user to enter their income, and test if their input is valid
        System.out.println("Please enter your income");
        String inc = scanner.nextLine();
        boolean tfi = numcheck(inc);
        double income;
        if(tfi){
            income = Double.parseDouble(inc);
        }
        else {
            boolean tft = false;
            String stri = null;
            while (tft == false) {
                System.out.println("Please enter your income again, but please make sure what you only enter numbers");
                stri = scanner.next();
                tft = numcheck(stri);
            }
            income = Double.parseDouble(stri);
        }

        //asking the user for their deductions, and test if what they entered is valid
        System.out.println("Please enter your deductions, if you have none, please enter 0");
        String dec = scanner.next();
        boolean tfd = numcheck(inc);
        double deductions;
        if(tfd==true){
            deductions = Double.parseDouble(dec);
        }
        else {
            boolean tfed = false;
            String strd = null;
            while (tfed == false) {
                System.out.println("Please enter your deductions again, but please make sure what you only enter numbers");
                strd = scanner.next();
                tfed = numcheck(strd);
            }
            deductions = Double.parseDouble(strd);
        }

        //asking the user for their allowances, and test if what they entered is valid
        System.out.println("Please enter you allowances, if you have none, please enter 0");
        String allow = scanner.next();
        boolean tfa = numcheck(allow);
        double allowances;
        if(tfa==true){
            allowances = Double.valueOf(allow);
        }
        else {
            boolean tfea = false;
            String stra = null;
            while (tfea == false) {
                System.out.println("Please enter your allowances again, but please make sure what you only enter numbers");
                stra = scanner.next();
                tfea = numcheck(stra);
            }
            allowances = Double.valueOf(stra);
        }

        //calculation of the user's net chargeable income
        double nci = income-allowances-deductions;

        //maxmimum of tax in each tax bracket (information provided by the HK government)
        int ftax = 1000;
        int stax = 3000;
        int ttax = 5000;
        int fotax = 7000;

        double tax=0;

        //test which tax bracket the user lands on
        if (nci<=50000){
            tax = nci*0.02;
        }
        else if(nci<=10000){
            double sbrac = nci-50000;
            double result = sbracket(sbrac);
            tax = ftax + result;
        }
        else if(nci<=150000){
            double tbrac = nci-10000;
            double result = tbracket(tbrac);
            tax = ftax+stax+result;
        }
        else if(nci<=200000){
            double fbrac = nci-150000;
            double result = fbracket(fbrac);
            tax = ftax+stax+ttax+result;
        }
        else if(nci>200000){
            double fibrac = nci-200000;
            double result = fibracket(fibrac);
            tax = ftax+stax+ttax+fotax+result;
        }

        //print out the tax due of the user
        System.out.println("The tax due is "+tax);

        //asking the user if they would like to save the information to a file, if so, a new file is created to store the tax due
        System.out.println("Do you want to save this information to a file?");
        String infoys = scanner.next();
        if (infoys.equals("Yes")||infoys.equals("yes")){
            PrintStream ps = new PrintStream(name+".txt");
            ps.println(tax);
        }
        System.out.println("Thank you for using the program!");

    }

    public static double sbracket(double a){
        double stbrac = a*0.06;
        return stbrac;
    }
    public static double tbracket(double a){
        double ttbrac = a*0.1;
        return ttbrac;
    }
    public static double fbracket(double a){
        double ftbrac = a*0.14;
        return ftbrac;
    }
    public static double fibracket(double a){
        double fitbrac = a*0.17;
        return fitbrac;
    }

    public static boolean numcheck(String a){
        boolean tf = true;
        try {
            Double num = Double.parseDouble(a);
            tf = true;
        } catch (NumberFormatException ex) {
            tf = false;
        }
        return tf;
    }



}
