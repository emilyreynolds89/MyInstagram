package com.codepath.myinstagram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.parse.ParseFile;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class PostActivity extends AppCompatActivity {

    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public String photoFileName = "photo.jpg";
    File photoFile;

    Bitmap bitmapImage;

    ImageView ivPreview;
    FloatingActionButton actionButton;
    ImageButton btnNext;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        ivPreview = findViewById(R.id.ivPreview);
        actionButton = findViewById(R.id.floatingActionButton);
        btnNext = findViewById(R.id.btnNext);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.postItem);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLaunchCamera(view);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.profileItem:
                        Intent profileIntent = new Intent(PostActivity.this, ProfileActivity.class);
                        startActivity(profileIntent);
                    case R.id.postItem:
                        return true;
                    case R.id.homeItem:
                        Intent homeIntent = new Intent(PostActivity.this, HomeActivity.class);
                        startActivity(homeIntent);
                        return true;
                    default:
                        return true;
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bitmapImage == null) {
                    Toast.makeText(PostActivity.this, "No image was taken", Toast.LENGTH_LONG).show();
                } else {
                    // compress bitmap to pass between activities
                    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                    bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, bStream);

                    byte [] byteArray = bStream.toByteArray();

                    ParseFile parseFile = new ParseFile(photoFile);

                    Intent intent = new Intent(PostActivity.this, DescriptionActivity.class);
                    intent.putExtra("byteArray", byteArray);
                    intent.putExtra("file", photoFile);
                    startActivity(intent);
                }
            }
        });

    }

    public void onLaunchCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoFile = getPhotoFileUri(photoFileName);

        // wrap File object into a content provider
        Uri fileProvider = FileProvider.getUriForFile(PostActivity.this,
                "com.codepath.fileprovider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }

    }

    // returns the File for a photo stored on disk given the fileName
    public File getPhotoFileUri(String fileName) {
        // get safe storage directory for photos
        File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "PostActivity");

        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.d("PostActivity", "Failed to create directory");
        }

        File file = new File(mediaStorageDir.getPath() + File.separator + fileName);
        return file;

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());

                ivPreview = findViewById(R.id.ivPreview);
                ivPreview.setImageBitmap(takenImage);

                bitmapImage = takenImage;
            } else {
                Toast.makeText(this, "No image was taken", Toast.LENGTH_LONG).show();
            }
        }
    }
}
