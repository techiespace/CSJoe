package com.techiespace.projects.csjoe;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UniversityFragment extends Fragment {
    Query quniversities = FirebaseFirestore.getInstance()
            .collection("university");
    FirestoreRecyclerOptions<University> response = new FirestoreRecyclerOptions.Builder<University>()
            .setQuery(quniversities, University.class)
            .build();
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView textName;
        @BindView(R.id.image)
        CircleImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    private FirestoreRecyclerAdapter uadapter = new FirestoreRecyclerAdapter<University, ViewHolder>(response) {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup group, int viewType) {
            View view = LayoutInflater.from(group.getContext())
                    .inflate(R.layout.list_item, group, false);
            return new ViewHolder(view);
        }

        @Override
        protected void onBindViewHolder(ViewHolder holder, int position, University model) {
            DocumentSnapshot snapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
            //progressBar.setVisibility(View.GONE);
            holder.textName.setText(model.getUniname());
            holder.itemView.setOnClickListener(view -> {
                Bundle bundle = new Bundle();
                bundle.putString("University",snapshot.getId());
                Fragment sFragment = new StreamFragment();
                sFragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.placeholder,sFragment).addToBackStack(null).commit();
            });
        }
    };



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.listRecyclerView);
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