package com.sonia.matdaan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Workers extends AppCompatActivity {
    EditText name, phn, wn, wi, com;
    com.google.firebase.database.DatabaseReference DatabaseReference;
    com.google.firebase.auth.FirebaseAuth FirebaseAuth;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private com.google.firebase.database.DatabaseReference root = db.getReference().child("workers");
    Button sub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers);
        name = findViewById(R.id.name);
        phn = findViewById(R.id.phn);
        wn = findViewById(R.id.wn);
        wi = findViewById(R.id.wi);
        com = findViewById(R.id.com);
        sub = findViewById(R.id.sub);

        FirebaseAuth = FirebaseAuth.getInstance();
        final String userID = FirebaseAuth.getCurrentUser().getUid();

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mname = name.getText().toString();
                String mphn = phn.getText().toString();
                String mwn = wn.getText().toString();
                String mwi = wi.getText().toString();
                String mcom = com.getText().toString();

                HashMap<String, String> userMap = new HashMap<>();


                userMap.put("name", mname);
                userMap.put("phn", mphn);
                userMap.put("wn", mwn);
                userMap.put("wi", mwi);
                userMap.put("com", mcom);
                root.child(userID).setValue(userMap);
                Toast toast=Toast.makeText(getApplicationContext(),"Your complaint has been sent successfully",Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();
            }


        });
    }
}
