package com.gao.text;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    private static List<String> mBeforeList = new ArrayList<String>();

    public static void main(String[] args) {
        //containPasserby("菲尔：呐，我才不怕！汉克也是，对吧？");
        

        File file = new File("D:/WorkSpace/XiaoLu/text.txt");
        FileUtils fileUtils = new FileUtils();
        try {
            mBeforeList = fileUtils.readLines(file, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String text : mBeforeList) {
            System.out.println(text);
        }
    
    }
    
/*    private static boolean containPasserby(String text) {
        if (null == text || text.equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile( "^\\w*:|：");
        String[] results = pattern.split(text);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }*/
}
