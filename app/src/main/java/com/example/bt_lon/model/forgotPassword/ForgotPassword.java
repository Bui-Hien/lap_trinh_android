package com.example.bt_lon.model.forgotPassword;


import com.example.bt_lon.model.question.Question;
import com.example.bt_lon.model.user.model.User;

public class ForgotPassword {
    private int forgot_password_id;
    private int user_id;
    private int question_id;
    private String answer;

    public ForgotPassword() {
    }

    public ForgotPassword(int forgot_password_id, int user_id, int question_id, String answer) {
        this.forgot_password_id = forgot_password_id;
        this.user_id = user_id;
        this.question_id = question_id;
        this.answer = answer;
    }

    public int getForgot_password_id() {
        return forgot_password_id;
    }

    public void setForgot_password_id(int forgot_password_id) {
        this.forgot_password_id = forgot_password_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
