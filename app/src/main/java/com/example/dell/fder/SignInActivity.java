package com.example.dell.fder;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SignInActivity extends AppCompatActivity {



private TextInputLayout TIL_signin_nickname;
private TextInputEditText nickname_signin;
private TextInputLayout TIL_signin_pw;
private EditText password_signin;
private ImageView headerSignin;
Button goToSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        Toolbar toolbar=findViewById(R.id.signin_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        TIL_signin_nickname=findViewById(R.id.TIL_signin_nickname);
        nickname_signin=findViewById(R.id.nickname_signin);
        TIL_signin_pw=findViewById(R.id.TIL_signin_pw);
        password_signin=findViewById(R.id.password_signin);

        headerSignin=(ImageView)findViewById(R.id.header_signin);

        checkNickName();
        checkPassword();

        goToSignUp=findViewById(R.id.gotoSignUp);
        goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this,SignUpActivity.class));
            }
        });
    }



    private void checkNickName(){
        nickname_signin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                Editable nickname=nickname_signin.getEditableText();
                if (nickname.length()<4){
                    TIL_signin_nickname.setError("太短了，长度在 4~10之间");
                }else if (nickname.length()>10){
                    TIL_signin_nickname.setError("过长了，长度在4~10之间");
                }else {
                    TIL_signin_nickname.setError("Right");
                    TIL_signin_nickname.setErrorEnabled(false);
                    TIL_signin_nickname.setErrorEnabled(true);
                }
            }
        });
    }



    private void checkPassword(){

        password_signin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (password_signin.getEditableText().length()<6){
                    TIL_signin_pw.setError("必须为6位密码");
                } else if (password_signin.getEditableText().length()==6){
                    TIL_signin_pw.setErrorEnabled(false);
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
