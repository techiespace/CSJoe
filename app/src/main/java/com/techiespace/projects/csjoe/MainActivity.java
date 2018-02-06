package com.techiespace.projects.csjoe;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    //@BindView(R.id.progress_bar)
    //ProgressBar progressBar;

    @BindView(R.id.listRecyclerView)
    RecyclerView listRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UniversityFragment ufrag = new UniversityFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.placeholder, ufrag);
        fragmentTransaction.commit();
    }


}