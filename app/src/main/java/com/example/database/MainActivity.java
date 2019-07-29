package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.database.data.TodoContract;
import com.example.database.data.source.local.Dao;
import com.example.database.data.Note;

public class MainActivity extends AppCompatActivity {

    EditText edtTitle, edtSubtitle;
    Dao dao;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTitle = findViewById(R.id.edtTxtTitle);
        edtSubtitle = findViewById(R.id.edtTxtSubtitle);
        listView = findViewById(R.id.listViewNotes);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dao = new Dao(this);
        dao.openDb();
        Cursor cursor = dao.readRows();
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, cursor,
                new String[]{TodoContract.TodoEntry.COLUMN_NAME_TITLE, TodoContract.TodoEntry.COLUMN_NAME_SUBTITLE},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(simpleCursorAdapter);
    }

    public void onClickHandler(View view) {

        switch  (view.getId())
        {
            case R.id.btnGet:
                String result =  dao.readRow();
                TextView textView = findViewById(R.id.textView);
                textView.setText(result);
                break;
            case R.id.btnPut:
                String title = edtTitle.getText().toString();
                String subtitle = edtSubtitle.getText().toString();
                //dao.creatRow(title,subtitle);
                Note note = new Note(title, subtitle);
                dao.creatRow(note);
                edtTitle.setText("");
                edtSubtitle.setText("" );
                break;
        }
    }
}
