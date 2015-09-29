
package com.gao.text;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;

public class MainTextProcessingNew {
    /**蕾雅*/
    static HashMap<String, String> mLeiaFaceMap = new HashMap<String, String>();
    /**洛库*/
    static HashMap<String, String> mLockeFaceMap = new HashMap<String, String>();
    /**克里斯特*/
    static HashMap<String, String> mChristFaceMap = new HashMap<String, String>();
    /**克里斯特2*/
    static HashMap<String, String> mChrist2FaceMap = new HashMap<String, String>();
    /**黎欧*/
    static HashMap<String, String> mLeoFaceMap = new HashMap<String, String>();
    /**泽希*/
    static HashMap<String, String> mJesseFaceMap = new HashMap<String, String>();
    /**泽希2*/
    static HashMap<String, String> mJesse2FaceMap = new HashMap<String, String>();
    /**泽希3*/
    static HashMap<String, String> mJesse3FaceMap = new HashMap<String, String>();
    /**艾莉森*/
    static HashMap<String, String> mAlisonFaceMap = new HashMap<String, String>();
    /**杜娜*/
    static HashMap<String, String> mDinaFaceMap = new HashMap<String, String>();
    /**纱妃*/
    static HashMap<String, String> mSophieFaceMap = new HashMap<String, String>();
    /**教皇/裘德*/
    static HashMap<String, String> mJudeFaceMap = new HashMap<String, String>();
    /**奇路*/
    static HashMap<String, String> mKiluFaceMap = new HashMap<String, String>();
    /**场景中英文对照*/
    static HashMap<String, String> mSceneMap = new HashMap<String, String>();
    /**演员中英文对照*/
    static HashMap<String, String> mActorMap = new HashMap<String, String>();
    static {
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
        mLeiaFaceMap.put("生气", "$ emo = angry");

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

        mChristFaceMap.put("正常", "show christ");
        mChristFaceMap.put("无奈", "show christ awkward");
        mChristFaceMap.put("惊讶", "show christ surprise");
        mChristFaceMap.put("生气", "show christ angry");
        mChristFaceMap.put("伤心", "show christ sad");
        mChristFaceMap.put("认真", "show christ serious");
        mChristFaceMap.put("大笑", "show christ laugh");

        mChrist2FaceMap.put("正常", "show christ 2");
        mChrist2FaceMap.put("无奈", "show christ awkward2");
        mChrist2FaceMap.put("惊讶", "show christ surprise2");
        mChrist2FaceMap.put("生气", "show christ angry2");
        mChrist2FaceMap.put("伤心", "show christ sad2");
        mChrist2FaceMap.put("认真", "show christ serious2");
        mChrist2FaceMap.put("大笑", "show christ laugh2");

        mLeoFaceMap.put("正常", "show locke");
        mLeoFaceMap.put("无奈", "show locke awkward");
        mLeoFaceMap.put("大笑1", "show locke laugh");
        mLeoFaceMap.put("大笑2", "show locke laugh2");
        mLeoFaceMap.put("惊讶", "show locke surprise");
        mLeoFaceMap.put("认真", "show locke serious");
        mLeoFaceMap.put("生气", "show locke angry");

        mJesseFaceMap.put("正常", "show jesse");
        mJesseFaceMap.put("疑问", "show jesse doubt");
        mJesseFaceMap.put("微笑", "show jesse smile");
        mJesseFaceMap.put("害羞", "show jesse shy");
        mJesseFaceMap.put("闭眼", "show jesse closeeyes");
        mJesseFaceMap.put("惊讶", "show jesse surprise");
        mJesseFaceMap.put("生气", "show jesse angry");

        mJesse2FaceMap.put("正常", "show jesse 2");
        mJesse2FaceMap.put("疑问", "show jesse doubt2");
        mJesse2FaceMap.put("微笑", "show jesse smile2");
        mJesse2FaceMap.put("害羞", "show jesse shy2");
        mJesse2FaceMap.put("闭眼", "show jesse closeeyes2");
        mJesse2FaceMap.put("惊讶", "show jesse surprise2");
        mJesse2FaceMap.put("生气", "show jesse angry2");

        // FIXME
        // 泽希3 show jesse 3

        mAlisonFaceMap.put("正常", "show alison");
        mAlisonFaceMap.put("微笑", "show alison");
        mAlisonFaceMap.put("生气", "show alison angry");
        mAlisonFaceMap.put("大笑", "show alison laugh");
        mAlisonFaceMap.put("惊讶", "show alison surprise");
        mAlisonFaceMap.put("担心", "show alison worry");
        mAlisonFaceMap.put("伤心", "show alison sad");

        mDinaFaceMap.put("正常", "show dina");
        mDinaFaceMap.put("微笑", "show dina");
        mDinaFaceMap.put("大笑", "show dina laugh");

        mSophieFaceMap.put("正常", "show sophie");
        mSophieFaceMap.put("微笑", "show sophie");
        mSophieFaceMap.put("大笑", "show sophie laugh");

        mJudeFaceMap.put("正常", "show jude");
        mJudeFaceMap.put("微笑", "show jude");
        mJudeFaceMap.put("大笑", "show jude smile");
        mJudeFaceMap.put("奸笑", "show jude laugh");
        mJudeFaceMap.put("狂笑", "show jude sinister");
        mJudeFaceMap.put("鄙视", "show jude despise");
        mJudeFaceMap.put("面瘫", "show jude serious");
        mJudeFaceMap.put("认真", "show jude serious");
        mJudeFaceMap.put("凶恶", "show jude viciously");
        mJudeFaceMap.put("凶恶2", "show jude viciously2");

        mKiluFaceMap.put("正常", "show kilu");
        mKiluFaceMap.put("笑1", "show kilu laugh");
        mKiluFaceMap.put("笑2", "shou kilu laugh2");
        mKiluFaceMap.put("担忧", "show kilu care");

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
        mSceneMap.put("校园花园早", "scene garden");
        mSceneMap.put("校内花园晚", "scene garden2");
        mSceneMap.put("校园花园晚", "scene garden2");
        mSceneMap.put("地下室", "scene basement");
        mSceneMap.put("学校正门", "scene gate");
        mSceneMap.put("学生会", "scene office");
        mSceneMap.put("医疗室", "scene infirmary");
        mSceneMap.put("游乐园早", "scene apark");
        mSceneMap.put("游乐园晚", "scene apark2");
        mSceneMap.put("杂货店", "scene shop");
        mSceneMap.put("中心公园", "scene cpark");
        mSceneMap.put("中心公园晚", "scene cpark2");

        mActorMap.put("蕾雅", "leia");
        mActorMap.put("洛库", "locke");
        mActorMap.put("克里斯特", "christ");
        mActorMap.put("黎欧", "leo");
        mActorMap.put("泽希", "jesse");
        mActorMap.put("艾莉森", "alison");
        mActorMap.put("杜娜", "dina");
        mActorMap.put("纱妃", "sophie");
        mActorMap.put("教皇", "jude");
        mActorMap.put("裘德", "jude");
        mActorMap.put("奇路", "kilu");
    }

    private static final String[] mActorCN = {
            "蕾雅", "洛库", "克里斯特", "黎欧", "泽希", "艾莉森", "杜娜", "纱妃", "教皇/裘德", "奇路"
    };
    private static final String[] mActorEN = {
            "leia", "locke", "christ", "leo", "jesse", "alison", "dina", "sophie", "jude", "kilu"
    };

    private static final String SCENE_ENDING = "with fade";
    private static final String FACE_NORMAL_ENDING = "with dissolve";
    private static final String FOUR_SPACE = "    ";

    private static List<String> mBeforeList = new ArrayList<String>();
    private static List<String> mAfterList = new ArrayList<String>();
    private static FileUtils mFileUtils = new FileUtils();
    private static HashMap<String, String> mActorFaceMap = new HashMap<String, String>();

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

        for (int i = 0; i < mBeforeList.size(); i++) {
            String text = mBeforeList.get(i).trim();
            String spaceStr = getSpaceStr(text);

            // 处理点好：234变…，567变……，8910变………，11以上变…………1个不变
            /*if (text.contains("·")) {
                text = formatDot(text);
            }*/

            // 处理→
            if (text.contains("→")) {
                text = replaseArrow2Space(text);
            }
            String nextText = "";
            if ((i + 1) != mBeforeList.size()) {
                nextText = mBeforeList.get(i + 1).trim();
                if (nextText.contains("→")) {
                    nextText = replaseArrow2Space(nextText);
                }
            }

            // 处理时间
            if (text.startsWith("时间：")) {
                String decodeText = "";
                try {
                    decodeText = decodeTime(text);
                } catch (Exception e) {
                    mAfterList.add(FOUR_SPACE + "#" + text);
                    e.printStackTrace();
                    continue;
                }
                if (null != decodeText) {
                    mAfterList.add(decodeText);
                }
                continue;
            }

            // 场景处理
            if (text.startsWith("○")) {
                String scene = text.substring(1);
                String sceneEn = mSceneMap.get(scene);
                if (null == sceneEn || sceneEn.length() == 0) {
                    mAfterList.add(FOUR_SPACE +  "#" + text);
                } else {
                    mAfterList.add(FOUR_SPACE + mSceneMap.get(scene));
                    mAfterList.add(FOUR_SPACE + SCENE_ENDING);
                }
                continue;
            }

            // 对话处理
            if (text.startsWith("@")) {
                try {
                    System.out.println(text);
                    System.out.println(nextText);
                    int index = text.indexOf("@");
                    String face = text.substring(index + 1);
                    String dialogue = nextText.split("#：|#:")[1];
                    if (nextText.startsWith("#蕾雅#")) {
                        String lastFace = mActorFaceMap.get("蕾雅");
                        if (null == lastFace || !face.equals(lastFace)) {
                            String faceEn = mLeiaFaceMap.get(face);
                            if (null == faceEn || faceEn.length() == 0) {
                                throw new Exception();
                            }
                            mAfterList.add(FOUR_SPACE + mLeiaFaceMap.get(face));
                            mActorFaceMap.put("蕾雅", face);
                        }
                        mAfterList.add(FOUR_SPACE + mActorMap.get("蕾雅") + " \"" + dialogue + "\"");
                    } else if (nextText.startsWith("#洛库#")) {
                        String lastFace = mActorFaceMap.get("洛库");
                        if (null == lastFace || !face.equals(lastFace)) {
                            String faceEn = mLockeFaceMap.get(face);
                            if (null == faceEn || faceEn.length() == 0) {
                                throw new Exception();
                            }
                            mAfterList.add(FOUR_SPACE + mLockeFaceMap.get(face));
                            mAfterList.add(FOUR_SPACE + FACE_NORMAL_ENDING);
                            mActorFaceMap.put("洛库", face);
                        }
                        mAfterList.add(FOUR_SPACE + mActorMap.get("洛库") + " \"" + dialogue + "\"");
                    } else if (nextText.startsWith("#克里斯特#")) {
                        String lastFace = mActorFaceMap.get("克里斯特");
                        if (null == lastFace || !face.equals(lastFace)) {
                            String faceEn = mChristFaceMap.get(face);
                            if (null == faceEn || faceEn.length() == 0) {
                                throw new Exception();
                            }
                            mAfterList.add(FOUR_SPACE + mChristFaceMap.get(face));
                            mAfterList.add(FOUR_SPACE + FACE_NORMAL_ENDING);
                            mActorFaceMap.put("克里斯特", face);
                        }
                        mAfterList.add(FOUR_SPACE + mActorMap.get("克里斯特") + " \"" + dialogue + "\"");
                    } else if (nextText.startsWith("#黎欧#")) {
                        String lastFace = mActorFaceMap.get("黎欧");
                        if (null == lastFace || !face.equals(lastFace)) {
                            String faceEn = mLeoFaceMap.get(face);
                            if (null == faceEn || faceEn.length() == 0) {
                                throw new Exception();
                            }
                            mAfterList.add(FOUR_SPACE + mLeoFaceMap.get(face));
                            mAfterList.add(FOUR_SPACE + FACE_NORMAL_ENDING);
                            mActorFaceMap.put("黎欧", face);
                        }
                        mAfterList.add(FOUR_SPACE + mActorMap.get("黎欧") + " \"" + dialogue + "\"");
                    } else if (nextText.startsWith("#泽希#")) {
                        String lastFace = mActorFaceMap.get("黎欧");
                        if (null == lastFace || !face.equals(lastFace)) {
                            String faceEn = mJesseFaceMap.get(face);
                            if (null == faceEn || faceEn.length() == 0) {
                                throw new Exception();
                            }
                            mAfterList.add(FOUR_SPACE + mJesseFaceMap.get(face));
                            mAfterList.add(FOUR_SPACE + FACE_NORMAL_ENDING);
                            mActorFaceMap.put("泽希", face);
                        }
                        mAfterList.add(FOUR_SPACE + mActorMap.get("泽希") + " \"" + dialogue + "\"");
                    } else if (nextText.startsWith("#艾莉森#")) {
                        String lastFace = mActorFaceMap.get("艾莉森");
                        if (null == lastFace || !face.equals(lastFace)) {
                            mAfterList.add(FOUR_SPACE + mAlisonFaceMap.get(face));
                            mAfterList.add(FOUR_SPACE + FACE_NORMAL_ENDING);
                            mActorFaceMap.put("艾莉森", face);
                        }
                        mAfterList.add(FOUR_SPACE + mActorMap.get("艾莉森") + " \"" + dialogue + "\"");
                    } else if (nextText.startsWith("#杜娜#")) {
                        String lastFace = mActorFaceMap.get("杜娜");
                        if (null == lastFace || !face.equals(lastFace)) {
                            String faceEn = mDinaFaceMap.get(face);
                            if (null == faceEn || faceEn.length() == 0) {
                                throw new Exception();
                            }
                            mAfterList.add(FOUR_SPACE + mDinaFaceMap.get(face));
                            mAfterList.add(FOUR_SPACE + FACE_NORMAL_ENDING);
                            mActorFaceMap.put("杜娜", face);
                        }
                        mAfterList.add(FOUR_SPACE + mActorMap.get("杜娜") + " \"" + dialogue + "\"");
                    } else if (nextText.startsWith("#纱妃#")) {
                        String lastFace = mActorFaceMap.get("纱妃");
                        if (null == lastFace || !face.equals(lastFace)) {
                            String faceEn = mSophieFaceMap.get(face);
                            if (null == faceEn || faceEn.length() == 0) {
                                throw new Exception();
                            }
                            mAfterList.add(FOUR_SPACE + mSophieFaceMap.get(face));
                            mAfterList.add(FOUR_SPACE + FACE_NORMAL_ENDING);
                            mActorFaceMap.put("纱妃", face);
                        }
                        mAfterList.add(FOUR_SPACE + mActorMap.get("纱妃") + " \"" + dialogue + "\"");
                    } else if (nextText.startsWith("#教皇#") || nextText.startsWith("#裘德#")) {
                        String lastFace = mActorFaceMap.get("教皇");
                        if (null == lastFace || !face.equals(lastFace)) {
                            String faceEn = mJudeFaceMap.get(face);
                            if (null == faceEn || faceEn.length() == 0) {
                                throw new Exception();
                            }
                            mAfterList.add(FOUR_SPACE + mJudeFaceMap.get(face));
                            mAfterList.add(FOUR_SPACE + FACE_NORMAL_ENDING);
                            mActorFaceMap.put("教皇", face);
                        }
                        mAfterList.add(FOUR_SPACE + mActorMap.get("教皇") + " \"" + dialogue + "\"");
                    } else if (nextText.startsWith("#奇路#")) {
                        String lastFace = mActorFaceMap.get("奇路");
                        if (null == lastFace || !face.equals(lastFace)) {
                            String faceEn = mKiluFaceMap.get(face);
                            if (null == faceEn || faceEn.length() == 0) {
                                throw new Exception();
                            }
                            mAfterList.add(FOUR_SPACE + mKiluFaceMap.get(face));
                            mAfterList.add(FOUR_SPACE + FACE_NORMAL_ENDING);
                            mActorFaceMap.put("奇路", face);
                        }
                        mAfterList.add(FOUR_SPACE + mActorMap.get("奇路") + " \"" + dialogue + "\"");
                    } else {
                        throw new Exception();
                    }
                    i++; // 处理两行
                } catch (Exception e) {
                    // 异常的处理，以#注释掉连续的两句
                    mAfterList.add(FOUR_SPACE + "#" + text);
                    mAfterList.add(FOUR_SPACE + "#" + nextText);
                    i++;
                }
                continue;
            }

            // 特殊对话处理
            if (isSpecialDialogue(text)) {
                // #后勤部学姐#：你好
                // "后勤部学姐" "你好
                int index = text.indexOf("#：");
                String dialogue = text.substring(index + 2);
                String actor = text.substring(1, index);
                mAfterList.add(FOUR_SPACE + "\"" + actor + "\"" + " \""
                        + dialogue + "\"");
                continue;
            }

            // 处理△红毛动作
            if (text.contains("红毛动作")) {
                String face = "";
                try {
                    face = text.split("@")[1];
                } catch (Exception e) {
                    face = "正常";// 出现异常，一般是没有表情的情况下，使用“正常”表情
                }
                String dialogue = "";
                try {
                    dialogue = nextText.split("#：|#:")[1];

                    if (text.startsWith("△红毛动作") || text.startsWith("﻿△红毛动作1")
                            || text.startsWith("△动作红毛动作1")) {
                        mAfterList.add(FOUR_SPACE + mChristFaceMap.get(face));
                        mAfterList.add(FOUR_SPACE + FACE_NORMAL_ENDING);
                        mAfterList.add(FOUR_SPACE + mActorMap.get("克里斯特")
                                + " \"" + dialogue + "\"");
                    } else if (text.startsWith("△红毛动作2")) {
                        mAfterList.add(FOUR_SPACE + mChrist2FaceMap.get(face));
                        mAfterList.add(FOUR_SPACE + FACE_NORMAL_ENDING);
                        mAfterList.add(FOUR_SPACE + mActorMap.get("克里斯特")
                                + " \"" + dialogue + "\"");
                    }
                    i++;
                } catch (Exception e) {
                    mAfterList.add(FOUR_SPACE + "#" + text);
                    e.printStackTrace();
                }
                continue;
            }

            // 处理△小白动作
            if (text.contains("△小白动作")) {
                String face = "";
                try {
                    face = text.split("@")[1];
                } catch (Exception e) {
                    face = "正常";// 出现异常，一般是没有表情的情况下，使用“正常”表情
                }
                String dialogue = "";
                try {
                    dialogue = nextText.split("#：|#:")[1];
                    if (text.startsWith("△小白动作1")) {
                        mAfterList.add(FOUR_SPACE + mJesseFaceMap.get(face));
                        mAfterList.add(FOUR_SPACE + FACE_NORMAL_ENDING);
                        mAfterList.add(FOUR_SPACE + mActorMap.get("泽希") + " \""
                                + dialogue + "\"");
                    } else if (text.startsWith("△小白动作2")) {
                        mAfterList.add(FOUR_SPACE + mJesse2FaceMap.get(face));
                        mAfterList.add(FOUR_SPACE + FACE_NORMAL_ENDING);
                        mAfterList.add(FOUR_SPACE + mActorMap.get("泽希") + " \""
                                + dialogue + "\"");
                    } else if (text.startsWith("△小白动作3")) {
                        mAfterList.add(FOUR_SPACE + "show jesse closeeye2");
                        mAfterList.add(FOUR_SPACE + FACE_NORMAL_ENDING);
                        mAfterList.add(FOUR_SPACE + mActorMap.get("泽希") + " \""
                                + dialogue + "\"");
                    }
                    i++;
                } catch (Exception e) {
                    mAfterList.add(FOUR_SPACE + "#" + text);
                    e.printStackTrace();
                }
                continue;
            }

            // 未知情况，暂时都以#进行注释
            mAfterList.add(FOUR_SPACE + "#" + text);
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

        System.out.println("-------------------------------------------------------------------");
        for (String text : mAfterList) {
            System.out.println(text);
        }
        try {
            mFileUtils.writeLines(new File("D:\\after.txt"), mAfterList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String replaseArrow2Space(String text) {
        char[] charArr = text.toCharArray();
        for (int j = 0; j < charArr.length; j++) {
            if (charArr[j] == '→') {
                charArr[j] = ' ';
            }
        }
        text = new String(charArr).trim();
        return text;
    }

    /**
     * 处理点号：234变…，567变……，8910变………，11以上变…………1个不变
     * @伤心
     * #蕾雅#：····
     * @param text
     */
    private static String formatDot(String text) {
        if (null == text || text.length() == 0) {
            return "";
        }
        char[] charArr = text.toCharArray();
        int count = 0;
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] == '·' && i != charArr.length - 1) {
                count++;
            } else {
                    if (charArr[i] == '·' && i == charArr.length - 1) {
                        count++;
                    }
                    String dotStr = "";
                    switch (count) {
                        case 0:
                        case 1:
                            break;
                        case 2:
                        case 3:
                        case 4:
                            dotStr = text.substring(i - count, i);
                            text = text.replace(dotStr, "...");
                            break;
                        case 5:
                        case 6:
                        case 7:
                            dotStr = text.substring(i - count, i);
                            text = text.replace(dotStr, "......");
                            break;
                        case 8:
                        case 9:
                        case 10:
                            dotStr = text.substring(i - count, i);
                            text = text.replace(dotStr, ".........");
                            break;
                        default:// >= 11
                            dotStr = text.substring(i - count, i);
                            text = text.replace(dotStr, "............");
                            break;
                    }
                    count = 0;
                    continue;
                }
        }

        return text;
    }


    /**
     * 截取开始的空格字符串
     * @param text
     * @return
     */
    private static String getSpaceStr(String text) {
        if (null == text || text.length() == 0) {
            return "";
        }
        char[] charArr = text.toCharArray();
        int count = 0;
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] == ' ') {
                count++;
            } else {
                break;
            }
        }
        return text.subSequence(0, count).toString();
    }


    /**
     * 没有表情的特殊对话，路人甲的对话
     * @param text
     * @return
     */
    private static boolean isSpecialDialogue(String text) {
        if (null == text || text.length() ==0) {
            return false;
        }
        // @正常
        // #蕾雅#：家人啊，真好！看你这么高兴，你们感情一定很好！
        // #艾莉森#：嗯，是啊！是啊！
        // 这种情况下不能处理成路人甲的形式，要处理成默认表情
        Iterator iterator = mActorMap.keySet().iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            String actor = "#" + key + "#";
            if (text.startsWith(actor)) {
                return false;
            }
        }

        Pattern pattern = Pattern.compile("^#.*#：.*");
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }


    /**
     * 解析日期  时间：8月1日
     * @param text
     * @throws Exception 
     */
    private static String decodeTime(String text) throws Exception{
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
                builder.append(122 + dayInt);
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
