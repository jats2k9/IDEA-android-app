package fiu.appv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class IdeasActivity extends AppCompatActivity {

    private EditText searchField;
    private ImageButton searchButton;
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FloatingActionButton post_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideas);

        databaseReference = FirebaseDatabase.getInstance().getReference("ideas");
        databaseReference.keepSynced(true);

        searchField = findViewById(R.id.seach_field);
        searchButton = findViewById(R.id.search_button);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        post_button = findViewById(R.id.post_button);

        // populate view
        firebaseIdeaSearch("");

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = searchField.getText().toString();
                firebaseIdeaSearch(word);
            }
        });
        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IdeasActivity.this, PostToDatabase.class));
            }
        });
    }

    private void firebaseIdeaSearch(String word) {
        Query query = databaseReference.orderByChild("name").startAt(word).endAt(word + "\uf8ff");
        FirebaseRecyclerAdapter<Idea, IdeasViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Idea, IdeasViewHolder>(
                        Idea.class,
                        R.layout.idea_item,
                        IdeasViewHolder.class,
                        query
                ) {
                    @Override
                    protected void populateViewHolder(IdeasViewHolder viewHolder, Idea model, int position) {
                        viewHolder.setDescription(model.getDescription());
                        viewHolder.setName(model.getName());
                    }
                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    /**
     * Declaration of ViewHolder
     */
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
