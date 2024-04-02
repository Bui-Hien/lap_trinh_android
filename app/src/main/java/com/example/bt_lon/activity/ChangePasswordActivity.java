package com.example.bt_lon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bt_lon.R;
import com.example.bt_lon.model.user.RepositoryUser;
import com.example.bt_lon.model.user.User;

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

        Intent intent = getIntent();
        if (intent != null) {
            String previous = intent.getStringExtra("previous");
            User user = RepositoryUser.getAccount();
            tvChangePasswordName.setText(String.valueOf(user.getUsername()));
            if (Objects.requireNonNull(previous).equals("forgot")) {
                tvPasswordOld.setVisibility(View.VISIBLE);
                edtPasswordOld.setVisibility(View.VISIBLE);
                btnFinishChangePassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (edtPasswordNew.getText().toString().isEmpty() || edtPasswordConfirm.getText().toString().isEmpty()) {
                            Toast.makeText(ChangePasswordActivity.this, "Mật khẩu không được bỏ trống.", Toast.LENGTH_SHORT).show();
                        } else {
                            if (!edtPasswordNew.getText().toString().equals(edtPasswordConfirm.getText().toString())) {
                                Toast.makeText(ChangePasswordActivity.this, "Mật khẩu bạn vừa nhập không khớp.", Toast.LENGTH_SHORT).show();
                            } else {
                                user.setPassword(String.valueOf(edtPasswordNew.getText()));
                                Intent intent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                });
            }
            if (Objects.requireNonNull(previous).equals("change")) {
                tvPasswordOld.setVisibility(View.GONE);
                edtPasswordOld.setVisibility(View.GONE);
                btnFinishChangePassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (edtPasswordNew.getText().toString().isEmpty() || edtPasswordConfirm.getText().toString().isEmpty() || tvPasswordOld.getText().toString().isEmpty()) {
                            Toast.makeText(ChangePasswordActivity.this, "Mật khẩu không được bỏ trống.", Toast.LENGTH_SHORT).show();
                        } else {
                            if (tvPasswordOld.getText().toString().equals("passwordOld")) {
                                if (edtPasswordNew.getText().toString().equals(edtPasswordConfirm.getText().toString())) {
                                    Toast.makeText(ChangePasswordActivity.this, "Mật khẩu bạn vừa nhập không khớp.", Toast.LENGTH_SHORT).show();
                                } else {
                                    user.setPassword(String.valueOf(edtPasswordNew.getText()));
                                    finish();
                                }
                            } else {
                                Toast.makeText(ChangePasswordActivity.this, "Mật khẩu bạn vừa nhập không đúng.", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });
            }
        }
    }
}