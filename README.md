# Project 3 - MyInstagram

MyInstagram is an android app and social media platform for sharing images between friends. The app follows a similar purpose and 
design to Instagram, with many similar features. The app maintains its own server with data on user accounts and posts.

Submitted by: Emily Reynolds

Time spent: 22 hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User sees app icon in home screen
* [x] User can sign up to create a new account using Parse authentication
* [x] User can log in and log out of his or her account
* [x] The current signed in user is persisted across app restarts
* [x] User can take a photo, add a caption, and post it to "Instagram"
* [x] User can view the last 20 posts submitted to "Instagram"
* [x] User can pull to refresh the last 20 posts submitted to "Instagram"
* [x] User can tap a post to view post details, including timestamp and caption

The following **stretch** features are implemented:

* [x] Style the login page to look like the real Instagram login page
* [x] Style the feed to look like the real Instagram feed
* [ ] The user should switch between different tabs - viewing all posts (feed view), capture (camera and photo gallery view) and profile tabs 
      (posts made) using fragments and a Bottom Navigation View
* [x] User can load more posts once he or she reaches the bottom of the feed using endless scrolling
* [x] Show the username and creation time for each post
* [ ] After the user submits a new post, show an indeterminate progress bar while the post is being uploaded to Parse
* [ ] User Profiles allow the logged in user to add a profile photo, display the profile photo with each post, and tapping on a post's username or profile 
      photo goes to that user's profile page and shows a grid view of the user's posts
* [x] User can comment on a post and see all comments for each post in the post details screen
* [x] User can like a post and see number of likes for each post in the post details screen

The following **additional** features are implemented:

* [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/link/to/your/gif/file.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.

I had some difficulty implementing the like and comment features, such that the data was updated in the server 
and maintained across uses.

## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
