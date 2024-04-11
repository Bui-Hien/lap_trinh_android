package com.example.bt_lon.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bt_lon.R;
import com.example.bt_lon.model.forgotPassword.ForgotPassword;
import com.example.bt_lon.model.question.Question;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DAO.ForgotPasswordDAO;
import com.example.bt_lon.sqlite_open_helper.DAO.UserDAO;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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


        Intent intentusername = getIntent();
        Bundle bundle = intentusername.getExtras();

        if (bundle != null) {
            String username = (String) bundle.get("username");
            if (initData(new User(username)) != null) {
                List<ForgotPassword> forgotPasswordList = initData(new User(username));

                tvForgotPasswordName.setText(forgotPasswordList.get(0).getUser().getUsername());
                tvQuestionSecurityOne.setText(forgotPasswordList.get(0).getQuestion().getQuestion());
                tvQuestionSecuritySecond.setText(forgotPasswordList.get(1).getQuestion().getQuestion());
                tvQuestionSecurityThird.setText(forgotPasswordList.get(2).getQuestion().getQuestion());

                if (forgotPasswordList.get(0).getQuestion().getQuestion_id() == 1) {
                    // Không cho phép chỉnh sửa nội dung
                    edtAnswerOne.setFocusable(false);
                    edtAnswerOne.setFocusableInTouchMode(false);
                    edtAnswerOne.setHint("dd/MM/yyyy");
                    edtAnswerOne.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            initDatePicker(edtAnswerOne);
                        }
                    });

                }
                if (forgotPasswordList.get(1).getQuestion().getQuestion_id() == 1) {
                    // Không cho phép chỉnh sửa nội dung
                    edtAnswersSecond.setFocusable(false);
                    edtAnswersSecond.setFocusableInTouchMode(false);
                    edtAnswersSecond.setHint("dd/MM/yyyy");
                    edtAnswersSecond.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            initDatePicker(edtAnswersSecond);
                        }
                    });
                }
                if (forgotPasswordList.get(2).getQuestion().getQuestion_id() == 1) {
                    // Không cho phép chỉnh sửa nội dung
                    edtAnswerThird.setFocusable(false);
                    edtAnswerThird.setFocusableInTouchMode(false);
                    edtAnswerThird.setHint("dd/MM/yyyy");
                    edtAnswerThird.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            initDatePicker(edtAnswerThird);
                        }
                    });
                }
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
                            if (!answer1.equals(forgotPasswordList.get(0).getAnswer()) || !answer2.equals(forgotPasswordList.get(1).getAnswer()) || !answer3.equals(forgotPasswordList.get(2).getAnswer())) {
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
            } else {
                Log.e("not found user", "ForgotPasswordActivityForgotPasswordActivity");
            }
        } else {
            Log.e("not found user", "ForgotPasswordActivityForgotPasswordActivity");
        }
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
        ForgotPasswordDAO forgotPasswordDAO = new ForgotPasswordDAO(ForgotPasswordActivity.this);
        UserDAO userDAO = new UserDAO(ForgotPasswordActivity.this);
        if (userDAO.checkUser(user) != null) {
            return forgotPasswordDAO.getAllForgotPasswordsByUserId(ForgotPasswordActivity.this, userDAO.checkUser(user));
        } else {
            return null;
        }

    }

    private void initDatePicker(EditText editText) {
        // Lấy ngày tháng năm hiện tại
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Khởi tạo DatePickerDialog với ngày tháng năm hiện tại và EditText đích
        DatePickerDialog datePickerDialog = getDatePickerDialog(year, month, dayOfMonth, editText);
        datePickerDialog.show();
    }

    @NonNull
    private DatePickerDialog getDatePickerDialog(int year, int month, int dayOfMonth, EditText editText) {
        // Khởi tạo DatePickerDialog với giá trị mặc định là ngày tháng năm hiện tại
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, null, year, month, dayOfMonth);

        // Tắt phần hiển thị kiểu chữ và chỉ hiển thị NumberPicker
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.getDatePicker().setSpinnersShown(true);

        // Xử lý sự kiện khi người dùng nhấn nút "OK"
        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lấy giá trị đã chọn từ DatePicker và cập nhật EditText
                int selectedYear = datePickerDialog.getDatePicker().getYear();
                int selectedMonth = datePickerDialog.getDatePicker().getMonth() + 1; // Tháng được đếm từ 0
                int selectedDayOfMonth = datePickerDialog.getDatePicker().getDayOfMonth();
                editText.setText(makeDateString(selectedDayOfMonth, selectedMonth, selectedYear));
            }
        });

        return datePickerDialog;
    }

    private String makeDateString(int dayOfMonth, int month, int year) {
        // Trả về chuỗi với ngày, tháng và năm dưới dạng số
        return String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month, year);
    }

}