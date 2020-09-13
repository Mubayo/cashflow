package com.example.cashflow.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.cashflow.AddProducts;
import com.example.cashflow.R;
import com.example.cashflow.AddSales;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        CardView productView = root.findViewById(R.id.product);
        CardView salesView = root.findViewById(R.id.sales);

        productView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i= new Intent(getActivity(), AddProducts.class);
                startActivity(i);
            }
        });
        salesView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i= new Intent(getActivity(), AddSales.class);
                startActivity(i);
            }
        });
        return root;

    }
}