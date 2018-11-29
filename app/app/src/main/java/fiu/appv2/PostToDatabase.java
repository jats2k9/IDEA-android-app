package fiu.appv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostToDatabase extends AppCompatActivity {

    private TextView idea_name;
    private TextView description;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_to_database);

        Button post_idea = findViewById(R.id.share_idea);
        idea_name = findViewById(R.id.idea_name_textfield);
        description = findViewById(R.id.idea_description_textfield);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("ideas").push();

        post_idea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idea = idea_name.getText().toString();
                String desc = description.getText().toString();

                if (!(idea.equals("") || desc.equals(""))) {
                    databaseReference.child("name").setValue(idea);
                    databaseReference.child("description").setValue(desc);
                    idea_name.setText("");
                    description.setText("");
                    startActivity(new Intent(PostToDatabase.this, IdeasActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(),
                            "All fields required.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
