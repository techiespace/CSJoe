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

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class SubtopicFragment extends Fragment {
    Query quniversities;
    FirestoreRecyclerOptions<Subtopic> response;
    String uni_name, course_name, topic_name, subtopic_name;
    private FirestoreRecyclerAdapter sadapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        uni_name = getArguments().getString("University");
        course_name = getArguments().getString("Course");
        topic_name = getArguments().getString("Topic");
        subtopic_name = getArguments().getString("Subtopic");
        //Toast.makeText(getActivity(), ""+uni_name, Toast.LENGTH_SHORT).show();
        quniversities = FirebaseFirestore.getInstance()
                .collection("university/" + uni_name + "/stream/" + course_name + "/courses/" + topic_name + "/topic/" + subtopic_name + "/subtopic");
        response = new FirestoreRecyclerOptions.Builder<Subtopic>()
                .setQuery(quniversities, Subtopic.class)
                .build();
        sadapter = new FirestoreRecyclerAdapter<Subtopic, SubtopicFragment.ViewHolder>(response) {
            @Override
            public SubtopicFragment.ViewHolder onCreateViewHolder(ViewGroup group, int viewType) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.list_item, group, false);
                return new SubtopicFragment.ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(SubtopicFragment.ViewHolder holder, int position, Subtopic model) {
                DocumentSnapshot snapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
                //progressBar.setVisibility(View.GONE);
                holder.textName.setText(model.getSubtopic_name());
                //Toast.makeText(getActivity(), "onBindViewHolder: ...."+model.getSubtopic_name(), Toast.LENGTH_SHORT).show();
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("Course", course_name);
                        bundle.putString("University", uni_name);
                        bundle.putString("Topic", topic_name);
                        bundle.putString("Subtopic", subtopic_name);
                        bundle.putString("Content", snapshot.getId());
                        Fragment sFragment = new ContentFragment();
                        sFragment.setArguments(bundle);
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.placeholder, sFragment).addToBackStack(null).commit();
                    }
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
} 