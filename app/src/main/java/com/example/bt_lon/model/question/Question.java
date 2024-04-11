package com.example.bt_lon.model.question;

import com.example.bt_lon.model.forgotPassword.ForgotPassword;

public class Question {
    private int question_id;
    private String question;

    public Question() {
    }

    public Question(int question_id) {
        this.question_id = question_id;
    }

    public Question(int question_id, String question) {
        this.question_id = question_id;
        this.question = question;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}

//D  id: 1	câu hỏi: Bạn sinh vào ngày nào?
//D  id: 2	câu hỏi: Bạn có con vật cưng nào?
//D  id: 3	câu hỏi: Bạn thích môn thể thao nào nhất?
//D  id: 4	câu hỏi: Tên người bạn thân nhất của bạn là gì?
//D  id: 5	câu hỏi: Bạn đã từng đi du lịch ở đâu?
//D  id: 6	câu hỏi: Trong gia đình bạn, bạn là người thứ mấy?
//D  id: 7	câu hỏi: Bạn tốt nghiệp từ trường đại học nào?
//D  id: 8	câu hỏi: Tên của người đồng nghiệp đầu tiên mà bạn làm việc cùng là gì?
//D  id: 9	câu hỏi: Bạn thường xem thể loại phim nào?
//D  id: 10	câu hỏi: Bạn đã từng tham gia sự kiện nào của cộng đồng gần đây nhất?

//    ForgotPassword forgotPassword1 = new ForgotPassword(1, user, question1, "15/03/2003");
//    ForgotPassword forgotPassword2 = new ForgotPassword(2, user, question2, "cho");
//    ForgotPassword forgotPassword3 = new ForgotPassword(3, user, question3, "ngu");