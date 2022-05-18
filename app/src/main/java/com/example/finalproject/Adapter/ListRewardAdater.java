package com.example.finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.finalproject.Models.Cart;
import com.example.finalproject.Models.Detail;
import com.example.finalproject.R;

import java.util.List;

public class ListRewardAdater extends ArrayAdapter<Detail> {
    Context context;
    View view;
    List<Detail> ListDetail;
    public ListRewardAdater(@NonNull Context context, List<Detail>datadetail){
        super(context, R.layout.order_item,datadetail);
        this.context=context;
        this.ListDetail=datadetail;
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.reward_item,null,true);

        TextView name=view.findViewById(R.id.Name);
        TextView date=view.findViewById(R.id.date);
        name.setText(ListDetail.get(position).getProduct().getName());
        date.setText(String.valueOf(ListDetail.get(position).getDate()));
        return view;
    }
}
