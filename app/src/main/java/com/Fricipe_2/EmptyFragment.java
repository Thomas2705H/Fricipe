package com.Fricipe_2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.Fricipe_2.Recipes;

public class EmptyFragment extends Fragment {

    public EmptyFragment() {
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Generiert das Layout für das Fragment
        final View view = inflater.inflate(R.layout.fragment_blank, container, false);


        Button fab = view.findViewById(R.id.button2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Recipes recipes = new Recipes();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.root_layout, recipes);
                transaction.commit();
            }

        });


        Button random = view.findViewById(R.id.button);
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchFragment searchFragment = new SearchFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.root_layout, searchFragment);
                transaction.commit();
            }
        });

        return view;
    }
}