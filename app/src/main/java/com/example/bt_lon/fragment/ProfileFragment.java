package com.example.bt_lon.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.bt_lon.R;
import com.example.bt_lon.model.user.model.User;
import com.example.bt_lon.model.user.repository.RepositoryUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private static final String ARG_PHONE_NUMBER = "phone number";
    TextView mTvPhoneNumber;

    private String mPhoneNumber;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String mPhoneNumber) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PHONE_NUMBER, mPhoneNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.bntChangePhoneNumber);
        User user = RepositoryUser.user;
        EditText edtPhoneNumber = view.findViewById(R.id.edtPhoneNumber);
        EditText edtBirthday = view.findViewById(R.id.edtBirthday);
        EditText edtFirstName = view.findViewById(R.id.edtFirstName);
        EditText edtLastName = view.findViewById(R.id.edtLastName);

        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        RadioButton radioMale  =view.findViewById(R.id.radioMale);
        RadioButton radioFemale = view.findViewById(R.id.radioFemale);


        edtPhoneNumber.setText(user.getPhone_number());
        edtBirthday.setText(user.getYear_of_birth().toString());
        edtFirstName.setText(user.getFirstname());
        edtLastName.setText(user.getLastname());
        if (user.isSex()) {
            radioMale.setChecked(true);
        }else {
            radioFemale.setChecked(true);
        }

//        edtPhoneNumber.setText(editTextPhone.getText().toString());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, new ChangePhoneFragment())
                        .commit();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPhoneNumber = getArguments().getString(ARG_PHONE_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}