package com.codepath.myinstagram;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.myinstagram.model.Post;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    static Context context;
    static List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvUsername;
        ImageView ivImage;
        TextView tvDescription;
        TextView tvCreatedAt;
        ImageButton ibLike;
        ImageButton ibComment;
        TextView tvLikes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            ibLike = itemView.findViewById(R.id.ibLike);
            ibComment = itemView.findViewById(R.id.ibMessage);
            tvLikes = itemView.findViewById(R.id.tvLikes);

            itemView.setOnClickListener(this);

            ibLike.setOnClickListener(this);
            ibComment.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            switch (view.getId()) {

                case R.id.item:
                    if (position != RecyclerView.NO_POSITION) {
                        Post post = posts.get(position);

                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("post", (Serializable) post);
                        context.startActivity(intent);
                    }
                    break;
                case R.id.ibLike:
                    if (position != RecyclerView.NO_POSITION) {
                        Post post = posts.get(position);
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

                case R.id.ibMessage:
                    comment(view.getRootView(), position);
                    break;
            }
        }

        public void bind(Post post) {
            tvUsername.setText(post.getUser().getUsername());
            tvDescription.setText(post.getDescription());

            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }

            tvCreatedAt.setText(post.getCreatedAt().toString());

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


        }

    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Post> newPosts){
        posts.addAll(newPosts);
        notifyDataSetChanged();
    }

    public static void comment(View view, int position) {
        Post post = posts.get(position);
        Intent intent = new Intent(view.getContext(), CommentActivity.class);
        intent.putExtra("post", (Serializable) post);
        context.startActivity(intent);
    }

}
