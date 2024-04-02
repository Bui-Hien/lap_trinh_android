package com.example.bt_lon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bt_lon.R;
import com.example.bt_lon.model.forgotPassword.ForgotPassword;
import com.example.bt_lon.model.question.Question;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        TextView tvForgotPasswordName = findViewById(R.id.tvForgotPasswordName);
        TextView tvQuestionSecurityOne = findViewById(R.id.tvQuestionSecurityOne);
        TextView tvQuestionSecuritySecond = findViewById(R.id.tvQuestionSecuritySecond);
        TextView tvQuestionSecurityThird = findViewById(R.id.tvQuestionSecurityThird);
        EditText edtAnswerOne = findViewById(R.id.edtAnswerOne);
        EditText edtAnswersSecond = findViewById(R.id.edtAnswersSecond);
        EditText edtAnswerThird = findViewById(R.id.edtAnswerThird);
        Button btnNextForgotPassword = findViewById(R.id.btnNextForgotPassword);
        ImageView imageBack = findViewById(R.id.imageBack);

        List<ForgotPassword> forgotPasswordList = initData(new User("buixuanhien5"));

        tvForgotPasswordName.setText(forgotPasswordList.get(0).getUser().getUsername());
        tvQuestionSecurityOne.setText(forgotPasswordList.get(0).getQuestion().getQuestion());
        tvQuestionSecuritySecond.setText(forgotPasswordList.get(1).getQuestion().getQuestion());
        tvQuestionSecurityThird.setText(forgotPasswordList.get(2).getQuestion().getQuestion());

        btnNextForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer1, answer2, answer3;
                answer1 = String.valueOf(edtAnswerOne.getText());
                answer2 = String.valueOf(edtAnswersSecond.getText());
                answer3 = String.valueOf(edtAnswerThird.getText());
                if (answer1.isEmpty() || answer2.isEmpty() || answer3.isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Câu trả lời không được bỏ trống.", Toast.LENGTH_SHORT).show();
                } else {
                    if (!answer1.equals(forgotPasswordList.get(0).getAnswer())
                            || !answer2.equals(forgotPasswordList.get(1).getAnswer())
                            || !answer3.equals(forgotPasswordList.get(2).getAnswer())) {
                        Toast.makeText(ForgotPasswordActivity.this, "Câu trả lời không chính xác.", Toast.LENGTH_SHORT).show();
                    } else {
                        RepositoryUser.createNewAccount(forgotPasswordList.get(0).getUser().getUser_id(), forgotPasswordList.get(0).getUser().getUsername());
                        Intent intent = new Intent(ForgotPasswordActivity.this, ChangePasswordActivity.class);

                        // Put extra data into the Intent using key-value pairs
                        intent.putExtra("previous", "forgot");

                        // Start the new activity
                        startActivity(intent);
                    }
                }
            }
        });
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (getIntent().getBooleanExtra("finish_forgot_password", false)) {
            Intent intent = new Intent(this, ForgotPasswordActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
    }

    private List<ForgotPassword> initData(User user) {
        DatabaseConnector databaseConnector = new DatabaseConnector(ForgotPasswordActivity.this);
        databaseConnector.checkUser(user);

        return databaseConnector.getAllForgotPasswordsByUserId(databaseConnector.checkUser(user));

    }

}