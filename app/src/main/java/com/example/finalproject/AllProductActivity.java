package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.Adapter.VPAdater;
import com.example.finalproject.Fragment.AllProduct.CategoryFragment1;
import com.example.finalproject.Fragment.AllProduct.CategoryFragment2;
import com.example.finalproject.Fragment.AllProduct.CategoryFragment3;
import com.google.android.material.tabs.TabLayout;

public class AllProductActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView BtnPre;
    private ImageView BtnPre1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_product);

        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);

        BtnPre=findViewById(R.id.BtnPre);
        BtnPre1=findViewById(R.id.BtnPre1);

        CategoryFragment1 fragment1=new CategoryFragment1();
        CategoryFragment2 fragment2=new CategoryFragment2();
        CategoryFragment3 fragment3=new CategoryFragment3();
        VPAdater vpAdater=new VPAdater(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        vpAdater.addFragment(fragment1,"Breverages");
        vpAdater.addFragment(fragment2,"Brewed Coffee");
        vpAdater.addFragment(fragment3,"Blended Coffee");

        viewPager.setAdapter(vpAdater);

        tabLayout.setupWithViewPager(viewPager);

        TextView title=findViewById(R.id.title);
        title.setText("All Product");
        BtnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AllProductActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        BtnPre1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AllProductActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
