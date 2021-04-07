package com.Fricipe_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.Fricipe_2.Helper.DatabaseHelper;
import com.Fricipe_2.Model.RecipeItem;
import java.util.ArrayList;

public class RecipeListActivity extends AppCompatActivity {

    ArrayList<RecipeItem> recipes = new ArrayList<>();
    //Connect with screen elements
    TextView txtTitle;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        //Show backbutton
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


    }

    @Override
    protected void onStart() {
        super.onStart();  // Always call the superclass method first
        recipes.clear();
        //Connect with screen elements
        txtTitle = findViewById(R.id.txt_recipeListTitle);
        listView = findViewById(R.id.listview_recipelist);

        //Connect with databeas
        DatabaseHelper databaseHelper = new DatabaseHelper(this, "Recipes.db", null, 1);

        String title = "";

        //Get the category or matching information from previous page
        Intent intent = getIntent();
        if (intent.hasExtra("category")) {
            title = intent.getStringExtra("category");
            //set product list title from intent key "category"
            txtTitle.setText(title);

            recipes = databaseHelper.recipesSelectByCategory(title);
        } else if (intent.hasExtra("title")) {
            title = intent.getStringExtra("title");
            //set product list title from intent key "category"
            txtTitle.setText(title);
            ArrayList<Integer> reciveRecipeList = intent.getIntegerArrayListExtra("list");
            for (int i = 0; i < reciveRecipeList.size(); i++) {
                RecipeItem getRecipe = databaseHelper.recipesSelectById(reciveRecipeList.get(i));
                recipes.add(getRecipe);
            }
        }

        //set ListView with Data
        listView.setAdapter(new RecipeListAdapter(this, recipes, R.layout.activity_recipe_list_item));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                RecipeItem selectRecipe = recipes.get(position);
                Intent intent = new Intent(getApplicationContext(), RecipeActivity.class);
                intent.putExtra("Fricipe_2", selectRecipe.get_recipeName());
                startActivity(intent);
                //Toast.makeText(view.getContext(),selectRecipe.get_recipName(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
