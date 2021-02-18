package com.example.listactor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActorForm extends AppCompatActivity {
    SQLiteDatabase db = MainActivity.db;
    EditText editName, editAge, editCountry, editImageName;
    Button btnAddActor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_actor_form);

        editName = (EditText) findViewById(R.id.editActorName);
        editAge = (EditText) findViewById(R.id.editActorAge);
        editCountry = (EditText) findViewById(R.id.editActorCountry);
        editImageName = (EditText) findViewById(R.id.editImageName);
        btnAddActor = (Button) findViewById(R.id.btnAddActor);

    }

    public void addActor(View v) {
        if(v == btnAddActor) {
            if (editName.getText().toString().trim().length() == 0 ||
                    editAge.getText().toString().trim().length() == 0 ||
                    editCountry.getText().toString().trim().length() == 0 ||
                    editImageName.getText().toString().trim().length() == 0) {
                Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs",
                        Toast.LENGTH_LONG).show();
                return;
            }
            db.execSQL("INSERT INTO actor VALUES('" + editName.getText()      + "','"
                                                    + editAge.getText()       + "','"
                                                    + editCountry.getText()   + "','"
                                                    + editImageName.getText() + "');");
            Toast.makeText(getApplicationContext(), "Acteur ajouté",
                    Toast.LENGTH_LONG).show();
            clearText();
        }
    }

    public void clearText() {
        editName.setText("");
        editAge.setText("");
        editCountry.setText("");
        editImageName.setText("");
    }

    public void backToMain(View v) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}