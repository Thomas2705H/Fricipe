package com.Fricipe_2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.Fricipe_2.Helper.DatabaseHelper;
import com.Fricipe_2.Model.RecipeItem;
import com.Fricipe_2.Model.RecipeResult;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class SearchFragment extends Fragment implements View.OnClickListener {


    Button buttonSearch;
    CheckBox checkBox, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9, checkBox10, checkBox11, checkBox12;

    ArrayList<String> listItems = new ArrayList<String>();

    ArrayAdapter<String> adapter;

    //Kontrolle wie oft der Button gedrückt wurde
    int clickCounter = 0;


    public SearchFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Generiert das Layout für das Fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        buttonSearch = view.findViewById(R.id.btn_Add_IngredientAdd);
        checkBox = view.findViewById(R.id.radioButton15);
        checkBox2 = view.findViewById(R.id.radioButton16);
        checkBox3 = view.findViewById(R.id.radioButton17);
        checkBox4 = view.findViewById(R.id.radioButton18);
        checkBox5 = view.findViewById(R.id.radioButton19);
        checkBox6 = view.findViewById(R.id.radioButton20);
        checkBox7 = view.findViewById(R.id.radioButton21);
        checkBox8 = view.findViewById(R.id.radioButton22);
        checkBox9 = view.findViewById(R.id.radioButton23);
        checkBox10 = view.findViewById(R.id.radioButton24);
        checkBox11 = view.findViewById(R.id.radioButton25);
        checkBox12 = view.findViewById(R.id.radioButton26);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listItems.add(checkBox.getText().toString());
                adapter.notifyDataSetChanged();
                clickCounter++;
            }
        });
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listItems.add(checkBox2.getText().toString());
                adapter.notifyDataSetChanged();
                clickCounter++;
            }
        });
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listItems.add(checkBox3.getText().toString());
                adapter.notifyDataSetChanged();
                clickCounter++;
            }
        });
        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listItems.add(checkBox4.getText().toString());
                adapter.notifyDataSetChanged();
                clickCounter++;
            }
        });
        checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listItems.add(checkBox5.getText().toString());
                adapter.notifyDataSetChanged();
                clickCounter++;
            }
        });
        checkBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listItems.add(checkBox6.getText().toString());
                adapter.notifyDataSetChanged();
                clickCounter++;
            }
        });
        checkBox7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listItems.add(checkBox7.getText().toString());
                adapter.notifyDataSetChanged();
                clickCounter++;
            }
        });
        checkBox8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listItems.add(checkBox8.getText().toString());
                adapter.notifyDataSetChanged();
                clickCounter++;
            }
        });
        checkBox9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listItems.add(checkBox9.getText().toString());
                adapter.notifyDataSetChanged();
                clickCounter++;
            }
        });
        checkBox10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listItems.add(checkBox10.getText().toString());
                adapter.notifyDataSetChanged();
                clickCounter++;
            }
        });
        checkBox11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listItems.add(checkBox11.getText().toString());
                adapter.notifyDataSetChanged();
                clickCounter++;
            }
        });
        checkBox12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listItems.add(checkBox12.getText().toString());
                adapter.notifyDataSetChanged();
                clickCounter++;
            }
        });


        adapter = new ArrayAdapter<String>(getContext(), R.layout.edit_text_custom_for_ingredients, listItems);
        buttonSearch.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == buttonSearch.getId()) {

            if (listItems.isEmpty()) { //Falls keine Zutaten ausgewählt wurden erscheint folgende Nachricht:
                Toast toast = Toast.makeText(getContext(), "Es wurden noch keine Zutaten gewählt", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                DatabaseHelper databaseHelper = new DatabaseHelper(v.getContext(), "Recipes.db", null, 1);
                ArrayList<RecipeResult> resultList = databaseHelper.selectRecipeByIngredientName(listItems);

                if (resultList.isEmpty()) { //wenn kein Geriicht gefunden wurde wird eine Fehlermeldung ausgegeben

                    IngredientsFragment ingredientsFragment = new IngredientsFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.root_layout, ingredientsFragment);
                    transaction.commit();

                } else {
                    ArrayList<Integer> idRecipes = new ArrayList<>();
                    ArrayList<Integer> matches = new ArrayList<>();

                    for (int i = 0; i < resultList.size(); i++) {
                        idRecipes.add(resultList.get(i).get_recipeId());
                    }

                    for (int j = 0; j < resultList.size(); j++) {
                        matches.add(resultList.get(j).get_ingrCount());
                    }
                    ArrayList<Integer> matchesNoDuplicates = removeDuplicates(matches);

                    Intent intent = new Intent(getActivity(), SearchResult.class);
                    intent.putExtra("idrecipes", idRecipes);

                    intent.putExtra("matchesNoDuplicates", matchesNoDuplicates);
                    intent.putExtra("matches", matches);

                    startActivity(intent);


                    databaseHelper.close();
                }


            }


        }

    }

    /*
    a function that search witch is the max in an array of int
    used for retrives the maximum of ingredients matched
    @param numMatches an array of Int that numbers of the matches
     */
    public int findMax(ArrayList<Integer> numMatches) {
        int maxValue = numMatches.get(0);

        for (int i = 0; i < numMatches.size(); i++) {
            if (numMatches.get(i) > maxValue) {
                maxValue = numMatches.get(i);
            }
        }
        return maxValue;
    }

    /*
   a function that search witch are the positions of the array in case of maxvalues duplicates
   used for retrives the positions of the maximum an then the id of recipes
   @param numMatches an array of Int that numbers of the matches
   return an array of int that shows the positions of the maximum
    */
    public ArrayList<Integer> findPosition(ArrayList<Integer> matches, int maxvalue) {
        ArrayList<Integer> positions = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            if (matches.get(i) == maxvalue) {
                positions.add(i);
            }
        }
        for (int j = 0; j < positions.size(); j++) {
            System.out.println(positions.get(j));
        }
        return positions;
    }

    /*
    this function call the db to find the best recipes and retun an arraylist of integer -> id of best recipes
     */
    public ArrayList<Integer> findBestRecipes() {
        ArrayList<Integer> bestRecipes = new ArrayList<>();
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext(), "Recipes.db", null, 1);
        ArrayList<RecipeItem> results = databaseHelper.favoriteSelection();
        if (results.isEmpty()) {

        } else {
            for (int i = 0; i < results.size(); i++) {
                bestRecipes.add(results.get(i).get_id());
            }
        }
        return bestRecipes;
    }

    /*
    function that removes the duplicates in an array of Integer
     */
    public ArrayList<Integer> removeDuplicates(ArrayList<Integer> array) {
        ArrayList<Integer> noduplicates = new ArrayList<>();
        Set<Integer> withoutDuplicates = new LinkedHashSet<Integer>(array);
        noduplicates.clear();
        noduplicates.addAll(withoutDuplicates);
        return noduplicates;
    }


}
