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

public class CourseFragment extends Fragment{
    private FirestoreRecyclerAdapter sadapter;
    Query quniversities;
    FirestoreRecyclerOptions<Course> response;
    String uni_name, course_name;

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
        course_name = getArguments().getString("Course");
        //Toast.makeText(getActivity(), ""+uni_name, Toast.LENGTH_SHORT).show();
        quniversities = FirebaseFirestore.getInstance()
                .collection("university/"+uni_name+"/stream/"+course_name+"/courses");
        response = new FirestoreRecyclerOptions.Builder<Course>()
                .setQuery(quniversities, Course.class)
                .build();
        sadapter = new FirestoreRecyclerAdapter<Course, CourseFragment.ViewHolder>(response) {
            @Override
            public CourseFragment.ViewHolder onCreateViewHolder(ViewGroup group, int viewType) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.list_item, group, false);
                return new CourseFragment.ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(CourseFragment.ViewHolder holder, int position, Course model) {
                DocumentSnapshot snapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
                //progressBar.setVisibility(View.GONE);
                holder.textName.setText(model.getCourse_name());
                holder.itemView.setOnClickListener(view -> {
                    Bundle bundle = new Bundle();
                    bundle.putString("University",uni_name);
                    bundle.putString("Course",course_name);
                    bundle.putString("Topic",snapshot.getId());
                    Fragment sFragment = new TopicFragment();
                    sFragment.setArguments(bundle);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.placeholder,sFragment).addToBackStack(null).commit();
                });
            }
        };

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView =  (RecyclerView)view.findViewById(R.id.listRecyclerView);
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