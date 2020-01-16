package com.kenyadevelopers.loanteketeke;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class LoanActivity extends SingleFragmentActivity
{
    public static final String PHONE_NO="phoneNo";

    public static Intent newIntent(Context context)
    {
        Intent intent=new Intent(context,LoanActivity.class);
        return intent;
    }
    @Override
    public Fragment createFragment()
    {
        String phoneN=getIntent().getStringExtra(PHONE_NO);
        return LoanFragment.newInstance(phoneN);
    }
}
