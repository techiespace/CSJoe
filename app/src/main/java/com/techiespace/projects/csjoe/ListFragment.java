package com.techiespace.projects.csjoe;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ListFragment extends Fragment {
    private FirebaseFirestore db;
    LinearLayoutManager linearLayoutManager;
    Query quniversities = FirebaseFirestore.getInstance()
            .collection("university");
    FirestoreRecyclerOptions<University> response = new FirestoreRecyclerOptions.Builder<University>()
            .setQuery(quniversities, University.class)
            .build();
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
    private FirestoreRecyclerAdapter uadapter = new FirestoreRecyclerAdapter<University, UniversityHolder>(response) {
        @Override
        public UniversityHolder onCreateViewHolder(ViewGroup group, int viewType) {
            View view = LayoutInflater.from(group.getContext())
                    .inflate(R.layout.list_item, group, false);
            return new UniversityHolder(view);
        }

        @Override
        protected void onBindViewHolder(UniversityHolder holder, int position, University model) {
            //progressBar.setVisibility(View.GONE);
            holder.textName.setText(model.getUniName());
            //Toast.makeText(ListAdapter.this, model.getUniName()+"1", Toast.LENGTH_SHORT).show();
            //Query qstream = db.collection("university").whereEqualTo(model.getUniName(),true);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //uadapter.stopListening();
                    //updateList(model, response);
                }
            });
        }
    };



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView =  (RecyclerView)view.findViewById(R.id.listRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(uadapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        uadapter.startListening(); //I think we need to put this inside a fetch button onclick to prevent realtime fetches
    }
    @Override
    public void onStop() {
        super.onStop();
        uadapter.stopListening();
    }
}