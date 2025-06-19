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

        // main screen text view prompt
        val mainScreenUserPrompt: TextView = findViewById(R.id.mainScreenInputPrompt)

        // initally have the main screen input fields, buttons, and text view as hidden
        songTitleInput.visibility = View.INVISIBLE
        artistNameInput.visibility = View.INVISIBLE
        songRatingInput.visibility = View.INVISIBLE
        userCommentInput.visibility = View.INVISIBLE
        goToDetailsButton.visibility = View.INVISIBLE
        goHomeButton.visibility = View.INVISIBLE
        mainScreenUserPrompt.visibility = View.INVISIBLE


        // Section for our global variable
        val songsArray: Array<String> = arrayOf()

        val artistArray: Array<String> = arrayOf()

        val ratingArray: Array<Int> = arrayOf()

        val commentArray: Array<String> = arrayOf()


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

            // show our go to details and go home buttons
            goToDetailsButton.visibility = View.VISIBLE
            goHomeButton.visibility = View.VISIBLE

            // show our user prompt to indicate the user to fill out the details
            mainScreenUserPrompt.visibility = View.VISIBLE
        }




    }
}