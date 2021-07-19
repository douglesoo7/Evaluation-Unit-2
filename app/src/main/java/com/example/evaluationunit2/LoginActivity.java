package com.example.evaluationunit2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mEtEmail, mEtPassword;
    private CheckBox checkBox;
    private Button mBtnLogin;
    private String eMailValidation = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (PreferenceHelper.isChecked(LoginActivity.this,"checked")){
            Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        else{
            initViews();
        }
    }

    private void initViews() {
        mEtEmail = findViewById(R.id.etUsername);
        mEtPassword = findViewById(R.id.etPassword);
        checkBox = findViewById(R.id.checkBoxRememberMe);
        mBtnLogin = findViewById(R.id.btnLogin);
        mEtEmail.setOnClickListener(this);
        mEtPassword.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnLogin:
                if (isEmailValid() && isPassWordValid()) {
                    checkSubmit();
                    Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    private void checkSubmit() {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    PreferenceHelper.checked(LoginActivity.this,"checked",true);
                    PreferenceHelper.writeUserNameToPreference(LoginActivity.this,"username",mEtEmail.getText().toString().trim());
                    PreferenceHelper.writePasswordToPreference(LoginActivity.this,"password",mEtPassword.getText().toString());
                }
                else {
                    PreferenceHelper.checked(LoginActivity.this,"checked",false);
                }
            }
        });
    }

    private boolean isPassWordValid() {
        if (mEtPassword.getText().toString().length() >= 6)
            return true;
        else{
            mEtPassword.setError("Password is very short");
            return false;
        }
    }

    private boolean isEmailValid() {
        if (mEtEmail.getText().toString().trim().matches(eMailValidation))
            return true;
        else {
            mEtEmail.setError("Invalid Email");
            return false;
        }

    }


}