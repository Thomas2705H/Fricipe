package com.example.fricipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class FindFragment extends Fragment implements View.OnClickListener {

    EditText editText;
    Button addButton;
    Button buttonSearch;
    GridView gridView;
    CheckBox checkBox,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox7,checkBox8,checkBox9,checkBox10,checkBox11,checkBox12;

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems = new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE GRIDTVIEW
    ArrayAdapter<String> adapter;

    //RECORDING HOW MANY TIMES THE BUTTON HAS BEEN CLICKED
    int clickCounter = 0;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        buttonSearch = (Button) view.findViewById(R.id.btn_Add_IngredientAdd);
        checkBox=(CheckBox)view.findViewById(R.id.radioButton15);
        checkBox2=(CheckBox)view.findViewById(R.id.radioButton16);
        checkBox3=(CheckBox)view.findViewById(R.id.radioButton17);
        checkBox4=(CheckBox)view.findViewById(R.id.radioButton18);
        checkBox5=(CheckBox)view.findViewById(R.id.radioButton19);
        checkBox6=(CheckBox)view.findViewById(R.id.radioButton20);
        checkBox7=(CheckBox)view.findViewById(R.id.radioButton21);
        checkBox8=(CheckBox)view.findViewById(R.id.radioButton22);
        checkBox9=(CheckBox)view.findViewById(R.id.radioButton23);
        checkBox10=(CheckBox)view.findViewById(R.id.radioButton24);
        checkBox11=(CheckBox)view.findViewById(R.id.radioButton25);
        checkBox12=(CheckBox)view.findViewById(R.id.radioButton26);

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

        //adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, listItems);
        adapter = new ArrayAdapter<String>(getContext(), R.layout.edit_text_custom_for_ingredients, listItems);

        //added a click listener on the item of the gridview
        buttonSearch.setOnClickListener(this);
        return view;
    }





    @Override

    public void onClick(View v) {
        if (v.getId() == buttonSearch.getId()) {

            if (listItems.isEmpty()){ //if user didin't put any ingredient display error message
                Toast toast = Toast.makeText(getContext(), "You didn't insert any ingredient", Toast.LENGTH_SHORT);
                toast.show();
            }
            else {
                //finding recipeid and count by ingredient
                DBHelper dbHelper = new DBHelper(v.getContext(), "Recipes.db", null, 1);
                ArrayList<SearchResultItem> resultList = dbHelper.ingredients_selectRecipeByIngredientName(listItems);

                if(resultList.isEmpty()){ //if didn't find anything display error message

                    Ingriendientsfrag ingriendientsfrag=new Ingriendientsfrag();
                    FragmentTransaction transaction=getFragmentManager().beginTransaction();
                    transaction.replace(R.id.root_layout,ingriendientsfrag);
                    transaction.commit();

                }else{
                    ArrayList<Integer> idRecipes = new ArrayList<>();
                    ArrayList<Integer> matches = new ArrayList<>();


                    //filling the array of idRecipes
                    for(int i=0;i<resultList.size();i++){
                        idRecipes.add(resultList.get(i).get_recipeId());
                    }

                    //filling the array of ingredients matches
                    for (int j=0;j<resultList.size();j++){
                        matches.add(resultList.get(j).get_ingrCount());
                    }
                    ArrayList<Integer> matchesNoDuplicates = removeDuplicates(matches);
                    /*
                    ArrayList<Integer> positionsfound = findPosition(matches, findMax(matches));
                    ArrayList<Integer> idRecipesSelected = new ArrayList<>();

                    //filling the array of the id only for the selected Recipes
                    for(int k=0; k<positionsfound.size();k++){
                        idRecipesSelected.add(idRecipes.get(positionsfound.get(k)));
                    }


                        Intent intent = new Intent(getActivity(), RecipeListActivity.class);
                        intent.putExtra("title", "Matching: "+ findMax(matches) + " ingredients");
                        intent.putExtra("list",idRecipesSelected);
                        startActivity(intent);

                        */
                    //sent to Search Result intent idRecipes, matches and also the number of matches without duplicates
                    Intent intent = new Intent(getActivity(), SearchResult.class);
                    intent.putExtra("idrecipes",idRecipes);

                    intent.putExtra("matchesNoDuplicates", matchesNoDuplicates);
                    intent.putExtra("matches",matches);

                    startActivity(intent);



                    dbHelper.close();
                }



            }



        }
         /*
    a function that search witch is the max in an array of int
    used for retrives the maximum of ingredients matched
    @param numMatches an array of Int that numbers of the matches
     */
        public int findMax(ArrayList<Integer> numMatches){
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
        public ArrayList<Integer> findPosition(ArrayList<Integer> matches, int maxvalue){
            ArrayList<Integer> positions = new ArrayList<>();
            for(int i =0; i<matches.size();i++){
                if(matches.get(i)==maxvalue){
                    positions.add(i);
                }
            }
            for (int j =0; j<positions.size(); j++){
                System.out.println(positions.get(j));
            }
            return positions;
        }

    /*
    this function call the db to find the best recipes and retun an arraylist of integer -> id of best recipes
     */
        public ArrayList<Integer> findBestRecipes(){
            ArrayList<Integer> bestRecipes = new ArrayList<>();
            DBHelper dbHelper = new DBHelper(getContext(), "Recipes.db", null, 1);
            ArrayList<RecipeItem> results = dbHelper.recipes_SelectBest();
            if(results.isEmpty()){

            }
            else {
                for(int i=0;i<results.size();i++){
                    bestRecipes.add(results.get(i).get_id());
                }
            }
            return  bestRecipes;
        }

    /*
    function that removes the duplicates in an array of Integer
     */
        public ArrayList<Integer> removeDuplicates(ArrayList<Integer> array){
            ArrayList<Integer> noduplicates = new ArrayList<>();
            Set<Integer> withoutDuplicates = new LinkedHashSet<Integer>(array);
            noduplicates.clear();
            noduplicates.addAll(withoutDuplicates);
            return noduplicates;
        }
    }
}
