package com.example.bt_lon.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bt_lon.R;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.model.user.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileActivity extends AppCompatActivity {

    private ImageView bntCancel;
    private ImageView imgEditAccount;
    private Button btnDone;
    private Button btnChaneImage;
    private Spinner spinnerSex;
    private EditText edtFullName;
    private EditText edtAddress;
    private EditText ediBirthDay;
    private EditText editPhoneAccount;
    private static final int PICK_IMAGE_REQUEST = 99;
    private Uri imagePath;
    private Bitmap imageToStore;
    private ArrayAdapter<String> sexAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bntCancel = findViewById(R.id.imageBackEditInfor);
        imgEditAccount = findViewById(R.id.imgEditAccount);
        btnDone = findViewById(R.id.btnDone);
        spinnerSex = findViewById(R.id.spinnerSex);
        edtFullName = findViewById(R.id.edtFullName);
        edtAddress = findViewById(R.id.edtAddress);
        ediBirthDay = findViewById(R.id.ediBirthDay);
        editPhoneAccount = findViewById(R.id.editPhoneAccount);
        btnChaneImage = findViewById(R.id.btnChaneImage);
        setupUI();
    }

    private void setupUI() {
        User user = RepositoryUser.getAccount();

        if (user != null) {
            ArrayList<String> listSex = new ArrayList<>();
            listSex.add("Nam");
            listSex.add("Nữ");

            sexAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listSex);
            sexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSex.setAdapter(sexAdapter);

            boolean selectedSex = user.isMale();

            if (selectedSex) {
                spinnerSex.setSelection(0);
            } else {
                spinnerSex.setSelection(1);
            }

            // Định dạng ngày tháng
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            edtFullName.setText(user.getFull_name());
            ediBirthDay.setText(String.valueOf(formatter.format(user.getYear_of_birth())));
            edtAddress.setText(user.getAddress());
            editPhoneAccount.setText(user.getPhone_number());
            if (user.getProfileImage() != null) {
                imgEditAccount.setImageBitmap(user.getProfileImage());
            } else {
                imgEditAccount.setScaleX(1.2F);
                imgEditAccount.setScaleY(1.2F);
            }

            imgEditAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    choseImage();
                }
            });
            btnChaneImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    choseImage();
                }
            });
            ediBirthDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initDatePicker();
                }
            });
            btnDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isMale = spinnerSex.getSelectedItem() == "Nam";

                    user.setFull_name(String.valueOf(edtFullName.getText()));
                    user.setSex(isMale);
                    user.setAddress(String.valueOf(edtAddress.getText()));

                    Drawable drawable = imgEditAccount.getDrawable();
                    if (drawable != null) {
                        if (drawable instanceof BitmapDrawable) {
                            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                            user.setProfileImage(bitmap);
                        }
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    try {
                        Date birth = sdf.parse(String.valueOf(ediBirthDay.getText()));
                        user.setYear_of_birth(birth);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    Dialog dialog;
                    dialog = new Dialog(ProfileActivity.this);
                    dialog.setContentView(R.layout.warning_dialog);
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.setCancelable(false);
                    Button btn = dialog.findViewById(R.id.btnWarningDialog);
                    TextView tv = dialog.findViewById(R.id.tvWarningDialog);
                    tv.setText("Chuỗi vừa nhập không phải số điện thoại.");
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editPhoneAccount.setText(user.getPhone_number());
                            dialog.dismiss();
                        }
                    });

                    if (updatePhoneNumber(String.valueOf(editPhoneAccount.getText()))) {
                        user.setPhone_number(String.valueOf(editPhoneAccount.getText()));
                        finish();
                    } else {
                        dialog.show();
                    }
                }
            });
        } else {
            Toast.makeText(ProfileActivity.this, "Không có tài khoản được tìm thấy", Toast.LENGTH_SHORT).show();
            finish();
        }

        bntCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setupUI();
    }

    private void choseImage() {
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                imagePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);

                imgEditAccount.setImageBitmap(imageToStore);
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    private void initDatePicker() {
        // Lấy ngày hiện tại từ ediBirthDay
        String birthDateString = ediBirthDay.getText().toString();
        Date birthDate = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            birthDate = sdf.parse(birthDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Sử dụng ngày sinh đã chuyển đổi để thiết lập giá trị mặc định cho DatePickerDialog
        Calendar calendar = Calendar.getInstance();
        if (birthDate != null) {
            calendar.setTime(birthDate);
        }

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = getDatePickerDialog(year, month, dayOfMonth);

        datePickerDialog.show();
    }

    @NonNull
    private DatePickerDialog getDatePickerDialog(int year, int month, int dayOfMonth) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = makeDateString(dayOfMonth, month, year);
                ediBirthDay.setText(date);
            }
        };

        // Khởi tạo DatePickerDialog với giá trị mặc định là ngày sinh từ ediBirthDay
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                dateSetListener, year, month, dayOfMonth);
        return datePickerDialog;
    }


    private String makeDateString(int dayOfMonth, int month, int year) {
        return String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month, year);
    }

    private boolean updatePhoneNumber(String phoneNumberString) {
        // Mẫu regex để phát hiện số điện thoại
        String regex = "\\b(?:\\+?\\d{1,3}[-.\\s]?)?\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}\\b";
//
//        // Tạo Pattern object
        Pattern pattern = Pattern.compile(regex);
//
//        // Tạo Matcher object
        Matcher matcher = pattern.matcher(phoneNumberString);
//
//        // Kiểm tra nếu là số điện thoại và cập nhật vào user
        if (matcher.find()) {
            String phoneNumber = matcher.group();
            // Cập nhật số điện thoại vào user
            return true;
        }
        return false;
    }

}
