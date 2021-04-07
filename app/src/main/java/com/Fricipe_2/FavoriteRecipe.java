package com.Fricipe_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.Fricipe_2.Helper.DatabaseHelper;
import com.Fricipe_2.Model.RecipeItem;

import java.util.ArrayList;


public class FavoriteRecipe extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FavoriteRecipe() {
    }

    public static FavoriteRecipe newInstance(String param1, String param2) {
        FavoriteRecipe fragment = new FavoriteRecipe();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Generiert das Layout für das Fragment
        final ArrayList<RecipeItem> bestList;
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext(), "Recipes.db", null, 1);
        final View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        GridView bestGridView = view.findViewById(R.id.GridView_Best);

        bestList = databaseHelper.favoriteSelection();

        bestGridView.setAdapter(new RecipeAdapter(this.getContext(), bestList, R.layout.fragment_home_recipeitem));

        bestGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                RecipeItem selectRecipe = bestList.get(position);
                Intent intent = new Intent(getActivity(), RecipeActivity.class);
                intent.putExtra("Fricipe_2", selectRecipe.get_recipeName());
                startActivity(intent);
            }
        });
        return view;
    }
}