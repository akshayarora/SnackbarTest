# SnackbarTest
This sample project exhibits a problem with the Snackbar implementation in Google Design Support Library. I believe this to be a combination of using ScrollViews and custom transitions. The use of the two, prevents the Snackbar from appearing at all on the screen after the transition, but will appear once you interact with the Activity again.

You can see how this happens by compiling this project with Android Studio and then comparing the two methods of dismissing the SecondActivity. One uses a custom transition and the other does not.
