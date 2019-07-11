package com.codepath.myinstagram;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.codepath.myinstagram.model.Post;
import com.parse.ParseFile;

public class DetailActivity extends AppCompatActivity {

    Post post;

    TextView tvUsername;
    ImageView ivImage;
    TextView tvDescription;
    TextView tvCreatedAt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvUsername = findViewById(R.id.tvUsername);
        ivImage = findViewById(R.id.ivImage);
        tvDescription = findViewById(R.id.tvDescription);
        tvCreatedAt = findViewById(R.id.tvCreatedAt);

        post = (Post) getIntent().getSerializableExtra("post");

        tvUsername.setText(post.getUser().getUsername());
        tvDescription.setText(post.getDescription());
        tvCreatedAt.setText(post.getCreatedAt().toString());

        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(getBaseContext()).load(image.getUrl()).into(ivImage);
        }

    }

}
