package com.example.dell.fder;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.dell.fder.ObjectClass.Friend;

public class PersonalCardActivity extends AppCompatActivity {

    public final static String nickName="nick name";
    public final static String signature="signature";
    public  static int  headerId=0;
    public static String birth="1970-01-01";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_card);

        final TextView Name=(TextView)findViewById(R.id.personal_name);
        TextView Birth=(TextView)findViewById(R.id.personal_birthDate);
        TextView sex=(TextView)findViewById(R.id.personal_sex);

        ImageView perSquare=(ImageView) findViewById(R.id.personal_square);

        Name.setText(nickName);
        Birth.setText(birth);
        Toolbar toolbar=(Toolbar)findViewById(R.id.personal_toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.personal_collapsing_toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(nickName);

        perSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                 startActivity(new Intent(PersonalCardActivity.this,PersonalSquareActivity.class).putExtra(PersonalSquareActivity.name,nickName));
            }
        });

    }
}
