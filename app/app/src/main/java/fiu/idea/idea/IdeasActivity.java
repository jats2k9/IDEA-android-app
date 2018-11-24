package fiu.idea.idea;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class IdeasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseRecyclerAdapter<Idea, IdeasActivity.IdeasViewHolder> ideasRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideas);

        setTitle("Ideas");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("ideas");
        databaseReference.keepSynced(true);
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        DatabaseReference ideasRef = FirebaseDatabase.getInstance().getReference().child("ideas");
        Query ideasQuery = ideasRef.orderByKey();

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions ideasOptions = new FirebaseRecyclerOptions.Builder<Idea>().setQuery(ideasQuery, Idea.class).build();


        ideasRVAdapter = new FirebaseRecyclerAdapter<Idea, IdeasViewHolder>(ideasOptions) {
            @Override
            protected void onBindViewHolder(@NonNull IdeasViewHolder holder, int position, @NonNull Idea model) {
                holder.setName(model.getName());
                holder.setDescription(model.getDescription());
            }

            @NonNull
            @Override
            public IdeasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.idea_row, parent, false);

                return new IdeasActivity.IdeasViewHolder(view);
            }
        };

        recyclerView.setAdapter(ideasRVAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        ideasRVAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ideasRVAdapter.stopListening();
    }

    public static class IdeasViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public IdeasViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }


        public void setName(String Name) {
            TextView post_name = (TextView) mView.findViewById(R.id.idea_name);
            post_name.setText(Name);
        }

        public void setDescription(String Description) {
            TextView post_name = (TextView) mView.findViewById(R.id.description);
            post_name.setText(Description);
        }


    }
}
