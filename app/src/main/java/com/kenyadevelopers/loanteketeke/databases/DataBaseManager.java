package com.kenyadevelopers.loanteketeke.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kenyadevelopers.loanteketeke.Client;

public class DataBaseManager
{
    public static DataBaseManager dbm;
    private final SQLiteDatabase db;

    private DataBaseManager(Context context)
    {
        TekeTekeSqlLiteOpenHelper tsh=new TekeTekeSqlLiteOpenHelper(context);
        db=tsh.getWritableDatabase();
    }
    public static DataBaseManager getDataBaseManager(Context context)
    {
        if(dbm==null)
        {
            DataBaseManager dbm=new DataBaseManager(context);
            return dbm;
        }
        return dbm;
    }
    public long insertUserInfo(Client client)
    {
        ContentValues contentValues= getContentValues(client);
        long l=db.insert(DataBaseSchema.UserDataTable.TABLE_NAME,null,contentValues);
        return l;
    }
    public Client queryUserInfo(String where,String[] whereArgs)
    {
       TekeTekeCursorWrapper cw=queryDb(where,whereArgs);
       cw.moveToLast();
       Client client=cw.getClient();
       return client;

    }

    private TekeTekeCursorWrapper queryDb(String where, String[]whereArgs)
    {
        Cursor cursor=db.query(DataBaseSchema.UserDataTable.TABLE_NAME,null,
          where, whereArgs, null,null,null,null);

        return new TekeTekeCursorWrapper(cursor);
    }

    public void upadateUserInfor(Client client)
    {
        ContentValues cv= getContentValues(client);
        int i=db.update(DataBaseSchema.UserDataTable.TABLE_NAME,cv,null,null);
    }

    private ContentValues getContentValues(Client client)
    {
        ContentValues cv=new ContentValues();
        cv.put(DataBaseSchema.UserDataTable.Columns.NAME,client.getName());
        cv.put(DataBaseSchema.UserDataTable.Columns.EMAIL,client.getEmail());
        cv.put(DataBaseSchema.UserDataTable.Columns.PHONE_NUMBER,Integer.toString(client.getPhoneNumber()));
        cv.put(DataBaseSchema.UserDataTable.Columns.PASSWORD,Integer.toString(client.getPassWord()));
        cv.put(DataBaseSchema.UserDataTable.Columns.LOAN_STRENGTH,Integer.toString(client.getLoanStrength()));
        cv.put(DataBaseSchema.UserDataTable.Columns.LOAN_BORROWED,Integer.toString(client.getLoanBorrowed()));
        cv.put(DataBaseSchema.UserDataTable.Columns.HAS_LOAN_ARREARS,Boolean.toString(client.getHasLoanArreas()));
        cv.put(DataBaseSchema.UserDataTable.Columns.LOAN_BORROWED_DATE,client.getDateBorrowed());
        cv.put(DataBaseSchema.UserDataTable.Columns.LOAN_REPAYMENT_DATE,client.getDateToPay());
        cv.put(DataBaseSchema.UserDataTable.Columns.LOGIN_STATUS,client.getLogOut().toString());
        return cv;
    }

}
