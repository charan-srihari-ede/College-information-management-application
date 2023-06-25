package com.example.admincollegeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.admincollegeapp.faculty.UpdateFaculty;
import com.example.admincollegeapp.notice.DeleteNoticeActivity;
import com.example.admincollegeapp.notice.UploadNotice;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    CardView uploadNotice, addGalleryImage, addEbook, faculty, deleteNoitce,add;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uploadNotice = findViewById(R.id.addNotice);
        addGalleryImage = findViewById(R.id.addGalleryImage);
        addEbook = findViewById(R.id.addEbook);
        faculty = findViewById(R.id.faculty);
        deleteNoitce = findViewById(R.id.deleteNotice);
        add=findViewById(R.id.add);
        uploadNotice.setOnClickListener(this);
        addGalleryImage.setOnClickListener(this);
        addEbook.setOnClickListener(this);
        faculty.setOnClickListener(this);
        deleteNoitce.setOnClickListener(this);
        add.setOnClickListener(this);
        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        myRef=database.getReference();
        auth.getUid();
        Button b1=findViewById(R.id.sign_out);
        b1.setOnClickListener(new View.OnClickListener() {             @Override
        public void onClick(View view) {

            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("SIGN OUT")

                    .setMessage("Are you sure?")

                    .setCancelable(false)

                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override

                        public void onClick(DialogInterface dialogInterface, int i) {

                            auth.signOut();
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            finish();
                        }

                    })

                    .setNegativeButton("No", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this,"Selected option is No",Toast.LENGTH_SHORT).show();
                        }
                    });
            AlertDialog dialog= builder.create();
            dialog.show();
        }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()){

            case R.id.addNotice:
                intent = new Intent(MainActivity.this, UploadNotice.class);
                startActivity(intent);
                break;

            case R.id.addGalleryImage:
                intent = new Intent(MainActivity.this,UploadImage.class);
                startActivity(intent);
                break;

            case R.id.addEbook:
                intent = new Intent(MainActivity.this,UploadPdfActivity.class);
                startActivity(intent);
                break;

            case R.id.faculty:
                intent = new Intent(MainActivity.this,UpdateFaculty.class);
                startActivity(intent);
                break;

            case R.id.deleteNotice:
                intent = new Intent(MainActivity.this, DeleteNoticeActivity.class);
                startActivity(intent);
                break;
            case R.id.add:
                intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
                break;

        }

    }
}