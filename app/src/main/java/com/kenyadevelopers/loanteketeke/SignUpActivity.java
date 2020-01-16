package com.kenyadevelopers.loanteketeke;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class SignUpActivity extends SingleFragmentActivity
{

    public static Intent newIntent(Context context)
    {
        Intent intent=new Intent(context,SignUpActivity.class);

        return intent;
    }
    @Override
    public Fragment createFragment() {
        return SignUpFragment.newInstance();
    }
}
