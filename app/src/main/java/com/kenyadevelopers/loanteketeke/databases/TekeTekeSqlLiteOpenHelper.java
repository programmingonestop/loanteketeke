package com.kenyadevelopers.loanteketeke.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TekeTekeSqlLiteOpenHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME="USER_INFOR.DB";
    private static final int DATABASE_VERSION=1;
    public TekeTekeSqlLiteOpenHelper(Context context)
    {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
       db.execSQL("CREATE TABLE "+DataBaseSchema.UserDataTable.TABLE_NAME
               + "("+DataBaseSchema.UserDataTable.Columns.NAME+","
               +DataBaseSchema.UserDataTable.Columns.EMAIL+","
               +DataBaseSchema.UserDataTable.Columns.PHONE_NUMBER+","
               +DataBaseSchema.UserDataTable.Columns.PASSWORD+","
               +DataBaseSchema.UserDataTable.Columns.LOAN_STRENGTH+","
               +DataBaseSchema.UserDataTable.Columns.LOAN_BORROWED+","
               +DataBaseSchema.UserDataTable.Columns.HAS_LOAN_ARREARS +","
               +DataBaseSchema.UserDataTable.Columns.LOAN_BORROWED_DATE+","
               +DataBaseSchema.UserDataTable.Columns.LOAN_REPAYMENT_DATE+","
               +DataBaseSchema.UserDataTable.Columns.LOGIN_STATUS
               +")"



       );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        db.execSQL("DROP TABLE IF EXISTS "+DataBaseSchema.UserDataTable.TABLE_NAME);
        onCreate(db);
    }
}
