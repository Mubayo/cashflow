package com.example.cashflow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.util.HashMap;

public class AddProducts extends AppCompatActivity {

    public TextView p_name;
    private AppCompatImageView back;
    EditText product_name, product__cost_price, product_selling_price, product_quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        p_name = findViewById(R.id.product_name);
        Button add_product = findViewById(R.id.add_prod);
        product_name = findViewById(R.id.name_text);
        product__cost_price = findViewById(R.id.price_text);
        product_selling_price = findViewById(R.id.sale_text);
        product_quantity = findViewById(R.id.quan_text);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddProducts.this, MainActivity.class));
            }
        });
        
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
                String cost_price = product__cost_price.getText().toString();
                String sell_price = product_selling_price.getText().toString();
                String quantity = product_quantity.getText().toString();
                DatabaseReference myRef = database.getReference("Products");

                HashMap<String, Object> addProducts = new HashMap<>();
                addProducts.put("name", name);
                addProducts.put("cost_price", cost_price);
                addProducts.put("sell_price", sell_price);
                addProducts.put("quantity", quantity);

                /*myRef.child(ts.toString()).push().setValue(name);
                myRef.child(ts.toString()).push().setValue(cost_price);
                myRef.child(ts.toString()).push().setValue(sell_price);
                myRef.child(ts.toString()).push().setValue(quantity);*/

                myRef.child(ts.toString()).push().setValue(addProducts).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){

                            startActivity(new Intent(AddProducts.this, MainActivity.class));
                        }else {
                            Toast.makeText(AddProducts.this, "Couldn't add: " + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

    }
    @Override
    public void setActionBar(@Nullable Toolbar toolbar) {
        super.setActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
    }
}
