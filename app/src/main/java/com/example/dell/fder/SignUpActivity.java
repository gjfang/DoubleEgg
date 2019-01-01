package com.example.dell.fder;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    //顺序对应注册页面的layout 和 edit
    private TextInputLayout TIL_signup_nick;
    private TextInputEditText nickname_signup;
    private TextInputLayout TIL_signupFirstPas;
    private EditText password_signup;
    private TextInputLayout TIL_signupSecondPas;
    private EditText confirm_signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Toolbar toolbar=findViewById(R.id.signup_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //昵称的layout
        TIL_signup_nick=findViewById(R.id.TIL_signup_nick);
        //输入昵称的edittext
        nickname_signup=findViewById(R.id.nickname_signup);
        //包含第一次输入密码layout
        TIL_signupFirstPas =findViewById(R.id.TIL_signupFirstPas);
        //第一次输入密码的edittext
        password_signup=findViewById(R.id.password_signup);
        Editable pass1;
        //确认密码的layout
        TIL_signupSecondPas=findViewById(R.id.ITL_signupSecondPas);
        //输入确认密码的edittext
        confirm_signup=findViewById(R.id.confirm_signup);
        Editable pass2;

         checkNickname();
         checkFirstPassword();
         checkSecondPassword();
    }



    private void checkNickname(){
        nickname_signup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable nick=nickname_signup.getEditableText();
                if (nick.length()<4){
                    TIL_signup_nick.setError("Too short, at least 4 words!");
                }else if (nick.length()>10){
                    TIL_signup_nick.setError("Too long, no longer than 10");
                }else {
                    TIL_signup_nick.setError("Right!");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
// j检查名称占用
//               Editable nick=nickname_signup.getEditableText();
//               if (nick.length()<6){
//                   TIL_signup_nick.setError("6666");
//               }
            }
        });
    }


    private void checkFirstPassword(){

        password_signup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ( password_signup.getEditableText().length()<6){
                    TIL_signupFirstPas.setError("必须是六位");
                }
                else {
                    TIL_signupFirstPas.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void checkSecondPassword(){
        confirm_signup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Editable secondPas=confirm_signup.getEditableText();
                if (confirm_signup.getEditableText().length()<6){
                    TIL_signupSecondPas.setError("必须是6位");
                } else if(!confirm_signup.getText().toString().equals(password_signup.getText().toString())  ){
                    TIL_signupSecondPas.setError("两次密码不一致，请检查");
                }else {
                    TIL_signupSecondPas.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
