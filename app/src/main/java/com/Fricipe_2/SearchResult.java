package com.Fricipe_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.Fricipe_2.RecipeListActivity;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity {
    ArrayList<String> listItems = new ArrayList<>();
    GridView gridView;
    ArrayAdapter<String> adapter;
    ArrayList<Integer> savedReceiveMatches;
    ArrayList<Integer> savedRecipeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Intent intent = getIntent();

        final ArrayList<Integer> reciveRecipeList = intent.getIntegerArrayListExtra("idrecipes");
        final ArrayList<Integer> receiveMatches = intent.getIntegerArrayListExtra("matches");
        this.savedReceiveMatches = receiveMatches;
        this.savedRecipeList = reciveRecipeList;
        final ArrayList<Integer> receiveMatchesNoDuplicates = intent.getIntegerArrayListExtra("matchesNoDuplicates");

        ArrayList<String> receiveMatchesNoDuplicatesString = createString(receiveMatchesNoDuplicates);

        gridView = findViewById(R.id.idGridSearchResult);
        adapter = new ArrayAdapter<String>(this, R.layout.searchresult_custom, receiveMatchesNoDuplicatesString);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Integer positionView = gridView.getPositionForView(view);
                Intent intent = new Intent(getApplicationContext(), RecipeListActivity.class);

                intent.putExtra("title", "Matching: " + receiveMatchesNoDuplicates.get(positionView) + " ingredients");
                ArrayList<Integer> positions = new ArrayList<Integer>();

                int valuePosition = receiveMatchesNoDuplicates.get(positionView);
                positions = findPositionIdResearch(valuePosition);

                ArrayList<Integer> idtosend = new ArrayList<Integer>();
                for (int i = 0; i < positions.size(); i++) {
                    idtosend.add(savedRecipeList.get(positions.get(i)));
                }
                intent.putExtra("list", idtosend);
                startActivity(intent);

            }
        });
    }


    public ArrayList<String> createString(ArrayList<Integer> array) {
        ArrayList<String> stringToShow = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {

            if (array.get(i) == 1) {
                stringToShow.add("Matched\n" + array.get(i) + "\ningredient");
            } else {
                stringToShow.add("Matched\n" + array.get(i) + "\ningredients");
            }

        }
        return stringToShow;
    }

    /*
    Method that research the position id of the matched when you have no duplicates, use to show the list of recipes related to matched ingredients
     */

    public ArrayList<Integer> findPositionIdResearch(int idIngredientMatched) {
        ArrayList<Integer> idResearched = new ArrayList<>();
        for (int i = 0; i < savedReceiveMatches.size(); i++) {
            if (savedReceiveMatches.get(i) == idIngredientMatched) {
                idResearched.add((i));
            }
        }
        return idResearched;

    }


}

