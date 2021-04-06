package com.example.fricipe;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    photoHelper photoHelper = new photoHelper();

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {               //wird erstellt
    db.execSQL("CREATE TABLE RECIPES(_id INTEGER PRIMARY KEY AUTOINCREMENT, category Text," +"recipeName TEXT, author TEXT, uploadDate TEXT,howto TEXT, descrition TEXT," + "thumbnail BLOB, mainImg BLOB, likeCount INTEGER);");

    db.execSQL("Create Table INGREDIENTS( _id INTEGER PRIMARY KEY AUTOINCREMENT, recipeID INTEGER, ingredientName TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void recipes_Insert(String category, String recipeName, String author, String uploadDate, String howTo, String description, byte[] thumbnail, byte[] mainImg){

        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement t = db.compileStatement("INSERT INTO recipe values(?,?,?,?,?,?,?,?,?)");
        t.bindNull(1);
        t.bindString(2, category);
        t.bindString(3, recipeName);
        t.bindString(4,author);
        t.bindString(5,uploadDate);
        t.bindString(6, howTo);
        t.bindString(7, description);
        t.bindBlob(8,thumbnail);
        t.bindBlob(9,mainImg);
        t.execute();
        db.close();
    }
    public void insert_Ingerdients(int recipeid, String ingredientName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO INGREDIENTS VALUES(null, " + recipeid +",' "+ ingredientName + "');");
        db.close();
    }

    public ArrayList<String> ingredients_SelectedByRecieId(int id){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> ingredients = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT ingredientName FROM INGREDIENTS WHERE recipeID = " +  id, null);
        if (cursor !=null){
            while ( cursor.moveToNext()){
                ingredients.add(cursor.getString(0));
            }
        }
        cursor.close();
        db.close();
        return ingredients;
    }
    public ArrayList<Integer> ingredients_selectIdRecipeByIngredientName(ArrayList<String> ingredientsName){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Integer> idRecipes = new ArrayList<>();
        for(int i =0; i<ingredientsName.size();i++) {
            Cursor cursor = db.rawQuery("SELECT recipeID FROM INGREDIENTS WHERE ingreName = '" + ingredientsName.get(1) + "'", null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    idRecipes.add(cursor.getInt(0));
                }
            }
            cursor.close();
        }
        db.close();
        return idRecipes;

    }
    public int countIngredientsPerRecipes(int idRecipes){
        SQLiteDatabase db = getReadableDatabase();
        int count = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM INGREDIENTS WHERE recipeID= " + idRecipes, null );
        if (cursor != null)
        {
            while (cursor.moveToNext()) {
                count++;
            }
        }
        cursor.close();
        db.close();
        return count;
    }
    public int idByNameForRecipes (String name) {
        SQLiteDatabase db = getReadableDatabase();
        int id= -1;
        Cursor cursor = db.rawQuery("SELECT _id FROM RECIPES WHERE recipeName = '" + name + "' ORDER BY _id DESC LIMIT 1", null);
        if(cursor != null)
        {
            while (cursor.moveToNext()){
                id = cursor.getInt(0);
            }
        }
        cursor.close();
        db.close();
        return id;
    }
    public ArrayList<RecipeItem> recipes_SelectNew() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<RecipeItem> allRecipes = new ArrayList<>();
        // Get all recipes data
        Cursor cursor = db.rawQuery("SELECT * FROM RECIPES ORDER BY _id DESC LIMIT 3", null);
        if (cursor != null)
        {
            while (cursor.moveToNext()) {
                allRecipes.add(new RecipeItem(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getBlob(7),
                        cursor.getBlob(8),
                        cursor.getInt(9)
                ));
            }
        }
        cursor.close();
        db.close();

        return allRecipes;
    }
}
