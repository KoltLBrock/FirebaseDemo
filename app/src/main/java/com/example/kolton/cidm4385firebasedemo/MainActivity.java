package com.example.kolton.cidm4385firebasedemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final EditText input = findViewById(R.id.EditTextFirebaseMemo);

        Button button = findViewById(R.id.ButtonSubmit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context = getApplicationContext();

                String message = input.getText().toString();

                DatabaseReference firebaseRef = database.getReference("message");
                firebaseRef.setValue("Hello, World! " + message);
                firebaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String valueFRomFirebase = dataSnapshot.getValue(String.class);

                        Toast toast = Toast.makeText(context, "Message: " + valueFRomFirebase, Toast.LENGTH_LONG);
                        toast.show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}