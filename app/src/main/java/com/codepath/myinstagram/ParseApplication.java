package com.codepath.myinstagram;

import android.app.Application;
import android.util.Log;

import com.codepath.myinstagram.model.Post;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

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

        ParseUser user = new ParseUser();

        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email@email.com");
        user.put("phone", "1234567890");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {

                } else {
                    Log.e("SignUp", "Error in sign up.");
                    e.printStackTrace();
                }
            }
        });

        /*Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        // monitors Parse OkHttp traffic
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.networkInterceptors().add(httpLoggingInterceptor);

        // set applicationId and server
        // any network interceptors must be added with the Configuration Builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
            .applicationId("myAppId")
            .clientKey(null)
            .clientBuilder(builder)
            .server("https://fbu-parse-instagram.herokuapp.com/parse/").build());

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();


        // sign up
        ParseUser user = new ParseUser();

        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email@email.com");
        user.put("phone", "1234567890");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {

                } else {
                    Log.e("SignUp", "Error in sign up.");
                    e.printStackTrace();
                }
            }
        });

        // log out
        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser();*/
    }


}
