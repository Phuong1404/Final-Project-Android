package com.example.finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.finalproject.R;

public class MpagerAdater extends PagerAdapter {
    private LayoutInflater layoutInflater;

    Context context;

    int[]layouts={
            R.layout.welcome_slide1,
            R.layout.welcome_slide2,
            R.layout.welcome_slide3
    };
    public MpagerAdater(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(layouts[position],container,false );
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view=(View) object;
        container.removeView(view);
    }
}
