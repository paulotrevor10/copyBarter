package com.example.barter10;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class viewPostFragment extends Fragment {

    ImageView btnViewPostBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_post, container, false);

        btnViewPostBack = view.findViewById(R.id.back);
        btnViewPostBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment mFragment = new viewSearchFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout, mFragment).commit();

            }
        });

        return view;
    }
}