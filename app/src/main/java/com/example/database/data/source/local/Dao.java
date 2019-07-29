package com.example.database.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.database.data.Note;
import com.example.database.data.TodoContract.TodoEntry;

import java.util.ArrayList;
import java.util.List;

public class Dao {

    SQLiteDatabase database;
    DBHelper dbHelper;

    public  Dao(Context context){
        dbHelper = new DBHelper(context);

    }

    public void openDb(){
        database = dbHelper.getWritableDatabase();
    }

    public void creatRow(String title, String subtitle) {
        ContentValues values = new ContentValues();
        values.put(TodoEntry.COLUMN_NAME_TITLE,title);
        values.put(TodoEntry.COLUMN_NAME_SUBTITLE,subtitle);
        database.insert(TodoEntry.TABLE_NAME,null, values);
    }

    public String readRow() {
        Cursor cursor = database.query(TodoEntry.TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToLast();
        int colTitleIndex = cursor.getColumnIndex(TodoEntry.COLUMN_NAME_TITLE);
        int colSubtitleIndex = cursor.getColumnIndex(TodoEntry.COLUMN_NAME_SUBTITLE);
        String title = cursor.getString(colTitleIndex);
        String subtitle = cursor.getString(colSubtitleIndex);

        return title + "\n" + subtitle;
    }

    public Cursor readRows() {
        return  database.query(TodoEntry.TABLE_NAME,null,null,null,null,null,null);
    }

    public List<Note> getRows() {
        List<Note> notes = new ArrayList<>();

        Cursor cursor = database.query(TodoEntry.TABLE_NAME,null,null,null,null,null,null);

        int colTitleIndex = cursor.getColumnIndex(TodoEntry.COLUMN_NAME_TITLE);
        int colSubtitleIndex = cursor.getColumnIndex(TodoEntry.COLUMN_NAME_SUBTITLE);
        while (cursor.moveToNext()) {
            String title = cursor.getString(colTitleIndex);
            String subtitle = cursor.getString(colSubtitleIndex);

            Note note = new Note(title, subtitle);
            notes.add(note);
        }
        return notes;
    }

    public void creatRow(Note note) {
        ContentValues values = new ContentValues();
        values.put(TodoEntry.COLUMN_NAME_TITLE,note.getTitle());
        values.put(TodoEntry.COLUMN_NAME_SUBTITLE,note.getSubTitle());
        database.insert(TodoEntry.TABLE_NAME,null, values);
    }

    public void updateRow() {

    }

    public void deleteRow() {

    }
}