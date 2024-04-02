package com.example.bt_lon.model.question;

public class Question
{
    private int question_id;
    private String question;

    public Question() {
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
//        Câu hỏi: Bạn sinh vào ngày nào?
//        Câu hỏi: Bạn có con vật cưng nào?
//        Câu hỏi: Bạn thích môn thể thao nào nhất?
//        Câu hỏi: Tên người bạn thân nhất của bạn là gì?
//        Câu hỏi: Bạn đã từng đi du lịch đến đâu trong chuyến cuối cùng của mình?
//        Câu hỏi: Trong gia đình bạn, bạn là người thứ mấy?
//        Câu hỏi: Bạn tốt nghiệp từ trường đại học nào?
//        Câu hỏi: Tên của người đồng nghiệp đầu tiên mà bạn làm việc cùng là gì?
//        Câu hỏi: Bạn thường xem thể loại phim nào?
//        Câu hỏi: Bạn đã từng tham gia sự kiện nào của cộng đồng gần đây nhất?
