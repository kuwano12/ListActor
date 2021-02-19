package com.example.listactor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

public class EditActorForm extends AppCompatActivity {
    SQLiteDatabase db = MainActivity.db;
    EditText editName, editAge, editCountry, editImageName;
    ImageView img;
    Button edit, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_actor_form);

        editName      = (EditText) findViewById(R.id.editViewName);
        editAge       = (EditText) findViewById(R.id.editViewAge);
        editCountry   = (EditText) findViewById(R.id.editCountry);
        editImageName = (EditText) findViewById(R.id.editImageName);

        img = (ImageView) findViewById(R.id.editImageView);

        edit   = (Button) findViewById(R.id.btnEditActor);
        delete = (Button) findViewById(R.id.btnDeleteActor);


        Bundle extra = getIntent().getBundleExtra("extra");
        Actor a = (Actor) extra.getSerializable("Actor");

        editName.setText(a.getName());
        editAge.setText(Integer.toString(a.getAge()));
        editCountry.setText(a.getCountry());
        editImageName.setText(a.getImageName());

        int id = getResources().getIdentifier(a.getImageName(), "mipmap", getPackageName());
        img.setImageResource(id);
    }

    public void deleteActor(View v) {
        if(v == delete) {
            Cursor c = db.rawQuery("SELECT * FROM actor WHERE name='"
                    + editName.getText() +"'", null);
            if(c.moveToFirst()) {
                db.execSQL("DELETE FROM actor WHERE name='" + editName.getText() + "'");
                Utils.showMessage("Success", "Acteur supprimé", EditActorForm.this);
            } else {
                Utils.showMessage("Error", "Name invalide", EditActorForm.this);
            }
        }
    }

}