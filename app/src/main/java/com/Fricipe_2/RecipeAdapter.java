package com.Fricipe_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Fricipe_2.Helper.PictureHelper;
import com.Fricipe_2.Model.RecipeItem;

import java.util.ArrayList;

public class RecipeAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private final ArrayList<RecipeItem> recipeList;
    private final int layout;
    private final PictureHelper pictureHelper = new PictureHelper();

    public RecipeAdapter(Context context, ArrayList<RecipeItem> recipeList, int layout) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.recipeList = recipeList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return recipeList.size();
    }

    @Override
    public Object getItem(int position) {
        return recipeList.get(position).get_recipeName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }

        RecipeItem recipeItem = recipeList.get(position);
        ImageView icon = convertView.findViewById(R.id.img_mainListItem);

        icon.setImageBitmap(pictureHelper.getBitmapFromByteArray(recipeItem.get_thumbnail()));

        TextView name = convertView.findViewById(R.id.txt_mainListItem);
        name.setText(recipeItem.get_recipeName());
        return convertView;
    }
}
