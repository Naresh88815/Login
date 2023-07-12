package com.application.login.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.application.login.model.SignUpData;
import com.application.login.parameters.Params;

public class MyDbHandler extends SQLiteOpenHelper {
    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME +
                " (" + Params.KEY_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Params.KEY_USERNAME + "  TEXT, " +
                Params.KEY_EMAIL + "  TEXT, " +
                Params.KEY_PASSWORD + "  TEXT" + ")";

        db.execSQL(create);
        Log.d("dbuser", "Query being run is: " + create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Params.TABLE_NAME);
        onCreate(db);
    }

    public void addUser(SignUpData signUpData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_USERNAME, signUpData.getUsername());
        values.put(Params.KEY_EMAIL, signUpData.getEmail());
        values.put(Params.KEY_PASSWORD, signUpData.getPassword());

        db.insert(Params.TABLE_NAME, null, values);
        Log.d("dbuser", "Successfully insert");
        db.close();
    }
}
