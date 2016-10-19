package edu.orangecoastcollege.cs273.mpaulding.gamersdelight;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GameDetailsActivity extends AppCompatActivity {

    private ImageView gameDetailsImageView;
    private TextView gameDetailsNameTextView;
    private TextView gameDetailsDescriptionTextView;
    private RatingBar gameDetailsRatingBar;

    private Context context =(Context) this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        gameDetailsImageView = (ImageView) findViewById(R.id.gameDetailsImageView);
        gameDetailsNameTextView = (TextView) findViewById(R.id.gameDetailsNameTextView);
        gameDetailsDescriptionTextView = (TextView) findViewById(R.id.gameDetailsDescriptionTextView);
        gameDetailsRatingBar = (RatingBar) findViewById(R.id.gameDetailsRatingBar);

        // TODO:  Use the Intent object sent from GameListActivity to populate the Views on
        Intent detailsIntent = getIntent();
        String imageName = detailsIntent.getStringExtra("ImageName");
        String name = detailsIntent.getStringExtra("Name");
        String description = detailsIntent.getStringExtra("Description");
        float rating = detailsIntent.getFloatExtra("Rating",0);

        // TODO:  this layout, including the TextViews, RatingBar and ImageView with the Game details.

        AssetManager am =  context.getAssets();
        try {
            InputStream stream = am.open(imageName);
            Drawable logo = Drawable.createFromStream(stream,name);
            gameDetailsImageView.setImageDrawable(logo);
        }
        catch (IOException ex)
        {
            Log.e("OC MusicEvents","Error loading " + imageName,ex);
        }

        gameDetailsNameTextView.setText(name);
        gameDetailsDescriptionTextView.setText(description);
        gameDetailsRatingBar.setRating(rating);
    }
}
