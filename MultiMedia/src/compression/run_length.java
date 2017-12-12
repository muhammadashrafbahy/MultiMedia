/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compression;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author osama fawzy
 */
public class run_length {

   public  String compress(String str) {
    StringBuffer fin = new StringBuffer();
    StringBuffer dest = new StringBuffer();
    
    for (int i = 0; i < str.length(); i++) {
        int count = 1;
        while (i + 1 < str.length()&& str.charAt(i) == str.charAt(i + 1)) {//هنا بيشوف الحرفين اللي جنب بعض لو شبه بعض
            count++;
            i++;
        } 
        dest.append("(");
        fin.append(str.charAt(i));
        dest.append(str.charAt(i));
        dest.append(",");
        fin.append(count);
        dest.append(count);
    dest.append(")");
    }

       System.out.println(dest.toString());    
    
    return dest.toString();
}

    public String decompress(String str){
    StringBuffer bfr_b = new StringBuffer();
//    String bfr = str;
    StringBuffer afr = new StringBuffer();
    List<String> chars = new ArrayList<>();
    List<Integer> count = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            String match= ""+str.charAt(i);
            String op ="(";
            String cl =")";
            String com =",";

//            if (!match.equals(op) || !match.equals(cl)|| !match.equals(com)) {
            if (!match.equals(op) ) {
            if (!match.equals(cl) ) {
            if (!match.equals(com) ) {
                
                bfr_b.append(match);
                
                System.out.println(match);
            }}}
          
        }
    String bfr  = bfr_b.toString();
    ////////////////////////////////////////////////////////////////////////////////////////////////////
        for (int i = 0; i < bfr.length(); i+=2) {
          String c =""+ bfr.charAt(i);
            chars.add(c);
//            System.out.println(c);
        }
        for (int i = 1; i <= bfr.length(); i+=2) {
            int c = Integer.parseInt(""+bfr.charAt(i));
            count.add(c);
//            System.out.println(c);
        }
        
        for (int i = 0; i < count.size(); i++) {
           int count_each = count.get(i);
            for (int j = 0; j < count_each; j++) {
                afr.append(chars.get(i));
            }
        }
    
    
    return  afr.toString();
    
    
    }
    public static void main(String[] args) {
        // TODO code application logic here
        run_length rl = new run_length();
         String example = "aaaaabbbbbcccccr";
         String res_comp = rl.compress(example);
         
//    System.out.println(rl.compress(example));
    System.out.println(rl.decompress(res_comp));
    }
    
}
