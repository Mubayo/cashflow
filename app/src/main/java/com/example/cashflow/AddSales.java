package com.example.cashflow;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.time.LocalDate;
import java.util.HashMap;

public class AddSales extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_try);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        Button add_product = findViewById(R.id.add_sales);
        final EditText product_name = findViewById(R.id.sale_name);
        final EditText product_price = findViewById(R.id.sale_price);
        final EditText product_quan = findViewById(R.id.sale_quan);
        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database
                LocalDate ts = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    ts = LocalDate.now();
                }
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                String name = product_name.getText().toString();
                String price = product_price.getText().toString();
                String quantity = product_quan.getText().toString();

                HashMap<String, Object> addSales = new HashMap<>();
                addSales.put("name", name);
                addSales.put("price", price);
                addSales.put("quantity", quantity);
                addSales.put("date_time", ts.toString());

                DatabaseReference myRef = database.getReference("Sales");
                myRef.push().setValue(addSales).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(AddSales.this, MainActivity.class));
                        }
                    }
                });

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
