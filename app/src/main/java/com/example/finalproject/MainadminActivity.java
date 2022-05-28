package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.finalproject.Admin.HomeFragment;
import com.example.finalproject.Admin.ProductFragment;
import com.example.finalproject.databinding.ActivityMainadminBinding;

public class MainadminActivity extends AppCompatActivity {

    ActivityMainadminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainadminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        relaceFragment(new HomeFragment());
        binding.bottomNavigrationView.setOnItemSelectedListener(item->{
            switch (item.getItemId()){
                case R.id.home:
                    relaceFragment(new HomeFragment());
                    break;
                case R.id.profile:
                    relaceFragment(new ProductFragment());
                    break;
                case R.id.setting:
                    break;
            }
            return true;
        });
    }
    private void relaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}