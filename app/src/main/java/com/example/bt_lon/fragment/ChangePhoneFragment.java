package com.example.bt_lon.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.bt_lon.R;
import com.example.bt_lon.model.user.model.User;
import com.example.bt_lon.model.user.repository.RepositoryUser;

public class ChangePhoneFragment extends Fragment {
    Button bntDoneChangePhone, bntBack;
    public static ChangePhoneFragment newInstance() {
        ChangePhoneFragment fragment = new ChangePhoneFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        bntDoneChangePhone = view.findViewById(R.id.bntDoneChangePhone);
        bntBack = view.findViewById(R.id.bntBack);
        User user =  RepositoryUser.user;
        EditText editTextPhone = view.findViewById(R.id.editTextPhone);
        editTextPhone.setText(user.getPhone_number());
        bntDoneChangePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setPhone_number(editTextPhone.getText().toString());
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, new ProfileFragment())
                        .commit();
            }
        });
        bntBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, new ProfileFragment())
                        .commit();
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_phone, container, false);
    }
}