package com.techiespace.projects.csjoe;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class StreamFragment extends Fragment{
    private FirestoreRecyclerAdapter sadapter;
    Query quniversities;
    FirestoreRecyclerOptions<Stream> response;
    String uni_name;

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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        uni_name = getArguments().getString("University");
        //Toast.makeText(getActivity(), ""+uni_name, Toast.LENGTH_SHORT).show();
        quniversities = FirebaseFirestore.getInstance()
                .collection("university/"+uni_name+"/stream");
        response = new FirestoreRecyclerOptions.Builder<Stream>()
                .setQuery(quniversities, Stream.class)
                .build();
        sadapter = new FirestoreRecyclerAdapter<Stream, StreamFragment.ViewHolder>(response) {
            @Override
            public StreamFragment.ViewHolder onCreateViewHolder(ViewGroup group, int viewType) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.list_item, group, false);
                return new StreamFragment.ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(StreamFragment.ViewHolder holder, int position, Stream model) {
                DocumentSnapshot snapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
                //progressBar.setVisibility(View.GONE);
                holder.textName.setText(model.getStream_name());
                holder.itemView.setOnClickListener(view -> {
                    Bundle cbundle = new Bundle();
                    cbundle.putString("Course",snapshot.getId());
                    cbundle.putString("University",uni_name);
                    Fragment sFragment = new CourseFragment();
                    sFragment.setArguments(cbundle);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.placeholder,sFragment).addToBackStack(null).commit();
                });
            }
        };

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.listRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(sadapter);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        sadapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        sadapter.stopListening();
    }
} 