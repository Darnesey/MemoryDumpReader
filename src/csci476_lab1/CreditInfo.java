/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci476_lab1;

import java.util.*;


/**
 *
 * @author Ryan Darnell & Will Paddock
 */
public class CreditInfo {

    private static ArrayList<String> creditCardNumber = new ArrayList<>();
    private static String primaryAccountNumber;
    private static String name = "";
    private static String CountryCode;
    private static String expirationDate;
    private static String discretionaryData;


	public static ArrayList<String> scanCredit(ArrayList<String> input) {
        String end = null;
        String[] dump = new String[input.size()];
        int integer = 0;
        for(String value : input)
        {
        	dump[integer] = value;
        	integer++;
        }
        
            for (int i = 0; i < dump.length; i++) {
                if (dump[i].charAt(0) == '%') { //Test for Track 1 Data     && dump[i].length() > 30
                    int j = 2;
                    boolean test1 = false;
                    boolean test2 = false;
                    while (!test1 || !test2){
                        if (dump[i].charAt(j) == '^')
                            test1 = true;
                        j++;
                        if (dump[i].charAt(j) == '?' && j < dump[i].length())
                            test2 = true;
                        if (j == dump[i].length() - 1)
                            break;
                    }
                    if (test1 && test2)
//                        if (dump[i].charAt(1) == 'B')
                        track1(dump[i]);
                }
                if (dump[i].charAt(0) == ';' && dump[i].length() < 50) { //Test for Track 2 Data    && dump[i].length() > 25
                    int j = 2;
                    boolean test1 = false;
                    boolean test2 = false;
                    
                    while (!test1 || !test2){
                        if (dump[i].contains("="))
                            test1 = true;
                        j++;
                        if (dump[i].contains("?"))
                            test2 = true;
                        if (j == dump[i].length())
                            break;
                    }
                    if (test1 && test2)
                        
                            track2(dump[i]);
                }
            }
            

        return creditCardNumber;
    }
        
        
     private static void track1(String string) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String account = "";
        
        if (string.charAt(1) == 'B') {
             String[] first = string.split("[^/\\s\\w]"); //[^/\\s\\w]
             if (first.length > 3) {
                 name = first[2];
                 //name = name.replace('/', ' ');
                 account = first[3];
             } else {
                 for (int i = 0; i < first[2].length(); i++) {
                     if ((int) first[2].charAt(i) > 65 || first[2].charAt(i)=='/') { //look for when the characters become numbers
                         name = first[2].substring(0, i+1);
                         //name = name.replace('/', ' ');
                         account = first[2].substring(i+1); //additional account information
                     }
                 }
             }
             primaryAccountNumber = first[1].substring(1); //grab card number information
             //build data from account variable
             expirationDate = account.substring(0, 4); //Date
             CountryCode = account.substring(4, 7); //CVC
             discretionaryData = account.substring(7, 11); //Pin
             String card = "Card #: " + primaryAccountNumber + "; Name: " + name + "; ExpDate: " + expirationDate + "; CVC: " + CountryCode + "; Pin: " + discretionaryData;
             creditCardNumber.add(card);
         }
        
            
        }

    

        private static void track2(String string) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String account = "";
            String[] first;
        
             first = string.split("="); //[^/\\s\\w]
             
             int index = first[1].indexOf('?');
             first[1] = first[1].substring(0,index);
             first[0] = first[0].substring(1);
             
             String tester = first[0] + first[1];
             char[] test = tester.toCharArray();
             boolean numbers = true;
             for (int i = 0; i < test.length; i++) {
                if ((int)test[i] > 57 || (int)test[i] < 48){
                    numbers = false;
                    break;
                }
            }
             
             if (numbers){
             account = first[1];
             primaryAccountNumber = first[0]; //grab card number information
             //build data from account variable
             expirationDate = account.substring(0, 4); //Date
             discretionaryData = account.substring(4, 8); //Pin
             String card = "Card #: " + primaryAccountNumber + "; ExpDate: " + expirationDate + "; Pin: " + discretionaryData;
             creditCardNumber.add(card);
             }
             numbers = true;
        
        }
        
        
        



    
}
