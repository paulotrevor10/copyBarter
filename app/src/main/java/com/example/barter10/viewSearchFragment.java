package com.example.barter10;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.barter10.Model.viewSearch;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class viewSearchFragment extends Fragment {

    ImageView btnViewBack;
    EditText viewSearchBar;
    RecyclerView rv;
    FirebaseRecyclerOptions<viewSearch> options;
    FirebaseRecyclerAdapter<viewSearch, viewSearchHolder> adapter;
    DatabaseReference dataRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_search, container, false);

        String searchedText = SearchFragment.searchedText;// getting the input search in parent fragment

        dataRef = FirebaseDatabase.getInstance().getReference("PostItem");
        btnViewBack = view.findViewById(R.id.btnViewBack);
        viewSearchBar = view.findViewById(R.id.viewSearch_bar);
        rv = view.findViewById(R.id.viewSearch_rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setHasFixedSize(true);

        //getting value in firebase
        LoadPostData("");
        viewSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString()!=null){
                    LoadPostData(editable.toString());
                }
                else {
                    LoadPostData("");
                }

            }
        });






        btnViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Back();
            }
        });


        viewSearchBar.setText(searchedText);
        viewSearchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Back();
            }
        });

        Toast.makeText(getContext(), viewSearchBar.getText().toString().trim(), Toast.LENGTH_SHORT).show();



        return view;
    }

    private void Back() {
        Fragment mFragment = new SearchFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout, mFragment).commit();
    }

    private void LoadPostData(String data) {
        Query query = dataRef.orderByChild("itemName").startAt(data).endAt(data+"\uf8ff");

        options = new FirebaseRecyclerOptions.Builder<viewSearch>().setQuery(query, viewSearch.class).build();
        adapter = new FirebaseRecyclerAdapter<viewSearch, viewSearchHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull viewSearchHolder holder, int position, @NonNull viewSearch model) {
                holder.itemName.setText(model.getItemName());
                holder.itemCondition.setText(model.getItemCondition());
                holder.viewPost.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Fragment mFragment = new viewPostFragment();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout, mFragment).commit();

                    }
                });
            }

            @NonNull
            @Override
            public viewSearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewsearched_item, parent, false);
                return  new viewSearchHolder(view);
            }
        };
        adapter.startListening();
        rv.setAdapter(adapter);

    }
}