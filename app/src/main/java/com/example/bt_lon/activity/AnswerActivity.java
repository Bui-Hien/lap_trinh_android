package com.example.bt_lon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bt_lon.R;
import com.example.bt_lon.model.forgotPassword.ForgotPassword;
import com.example.bt_lon.model.question.Question;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DAO.ForgotPasswordDAO;
import com.example.bt_lon.sqlite_open_helper.DAO.ProductDAO;
import com.example.bt_lon.sqlite_open_helper.DAO.QuestionDAO;
import com.example.bt_lon.sqlite_open_helper.DAO.UserDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        EditText editText1 = findViewById(R.id.edt1);
        EditText editText2 = findViewById(R.id.edt2);
        EditText editText3 = findViewById(R.id.edt3);
        Button btnThoat = findViewById(R.id.btnThoat);
        Button btnXacnhan = findViewById(R.id.btnXacnhan);

        Spinner spinner1 = findViewById(R.id.spinnner);
        Spinner spinner2 = findViewById(R.id.spinnner1);
        Spinner spinner3 = findViewById(R.id.spinnner2);

        QuestionDAO questionDAO = new QuestionDAO(getApplicationContext());
        List<Question> questions = questionDAO.getAllQuestions();

        ArrayList<String> listQuestionTexts = new ArrayList<>();
        ArrayList<Integer> listQuestionIds = new ArrayList<>();

        for (Question question : questions) {
            listQuestionTexts.add(question.getQuestion());
            listQuestionIds.add(question.getQuestion_id());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listQuestionTexts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);

        final int[] questionIds = new int[3];
        List<Integer> selectedQuestionIds = new ArrayList<>(); // Danh sách lưu trữ các câu hỏi đã được chọn
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int selectedQuestionId = listQuestionIds.get(position);
                if (!selectedQuestionIds.contains(selectedQuestionId)) {
                    questionIds[0] = selectedQuestionId;
                    selectedQuestionIds.add(selectedQuestionId);
                } else {
                    // Bạn có thể hiển thị thông báo hoặc thực hiện hành động khác khi câu hỏi đã được chọn trước đó
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Nothing to do here
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int selectedQuestionId = listQuestionIds.get(position);
                if (!selectedQuestionIds.contains(selectedQuestionId)) {
                    questionIds[1] = selectedQuestionId;
                    selectedQuestionIds.add(selectedQuestionId);
                } else {
                    // Bạn có thể hiển thị thông báo hoặc thực hiện hành động khác khi câu hỏi đã được chọn trước đó
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Nothing to do here
            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int selectedQuestionId = listQuestionIds.get(position);
                if (!selectedQuestionIds.contains(selectedQuestionId)) {
                    questionIds[2] = selectedQuestionId;
                    selectedQuestionIds.add(selectedQuestionId);
                } else {
                    // Bạn có thể hiển thị thông báo hoặc thực hiện hành động khác khi câu hỏi đã được chọn trước đó
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Nothing to do here
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();

                if (bundle != null) {
                    String username = (String) bundle.get("username");
                    String password = (String) bundle.get("password");

                    String answer1 = String.valueOf(editText1.getText());
                    String answer2 = String.valueOf(editText2.getText());
                    String answer3 = String.valueOf(editText3.getText());
                    if (answer1.isEmpty() || answer2.isEmpty() || answer3.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Không được để trống câu trả lời.", Toast.LENGTH_LONG).show();
                    } else {
                        User user = new User(1, username, username, password, true, new Date(), null, null, null);
                        Log.d("hien",
                                " getUsername: " + user.getUsername() +
                                        " getPassword: " + user.getPassword() +
                                        " questionIds 1: " + questionIds[0] +
                                        " questionIds 2: " + questionIds[1] +
                                        " questionIds 3: " + questionIds[2]);

                        UserDAO userDAO = new UserDAO(getApplicationContext());
                        userDAO.storeUser(user);

                        User userId = userDAO.checkUser(user);

                        Question question1 = new Question(questionIds[0]);
                        Question question2 = new Question(questionIds[1]);
                        Question question3 = new Question(questionIds[2]);
//

                        Toast.makeText(getApplicationContext(), " " + question1.getQuestion_id() + " " + question2.getQuestion_id() + " " + question3.getQuestion_id(), Toast.LENGTH_LONG).show();

                        ForgotPassword forgotPassword1 = new ForgotPassword(1, userId, question1, answer1);
                        ForgotPassword forgotPassword2 = new ForgotPassword(2, userId, question2, answer2);
                        ForgotPassword forgotPassword3 = new ForgotPassword(3, userId, question3, answer3);

                        ForgotPasswordDAO forgotPasswordDAO = new ForgotPasswordDAO(getApplicationContext());

                        if (!forgotPasswordDAO.storeForgotPassword(getApplicationContext(), forgotPassword1) ||
                                !forgotPasswordDAO.storeForgotPassword(getApplicationContext(), forgotPassword2) ||
                                !forgotPasswordDAO.storeForgotPassword(getApplicationContext(), forgotPassword3)
                        ) {
                            userDAO.deleteUser(userId.getUsername());
                            Toast.makeText(getApplicationContext(), "Lỗi tạo tài khoản.", Toast.LENGTH_LONG).show();

                        } else {
                            Intent intentLogin = new Intent(AnswerActivity.this, LoginActivity.class);
                            startActivity(intentLogin);
                            finish();
                        }
                    }
                }
            }
        });

    }
}