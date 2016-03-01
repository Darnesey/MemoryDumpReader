/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci476_lab1;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Ryan Darnell & Will Paddock
 */
public class CSCI476_Lab1 {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) throws FileNotFoundException, IOException {
	// write your code here

        DumpReader dump = new DumpReader(args[0]);
        
        ArrayList<String> dumpFile = dump.dumpDecrypt();
        
        System.out.println();
        
        CreditInfo scan = new CreditInfo();
        ArrayList<String> credits = new ArrayList<>();
                credits = scan.scanCredit(dumpFile);
        for (int i = 0; i < credits.size(); i++){
        	System.out.println(credits.get(i) + "\n");
        }
        
        //String[] credit = scan.CreditInfo(dump);
        


    }
    
}


