This app was developed as a learning experience of the Kotlin language and the Android platform.

The app provides a random joke to the user. The joke is loaded from the icanhazdadjoke REST service.
https://icanhazdadjoke.com

### Features
 - Loads joke from HTTP REST source
 - Saves / Deletes liked jokes to / from the SQLite DB
 - Allows sharing with other Apps
 - Passes data between activities
 - Favourites or liked jokes are read from the DB and displayed in a list
 - liked jokes are read with pagination for performance reasons
 - loads more liked jokes from the DB when the end of the list is reached
 - Does not try to load more jokes from DB when the end of the DB is reached


### TO-DO List
 - Animations for loading
 - Reskin the UI
 - juice up the UI. Try lottie Air BnB lib perhaps?
 - back button, gesture
 - Include Google advertising
 - Include UI testing
 - Bug - pagination, when we reach the end of the list of favourites, we load more jokes from the
        DB. The list is reset to the top of the listview and not from the current point
 - Bug - when starting up, if a joke was already liked, we check the like check box, this triggers
        an action which likes it again, causing it to get pushed on the favourites list again
