package com.example.cashflow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;

public class product extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        final TextView p_name = findViewById(R.id.product_name1);
        Button add_product = findViewById(R.id.add_prod);
        final EditText product_name = findViewById(R.id.name_text);
        final EditText product__cost_price = findViewById(R.id.price_text);
        final EditText product_selling_price = findViewById(R.id.sale_text);
        final EditText product_quan = findViewById(R.id.quan_text);
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
                String quan = product_quan.getText().toString();
                DatabaseReference myRef = database.getReference("Products");
                myRef.child(ts.toString()).push().setValue(name);
                myRef.child(ts.toString()).push().setValue(cost_price);
                myRef.child(ts.toString()).push().setValue(sell_price);
                myRef.child(ts.toString()).push().setValue(quan);
                product_name.setText("");
                product_quan.setText("");
                product_selling_price.setText("");
                product__cost_price.setText("");
                // Read from the database
                myRef.child(ts.toString()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            // TODO: handle the post
                            String value = postSnapshot.getValue(String.class);
                            p_name.setText(value);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Failed to read value
                        databaseError.toException();
                    }
                });

            }
        });

//        android:background="#022a5b"
    }
    @Override
    public void setActionBar(@Nullable Toolbar toolbar) {
        super.setActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
    }
}
