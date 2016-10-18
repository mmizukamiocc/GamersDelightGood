package edu.orangecoastcollege.cs273.mpaulding.gamersdelight;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class GameListActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Game> gamesList;
    private GameListAdapter gamesListAdapter;
    private ListView gamesListView;
    private EditText descriptionEditText;
    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        this.deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);

        db.addGame(new Game("League of Legends", "Multiplayer online battle arena", 4.5f, "lol.png"));
        db.addGame(new Game("Dark Souls III", "Action role-playing", 4.0f, "ds3.png"));
        db.addGame(new Game("The Division", "Single player experience", 3.5f, "division.png"));
        db.addGame(new Game("Doom FLH", "First person shooter", 2.5f, "doomflh.png"));
        db.addGame(new Game("Battlefield 1", "Single player campaign", 5.0f, "battlefield1.png"));

        // TODO:  Populate all games from the database into the list

        gamesList = db.getAllGames();
        // TODO:  Create a new ListAdapter connected to the correct layout file and list
        gamesListAdapter = new GameListAdapter(this,R.layout.game_list_item,gamesList);

        // TODO:  Connect the ListView with the ListAdapter
        gamesListView = (ListView) findViewById(R.id.gameListView);

        gamesListView.setAdapter(gamesListAdapter);

        descriptionEditText = (EditText) findViewById(R.id.descriptionEditText);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
    }

    public void viewGameDetails(View view) {

        // TODO: Use an Intent to start the GameDetailsActivity with the data it needs to correctly inflate its views.
    }

    public void addGame(View view)
    {
        String name = nameEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        // TODO:  Add a game to the database, list, list adapter
        if(name.isEmpty()||description.isEmpty())
        {
            Toast.makeText(this,"Game name and description cannot be empty.",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Game newGame = new Game(name,description,0);

            gamesListAdapter.add(newGame);
            db.addGame(newGame);

        }

        // TODO:  Make sure the list adapter is updated
        gamesListAdapter.notifyDataSetChanged();
        // TODO:  Clear all entries the user made (edit text and rating bar)

        nameEditText.setText("");
        descriptionEditText.setText("");

    }

    public void clearAllGames(View view)
    {
        // TODO:  Delete all games from the database and lists
        gamesList.clear();
        db.deleteAllGames();
        gamesListAdapter.notifyDataSetChanged();
    }

}
