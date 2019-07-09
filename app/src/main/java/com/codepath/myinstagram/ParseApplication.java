package com.codepath.myinstagram;

import android.app.Application;

import com.codepath.myinstagram.model.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("myAppId")
                .clientKey("myMasterKey")
                .server("http://fbu-parse-instagram.herokuapp.com/parse")
                .build();

        Parse.initialize(configuration);


    }


}
