package com.example.finalproject.Admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.finalproject.Helper.FirebaseData;
import com.example.finalproject.Models.Category;
import com.example.finalproject.Models.Product;
import com.example.finalproject.Models.User;
import com.example.finalproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataLongClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class UserFragment extends Fragment {

    AlertDialog dialog;
    AlertDialog.Builder builder;
    View view;
    ImageView add;
    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view==null)
        {
            view=inflater.inflate(R.layout.fragment_user, container, false);
        }
        TableView table=view.findViewById(R.id.table_data_view);



        add=view.findViewById(R.id.add);
        String[] Headers={"Name","Email","Address","Phone"};
        FirebaseData data=new FirebaseData();
        List<User> userList=new ArrayList<>();
        data.GetListUser().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    Map singleValue=(Map)ds.getValue();
                    String Id=ds.getKey();
                    String Name=(String) singleValue.get("name");
                    String Email=(String)singleValue.get("email");
                    String Phone=(String)singleValue.get("phoneNumber");
                    String Address=(String)singleValue.get("address");
                    userList.add(new User(Id,Name,"",Address,Email,Phone,0,0,""));
                }
                if(userList.size()>0)
                {
                    String[][] datatable=new String[userList.size()][4];
                    for(int i=0;i<userList.size();i++){
                        User p=userList.get(i);
                        datatable[i][0]=p.getName();
                        datatable[i][1]=p.getEmail();
                        datatable[i][2]=p.getAddress();
                        datatable[i][3]=p.getPhoneNumber();
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


        table.addDataLongClickListener(new TableDataLongClickListener() {
            @Override
            public boolean onDataLongClicked(int rowIndex, Object clickedData) {
                builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("Are you sure you want to remove");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        data.DeleteUser(userList.get(rowIndex).getId());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog=builder.create();
                dialog.show();
                return true;
            }
        });

        return view;
    }
}