
package com.gao.text;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;

public class MainTextProcessing {
    private static final String[] mFeelingsCN = {
            "【微笑", "【惊讶", "【大笑", "【伤心", "【苦笑", "【疑问", "【无奈", "【认真", "【流汗"
    };
    /*
     * private static final String[] mFeelingsCN = { "【微笑】", "【微笑表情】", "【惊讶】",
     * " 【惊讶表情】", "【大笑】", " 【大笑表情】", "【伤心】", " 【伤心表情】", "【苦笑】", " 【苦笑表情】" };
     */
    private static final String[] mFeelingsEN = {
            "smile", "surprise", "laugh", "sad", "awkward", "doubt", "awkward", "serious", "sweat"
    };

    private static final String[] mActorCN = {
            "蕾雅|蕾雅•艾菲利斯", "克里斯特", "泽希", "流", "黎欧", "洛库", "纱利雅", "老板娘/杜娜", "杜娜"
    };
    private static final String[] mActorEN = {
            "leia", "crister", "jesse", "liu", "leo", "locke", "saria", "saria", "saria"
    };

    private static final String[] mActorFeeling = {
            "", "", "", "", "", "", "", "", ""
    };

    private static List<String> mBeforeList = new ArrayList<String>();
    private static List<String> mAfterList = new ArrayList<String>();

    public static void main(String[] args) {
        File file = null;
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("选择一个目录");
        jfc.setDialogType(JFileChooser.OPEN_DIALOG);
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int res = jfc.showOpenDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            file = jfc.getSelectedFile();
            System.out.println(file.getAbsolutePath());
        }

        if (file == null) {
            return;
        }
        FileUtils fileUtils = new FileUtils();
        try {
            mBeforeList = fileUtils.readLines(file, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String text : mBeforeList) {
            System.out.println(text);
        }

        for (String text : mBeforeList) {

            text = handleDot(text);

            if (text.contains("时间：")) {
                mAfterList.add("#label dayxx:");
            } else if (text.contains("场景：")) {
                mAfterList.add("#scene garden");
                mAfterList.add("#with fade");
                for (int i = 1; i < mActorEN.length; i++) {
                    mAfterList.addAll(getActorFeeling(mActorEN[i], ""));
                }
                for (int i = 1; i < mActorFeeling.length; i++) {
                    mActorFeeling[i] = mFeelingsEN[0]; // smile is default
                                                       // feeling.
                }
            } else if (needComment(text)) {
                System.out.println("needComment text: " + text);
                mAfterList.add("#" + text);
            } else if (containFeeling(text)) {
                System.out.println("containFeeling text: " + text);
                String actor = getActor(text);
                int index = getActorFeelingIndex(actor);
                String feeling = getFeeling(text);
                if (isMainActor(actor)) {
                    if (mActorFeeling[index].equals("") || !feeling.equals(mActorFeeling[index])) {
                        mActorFeeling[index] = feeling;
                        mAfterList.add("    $ emo = " + mActorFeeling[index]);
                    }
                } else {
                    if (!feeling.equals(mActorFeeling[index])) {
                        mActorFeeling[index] = feeling;
                        mAfterList.addAll(getActorFeeling(actor, mActorFeeling[index]));
                    }
                }

                String sayContent = getSayContent(text);
                mAfterList.add("    " + actor + " \"" + sayContent + "\"");
            } else if (containPasserby(text)) {
                System.out.println("containPasserby  text: " + text);
                Pattern pattern = Pattern.compile( "^\\w*:|：");
                String[] results = pattern.split(text);
                if (results.length == 2) {
                    mAfterList.add("    \"" + results[0]  + "\" " + "\"" + results[1] + "\"");
                } else {
                    //致蕾雅：
                    mAfterList.add("    \"" + results[0]  + "\" ");
                }
            } else {
                mAfterList.add("#" + text);
            }
        }

        for (String text : mAfterList) {
            System.out.println(text);
        }
        try {
            fileUtils.writeLines(new File("D:\\after.txt"), mAfterList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 
     * @param text
     * @return
     */
    private static String handleDot(String text) {
        
        return null;
    }

    private static boolean needComment(String text) {
        Pattern pattern = Pattern.compile("^选择[a-zA-Z](：|:)");
        Matcher matcher = pattern.matcher(text);
        return matcher.find() || text.startsWith("BGM") || text.startsWith("自动剧情")
                || text.startsWith("条件剧情") || text.startsWith("场景跳转至");
    }

    /**
     * Contain passerby not actor
     * 菲尔：呐，我才不怕！汉克也是，对吧？
     * (4空格)"菲尔" "呐，我才不怕！汉克也是，对吧？"
     * @param text
     * @return
     */
    private static boolean containPasserby(String text) {
        if (null == text || text.equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile( "^(\\w|\\W)*(：|:)");
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    private static int getActorFeelingIndex(String actor) {
        for (int i = 0; i < mActorCN.length; i++) {
            if (actor.equals(mActorEN[i])) {
                return i;
            }
        }
        return -1;
    }

    private static String getSayContent(String text) {
        int index = text.indexOf("：");
        return text.substring(index + 1);
    }

    private static String getActor(String text) {
        for (int i = 0; i < mActorCN.length; i++) {
            String actor = mActorCN[i];
            String[] actorNames = actor.split("\\|");
            for (int j = 0; j < actorNames.length; j++) {
                Pattern pattern = Pattern.compile(actorNames[j] + "([\\s]*:|[\\s]*：)");
                Matcher matcher = pattern.matcher(text);
                if (matcher.find()) {
                    return mActorEN[i];
                }
            }
        }
        return "";
    }

    private static boolean containFeeling(String text) {
        for (int i = 0; i < mFeelingsCN.length; i++) {
            if (text.contains(mFeelingsCN[i])) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMainActor(String text) {
        return text.contains(mActorEN[0]);
    }

    private static String getFeeling(String text) {
        for (int i = 0; i < mFeelingsCN.length; i++) {
            if (text.contains(mFeelingsCN[i])) {
                return mFeelingsEN[i];
            }
        }
        return "";
    }

    /**
     * @param actor
     * @param feeling
     * @return show crister sad with dissolve
     */
    private static List<String> getActorFeeling(String actor, String feeling) {
        List<String> list = new ArrayList<String>();
        list.add("    show " + actor + " " + feeling);
        list.add("    with dissolve");
        return list;
    }
}
