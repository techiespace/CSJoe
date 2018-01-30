package com.techiespace.projects.csjoe;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;  //https://github.com/firebase/FirebaseUI-Android/tree/master/firestore
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    //@BindView(R.id.progress_bar)
    //ProgressBar progressBar;

    @BindView(R.id.listRecyclerView)
    RecyclerView listRecyclerView;

    private FirebaseFirestore db;
    private FirestoreRecyclerAdapter adapter;
    private FirestoreRecyclerAdapter uadapter;
    private FirestoreRecyclerAdapter newadapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init();
        ListFragment ufrag = new ListFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.placeholder,ufrag);
        fragmentTransaction.commit();
        //ButterKnife.bind(this);   //causes error

        //getUniversities();
    }
    /*
    private void init(){
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        listRecyclerView.setLayoutManager(linearLayoutManager);
        db = FirebaseFirestore.getInstance();
    }

    private void getUniversities(){
        Query quniversities = db.collection("university");
        FirestoreRecyclerOptions<University> response = new FirestoreRecyclerOptions.Builder<University>()
                .setQuery(quniversities, University.class)
                .build();
        uadapter = new FirestoreRecyclerAdapter<University, UniversityHolder>(response) {
            @Override
            public UniversityHolder onCreateViewHolder(ViewGroup group, int viewType) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.list_item, group, false);
                return new UniversityHolder(view);
            }

            @Override
            protected void onBindViewHolder(UniversityHolder holder, int position, University model) {
                progressBar.setVisibility(View.GONE);
                holder.textName.setText(model.getUniName());
                Toast.makeText(MainActivity.this, model.getUniName()+"1", Toast.LENGTH_SHORT).show();
                Query qstream = db.collection("university").whereEqualTo(model.getUniName(),true);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        uadapter.stopListening();
                        newadapter.startListening();
                        updateList(model, response);
                    }
                });
            }
        };


*//*
    private void getFriendList(){
        Query query = db.collection("friends");

        FirestoreRecyclerOptions<FriendsResponse> response = new FirestoreRecyclerOptions.Builder<FriendsResponse>()
                .setQuery(query, FriendsResponse.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<FriendsResponse, FriendsHolder>(response) {
            @Override
            public void onBindViewHolder(FriendsHolder holder, int position, FriendsResponse model) {
                progressBar.setVisibility(View.GONE);
                holder.textName.setText(model.getName());
                holder.textTitle.setText(model.getTitle());
                holder.textCompany.setText(model.getCompany());
                Glide.with(getApplicationContext())
                        .load(model.getImage())
                        .into(holder.imageView);

                holder.itemView.setOnClickListener(v -> {
                    Snackbar.make(friendList, model.getName()+", "+model.getTitle()+" at "+model.getCompany(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                });
            }

            @Override
            public FriendsHolder onCreateViewHolder(ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.list_item, group, false);

                return new FriendsHolder(view);
            }

            @Override
            public void onError(FirebaseFirestoreException e) {
                Log.e("error", e.getMessage());
            }
        };
*//*
        //uadapter.notifyDataSetChanged();
        friendList.setAdapter(uadapter);
    }
    private void updateList(University model, FirestoreRecyclerOptions<University> response){
        Snackbar.make(friendList, model.getUniName(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        Query qstream = db.collection("university/jXrYSu115nHLSS6dQ0vP/stream");
        qstream.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if(task.getResult() == null){
                        Toast.makeText(MainActivity.this, "Query chukichi aahe watta", Toast.LENGTH_SHORT).show();
                    }
                    for (DocumentSnapshot document : task.getResult()) {
                        Log.d("123", document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.d("456", "Error getting documents: ", task.getException());
                }
            }
        });
        FirestoreRecyclerOptions<University> stream_response = new FirestoreRecyclerOptions.Builder<University>()
                .setQuery(qstream, University.class)
                .build();
        newadapter = new FirestoreRecyclerAdapter<University, UniversityHolder>(stream_response) {
            @Override
            public UniversityHolder onCreateViewHolder(ViewGroup group, int viewType) {
                Toast.makeText(MainActivity.this, "Cretating view holder", Toast.LENGTH_SHORT).show();
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.list_item, group, false);

                return new UniversityHolder(view);
            }

            @Override
            protected void onBindViewHolder(UniversityHolder holder, int position, University model) {
                //progressBar.setVisibility(View.GONE);
                holder.textName.setText(model.getUniName());
                Toast.makeText(MainActivity.this, model.getUniName(), Toast.LENGTH_SHORT).show();
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //updateList(model, response);
                        Toast.makeText(MainActivity.this, "This is the inner one", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        friendList.setAdapter(newadapter);
        friendList.invalidate();
        //uadapter.notifyDataSetChanged();
    }
    public class UniversityHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView textName;
        @BindView(R.id.image)
        CircleImageView imageView;

        public UniversityHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class FriendsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView textName;
        *//*@BindView(R.id.image)
        CircleImageView imageView;
        @BindView(R.id.title)
        TextView textTitle;
        @BindView(R.id.company)
        TextView textCompany;*//*

        public FriendsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        uadapter.startListening();   //I think we need to put this inside a fetch button onclick to prevent realtime fetches
    }

    @Override
    public void onStop() {
        super.onStop();
        uadapter.stopListening();
    }*/

}