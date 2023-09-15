package common;

import java.util.Scanner;


public class Library {

  
    protected Scanner sc;
    public Library(){
        sc  = new Scanner(System.in);
    }
    
    public String getString(String mes){
        System.out.println(mes);
        return sc.nextLine();
    }
    
        public int getInt(String promt, int m, int n) {
        int a = -1;
        while (true) {
            System.out.print(promt + ": ");
            try {
                String s = sc.nextLine();
                a = Integer.parseInt(s);
                if (a >= m && a <= n) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Please enter a number between " + m + " and " + n);
            }
        }
        return a;
    }
}
