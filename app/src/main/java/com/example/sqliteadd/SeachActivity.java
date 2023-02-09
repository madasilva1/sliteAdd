package com.example.sqliteadd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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
        ArrayAdapter<Object> nameadapter;
        final ListView namelist = (ListView) findViewById(R.id.lv);
        nameadapter = new ArrayAdapter<Object>(getApplicationContext(), android.R.layout.simple_list_item_1);
        namelist.setAdapter(nameadapter);

        String word = enterText.getText().toString();
        mTextView.setText("Resulted for " + word + ":\n  \n");
        mTextView.setMovementMethod(new ScrollingMovementMethod());
        Cursor cursor = mDB.search(word);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {

                StringBuilder result = new StringBuilder();
                result.append(cursor.getString(cursor.getColumnIndex(mDB.NAME_COL)));
                result.append(cursor.getString(cursor.getColumnIndex(mDB.TRACKS_COL)));
                result.append(cursor.getString(cursor.getColumnIndex(mDB.DESCRIPTION_COL)));
                result.append(cursor.getString(cursor.getColumnIndex(mDB.DURATION_COL)));
                result.append(":\n\n");
                mTextView.append(result + "\n");
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            enterText.append(getString(R.string.no_result ));
            
            mTextView.setText("");
        }
        }
    }
