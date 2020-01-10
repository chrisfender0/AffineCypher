/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affinecipher;


import java.util.*;

/**
 *
 * @author chrisjuste
 * 
 * IMPORTANT: 
 * 
 * To change from encrypting to decrypting 
 * change the method used in bruteForce() to either 
 * encryptArray(a,b) or decryptArray(a,b)
 * 
 */
public class AffineCipher {
    
    //static string for alphabet 
    static String alpha = "abcdefghijklmnopqrstuvwxyz";
    //coprimes of 26 
    static int[] coprimes = {1,3,5,7,9,11,15,17,19,21,23,25};
    //modular inverse of each coprime 
    static int[] invMod = {1,9,21,15,3,19,7,23,11,5,17,25};
    //arraylist that will hold the original value 
    ArrayList cipherArray = new ArrayList();
    //object array that will hold temp values 
    Object[] cipherArray2;
    //int t is just a counter 
    int t;
    
    
    //This method will set appropriate char values, like a=0, b=1 etc 
    public void indexCipher(String str){
        //iterate through length of given string 
        for(int i=0; i<str.length();i++){
            //iterate through alphabet to find a match 
            for(int j=0;j<alpha.length();j++){
                //if charAt[i] is found in alphabet add the numbered item
                if(str.charAt(i)==alpha.charAt(j)){
                    cipherArray.add(j);
                }
            }
        }
        //print the given values 
        System.out.println(cipherArray.toString());
        //set temp array to arrayList value 
        cipherArray2 = cipherArray.toArray();
        translate(cipherArray2);
    }
    //This method will translate the array into appropriate characters 
    public void translate(Object[] obj){
        StringBuilder temp = new StringBuilder();
        //iterate through all array elements 
        for(int i=0;i<obj.length;i++){
            int x = (int) obj[i];
            temp.append(alpha.charAt(x));
        }
        System.out.println(temp.toString());
    }
    
    //This method allows us to encrypt a given sentence with the values a and b in f(x)=ax+b mod 26
    public void encryptArray(int a, int b){
        Object[] temp = cipherArray.toArray();
        System.out.println(Arrays.toString(temp));
        for(int i=0;i<temp.length;i++){
            int x = (int) temp[i];
            System.out.println(temp[i]);
            temp[i] = (coprimes[a]*x+b)%26;
            
            
        }
        System.out.println(Arrays.toString(temp));
        translate(temp);
    }
    
    //This method decrypts the given sentence and prints every possible value 
    public void decryptArray(int a, int b){
        Object[] temp = cipherArray.toArray();
        System.out.println(Arrays.toString(temp));
        //iterate through each element in the array 
        for(int i=0;i<temp.length;i++){
            //set a temp variable = to element at [i]
            int x = (int) temp[i];
            x = ((x-b)*invMod[a])%26; //item at value - value B times modular inverse of A
            if(x<0){ //if the item is negative we need to return a positive mod value 
                x = 26 - (x * (-1));
                temp[i] = x;
            } else {
                temp[i] = x;
            }
        }
        System.out.println(Arrays.toString(temp));
        translate(temp);
    }
    
    //This method iterates through all possible values of A and iterates through all possible values of B
    public void bruteForce(Object[] obj){
        for(int a=0;a<coprimes.length;++a){
            for(int b=1;b<26;++b){
                System.out.println("\n*****");
                //CHANGE ME TO DECRYPT OR ENCRYPT 
                encryptArray(a,b);
                System.out.println("a= "+ coprimes[a] + " b= " + b);
                System.out.println("-------");
                t++;
            }
        }
    }
    
    public void runMe(){
        //rmirwpe = testing; a=9, b=2 
        String a = "pooppooppoop";
        indexCipher(a.toLowerCase());
        bruteForce(cipherArray2);
    }
    
    public static void main(String[] args) {
        AffineCipher test = new AffineCipher();
        test.runMe();
        System.out.println("printed " + test.t + " times.");
    }
    
}
