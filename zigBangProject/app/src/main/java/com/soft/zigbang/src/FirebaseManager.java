package com.soft.zigbang.src;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseManager {
    private FirebaseStorage storage;
    private StorageReference storageUploadRef;
    private StorageReference storageDownRef;

    private final String storageUrl = "gs://zigbang-863a2.appspot.com";
    private final String TAG = "FirebaseManager";

    public FirebaseManager() {
        storage = FirebaseStorage.getInstance();
    }

    /**
     * Storage 이미지 다운로드 경로 가져오기
     */
    public void getDownloadImageUrl(String fileName) {
        storageDownRef = storage.getReferenceFromUrl(storageUrl).child("images").child(fileName);
        storageDownRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()) {
                    Log.d(TAG, task.getResult().toString());
                } else {
                    Log.d(TAG, "Image Download URL load Fail");
                }
            }
        });
    }
}
