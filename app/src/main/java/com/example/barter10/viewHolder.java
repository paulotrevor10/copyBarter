package com.example.barter10;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class viewHolder extends RecyclerView.ViewHolder {

    TextView recent_search;
    ImageView btnDelete;
    DatabaseReference dataRefDelete;
    View v;
    public viewHolder(@NonNull View itemView) {
        super(itemView);



        recent_search = itemView.findViewById(R.id.recentsearch);
        btnDelete = itemView.findViewById(R.id.recentDelete);
        v = itemView;
        dataRefDelete= FirebaseDatabase.getInstance().getReference().child("RecentSearch");

        // removing recent searches
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataRefDelete.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(view.getContext(), "Search Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
