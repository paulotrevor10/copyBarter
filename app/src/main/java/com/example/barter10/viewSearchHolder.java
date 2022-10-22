package com.example.barter10;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class viewSearchHolder extends RecyclerView.ViewHolder {

    TextView itemName;
    TextView itemCondition;
    Button viewPost;
    public viewSearchHolder(@NonNull View itemView) {
        super(itemView);

        itemName= itemView.findViewById(R.id.itemName);
        itemCondition = itemView.findViewById(R.id.itemCondition);
       viewPost = itemView.findViewById(R.id.viewPost);
    }

}
