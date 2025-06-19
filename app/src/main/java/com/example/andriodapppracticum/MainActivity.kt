package com.example.andriodapppracticum

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Section for our layout variables
        val addSongButton: Button = findViewById(R.id.addSongToPlaylistBtn)

        // main screen input fields
        val songTitleInput: EditText = findViewById(R.id.songTitleInput)
        val artistNameInput: EditText = findViewById(R.id.artistNameInput)
        val songRatingInput: EditText = findViewById(R.id.songRatingInput)
        val userCommentInput: EditText = findViewById(R.id.userCommentInput)

        // main screen buttons
        val goToDetailsButton: Button = findViewById(R.id.goToDetailsBtn)
        val goHomeButton: Button = findViewById(R.id.goBackToHomePageBtn)
        val saveSongButton: Button = findViewById(R.id.saveSongBtn)

        // main screen text view prompt, header, and validation
        val mainScreenUserPrompt: TextView = findViewById(R.id.mainScreenInputPrompt)
        val songNumberHeader: TextView = findViewById(R.id.songNumberHeader)
        val ratingNumberValidation: TextView = findViewById(R.id.ratingNumberValidation)

        // initially have the main screen input fields, buttons, and text view as hidden
        songTitleInput.visibility = View.INVISIBLE
        artistNameInput.visibility = View.INVISIBLE
        songRatingInput.visibility = View.INVISIBLE
        userCommentInput.visibility = View.INVISIBLE
        goToDetailsButton.visibility = View.INVISIBLE
        goHomeButton.visibility = View.INVISIBLE
        mainScreenUserPrompt.visibility = View.INVISIBLE
        saveSongButton.visibility = View.INVISIBLE
        songNumberHeader.visibility = View.INVISIBLE
        ratingNumberValidation.visibility = View.INVISIBLE


        // Playlist Details page layout variables
        val displayCurrentSongDetailsBtn: Button = findViewById(R.id.displayCurrentSongDetails)
        val displayAvgRatingBtn: Button = findViewById(R.id.displayAvgRatingBtn)
        val songDetailsDisplayBox: TextView = findViewById(R.id.songDetailsDisplayBox)

        // initially we also hide our Playlist details page buttons
        displayCurrentSongDetailsBtn.visibility = View.INVISIBLE
        displayAvgRatingBtn.visibility = View.INVISIBLE
        displayAvgRatingBtn.visibility = View.INVISIBLE


        // Section for our global variable
        val songsArray: Array<String> = arrayOf()

        val artistArray: Array<String> = arrayOf()

        val ratingArray: Array<Int> = arrayOf()

        val commentArray: Array<String> = arrayOf()

        // global array index counter to keep track of what song we are on
        var globalSongCounter: Int = 0

        // store our original text prompts for out song input fields for the main screen
        val songTitleInputTextPrompt = songTitleInput.text
        val artistNameInputTextPrompt = songTitleInput.text
        val songRatingInputTextPrompt = songRatingInput.text
        val userCommentInputTextPrompt = userCommentInput.text


        // Section for our onClick events

        // when the user clicks the Add to playlist button
        addSongButton.setOnClickListener(){
            // first we hide our add to playlist button
            addSongButton.visibility = View.INVISIBLE

            // then we show our 4 input fields
            songTitleInput.visibility = View.VISIBLE
            artistNameInput.visibility = View.VISIBLE
            songRatingInput.visibility = View.VISIBLE
            userCommentInput.visibility = View.VISIBLE

            // show our save song button
            saveSongButton.visibility = View.VISIBLE

            // show our go to details and go home buttons
            goToDetailsButton.visibility = View.VISIBLE
            goHomeButton.visibility = View.VISIBLE

            // show our user prompt to indicate the user to fill out the details
            mainScreenUserPrompt.visibility = View.VISIBLE

            // show our song number header and display the first number
            songNumberHeader.visibility = View.VISIBLE
            // we increment the global counter by 1 to mitigate the 0 based indexing so our first
            // song shows as Song 1 not Song 0
            songNumberHeader.text = "Song ${globalSongCounter + 1}"
        }

        // clicking the save song button to add one of the 4 songs to the playlist
        saveSongButton.setOnClickListener(){
            // 1. grab the text values from the input fields
            val songTitle = songTitleInput.text.toString()
            val artistName = artistNameInput.text.toString()
            val songRatingString = songRatingInput.text.toString()
            val userComment = userCommentInput.text.toString()

            // 2. check to ensure the user has inputted a valid number for songRating
            // if they have then we can continue
            // else we need to display a message to enter a valid number
            val songRating = songRatingString.toIntOrNull()

            if (songRating == null){
                // if it's not a valid number then we show a pop up message
                // to tell the user to input a number for song rating
                ratingNumberValidation.visibility = View.VISIBLE


            } else {
                // if song rating is a valid number then we continue with the saving of song details

                // 3. store the values in our parallel arrays using
                // our global index counter to tell us to move through the 4 songs
                songsArray[globalSongCounter] = songTitle
                artistArray[globalSongCounter] = artistName
                ratingArray[globalSongCounter] = songRating
                commentArray[globalSongCounter] = userComment

                // 4. then we increment our global variable song counter until it has cycled through all 4 songs (0-3 indexing)
                // to ensure we go to the next song for saving and to update our song number header
                if (globalSongCounter < 3){
                    globalSongCounter++
                }

                // 5. then reset the input fields ready for the next song
                songTitleInput.text = songTitleInputTextPrompt
                artistNameInput.text = artistNameInputTextPrompt
                songRatingInput.text = songRatingInputTextPrompt
                userCommentInput.text = userCommentInputTextPrompt
            }
        }


        // clicking go To Details Button to navigate to the next screen
        goToDetailsButton.setOnClickListener(){
            // 1. hide our main page content
            songTitleInput.visibility = View.INVISIBLE
            artistNameInput.visibility = View.INVISIBLE
            songRatingInput.visibility = View.INVISIBLE
            userCommentInput.visibility = View.INVISIBLE
            saveSongButton.visibility = View.INVISIBLE
            goToDetailsButton.visibility = View.INVISIBLE
            mainScreenUserPrompt.visibility = View.INVISIBLE
            songNumberHeader.visibility = View.INVISIBLE

            // 2. show button which when clicked displays list of songs
            displayCurrentSongDetailsBtn.visibility = View.INVISIBLE

            // 3. show button which when clicked calculates and displays the average song rating
            displayAvgRatingBtn.visibility = View.INVISIBLE


            // 4. keep the goHomeButtonVisible
        }


        // when clicking the display details button it shows the list of songs
        // alongside the corresponding details using a loop
        displayCurrentSongDetailsBtn.setOnClickListener(){
            // unhide the songDetailsDisplayBox
            songDetailsDisplayBox.visibility = View.VISIBLE

            // make sure the user has saved atleast one song using our global index counter
            // if they have then show the song details
            // else show 'Song details currently unavailable please add a song'
            if (globalSongCounter > 0){
                // then loop through the existing songs in the playlist
                // using our 4 parallel arrays to display the corresponding details
                // here we need another index counter in order to access the corresponding details for the song
                var songDetailsIndexIterator = 0;
                for (song in songsArray){
                    // 1. first we get our song details and store them in variables
                    var currentSongTitle = songsArray[songDetailsIndexIterator]
                    var currentSongArtistName = artistArray[songDetailsIndexIterator]
                    var currentSongRating = ratingArray[songDetailsIndexIterator]
                    var currentSongCommet = commentArray[songDetailsIndexIterator]

                    // 2. then we append the details to our songDetailsDisplayBox
                }

            } else{

            }
        }



        // on click function to go back to home page:
        goHomeButton.setOnClickListener(){

        }


    }
}