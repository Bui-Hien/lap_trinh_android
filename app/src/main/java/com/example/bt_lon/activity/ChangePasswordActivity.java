package com.example.bt_lon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bt_lon.R;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.model.user.User;
import com.example.bt_lon.sqlite_open_helper.DatabaseConnector;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        TextView tvChangePasswordName = findViewById(R.id.tvChangePasswordName);
        TextView tvPasswordOld = findViewById(R.id.tvPasswordOld);
        EditText edtPasswordOld = findViewById(R.id.edtPasswordOld);
        EditText edtPasswordNew = findViewById(R.id.edtPasswordNew);
        EditText edtPasswordConfirm = findViewById(R.id.edtPasswordConfirm);
        Button btnFinishChangePassword = findViewById(R.id.btnFinishChangePassword);
        ImageView imageBack = findViewById(R.id.imageBack);
        ImageView imgChangePassword = findViewById(R.id.imgChangePassword);
        Intent intent = getIntent();
        if (intent != null) {
            String previous = intent.getStringExtra("previous");
            tvChangePasswordName.setText(RepositoryUser.getAccount().getUsername());
            if (Objects.requireNonNull(previous).equals("forgot")) {
                tvPasswordOld.setVisibility(View.GONE);
                edtPasswordOld.setVisibility(View.GONE);
                btnFinishChangePassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (edtPasswordNew.getText().toString().isEmpty() || edtPasswordConfirm.getText().toString().isEmpty()) {
                            Toast.makeText(ChangePasswordActivity.this, "Mật khẩu không được bỏ trống.", Toast.LENGTH_SHORT).show();
                        } else {
                            if (!edtPasswordNew.getText().toString().equals(edtPasswordConfirm.getText().toString())) {
                                Toast.makeText(ChangePasswordActivity.this, "Mật khẩu bạn vừa nhập không khớp.", Toast.LENGTH_SHORT).show();
                            } else {
                                RepositoryUser.getAccount().setPassword(String.valueOf(edtPasswordNew.getText()));

                                DatabaseConnector databaseConnector = new DatabaseConnector(ChangePasswordActivity.this);

                                if (databaseConnector.updatePasswordUser(RepositoryUser.getAccount()) > 0) {
                                    Intent intent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Intent loginIntent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                                    loginIntent.putExtra("finish_forgot_password", true);
                                    startActivity(loginIntent);
                                } else {
                                    Toast.makeText(ChangePasswordActivity.this, "Lỗi thay đổi mật khẩu.", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                    }
                });
            }
            if (Objects.requireNonNull(previous).equals("change")) {
                tvPasswordOld.setVisibility(View.VISIBLE);
                edtPasswordOld.setVisibility(View.VISIBLE);
                btnFinishChangePassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (edtPasswordNew.getText().toString().isEmpty() || edtPasswordConfirm.getText().toString().isEmpty() || edtPasswordOld.getText().toString().isEmpty()) {
                            Toast.makeText(ChangePasswordActivity.this, "Mật khẩu không được bỏ trống.", Toast.LENGTH_SHORT).show();
                        } else {
                            if (!edtPasswordNew.getText().toString().equals(edtPasswordConfirm.getText().toString())) {
                                Toast.makeText(ChangePasswordActivity.this, "Mật khẩu bạn vừa nhập không khớp.", Toast.LENGTH_SHORT).show();
                            } else {
                                RepositoryUser.getAccount().setPassword(String.valueOf(edtPasswordNew.getText()));
                                DatabaseConnector databaseConnector = new DatabaseConnector(ChangePasswordActivity.this);
                                if (databaseConnector.updatePasswordUser(RepositoryUser.getAccount(), edtPasswordOld.getText().toString()) > 0) {
                                    finish();
                                } else {
                                    Toast.makeText(ChangePasswordActivity.this, "Mật khẩu bạn vừa nhập không đúng.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });
                imgChangePassword.setImageBitmap(RepositoryUser.getAccount().getProfileImage());
            }
        }
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}