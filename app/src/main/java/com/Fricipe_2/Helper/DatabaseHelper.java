package com.Fricipe_2.Helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.Fricipe_2.Model.CategoryItem;
import com.Fricipe_2.Model.RecipeItem;
import com.Fricipe_2.Model.RecipeResult;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    PictureHelper pictureHelper = new PictureHelper();

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Hier wird die Datenbank für die Rezepte erstellt.

        db.execSQL("CREATE TABLE RECIPES( _id INTEGER PRIMARY KEY AUTOINCREMENT, category TEXT,"
                + "recipeName TEXT, author TEXT, uploardDate TEXT, howTo TEXT, description TEXT,"
                + "thumbnail BLOB, mainImg BLOB, likeCount INTEGER);");

        db.execSQL("CREATE TABLE INGREDIENTS( _id INTEGER PRIMARY KEY AUTOINCREMENT, recipeID INTEGER, ingreName TEXT);");

        db.execSQL("CREATE TABLE LIKECOUNT( _id INTEGER PRIMARY KEY AUTOINCREMENT, userID TEXT, recipeID INTEGER);");

        db.execSQL("CREATE TABLE USER( _id INTEGER PRIMARY KEY AUTOINCREMENT, userID TEXT, password TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertRecipe(String category, String recipeName, String author,
                             String uploadDate, String howTo, String description,
                             byte[] thumbnail, byte[] mainImg, int likeCount) {
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement p = db.compileStatement("INSERT INTO recipes values(?,?,?,?,?,?,?,?,?,?);");
        p.bindNull(1);
        p.bindString(2, category);
        p.bindString(3, recipeName);
        p.bindString(4, author);
        p.bindString(5, uploadDate);
        p.bindString(6, howTo);
        p.bindString(7, description);
        p.bindBlob(8, thumbnail);
        p.bindBlob(9, mainImg);
        p.bindLong(10, likeCount);
        p.execute();
        db.close();
    }

    public void insertIngredients(int recipeid, String ingreName) {
        SQLiteDatabase db = getWritableDatabase();

        //Hier wird das Rezept in die Datenbank eingefügt
        db.execSQL("INSERT INTO INGREDIENTS VALUES(null, " + recipeid + ", '" + ingreName + "');");

        db.close();
    }

    public ArrayList<String> selectRecipeByIngredientId(int id) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> ingredients = new ArrayList<>();

        //sucht nach allen verfügbaren Rezepten
        Cursor cursor = db.rawQuery("SELECT ingreName FROM INGREDIENTS WHERE recipeID = " + id, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ingredients.add(cursor.getString(0));
            }
        }
        cursor.close();
        db.close();

        return ingredients;


    }

    public ArrayList<RecipeResult> selectRecipeByIngredientName(ArrayList<String> ingredientsName) {
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<RecipeResult> idRecipes = new ArrayList<>();
        String strNames = "";
        for (int i = 0; i < ingredientsName.size(); i++) {
            strNames += "ingreName = '" + ingredientsName.get(i) + "'";
            if (i != ingredientsName.size() - 1) {
                strNames += " OR ";
            }
        }

        Cursor cursor = db.rawQuery("SELECT recipeID, count(*) FROM INGREDIENTS WHERE " +
                strNames + " GROUP BY recipeID", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                idRecipes.add(new RecipeResult(cursor.getInt(0), cursor.getInt(1)));
            }
        }
        cursor.close();
        db.close();
        return idRecipes;

    }


    public int GetIdByRecipeName(String name) {

        SQLiteDatabase db = getReadableDatabase();

        int id = -1;
        Cursor cursor = db.rawQuery("SELECT _id FROM RECIPES WHERE recipeName = '" + name + "' ORDER BY _id DESC LIMIT 1", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                id = cursor.getInt(0);
            }
        }

        cursor.close();
        db.close();
        return id;
    }

    public ArrayList<RecipeItem> recipeSelection() {

        SQLiteDatabase db = getReadableDatabase();
        ArrayList<RecipeItem> allRecipes = new ArrayList<>();

        //sucht nach allen verfügbaren Rezepten
        Cursor cursor = db.rawQuery("SELECT * FROM RECIPES ORDER BY _id DESC LIMIT 3", null);
        if (cursor != null) {
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


    public ArrayList<CategoryItem> categorySelection() {

        SQLiteDatabase db = getReadableDatabase();
        ArrayList<CategoryItem> categoryList = new ArrayList<>();

        //sucht nach allen verfügbaren Rezepten
        Cursor cursor = db.rawQuery("SELECT category, mainImg FROM RECIPES GROUP BY category HAVING max(_id)", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                categoryList.add(new CategoryItem(
                        cursor.getString(0),
                        cursor.getBlob(1)
                ));
            }
        }
        cursor.close();
        db.close();
        return categoryList;
    }


    public ArrayList<RecipeItem> favoriteSelection() {

        SQLiteDatabase db = getReadableDatabase();
        ArrayList<RecipeItem> allRecipes = new ArrayList<>();

        //sucht nach allen verfügbaren Rezepten
        Cursor cursor = db.rawQuery("SELECT * FROM RECIPES ORDER BY likeCount DESC LIMIT 3", null);
        if (cursor != null) {
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


    public ArrayList<RecipeItem> allRecipes() {

        SQLiteDatabase db = getReadableDatabase();
        ArrayList<RecipeItem> allRecipes = new ArrayList<>();

        //sucht nach allen verfügbaren Rezepten
        Cursor cursor = db.rawQuery("SELECT * FROM RECIPES", null);
        if (cursor != null) {
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

    public ArrayList<RecipeItem> recipesSelectByCategory(String category) {

        SQLiteDatabase db = getReadableDatabase();
        ArrayList<RecipeItem> allRecipes = new ArrayList<>();

        //sucht nach allen verfügbaren Rezepten
        Cursor cursor = db.rawQuery("SELECT * FROM RECIPES WHERE category = '" + category + "'", null);
        if (cursor != null) {
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

    public RecipeItem recipesSelectByName(String name) {

        SQLiteDatabase db = getReadableDatabase();

        //sucht nach allen verfügbaren Rezepten
        Cursor cursor = db.rawQuery("SELECT * FROM RECIPES WHERE recipeName = '" + name + "'", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                RecipeItem recipe = new RecipeItem(
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
                );
                cursor.close();
                db.close();
                return recipe;
            }


        }
        return null;
    }

    public RecipeItem recipesSelectById(int id) {

        SQLiteDatabase db = getReadableDatabase();

        //sucht nach allen verfügbaren Rezepten
        Cursor cursor = db.rawQuery("SELECT * FROM RECIPES WHERE _id = " + id, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                RecipeItem recipe = new RecipeItem(
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
                );
                cursor.close();
                db.close();
                return recipe;
            }


        }
        return null;
    }

    public int addToFavorite(int recipeId) {

        SQLiteDatabase db = getReadableDatabase();
        int count = -1;
        Cursor cursor = db.rawQuery("SELECT likeCount FROM RECIPES WHERE _id = " + recipeId, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                count = cursor.getInt(0);
            }
        }
        cursor.close();

        db = getWritableDatabase();
        db.execSQL("UPDATE RECIPES SET likeCount = " + (count + 1) + " WHERE _id = " + recipeId + ";");
        db.execSQL("INSERT INTO LIKECOUNT values(null, '" + "" + "', " + recipeId + ");");
        db.close();
        return count;
    }

    public int deleteFromFavorite(int recipeId) {

        SQLiteDatabase db = getReadableDatabase();
        int count = -1;
        Cursor cursor = db.rawQuery("SELECT likeCount FROM RECIPES WHERE _id = " + recipeId, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                count = cursor.getInt(0);
            }
        }
        cursor.close();

        db = getWritableDatabase();
        db.execSQL("UPDATE RECIPES SET likeCount = " + (count - count) + " WHERE _id = " + recipeId + ";");
        db.execSQL("DELETE FROM LIKECOUNT WHERE userID = '" + "" + "' AND recipeID = " + recipeId + ";");
        db.close();
        return count;
    }


    //nochmal anschauen!!
    public boolean like_GetLikeYNByUserId(int recipeId) {

        SQLiteDatabase db = getReadableDatabase();

        boolean likeNY = false;
        Cursor cursor = db.rawQuery("SELECT * FROM LIKECOUNT WHERE userID = '" + "" + "' AND recipeID = " + recipeId, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                likeNY = true;
            }
        }
        cursor.close();
        db.close();
        return likeNY;
    }

}