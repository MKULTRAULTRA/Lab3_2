package com.example.lab3;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Date;

public class MakeRecord extends AppCompatActivity implements View.OnClickListener {
    DBHelper dbHelper;
    EditText etSecName, etFirName, etPatronymic ;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_rec);
        etSecName = (EditText) findViewById(R.id.etSecName);
        etFirName = (EditText) findViewById(R.id.etFirName);
        etPatronymic = (EditText) findViewById(R.id.etPatronymic);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View v) {
        String second_name = etSecName.getText().toString();
        String first_name = etFirName.getText().toString();
        String patronymic = etPatronymic.getText().toString();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Date date = new Date();
        String hours = String.format("%tH", date);
        String minutes = String.format("%tM", date);
        String time = hours+':'+minutes;


        switch (v.getId()) {

            case R.id.btnAdd:
                contentValues.put(DBHelper.KEY_SECOND_NAME, second_name);
                contentValues.put(DBHelper.KEY_FIRST_NAME, first_name);
                contentValues.put(DBHelper.KEY_PATRONYMIC, patronymic);
                contentValues.put(DBHelper.KEY_TIME, time);
                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                break;
        }
        finish();
        dbHelper.close();
    }

}