package com.example.dell.fder;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class ChangeInfoActivity extends AppCompatActivity {

    public static  final String userName="userName";

    public static final int TAKE_PHOTO = 1;

    public static final int CHOOSE_PHOTO = 2;

    private ImageView headerImage_toChange;
    private ImageView  takePhoto_forChangHead;
    private ImageButton choosePhoto_forChange;
    private Uri imageUri;
    private TextView btn_save_changeInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        Toolbar toolbar=(Toolbar)findViewById(R.id.change_info_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        headerImage_toChange=(ImageView)findViewById(R.id.headerImage_toChange);
        takePhoto_forChangHead=(ImageView)findViewById(R.id.takePhoto_forChangHead);
        choosePhoto_forChange=(ImageButton)findViewById(R.id.choosePhoto_forChange);
        btn_save_changeInfo=(TextView) findViewById(R.id.btn_save_changeInfo);

        TextView signature=findViewById(R.id.personal_name);

      btn_save_changeInfo.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              //保存修改
          }
      });


        takePhoto_forChangHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建File对象，用于存储拍照后的图片
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT < 24) {
                    imageUri = Uri.fromFile(outputImage);
                } else {
                    imageUri = FileProvider.getUriForFile(ChangeInfoActivity.this, "com.example.dell.fder.fileprovider", outputImage);
                }
                // 启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });

       choosePhoto_forChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ChangeInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ChangeInfoActivity.this, new String[]{ Manifest.permission. WRITE_EXTERNAL_STORAGE }, 1);
                } else {
                    openAlbum();
                }
            }
        });




    }


/****
 *
 *
 */



private void openAlbum() {
    Intent intent = new Intent("android.intent.action.GET_CONTENT");
    intent.setType("image/*");
    startActivityForResult(intent, CHOOSE_PHOTO); // 打开相册
}




    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        headerImage_toChange.setVisibility(View.VISIBLE);
                        headerImage_toChange.setImageBitmap(bitmap);
                        takePhoto_forChangHead.setVisibility(View.GONE);
                        choosePhoto_forChange.setVisibility(View.GONE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    // 判断手机系统版本号
                    headerImage_toChange.setVisibility(View.VISIBLE);
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        // 4.4以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                    takePhoto_forChangHead.setVisibility(View.GONE);
                    choosePhoto_forChange.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath); // 根据图片路径显示图片
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
           headerImage_toChange.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

/*
*
*
*
 */


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                //携带修改信息
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
