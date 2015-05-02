
package com.gao.text;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

public class MainTextProcessing {
    private static final String[] mFeelingsCN = {
            "【微笑】", "【惊讶】", "【大笑】", "【伤心】", "【苦笑】"
    };
    private static final String[] mFeelingsEN = {
            "smile", "surprise", "laugh", "sad", "awkward"
    };

    private static final String[] mActorCN = {
            "蕾雅：", "克里斯特："
    };
    private static final String[] mActorEN = {
            "leia", "crister"
    };

    private static List<String> mBeforeList = new ArrayList<String>();
    private static List<String> mAfterList = new ArrayList<String>();
    private static String mMainActorFeeling = "";
    private static String mNormalFeeling = "";

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
        System.out.println("------------------before------------------");

        for (String text : mBeforeList) {
            
            System.out.println("------------------1------------------");
            if (text.contains("时间：")) {
                mAfterList.add("#label day51:");
            }
            System.out.println("------------------2------------------");
            if (text.contains("场景：")) {
                System.out.println("------------------3------------------");
                mAfterList.add("    #scene garden");
                mAfterList.add("    #with fade");
                mAfterList.add("    #play music \"music\\3.bgm.mp3\" fadein 1");
                mAfterList.addAll(getActorFeeling("crister", ""));
                mNormalFeeling = mFeelingsEN[0]; // smile is default feeling.
            }

            if (containFeeling(text)) {
                System.out.println("------------------4------------------");
                String actor = getActor(text);
                String feeling = getFeeling(text);
                if (isMainActor(actor)) {
                    if (mMainActorFeeling.equals("") || !feeling.equals(mMainActorFeeling)) {
                        mMainActorFeeling = feeling;
                        mAfterList.add("    $ emo = " + mMainActorFeeling);
                    }
                } else {
                    if (!feeling.equals(mNormalFeeling)) {
                        mNormalFeeling = feeling;
                        mAfterList.addAll(getActorFeeling(actor, mNormalFeeling));
                    }
                }

                String sayContent = getSayContent(text);
                sayContent =  sayContent.replace("•", ".");
                mAfterList.add("    " + actor + " \"" + sayContent + "\"");
            }

        }

        System.out.println("------------------5-ssss-----------------");
        for (String text : mAfterList) {
            System.out.println("------------------511------------------");
            System.out.println(text);
        }
        try {
            System.out.println("------------------6------------------");
            fileUtils.writeLines(new File("D:\\after.txt"), mAfterList);
        } catch (IOException e) {
            System.out.println("------------------7------------------");
            e.printStackTrace();
        }

    }

    private static String formatDot(String text) {
        text.replace("•", ".");
        return text;
    }

    private static String getSayContent(String text) {
        int index = text.indexOf("：");
        return text.substring(index + 1);
    }

    private static String getActor(String text) {
        for (int i = 0; i < mActorCN.length; i++) {
            if (text.contains(mActorCN[i])) {
                return mActorEN[i];
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
