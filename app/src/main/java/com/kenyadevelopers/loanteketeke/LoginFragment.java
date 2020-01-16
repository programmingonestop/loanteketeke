package com.kenyadevelopers.loanteketeke;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kenyadevelopers.loanteketeke.databases.DataBaseManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private Button buttonLogin;
    private EditText editTextPhone;
    private EditText editTextPassword;
    private String phoneNumber;
    private String passWord;
    private boolean isPasswordAndPhoNeNumberValid;

    public static LoginFragment newInstance()
    {

        LoginFragment fragment = new LoginFragment();
        return fragment;
    }
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_login, container, false);
        buttonLogin= view.findViewById(R.id.button_login_loginpage);
        editTextPhone= view.findViewById(R.id.edittext_phone_no_login_page);
        editTextPassword= view.findViewById(R.id.edittext_password_login_page);
        setListeners();
        return view;
    }

    private void setListeners()
    {
        buttonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkIfPasswordMatches();
                if(isPasswordAndPhoNeNumberValid)
                {
                    Intent intent=LoanActivity.newIntent(getContext());
                    intent.putExtra(LoanActivity.PHONE_NO,phoneNumber);
                    startActivity(intent);
                }
              return;
            }
        });

        editTextPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

                phoneNumber=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                    passWord=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void checkIfPasswordMatches()
    {
       Client client=DataBaseManager.getDataBaseManager(getContext()).queryUserInfo(null,null);
       int passWordFromDb=client.passWord;
       int phoneFromDb=client.getPhoneNumber();
       if(passWordFromDb==Integer.parseInt(passWord)&&phoneFromDb==Integer.parseInt(phoneNumber))
       {
           isPasswordAndPhoNeNumberValid =true;
           //continue with login
       }
       else
           {
               isPasswordAndPhoNeNumberValid =false;

               Toast.makeText(getContext(),getString(R.string.valid_password_and_phone),Toast.LENGTH_LONG).show();
           }
    }

}
