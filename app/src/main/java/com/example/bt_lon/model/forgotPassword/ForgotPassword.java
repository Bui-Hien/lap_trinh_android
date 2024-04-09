package com.example.bt_lon.model.forgotPassword;


import com.example.bt_lon.model.question.Question;
import com.example.bt_lon.model.user.User;

public class ForgotPassword {
    private int forgot_password_id;
    private User user;
    private Question question;
    private String answer;

    public ForgotPassword() {
    }

    public ForgotPassword(int forgot_password_id, User user, Question question, String answer) {
        this.forgot_password_id = forgot_password_id;
        this.user = user;
        this.question = question;
        this.answer = answer;
    }

    public int getForgot_password_id() {
        return forgot_password_id;
    }

    public void setForgot_password_id(int forgot_password_id) {
        this.forgot_password_id = forgot_password_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
