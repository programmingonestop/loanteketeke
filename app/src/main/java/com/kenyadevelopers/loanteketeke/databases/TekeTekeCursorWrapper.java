package com.kenyadevelopers.loanteketeke.databases;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.kenyadevelopers.loanteketeke.Client;

public class TekeTekeCursorWrapper extends CursorWrapper
{

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public TekeTekeCursorWrapper(Cursor cursor)
    {
        super(cursor);
    }
    public Client getClient()
    {
        String name=getString(getColumnIndex(DataBaseSchema.UserDataTable.Columns.NAME));
        String email=getString(getColumnIndex(DataBaseSchema.UserDataTable.Columns.EMAIL));
        String phoneNumber=getString(getColumnIndex(DataBaseSchema.UserDataTable.Columns.PHONE_NUMBER));
        String passWord=getString(getColumnIndex(DataBaseSchema.UserDataTable.Columns.PASSWORD));
        String loanStrength=getString(getColumnIndex(DataBaseSchema.UserDataTable.Columns.LOAN_STRENGTH));
        String loanBorrowed=getString(getColumnIndex(DataBaseSchema.UserDataTable.Columns.LOAN_BORROWED));
        String hasLoanArrears=getString(getColumnIndex(DataBaseSchema.UserDataTable.Columns.HAS_LOAN_ARREARS));
        String dateBorrowed=getString(getColumnIndex(DataBaseSchema.UserDataTable.Columns.LOAN_BORROWED_DATE));
        String dateToPay=getString(getColumnIndex(DataBaseSchema.UserDataTable.Columns.LOAN_REPAYMENT_DATE));

        Client client=new Client(name,email,Boolean.parseBoolean(hasLoanArrears),Integer.parseInt(phoneNumber),
                Integer.parseInt(passWord),Integer.parseInt(loanBorrowed),Integer.parseInt(loanStrength),dateBorrowed,dateToPay);

        return client;
    }

}
