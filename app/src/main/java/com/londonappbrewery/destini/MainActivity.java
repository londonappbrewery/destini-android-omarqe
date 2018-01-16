package com.londonappbrewery.destini;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    // TODO: Steps 4 & 8 - Declare member variables here:
    int mStoryIndex;
    boolean doRestart = false;
    TextView storyTextView;
    Button mButtonTop, mButtonBottom;
    DestiniStory[] mStories = new DestiniStory[] {
            new DestiniStory(R.string.T1_Story, R.string.T1_Ans1, R.string.T1_Ans2, 3, 2),
            new DestiniStory(R.string.T2_Story, R.string.T2_Ans1, R.string.T2_Ans2, 3, 4),
            new DestiniStory(R.string.T3_Story, R.string.T3_Ans1, R.string.T3_Ans2, 6, 5),
            new DestiniStory(R.string.T4_End),
            new DestiniStory(R.string.T5_End),
            new DestiniStory(R.string.T6_End)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
        mButtonTop       = (Button) findViewById(R.id.buttonTop);
        mButtonBottom    = (Button) findViewById(R.id.buttonBottom);

        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:
        mButtonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStory(true);
            }
        });

        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
        mButtonBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStory(false);
            }
        });
    }

    /**
     * Update the story
     *
     * This method updates the current story with the next story. As we all know, the next story
     * totally depends on the player's choice.
     *
     * @param  isTopButton      Determine which button is being clicked by the player.
     * @since  1.0
     */
    public void updateStory(boolean isTopButton){
        // Create a new variable that points to the current story for easier use.
        DestiniStory thisStory = mStories[mStoryIndex];

        // Get the next story index
        if ( !doRestart ) {
            mStoryIndex = isTopButton ? thisStory.getNextStoryTop() : thisStory.getNextStoryBottom();
        } else {
            mStoryIndex = 0;
        }

        // Point thisStory to a new story object since we've updated the mStoryIndex to the next story
        thisStory   = mStories[mStoryIndex];

        // The story resource ID and its corresponding buttons
        int storyId         = thisStory.getStoryId(),
            buttonTopTxt    = thisStory.getButtonTop(),
            buttonBottomTxt = thisStory.getButtonBottom();

        storyTextView    = (TextView) findViewById(R.id.storyTextView);
        mButtonTop       = (Button) findViewById(R.id.buttonTop);
        mButtonBottom    = (Button) findViewById(R.id.buttonBottom);

        // Update the story text
        storyTextView.setText(storyId);

        // Reset the doRestart back to it's initial value, false.
        doRestart = false;

        // Update the story buttons. If the player reaches the end of the game, prompt an alert.
        if ( (buttonTopTxt + buttonBottomTxt) != 0 ) {
            mButtonTop.setText(buttonTopTxt);
            mButtonBottom.setText(buttonBottomTxt);
        } else {
            getEndAlert();
        }
    }

    /**
     * Get the alert
     *
     * When the game has reached the end, we prompt the player whether he wants to restart the game
     * or exit the game instead.
     *
     * @since   1.0
     */
    public void getEndAlert(){
        AlertDialog.Builder alert   = new AlertDialog.Builder(this);
        DestiniStory thisStory      = mStories[mStoryIndex];

        // Set alert message: Do you want to restart the game?
        alert.setTitle(R.string.alert_text);
        alert.setMessage(thisStory.getStoryId());
        alert.setCancelable(false);

        // Configure the restart button, this shall restart the game to the first story.
        alert.setPositiveButton(R.string.restart, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Perform the restart procedure
                doRestart = true;
                updateStory(true);
            }
        });

        // Configure the exit button, this shall close the application.
        alert.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        // Show the alert
        alert.show();
    }
}
