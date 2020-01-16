package com.kenyadevelopers.loanteketeke;

import android.content.Context;
import android.content.Intent;
import android.database.CursorIndexOutOfBoundsException;
import android.support.v4.app.Fragment;

import com.kenyadevelopers.loanteketeke.databases.DataBaseManager;

public class MainActivity extends SingleFragmentActivity
{
    public static Intent newIntent(Context context)
    {
        Intent intent=new Intent(context,MainActivity.class);
        return intent;

    }


    @Override
    public Fragment createFragment()
    {
        //check if database is not null meanining there are records
        //hence not first time client
        //take client direct to loan fragment


            try
            {

                Client client = DataBaseManager.getDataBaseManager(this).queryUserInfo(null, null);
                if(!client.getLogOut())
                {
                    return LoanFragment.newInstance(Integer.toString(client.getPhoneNumber()));
                }
                else
                    {
                        return StartPageFragment.newInstance();
                    }
            } catch (CursorIndexOutOfBoundsException ex){

                return StartPageFragment.newInstance();



            }


    }

}
