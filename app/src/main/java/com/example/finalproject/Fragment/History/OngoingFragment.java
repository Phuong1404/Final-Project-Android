package com.example.finalproject.Fragment.History;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.finalproject.Adapter.HistoryAdater;
import com.example.finalproject.Adapter.OnGoingAdater;
import com.example.finalproject.DetailHistoryActivity;
import com.example.finalproject.Helper.FirebaseData;
import com.example.finalproject.Models.Order;
import com.example.finalproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OngoingFragment extends Fragment {

    private View view;
    ListView listView;
    OnGoingAdater onGoingAdater;
    public OngoingFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FirebaseData data=new FirebaseData();
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_ongoing, container, false);
        }
        listView=(ListView) view.findViewById(R.id.listview);
        List<Order> ongoinglist = new ArrayList<>();
        data.GetDataHistory("User01").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ongoinglist.clear();
                for(DataSnapshot ds:snapshot.getChildren()) {
                    Map singleValue = (Map) ds.getValue();
                    String Id=ds.getKey();
                    String Name= (String) singleValue.get("Name");
                    String Address= (String) singleValue.get("Address");
                    String Phone= (String) singleValue.get("Phone");
                    String Status= (String) singleValue.get("Status");
                    String TimeOrder= (String) singleValue.get("TimeOrder");
                    //double Total=((Long) singleValue.get("Total")).doubleValue();
                    ongoinglist.add(new Order(Id,Address,Name,Phone,Status,TimeOrder,2.3));
                }
                onGoingAdater=new OnGoingAdater(getActivity(),ongoinglist);
                //listView.setDivider(null);
                listView.setAdapter(onGoingAdater);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent=new Intent(getActivity(), DetailHistoryActivity.class);
                        //intent.putExtra("id",productList.get(i).getId());
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