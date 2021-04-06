package com.example.fricipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainRecipeAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<RecipeItem> recipeList;
    private int layout;
    private ImageHelper imageHelper = new ImageHelper();

    public MainRecipeAdapter(Context context, ArrayList<RecipeItem> recipeList, int layout) {
        this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.recipeList = recipeList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return recipeList.size();
    }

    @Override
    public Object getItem(int i) {

            return recipeList.get(i).get_recipeName();


    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view=inflater.inflate(layout,viewGroup,false);
        }

        RecipeItem recipeItem = recipeList.get(i);
        ImageView icon=(ImageView)view.findViewById(R.id.img_mainListItem);
        //icon.setImageURI(Uri.parse(recipeItem.get_thumbnail()));
        //icon.setImageResource(recipeItem.get_thumbnail());
        icon.setImageBitmap(imageHelper.getBitmapFromByteArray(recipeItem.get_thumbnail()));

        TextView name=(TextView)view.findViewById(R.id.txt_mainListItem);
        name.setText(recipeItem.get_recipeName());
        return view;
    }
    }
}
