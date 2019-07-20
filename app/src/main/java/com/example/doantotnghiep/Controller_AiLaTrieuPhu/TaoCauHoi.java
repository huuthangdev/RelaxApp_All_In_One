package com.example.doantotnghiep.Controller_AiLaTrieuPhu;
import android.app.Activity;

import com.example.doantotnghiep.Model.AiLaTrieuPhu.Question;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


class TaoCauHoi {

    static ArrayList<Question> createEasyQuestions(Activity activity){
        ArrayList<Question> listEasyQuestion = new ArrayList<>();
        XmlPullParserFactory pullParserFactory;

        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();
            InputStream in_s = activity.getApplicationContext().getAssets().open("cauhoi.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in_s, null);
            listEasyQuestion = readQuestionFromXml(parser);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(listEasyQuestion);
        return listEasyQuestion;
    }

    private static ArrayList<Question> readQuestionFromXml(XmlPullParser parser) throws XmlPullParserException, IOException {
        String text = "";
        ArrayList<Question> questionList = new ArrayList<>();
        int eventType = parser.getEventType();
        Question question = null;
        Map<String, String> answers = new HashMap<>();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tagName = parser.getName();
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (tagName.equalsIgnoreCase("questionParse")) {
                        question = new Question();
                    }
                    break;

                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;

                case XmlPullParser.END_TAG:
                    if (tagName.equalsIgnoreCase("questionParse")) {
                        if (question != null) {
                            question.setAnswer(answers);
                        }
                        answers = new HashMap<>();
                        questionList.add(question);
                    } else if (tagName.equalsIgnoreCase("question")) {
                        if (question != null) {
                            question.setQuestion(text);
                        }
                    } else if (tagName.equalsIgnoreCase("a")) {
                        answers.put("1", text);
                    } else if (tagName.equalsIgnoreCase("b")) {
                        answers.put("2", text);
                    } else if (tagName.equalsIgnoreCase("c")) {
                        answers.put("3", text);
                    } else if (tagName.equalsIgnoreCase("d")) {
                        answers.put("4", text);
                    } else if (tagName.equalsIgnoreCase("reply")) {
                        if (question != null) {
                            question.setRightAnswer(text);
                        }
                    }
                    break;

                default:
                    break;
            }
            eventType = parser.next();
        }
        return questionList;
    }
}
