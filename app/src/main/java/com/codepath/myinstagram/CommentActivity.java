package com.codepath.myinstagram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codepath.myinstagram.model.Post;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {

    EditText etComment;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        etComment = findViewById(R.id.etComment);
        btnSubmit = findViewById(R.id.btnSubmit);

        final Post post = (Post) getIntent().getSerializableExtra("post");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etComment.getText().toString() == null) {
                    Toast.makeText(CommentActivity.this, "Please enter a comment", Toast.LENGTH_LONG).show();
                } else {
                    String comment = etComment.getText().toString();
                    comment = post.getUser().getUsername() + ": " + comment + "\n";
                    List<String> comments = post.getComments();
                    if (comments == null) {
                        comments = new ArrayList<String>();
                    }
                    comments.add(comment);
                    post.setComment(comments);
                    post.saveInBackground();

                    Intent intent = new Intent(CommentActivity.this, PostsAdapter.class);
                    intent.putExtra("post", (Serializable) post);
                    intent.putExtra("comment", comment);
                    startActivity(intent);
                }
            }
        });
    }
}
