package com.example.finalproject.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class DetaiProActivity extends AppCompatActivity {
    private Spinner spnCategory;
    ImageView edit;
    EditText name,price,quanlity;
    TextView btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detai_pro);
        spnCategory = (Spinner) findViewById(R.id.category);
        List<String> category=new ArrayList<>();
        category.add("Category 1");
        category.add("Category 2");
        category.add("Category 3");
        category.add("Category 4");
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,category);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnCategory.setAdapter(adapter);
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(DetaiProActivity.this, spnCategory.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnCategory.setEnabled(false);
        edit=findViewById(R.id.menubar);
        name=findViewById(R.id.name);
        price=findViewById(R.id.price);
        quanlity=findViewById(R.id.quantity);
        btn=findViewById(R.id.update);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setEnabled(true);
                price.setEnabled(true);
                quanlity.setEnabled(true);
                spnCategory.setEnabled(true);
                btn.setEnabled(true);
                btn.setVisibility(View.VISIBLE);
            }
        });
    }
}