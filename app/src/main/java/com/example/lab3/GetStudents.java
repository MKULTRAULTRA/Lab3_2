package com.example.lab3;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class GetStudents extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_students);
        DBHelper dbHelper;
        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Element> content = new ArrayList<Element>();
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int secnameIndex = cursor.getColumnIndex(DBHelper.KEY_SECOND_NAME);
            int firnameIndex = cursor.getColumnIndex(DBHelper.KEY_FIRST_NAME);
            int patronymicIndex = cursor.getColumnIndex(DBHelper.KEY_PATRONYMIC);
            int timeIndex = cursor.getColumnIndex(DBHelper.KEY_TIME);
            do {
                content.add(new Element("ID:\n "+cursor.getString(idIndex),
                        "Ф: "+ cursor.getString(secnameIndex), "И: "+ cursor.getString(firnameIndex),
                        "О: "+ cursor.getString(patronymicIndex),"Время:\n"+cursor.getString(timeIndex)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        dbHelper.close();
        RecyclerAdapter recAdapter = new RecyclerAdapter(content);
        recyclerView.setAdapter(recAdapter);
    }
}