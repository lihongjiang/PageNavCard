package com.supets.pet.pagenav;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.supets.pet.pagenav.viewholder.PageNavCardHolder;
import com.supets.pet.pagnav.R;

public class MainActivity extends AppCompatActivity {

    PageNavCardHolder mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPager = new PageNavCardHolder(this);
    }

}
