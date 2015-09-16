
package com.gao.text;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;

public class MainTextProcessingNew {
    static {
        HashMap<String, String> mLeiaFaceMap = new HashMap<String, String>();
        mLeiaFaceMap.put("正常", "$ emo = leiabase");
        mLeiaFaceMap.put("吐槽", "$ emo = sarcasm");
        mLeiaFaceMap.put("大笑", "$ emo = laugh");
        mLeiaFaceMap.put("伤心", "$ emo = sad");
        mLeiaFaceMap.put("大哭", "$ emo = cry");
        mLeiaFaceMap.put("惊恐", "$ emo = shock");
        mLeiaFaceMap.put("无奈", "$ emo = awkward");
        mLeiaFaceMap.put("害羞", "$ emo = shy");
        mLeiaFaceMap.put("流汗", "$ emo = sweat");
        mLeiaFaceMap.put("疑问", "$ emo = doubt");
        mLeiaFaceMap.put("惊讶", "$ emo = surprise");

        HashMap<String, String> mLockeFaceMap = new HashMap<String, String>();
        mLockeFaceMap.put("正常", "show locke");
        mLockeFaceMap.put("大笑1", "show locke laugh");
        mLockeFaceMap.put("大笑2", "show locke laugh2");
        mLockeFaceMap.put("腹黑1", "show locke scheming");
        mLockeFaceMap.put("腹黑2", "show locke scheming2");
        mLockeFaceMap.put("惊讶", "show locke surprise");
        mLockeFaceMap.put("无奈", "show locke awkward");
        mLockeFaceMap.put("认真", "show locke serious");
        mLockeFaceMap.put("伤心", "show locke sad");
        mLockeFaceMap.put("生气", "show locke angry");

        HashMap<String, String> mChristFaceMap = new HashMap<String, String>();
        mChristFaceMap.put("正常", "show christ");
        mChristFaceMap.put("无奈", "show christ awkward");
        mChristFaceMap.put("惊讶", "show christ surprise");
        mChristFaceMap.put("生气", "show christ angry");
        mChristFaceMap.put("伤心", "show christ sad");
        mChristFaceMap.put("认真", "show christ serious");
        mChristFaceMap.put("大笑", "show christ laugh");
        
        HashMap<String, String> mChrist2FaceMap = new HashMap<String, String>();
        mChrist2FaceMap.put("正常", "show christ2");
        mChrist2FaceMap.put("无奈", "show christ awkward2");
        mChrist2FaceMap.put("惊讶", "show christ surprise2");
        mChrist2FaceMap.put("生气", "show christ angry2");
        mChrist2FaceMap.put("伤心", "show christ sad2");
        mChrist2FaceMap.put("认真", "show christ serious2");
        mChrist2FaceMap.put("大笑", "show christ laugh2");

        HashMap<String, String> mLeoFaceMap = new HashMap<String, String>();
        mLeoFaceMap.put("正常", "show locke");
        mLeoFaceMap.put("无奈", "show locke awkward");
        mLeoFaceMap.put("大笑1", "show locke laugh");
        mLeoFaceMap.put("大笑2", "show locke laugh2");
        mLeoFaceMap.put("惊讶", "show locke surprise");
        mLeoFaceMap.put("认真", "show locke serious");
        mLeoFaceMap.put("生气", "show locke angry");

        HashMap<String, String> mJesseFaceMap = new HashMap<String, String>();
        mJesseFaceMap.put("正常", "show jesse");
        mJesseFaceMap.put("疑问", "show jesse doubt");
        mJesseFaceMap.put("微笑", "show jesse smile");
        mJesseFaceMap.put("害羞", "show jesse shy");
        mJesseFaceMap.put("闭眼", "show jesse closeeyes");
        mJesseFaceMap.put("惊讶", "show jesse surprise");
        mJesseFaceMap.put("生气", "show jesse angry");
        
        HashMap<String, String> mJesse2FaceMap = new HashMap<String, String>();
        mJesse2FaceMap.put("正常", "show jesse2");
        mJesse2FaceMap.put("疑问", "show jesse doubt2");
        mJesse2FaceMap.put("微笑", "show jesse smile2");
        mJesse2FaceMap.put("害羞", "show jesse shy2");
        mJesse2FaceMap.put("闭眼", "show jesse closeeyes2");
        mJesse2FaceMap.put("惊讶", "show jesse surprise2");
        mJesse2FaceMap.put("生气", "show jesse angry2");
        
        // FIXME 
        // 泽希3      show jesse 3


        HashMap<String, String> mAlisonFaceMap = new HashMap<String, String>();
        mAlisonFaceMap.put("正常/微笑", "show alison");
        mAlisonFaceMap.put("生气", "show alison angry");
        mAlisonFaceMap.put("大笑", "show alison laugh");
        mAlisonFaceMap.put("惊讶", "show alison surprise");

        HashMap<String, String> mDinaFaceMap = new HashMap<String, String>();
        mDinaFaceMap.put("正常/微笑", "show dina");
        mDinaFaceMap.put("大笑", "show dina laugh");

        HashMap<String, String> mSophieFaceMap = new HashMap<String, String>();
        mSophieFaceMap.put("正常/微笑", "show sophie");
        mSophieFaceMap.put("大笑", "show sophie laugh");

        HashMap<String, String> mJudeFaceMap = new HashMap<String, String>();
        mJudeFaceMap.put("正常/微笑", "show jude");
        mJudeFaceMap.put("大笑", "show jude smile");
        mJudeFaceMap.put("奸笑", "show jude laugh");
        mJudeFaceMap.put("狂笑", "show jude sinister");
        mJudeFaceMap.put("鄙视", "show jude despise");
        mJudeFaceMap.put("面瘫/认真", "show jude serious");
        mJudeFaceMap.put("凶恶", "show jude viciously");
        mJudeFaceMap.put("凶恶2", "show jude viciously2");

        HashMap<String, String> mKiluFaceMap = new HashMap<String, String>();
        mKiluFaceMap.put("正常", "show kilu");
        mKiluFaceMap.put("笑1", "show kilu laugh");
        mKiluFaceMap.put("笑2", "shou kilu laugh2");
        mKiluFaceMap.put("担忧", "show kilu care");
        
        HashMap<String, String> mSceneMap = new HashMap<String, String>();
        mSceneMap.put("教室", "scene classroom");
        mSceneMap.put("宿舍早", "scene dormitory");
        mSceneMap.put("宿舍深夜", "scene dormitory3");
        mSceneMap.put("宿舍晚", "scene dormitory2");
        mSceneMap.put("豪宅", "scene mansion");
        mSceneMap.put("教堂", "scene church");
        mSceneMap.put("顶楼", "scene top");
        mSceneMap.put("水底", "scene lake");
        mSceneMap.put("领地傍晚", "scene altar2");
        mSceneMap.put("领地早", "scene altar");
        mSceneMap.put("教堂后院", "scene church2");
        mSceneMap.put("教堂回廊", "scene church3");
        mSceneMap.put("咖啡屋", "scene cafe");
        mSceneMap.put("摩天轮内早", "scene fwheel");
        mSceneMap.put("摩天轮内晚", "scene fwheel2");
        mSceneMap.put("魔法屋", "scene magichouse");
        mSceneMap.put("墓园", "scene cemetery");
        mSceneMap.put("琴房", "scene pianoroom");
        mSceneMap.put("实验室", "scene lab");
        mSceneMap.put("图书馆", "scene library");
        mSceneMap.put("舞会", "scene hall");
        mSceneMap.put("小黑屋", "scene darkhouse");
        mSceneMap.put("校内花园早", "scene garden");
        mSceneMap.put("校内花园晚", "scene garden2");
        mSceneMap.put("地下室", "scene basement");
        mSceneMap.put("学校正门", "scene gate");
        mSceneMap.put("学生会", "scene office");
        mSceneMap.put("医疗室", "scene infirmary");
        mSceneMap.put("游乐园早", "scene apark");
        mSceneMap.put("游乐园晚", "scene apark2");
        mSceneMap.put("杂货店", "scene shop");
        mSceneMap.put("中心公园", "scene cpark");
        mSceneMap.put("中心公园晚", "scene cpark2");
    }

    private static final String[] mActorCN = {
            "蕾雅", "洛库", "克里斯特", "黎欧", "泽希", "艾莉森", "杜娜", "纱妃", "教皇/裘德", "奇路"
    };
    private static final String[] mActorEN = {
            "leia", "locke", "christ", "leo", "jesse", "alison", "dina", "sophie", "jude", "kilu"
    };

    private static List<String> mBeforeList = new ArrayList<String>();
    private static List<String> mAfterList = new ArrayList<String>();
    private static FileUtils mFileUtils = new FileUtils();

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
        try {
            mBeforeList = mFileUtils.readLines(file, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String text : mBeforeList) {
            System.out.println(text);
        }
        for (String text : mBeforeList) {
            if (text.startsWith("时间：")) {
                String decodeText = decodeTime(text);
                if (null != decodeText) {
                    mAfterList.add(decodeText);
                }
            }
            
            /*

            
            if (text.contains("时间：")) {
                mAfterList.add("#label dayxx:");
            } else if (text.contains("场景：")) {
                mAfterList.add("#scene garden");
                mAfterList.add("#with fade");
                for (int i = 1; i < mActorEN.length; i++) {
                    mAfterList.addAll(getActorFeeling(mActorEN[i], ""));
                }
            } else if (needComment(text)) {
                System.out.println("needComment text: " + text);
                mAfterList.add("#" + text);
            } else if (containFeeling(text)) {
                System.out.println("containFeeling text: " + text);
                String actor = getActor(text);
                int index = getActorFeelingIndex(actor);
                String feeling = getFeeling(text);

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
        */}

        for (String text : mAfterList) {
            System.out.println(text);
        }
        try {
            mFileUtils.writeLines(new File("D:\\after.txt"), mAfterList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 解析日期  时间：8月1日
     * @param text
     */
    private static String decodeTime(String text) {
        System.out.println("text: " + text);
        if (null == text || text.equals("")) {
            return null;
        }
        String time = text.split("：")[1];
        System.out.println("time: " + time);
        int monthPos = time.indexOf("月");
        int dayPos = time.indexOf("日");
        String month = time.substring(0, monthPos);
        String day = time.substring(monthPos + 1, dayPos);
        int monthInt = Integer.parseInt(month);
        int dayInt = Integer.parseInt(day);
        System.out.println("monthInt: " + monthInt);
        System.out.println("dayInt: " + dayInt);
        StringBuilder builder = new StringBuilder("label day");
        switch (monthInt) {
            case 8:
                builder.append(day);
                break;
            case 9:
                builder.append(31 + dayInt);
                break;
            case 10:
                builder.append(61 + dayInt);
                break;
            case 11:
                builder.append(92 + dayInt);
                break;
            case 12:
                builder.append(122 + day);
                break;
            default:
                break;
        }
        builder.append(":");
        builder.append("#" + time);
        return builder.toString();
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

    private static boolean isMainActor(String text) {
        return text.contains(mActorEN[0]);
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
