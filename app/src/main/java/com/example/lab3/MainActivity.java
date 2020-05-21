package com.example.lab3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.time.LocalTime;
import java.util.Random;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        Button getButton = (Button) findViewById(R.id.button1);
        getButton.setOnClickListener(this);
        Button makeRecordButton = (Button) findViewById(R.id.button2);
        makeRecordButton.setOnClickListener(this);
        Button changeNameButton = (Button) findViewById(R.id.button3);
        changeNameButton.setOnClickListener(this);

        DBHelper dbHelper;
        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.execSQL("DELETE FROM students");
        ContentValues contentValues = new ContentValues();

        final Random random = new Random();
        String Sec_name, Fir_name, Patronymic;

        String [] name={"Продуктовый Владимир Владимирович","Контрабас Алексей Павлович","Заморская Катерина Андреевна","Меркулов Павел Денисович","Рандомный Валерий Валерьевич","Картофельный Борис Вольфрамович","Навальный Алексей Антатольевич",
                "Жириновский Владимир Вольфович","Вольфганг Амадей Моцарт","Гимлер Генрих Вольфович","Абдулай Мухамед Ахматович", "Жиженко Кассандра Валерьевна"};

        for (int i=0; i<5; i++){
            int j = random.nextInt(name.length);
            String[] SplitWords = name[j].split(" ");
            Sec_name = SplitWords[0];
            Fir_name = SplitWords[1];
            Patronymic = SplitWords[2];
            contentValues.put(DBHelper.KEY_SECOND_NAME, Sec_name);
            contentValues.put(DBHelper.KEY_FIRST_NAME, Fir_name);
            contentValues.put(DBHelper.KEY_PATRONYMIC, Patronymic);
            LocalTime time = LocalTime.of(random.nextInt(24),random.nextInt(60));
            contentValues.put(DBHelper.KEY_TIME, String.valueOf(time));
            database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
        }
        database.close();
        dbHelper.close();
    }

    @Override
    public void onClick(View v) {

        ContentValues contentValues = new ContentValues();

        switch (v.getId()) {
            case R.id.button1: {
                Intent intent = new Intent();
                intent.setClass(this, GetStudents.class);
                startActivity(intent);
            }

            break;
            case R.id.button2: {
                Intent intent = new Intent();
                intent.setClass(this, MakeRecord.class);
                startActivity(intent);
            }
            break;
            case R.id.button3: {
                DBHelper dbHelper;
                dbHelper = new DBHelper(this);
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                database.execSQL(
                        "UPDATE students " +
                                "SET second_name = 'Иванов', first_name = 'Иван', patronymic = 'Иванович' " +
                                "WHERE _id = (SELECT MAX(_id) FROM students)"
                );
                database.close();
                dbHelper.close();
            }
            break;
            default:
                break;
        }
      }

}
