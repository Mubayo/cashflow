package com.example.cashflow.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cashflow.Adapters.ProductAdapter;
import com.example.cashflow.Models.Products;
import com.example.cashflow.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private List<Products> products_list;
    private RecyclerView product_list_view;
    private ProductAdapter productAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        products_list = new ArrayList<>();
        product_list_view = root.findViewById(R.id.product_list);
        productAdapter = new ProductAdapter(getContext(), products_list);

        product_list_view.setLayoutManager(new LinearLayoutManager(container.getContext()));
        product_list_view.setAdapter(productAdapter);
        product_list_view.setHasFixedSize(true);

        readProducts();

        return root;
    }

    private void readProducts(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Products");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                products_list = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Products products = snapshot.getValue(Products.class);

                    products_list.add(products);
                }

                productAdapter = new ProductAdapter(getContext(), products_list);

                product_list_view.setAdapter(productAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}