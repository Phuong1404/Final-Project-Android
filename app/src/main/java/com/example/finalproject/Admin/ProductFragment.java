package com.example.finalproject.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.Helper.FirebaseData;
import com.example.finalproject.Models.Category;
import com.example.finalproject.Models.Product;
import com.example.finalproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.listeners.TableDataLongClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class ProductFragment extends Fragment {

    View view;
    public ProductFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view==null)
        {
            view=inflater.inflate(R.layout.fragment_product, container, false);
        }
        TableView table=view.findViewById(R.id.table_data_view);

        String[] Headers={"Name","Price","Category","Quality"};
        FirebaseData data=new FirebaseData();
        List<Product> productList=new ArrayList<>();
        data.GetData().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productList.clear();
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    Map singleValue=(Map)ds.getValue();
                    String Name=(String) singleValue.get("Name");
                    Category Category=ds.child("Category").getValue(Category.class);
                    String Price=(String)singleValue.get("Price");
                    String Quantity=(String)singleValue.get("Quantity");
                    productList.add(new Product("",Name,Price,"",Quantity,"",Category));
                }
                if(productList.size()>0)
                {
                    String[][] datatable=new String[productList.size()][4];
                    for(int i=0;i<productList.size();i++){
                        Product p=productList.get(i);
                        datatable[i][0]=p.getName();
                        datatable[i][1]=p.getPrice();
                        datatable[i][2]=p.getQuantity();
                        datatable[i][3]=p.getCategory().getName();
                    }
                    table.setHeaderAdapter(new SimpleTableHeaderAdapter(getActivity(),Headers));
                    table.setDataAdapter(new SimpleTableDataAdapter(getActivity(),datatable));
                }
                else {
                    String[][] datatable={{"","","",""}};
                    table.setHeaderAdapter(new SimpleTableHeaderAdapter(getActivity(),Headers));
                    table.setDataAdapter(new SimpleTableDataAdapter(getActivity(),datatable));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        table.addDataClickListener(new TableDataClickListener() {
            @Override
            public void onDataClicked(int rowIndex, Object clickedData) {
                Intent i=new Intent(getActivity(),DetaiProActivity.class);
                startActivity(i);
            }
        });

        table.addDataLongClickListener(new TableDataLongClickListener() {
            @Override
            public boolean onDataLongClicked(int rowIndex, Object clickedData) {
                return false;
            }
        });
        return view;
    }

}