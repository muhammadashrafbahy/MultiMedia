/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compression;


import java.util.ArrayList;

 
public class arth_comp {
 public  void sortString(ArrayList<String> chara, ArrayList<Double> prob) {
        for (int i = 0; i < chara.size(); i++) {//الحرف الحالي
            String s = "";
            s += chara.get(i);
            for (int j = i + 1; j < chara.size(); j++) {//الحرف اللي بعده
                if (s.compareTo(chara.get(j).toString()) > 0) {//لو الحرف الحالي اكبر من اللي بعده 
                    s = "";
                    s += chara.get(j);
                    
                    chara.set(j, chara.get(i));//هبدل ما بينهم
                    chara.set(i, s);
                    double d = prob.get(j);
                    prob.set(j, prob.get(i));
                    prob.set(i, d);
                }
            }
        }
    }
    
     
    public  String compression(String symbols) {
        String temp1="";
        String temp2="";
        double size=0;
        double counter=0;
        boolean key; 
        ArrayList<Double> probability = new ArrayList<Double>();
        ArrayList<String> symbol = new ArrayList<String>();
        
        for(int i=0; i<symbols.length();i++) {
            temp1+=symbols.charAt(i);
            key=symbol.contains(temp1);//الحرف ده موجود ولا لا في اللي حسبتلهم الاحتمالية
            if(key){}
            else{//لو مش موجود 
                symbol.add(temp1);//ضيفه من اللي اتحسبوا
                for(int j=0;j<symbols.length();j++) {
                    temp2+=symbols.charAt(j);
                    if(temp1.contains(temp2)) counter++;//ديه اللي بتحسب الاحتمالية
                    temp2="";
                }
                size=(counter/symbols.length());
                probability.add(size);
            }
            counter=0;
            temp1="";
        }
        sortString(symbol, probability);//عمل ترتيب ما بينهم
        double temp=0;
        ArrayList<Double> comulitive = new ArrayList<Double>();
        for(int i=0; i<probability.size(); i++) {//بيقسم اول لوا و هاي لكل حرف لاول جولة
            temp+=probability.get(i);
            if(i==probability.size()-1) {
                comulitive.add(1.0);
           }
            else comulitive.add(temp);
             System.out.println(temp);
        }
        ArrayList<Double> com = new ArrayList<Double>();
        com.addAll(comulitive);
        double L=0;
        double U=0;
        for (int i = 0; i < symbols.length(); i++) {
            String str = "";
            str += symbols.charAt(i);
            int index = symbol.indexOf(str);
//            System.out.println(index);
            if (index == 0) {
            } else {
                    L = comulitive.get(index - 1);
            }
            U = comulitive.get(index);
            for (int k = 0; k < symbol.size(); k++) {
                    comulitive.set(k, L + (U - L) * com.get(k));
            }
        }
        double result=(L+U)/2;
////        System.out.println(result);
//        System.out.println(L);
//        System.out.println(U);
        
        String upper  = ""+U;
        String low  = ""+L;
String res = "low --->"+low+"  upper --->"+upper;
        return res;
    }
    public static void main(String[] args) {
   arth_comp  ac = new arth_comp();
        System.out.println(ac.compression("BACA"));
    
    }
}