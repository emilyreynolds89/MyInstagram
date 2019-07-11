package com.codepath.myinstagram;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.codepath.myinstagram.model.Post;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    Post post;

    TextView tvUsername;
    ImageView ivImage;
    TextView tvDescription;
    TextView tvCreatedAt;
    ImageButton ibLike;
    ImageButton ibComment;
    TextView tvLikes;
    TextView tvComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvUsername = findViewById(R.id.tvUsername);
        ivImage = findViewById(R.id.ivImage);
        tvDescription = findViewById(R.id.tvDescription);
        tvCreatedAt = findViewById(R.id.tvCreatedAt);
        ibLike = findViewById(R.id.ibLike);
        ibComment = findViewById(R.id.ibMessage);
        tvLikes = findViewById(R.id.tvLikes);
        tvComments = findViewById(R.id.tvComments);

        post = (Post) getIntent().getSerializableExtra("post");

        tvUsername.setText(post.getUser().getUsername());
        tvDescription.setText(post.getDescription());
        tvCreatedAt.setText(post.getCreatedAt().toString());

        List<String> comments = post.getComments();
        if (comments != null) {
            String comment = "";
            for(String c: comments) {
                comment += c;
            }
            tvComments.setText(comment);
        } else {
            tvComments.setText("");
        }

        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(getBaseContext()).load(image.getUrl()).into(ivImage);
        }

        ParseUser currentUser = ParseUser.getCurrentUser();
        List<String> users = post.getLikes();

        if (users != null && users.contains(currentUser.getObjectId())) {
            ibLike.setBackgroundResource(R.drawable.ufi_heart_active);
        } else {
            ibLike.setBackgroundResource(R.drawable.ufi_heart);
        }

        if (users != null) {
            tvLikes.setText(post.getLikes().size() + " likes");
        } else {
            tvLikes.setText("0 likes");
        }

        ibLike.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibLike:
                if (post != null) {
                    int size;

                    final ParseUser currentUser = ParseUser.getCurrentUser();
                    List<String> users = post.getLikes();
                    if (users == null) {
                        users = new ArrayList<String>();
                    }
                    if (users != null) {
                        size = users.size();
                    } else {
                        size = 0;
                    }
                    if (users != null && users.contains(currentUser.getObjectId())) {
                        ibLike.setBackgroundResource(R.drawable.ufi_heart);
                        users.remove(currentUser.getObjectId());
                        tvLikes.setText(size - 1 + " likes");
                        post.setLikes(users);
                    } else {
                        ibLike.setBackgroundResource(R.drawable.ufi_heart_active);
                        users.add(currentUser.getObjectId());
                        tvLikes.setText(size + 1 + " likes");
                        post.setLikes(users);
                    }
                    post.saveInBackground();
                }

                break;
        }
    }
}
