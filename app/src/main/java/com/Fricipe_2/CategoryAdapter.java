package com.Fricipe_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Fricipe_2.Helper.PictureHelper;
import com.Fricipe_2.Model.CategoryItem;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private final ArrayList<CategoryItem> categoryList;
    private final int layout;
    private final PictureHelper pictureHelper = new PictureHelper();

    public CategoryAdapter(Context context, ArrayList<CategoryItem> categoryList, int layout) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.categoryList = categoryList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position).get_category();
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
        CategoryItem categoryItem = categoryList.get(position);
        ImageView icon = convertView.findViewById(R.id.categoryitem_img);
        icon.setImageBitmap(pictureHelper.getBitmapFromByteArray(categoryItem.get_mainImg()));

        TextView name = convertView.findViewById(R.id.categoryitem_text);
        name.setText(categoryItem.get_category());
        return convertView;
    }
}
