package com.soft.zigbang.src.house.oneroom.sell;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.soft.zigbang.R;
import com.soft.zigbang.src.BaseActivity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import static com.soft.zigbang.src.ApplicationClass.DATE_FORMAT;

public class SellHouseActivity extends BaseActivity {

    ImageView imageView;
    ImageView imageView2;

    FirebaseStorage mStorage;
    StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_house);

        checkCameraPermission();

        imageView = findViewById(R.id.sell_house_iv_image);
        imageView2 = findViewById(R.id.sell_house_iv_image2);

        mStorage = FirebaseStorage.getInstance();

    }

    public void sellHouseOnClick(View view) {
        switch (view.getId()) {
            case R.id.sell_house_btn_image:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 101);
                break;
            case R.id.sell_house_btn_image2:
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, 102);
                break;
            case R.id.sell_house_btn_image3:
                getImage();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String uri = "";
        Uri filePath = null;
        if (resultCode == RESULT_OK) {
            if (requestCode == 101) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    imageView.setImageBitmap(img);

                    filePath = data.getData();
                } catch (Exception e) {

                }
            } else {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imageBitmap);
            }
            uploadImage(filePath);
        }
    }

    public void uploadImage(Uri filePath) {

        showProgressDialog();

        String filename = DATE_FORMAT.format(new Date()) + ".png";

        // storage 주소와 폴더 파일명 지정
        mStorageRef = mStorage.getReferenceFromUrl("gs://zigbang-863a2.appspot.com").child("images/" + filename);
        mStorageRef.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        hideProgressDialog();
                        showCustomToast("업로드 성공");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showCustomToast("업로드 실패");
                    }
                });
    }

    private void getImage() {
        mStorageRef = mStorage.getReferenceFromUrl("gs://zigbang-863a2.appspot.com").child("images").child("1_ap_dunchon.png");
        mStorageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()) {
                    Glide.with(SellHouseActivity.this)
                            .load(task.getResult())
                            .override(200, 200)
                            .into(imageView2);
                } else {
                    showCustomToast("이미지 다운로드 실패");
                }
            }
        });

    }

    private void checkCameraPermission() {
        TedPermission.with(getApplicationContext())
                .setRationaleMessage("카메라 권한이 필요합니다.\n(거부할 경우 진행불가)")
                .setDeniedMessage("카메라 권한을 거부하셨습니다.")
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();
    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
//            Toast.makeText(getApplicationContext(), "권한이 허용됨", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(getApplicationContext(), "집 내놓기가 취소됩니다.", Toast.LENGTH_SHORT).show();
            finish();
        }
    };
}