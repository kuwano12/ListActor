package com.example.listactor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static SQLiteDatabase db;
    Button btnGoToAddForm, btnEdit, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGoToAddForm = (Button) findViewById(R.id.btnGoToAddForm);

//        btnEdit   = (Button) findViewById(R.id.btnEditActor);
//        btnDelete = (Button) findViewById(R.id.btnDeleteActor);


        db = openOrCreateDatabase("Cinema", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS actor(name VARCHAR, age VARCHAR, country VARCHAR," +
                "imageName VARCHAR);");

        List<Actor> image_details = getListData();
        final ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(new CustomListAdapter(this, image_details));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Actor a = (Actor) o;

                Intent i = new Intent(getApplicationContext(), EditActorForm.class);
                Bundle extra = new Bundle();
                extra.putSerializable("Actor", a);
                i.putExtra("extra", extra);
                startActivity(i);

//                Toast.makeText(MainActivity.this, "Selected :" + " " + a, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void goToAddForm(View v) {
        Intent i = new Intent(getApplicationContext(), AddActorForm.class);
        startActivity(i);
    }

    private List<Actor> getListData() {
        List<Actor> list = new ArrayList<Actor>();
        Actor tom = new Actor("Tom", 58, "USA", "tom");
        Actor brad = new Actor("Brad", 57, "USA", "brad");
        Actor dicaprio = new Actor("Leonardo", 46, "USA", "dicaprio");
        Actor angelina = new Actor("Angelina", 45, "USA", "angelina");

        list.add(tom);
        list.add(brad);
        list.add(dicaprio);
        list.add(angelina);

        Cursor c = db.rawQuery("SELECT * FROM actor", null);
        if(c.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "Table vide", Toast.LENGTH_LONG).show();

        }
        while(c.moveToNext()) {
            Actor a = new Actor(c.getString(0), Integer.parseInt(c.getString(1)), c.getString(2), c.getString(3));
            list.add(a);
        }
        return list;
    }

    public void goToEditForm(View v) {
        Intent i = new Intent(getApplicationContext(), EditActorForm.class);
        startActivity(i);
    }
}