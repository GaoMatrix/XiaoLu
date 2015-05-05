package com.gao.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {
        containPasserby("菲尔：呐，我才不怕！汉克也是，对吧？");
    }
    
    private static boolean containPasserby(String text) {
        if (null == text || text.equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile( "^\\w*:|：");
        String[] results = pattern.split(text);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}
