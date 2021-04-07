package com.Fricipe_2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Fricipe_2.Helper.DatabaseHelper;
import com.Fricipe_2.Helper.PictureHelper;
import com.Fricipe_2.Model.RecipeItem;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {
    PictureHelper pictureHelper = new PictureHelper();
    RecipeItem recipeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        //Connect DB
        final DatabaseHelper databaseHelper = new DatabaseHelper(this, "Recipes.db", null, 1);

        //Show backbutton
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        //set userid
        SharedPreferences pref = getSharedPreferences("Login", Activity.MODE_PRIVATE);
        final String userID = pref.getString("userID", "");

        //connect with view
        TextView recipeName = findViewById(R.id.txt_recipeName);
        TextView author = findViewById(R.id.txt_recipeAuthor);
        TextView uploadDate = findViewById(R.id.txt_recipeUploaddate);
        TextView country = findViewById(R.id.txt_recipeCountry);
        TextView ingredients = findViewById(R.id.txt_recipeIngredients);
        TextView description = findViewById(R.id.txt_recipeDescription);
        TextView howto = findViewById(R.id.txt_recipeHowto);
        ImageView mainImg = findViewById(R.id.img_recipeMainImg);
        CheckBox like = findViewById(R.id.chk_recipeLike);

        //set for received data
        Intent intent = getIntent();
        String name = intent.getStringExtra("Fricipe_2");
        //Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
        //set to selected Fricipe_2 data
        //recipeItem = (RecipeItem)intent.getSerializableExtra("Fricipe_2");


        recipeItem = databaseHelper.recipesSelectByName(name);

        if (recipeItem != null) {
            //data bind
            mainImg.setImageBitmap(pictureHelper.getBitmapFromByteArray(recipeItem.get_mainImg()));
            recipeName.setText(recipeItem.get_recipeName());
            author.setText(recipeItem.get_author());
            uploadDate.setText(recipeItem.get_uploadDate());
            country.setText(recipeItem.get_category());
            description.setText(recipeItem.get_Description());
            howto.setText(recipeItem.get_howTo());

            ArrayList<String> ingredentList = databaseHelper.selectRecipeByIngredientId(recipeItem.get_id());
            String tempIngre = "";

            for (int i = 0; i < ingredentList.size(); i++) {
                tempIngre += ingredentList.get(i);
                if (i != (ingredentList.size() - 1))
                    tempIngre += " / ";
            }

            ingredients.setText(tempIngre);

        } else {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }

        if (userID != "") {
            like.setChecked(databaseHelper.like_GetLikeYNByUserId(recipeItem.get_id()));
        }

        like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (userID == "") {
                    if (isChecked) {
                        int count = databaseHelper.addToFavorite(recipeItem.get_id());

                    } else {
                        int count = databaseHelper.deleteFromFavorite(recipeItem.get_id());
                    }
                } else {

                    Toast.makeText(getBaseContext(), Boolean.toString(isChecked), Toast.LENGTH_SHORT).show();
                }
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
