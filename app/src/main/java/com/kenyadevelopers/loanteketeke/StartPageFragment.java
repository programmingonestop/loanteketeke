package com.kenyadevelopers.loanteketeke;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartPageFragment extends Fragment
{

    private Button buttonSignUp;
    private Button buttonLogin;

    public static StartPageFragment newInstance()
    {
        StartPageFragment fragment = new StartPageFragment();
        return fragment;
    }
    public StartPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_start_page, container, false);

        buttonSignUp= view.findViewById(R.id.button_sign_up_startpage);
        buttonLogin= view.findViewById(R.id.button_login_startpage);

        setListenersOnViews();
        return view;
    }

    private void setListenersOnViews()
    {
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=SignUpActivity.newIntent(getContext());
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=LoginActivity.newIntent(getContext());
                startActivity(intent);
            }
        });
    }

}
