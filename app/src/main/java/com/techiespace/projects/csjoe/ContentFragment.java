package com.techiespace.projects.csjoe;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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

public class ContentFragment extends Fragment {
    Query quniversities;
    FirestoreRecyclerOptions<Content> response;
    String uni_name, course_name, topic_name, subtopic_name, content;
    private FirestoreRecyclerAdapter sadapter;

    public static ContentFragment newInstance(String text) {

        ContentFragment f = new ContentFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        uni_name = getArguments().getString("University");
        course_name = getArguments().getString("Course");
        topic_name = getArguments().getString("Topic");
        subtopic_name = getArguments().getString("Subtopic");
        content = getArguments().getString("Content");
        //Toast.makeText(getActivity(), ""+uni_name, Toast.LENGTH_SHORT).show();
        quniversities = FirebaseFirestore.getInstance()
                .collection("university/" + uni_name + "/stream/" + course_name + "/courses/" + topic_name + "/topic/" + subtopic_name + "/subtopic/" + content + "/content");
        response = new FirestoreRecyclerOptions.Builder<Content>()
                .setQuery(quniversities, Content.class)
                .build();
        sadapter = new FirestoreRecyclerAdapter<Content, ContentFragment.ViewHolder>(response) {
            @Override
            public ContentFragment.ViewHolder onCreateViewHolder(ViewGroup group, int viewType) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.leaf_grid, group, false);
                return new ContentFragment.ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(ContentFragment.ViewHolder holder, int position, Content model) {
                //progressBar.setVisibility(View.GONE);
                holder.textName.setText(model.getConcept());
                holder.itemView.setOnClickListener(view -> {
                    Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(model.getLink()));
                    startActivity(browser);
                });
            }
        };

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.listRecyclerView);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
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
        @BindView(R.id.concept_name)
        TextView textName;
        @BindView(R.id.category_image)
        CircleImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}