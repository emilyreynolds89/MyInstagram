package com.codepath.myinstagram.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.myinstagram.PostsAdapter;
import com.codepath.myinstagram.R;
import com.codepath.myinstagram.model.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment {

    private RecyclerView rvFeed;
    private PostsAdapter adapter;
    private List<Post> mPosts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragement_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvFeed = view.findViewById(R.id.rvFeed);

        mPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), mPosts);

        rvFeed.setAdapter(adapter);
        rvFeed.setLayoutManager(new LinearLayoutManager(getContext()));

        queryPosts();

    }

    private void queryPosts() {
        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        postQuery.include(Post.KEY_USER);

        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e("Query", "Error with query");
                    e.printStackTrace();
                } else {
                    mPosts.addAll(posts);
                    adapter.notifyDataSetChanged();

                    for(int i = 0; i < posts.size(); i++) {
                        Post post = posts.get(i);
                        Log.d("Query", "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                    }
                }
            }
        });


    }
}
