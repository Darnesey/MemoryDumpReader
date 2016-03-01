/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci476_lab1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Ryan Darnell & Will Paddock
 */
public class DumpReader {

    static FileInputStream read;
    static FileWriter write;
    static String file;
    public DumpReader(String file) throws FileNotFoundException, IOException {
        this.read = new FileInputStream(file);
        this.file = file;
        write = new FileWriter("outputAC.txt");
//        dumpDecrypt();
    }
    
    public static ArrayList<String> dumpDecrypt() throws IOException{
        int i = 0;
        char temp;
        String s = "";
        ArrayList<String> total = new ArrayList<>();
        int nonsense = 0;
        int[] code = new int[32];
        while (read.available() > 0){
            while (i < 32) {
                code[i] = read.read();
                if (code[i] < 127 && code[i] > 32) { //avoid special keys
                    
                    if (code[i] == 37 || code[i] == 59){
                        if (s.length() > 25){
                            total.add(s);
                        }
                        s = "";
                    }
                    
                    s += (char) code[i];
                    
                    nonsense = 0;
                } else {
                    nonsense++;
                    if (nonsense == 4){
                            //System.out.print("..");
                            //write.write(".");
                            nonsense = 0;
                    }
                }
                code[i] = 0; //clean 'n go
                i++;
            }
            i = 0;
            
            //System.out.print(total.get(total.size()-1));
            //write.write(s + "\n");
            i = 0;
            
        }
        //write.close();
        return total;
    }
    
}


