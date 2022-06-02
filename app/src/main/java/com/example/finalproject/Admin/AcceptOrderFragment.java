package com.example.finalproject.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.finalproject.Adapter.AcceptAdater;
import com.example.finalproject.Adapter.HistoryAdater;
import com.example.finalproject.DetailHistoryActivity;
import com.example.finalproject.Helper.FirebaseData;
import com.example.finalproject.Models.Order;
import com.example.finalproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class AcceptOrderFragment extends Fragment {

    private View view;
    ListView listView;
    AcceptAdater acceptAdater;
    FirebaseAuth mAuth;
    public AcceptOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseData data=new FirebaseData();
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_accept_order, container, false);
        }
        listView=(ListView) view.findViewById(R.id.listview);
        mAuth=FirebaseAuth.getInstance();
        List<Order> acceptlist = new ArrayList<>();
        data.DataOrder().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                acceptlist.clear();
                for (DataSnapshot ds:snapshot.getChildren())
                {
                    Map singleValue = (Map) ds.getValue();
                    String Id=ds.getKey();
                    String Name= (String) singleValue.get("name");
                    String Address= (String) singleValue.get("address");
                    String Phone= (String) singleValue.get("phone");
                    String TimeOrder= (String) singleValue.get("timeOrder");
                    double Total=((Long) singleValue.get("total")).doubleValue();
                    String Status= (String) singleValue.get("status");
                    acceptlist.add(new Order(Id,Address,Name,Phone,Status,TimeOrder,Total));
                }
                acceptAdater=new AcceptAdater(getActivity(),acceptlist);
                listView.setAdapter(acceptAdater);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent=new Intent(getActivity(), DetailOrderAcceptActivity.class);
                        intent.putExtra("id",acceptlist.get(i).getId());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}