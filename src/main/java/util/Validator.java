package util;

public class Validator {

    public Validator() {}

    public static boolean isInteger(String str){
        for(char c: str.toCharArray()){
            if(c == '-') continue;
            if(!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }

    public static boolean isOnlyLetters(String str){
        for(char c: str.toCharArray()){
            if(c == ' ') continue;
            if(!Character.isLetter(c)){
                return false;
            }
        }
        return true;
    }
}
