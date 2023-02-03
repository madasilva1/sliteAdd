package com.example.sqliteadd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SeachActivity extends AppCompatActivity {

    DBHandler mDB;
    private TextView mTextView, nameTextview;
    private EditText enterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);
        mDB = new DBHandler(this);
        enterText = (EditText) findViewById(R.id.search_word);
        mTextView = (TextView) findViewById(R.id.search_resulted);
    }
    //Click handler for search button

    @SuppressLint("Range")
    public void showResulted(View view) {
        String word = enterText.getText().toString();
        mTextView.setText("Resulted for " + word + ":\n\n");
        Cursor cursor = mDB.search(word);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            //int index;
            // String resulted;

            do {

                StringBuilder result = new StringBuilder();
                result.append(cursor.getString(cursor.getColumnIndex(mDB.NAME_COL)));
                result.append(cursor.getString(cursor.getColumnIndex(mDB.TRACKS_COL)));
                result.append(cursor.getString(cursor.getColumnIndex(mDB.DESCRIPTION_COL)));
                result.append(cursor.getString(cursor.getColumnIndex(mDB.DURATION_COL)));
                result.append("\n");
                mTextView.append(result + "\n");
            } while (cursor.moveToNext());
            cursor.close();
        }
    }
}