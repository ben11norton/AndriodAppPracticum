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

        // main screen text view prompt
        val mainScreenUserPrompt: TextView = findViewById(R.id.mainScreenInputPrompt)
        val songNumberHeader: TextView = findViewById(R.id.songNumberHeader)

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


        // Section for our global variable
        val songsArray: Array<String> = arrayOf()

        val artistArray: Array<String> = arrayOf()

        val ratingArray: Array<Int> = arrayOf()

        val commentArray: Array<String> = arrayOf()

        // global array index counter to keep track of what song we are on
        var globalSongCounter: Int = 0


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

            // 3. store the values in our parallel arrays using
            // our global index counter to tell us to move through the 4 songs
            songsArray[globalSongCounter] = songTitle
            artistArray[globalSongCounter] = artistName
            ratingArray[globalSongCounter] = songRatingString.toInt()
            commentArray[globalSongCounter] = userComment

            // 4. then we increment our global variable song counter
            // to ensure we go to the next song
            globalSongCounter++

        }


    }
}