package com.yongxing.activity.demo01.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;

import com.makeramen.roundedimageview.RoundedImageView;
import com.yongxing.activity.demo01.Bean.UserBean;
import com.yongxing.activity.demo01.R;
import com.yongxing.activity.demo01.utils.StatusBarUtils;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import Utils.ControlUtils;

public class UserMessageActivity extends Activity implements View.OnClickListener {

    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";

    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;

    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 480;
    private static int output_Y = 480;





    private TextView userTop;
    private ImageView userLeft;
    private ImageView userRight;

    private RoundedImageView userHead;
    private LinearLayout nameLayout;
    private TextView userName;
    private LinearLayout addressLayout;
    private TextView userAddress;
    private LinearLayout phoneLayout;
    private TextView userPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);

        StatusBarUtils.setWindowStatusBarColor(this,R.color.blue);
        userHead = (RoundedImageView) findViewById(R.id.user_head);
        nameLayout = (LinearLayout) findViewById(R.id.name_layout);
        userName = (TextView) findViewById(R.id.user_name);
        addressLayout = (LinearLayout) findViewById(R.id.address_layout);
        userAddress = (TextView) findViewById(R.id.user_address);
        phoneLayout = (LinearLayout) findViewById(R.id.phone_layout);
        userPhone = (TextView) findViewById(R.id.user_phone);
        userLeft = (ImageView) findViewById(R.id.top_left);
        userTop = (TextView) findViewById(R.id.fragment_text_view);
        userRight = (ImageView) findViewById(R.id.top_right);
        userTop.setText("个人信息");
        userRight.setVisibility(View.INVISIBLE);
        userLeft.setVisibility(View.VISIBLE);
        userLeft.setOnClickListener(this);
        userHead.setOnClickListener(this);
        nameLayout.setOnClickListener(this);
        addressLayout.setOnClickListener(this);
        phoneLayout.setOnClickListener(this);
        userHead.setImageResource(R.drawable.headimage);
        UserBean userBean= ControlUtils.userBean;
        userName.setText(userBean.getUserName());
        userAddress.setText(userBean.getUserAddress());
        userPhone.setText(userBean.getUserPhone());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_head:
//                choseHeadImageFromGallery();
                choseHeadImageFromCameraCapture();
                break;
            case R.id.top_left:
                finish();
                break;
            case R.id.address_layout:
                Intent add=new Intent(this,AddressSetActivity.class);

                add.putExtra("address",userAddress.getText().toString());
//                add.set
                this.startActivity(add);
                break;
            case R.id.phone_layout:
                Intent phone=new Intent(this,PhoneSetActivity.class);
                phone.putExtra("phone",userPhone.getText().toString());
                this.startActivity(phone);
                break;
            case R.id.name_layout:
                Intent name=new Intent(this,NameSetActivity.class);
                name.putExtra("name",userName.getText().toString());
                this.startActivity(name);
                break;
        }
    }

    // 从本地相册选取图片作为头像
    private void choseHeadImageFromGallery() {
        Intent intentFromGallery = new Intent();
        // 设置文件类型
        intentFromGallery.setType("image/*");
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }

    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 判断存储卡是否可用，存储照片文件
//        if (hasSdcard()) {
//            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
//                    .fromFile(new File(Environment
//                            .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
//        }
//
        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {

        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
            return;
        }

        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(intent.getData());
                break;

            case CODE_CAMERA_REQUEST:
                if (intent != null) {
                    setImageToHeadView(intent);
                }
               /* if (hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }*/

                break;

            case CODE_RESULT_REQUEST:
                if (intent != null) {
                    setImageToHeadView(intent);
                }

                break;
        }

        super.onActivityResult(requestCode, resultCode, intent);
    }

    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            userHead.setImageBitmap(photo);
        }
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }


}
