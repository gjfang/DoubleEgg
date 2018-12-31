package com.example.dell.fder;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
//  单个动态的页面
public class MomentsActivity extends AppCompatActivity {

    public static final String MOMENT_NAME = "fruit_name";

    public static final String MOMENT_IMAGE_ID = "fruit_image_id";

    public static  final String CONTENT_OF_MOMENT="content_of_moment";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moments);
        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(MOMENT_NAME);
        int fruitImageId = intent.getIntExtra(MOMENT_IMAGE_ID, 0);
        String contentOfMoment=intent.getStringExtra(CONTENT_OF_MOMENT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView fruitImageView = (ImageView) findViewById(R.id.fruit_image_view);
        TextView whole_title=(TextView)findViewById(R.id.the_whole_title);
        TextView fruitContentText = (TextView) findViewById(R.id.fruit_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(fruitImageView);
         whole_title.setText(fruitName);
        fruitContentText.setText(contentOfMoment);
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
