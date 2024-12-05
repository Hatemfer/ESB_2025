package com.example.esb_2025;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MabaseSQLite extends SQLiteOpenHelper {

    private static final String TABLE_USER = "table_user";
    private static final String COL_Email = "Email";

    private static final String COL_UserName = "Username";
    private static final String COL_Password = "Password";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_USER + " (" +
            COL_Email + " TEXT PRIMARY KEY, " +
            COL_UserName + " TEXT NOT NULL, " +
            COL_Password + " TEXT NOT NULL)";
    @Override
    public void onCreate(SQLiteDatabase SqLiteDatabase) {
        SqLiteDatabase.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase SqLiteDatabase, int i, int i1) {
        SqLiteDatabase.execSQL("DROP TABLE " + TABLE_USER + ";");
        onCreate(SqLiteDatabase);
    }

    public MabaseSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

}
