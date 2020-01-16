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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.kenyadevelopers.loanteketeke.databases.DataBaseManager;

import java.util.Date;
import java.util.PropertyResourceBundle;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment
{
    private Button buttonSignUp;
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPhoneNumber;
    private EditText editextPassword;
    private EditText editTextConfirmPassword;
    private Spinner spinner;
    private String clientName;
    private String clientEmail;
    private String clientPhoneNumber;
    private String clientConfirmPassword;
    private String clientPassword;
    private boolean isValueNull;
    private ProgressBar progressBar;

    public static SignUpFragment newInstance() {


        SignUpFragment fragment = new SignUpFragment();
        return fragment;
    }
    public SignUpFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_sign_up, container, false);
        findViewsInLayout(view);
        setUpListenersToViews();
        return view;
    }

    private void findViewsInLayout(View view)
    {
        buttonSignUp= view.findViewById(R.id.button_signup_signup_page);
        editTextName= view.findViewById(R.id.edittext_name_sign_up_page);
        editTextEmail= view.findViewById(R.id.edittext_email_signup_page);
        editTextPhoneNumber= view.findViewById(R.id.edittext_phoneNumber_signup_page);
        editextPassword= view.findViewById(R.id.edittext_Password_signup_page);
        editTextConfirmPassword= view.findViewById(R.id.edittext_confirm_password_signup_page);
        spinner= view.findViewById(R.id.spinner_signup_page);
        progressBar=(ProgressBar)view.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

    }

    private void setUpListenersToViews()
    {
        buttonSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkToEliminateNull();
                if(!isValueNull)
                {
                    progressBar.setVisibility(View.VISIBLE);
                    Client client=new Client(clientName,clientEmail,false,
                            Integer.parseInt(clientPhoneNumber),Integer.parseInt(clientPassword),
                            0,1,new Date().toString(),new Date().toString());
                    long s=DataBaseManager.getDataBaseManager(getContext()).insertUserInfo(client);
                    Intent intent=LoanActivity.newIntent(getContext());
                    intent.putExtra(LoanActivity.PHONE_NO,clientPhoneNumber);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignUpFragment.this.getContext(),getString(R.string.sign_up_success),Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            }
        });

        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

                    clientName =s.toString();


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                    clientEmail=s.toString();


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

                clientPhoneNumber=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

                    clientConfirmPassword=s.toString();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                    clientPassword=s.toString();

            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
    }

    private void checkToEliminateNull()
    {
        try {
            Integer.parseInt(clientPhoneNumber);
            isValueNull=false;
        }
        catch (NumberFormatException ex)
        {
            isValueNull=true;
            editTextPhoneNumber.setError(getString(R.string.field_error_or_invalid));
            editTextPhoneNumber.requestFocus();
            return;
        }

        try {
            Integer.parseInt(clientConfirmPassword);
            isValueNull=false;
        }
        catch (NumberFormatException ex)
        {
            isValueNull=true;
            editTextConfirmPassword.setError(getString(R.string.field_error));
            editTextConfirmPassword.requestFocus();
            return;
        }

        try {
            Integer.parseInt(clientPassword);
            isValueNull=false;
        }
        catch (NumberFormatException ex)
        {
            isValueNull=true;
            editextPassword.setError(getString(R.string.field_error));
            editextPassword.requestFocus();
            return;
        }
        if(clientName.length()<8)
        {
            isValueNull=true;
            editTextName.setError(getString(R.string.invalid_name));
            editTextName.requestFocus();
            return;
        }
        else
            {
                isValueNull=false;
            }

            if(clientEmail.length()<8|| !clientEmail.contains("@")|| !clientEmail.contains(".com"))
            {
                isValueNull=true;
                editTextEmail.setError(getString(R.string.invalid_email));
                editTextEmail.requestFocus();
                return;
            }
            else
                {
                    isValueNull=false;
                }

                if(clientPhoneNumber.length()<6 ||clientPhoneNumber.length()>12)
                {
                    isValueNull=true;
                    editTextPhoneNumber.setError(getString(R.string.invalid_phone_no));
                    editTextPhoneNumber.requestFocus();
                    return;
                }
                else
                    {
                        isValueNull=false;
                    }

                    if(!clientPassword.equals(clientConfirmPassword))
                    {
                        isValueNull=true;
                        editTextConfirmPassword.setError(getString(R.string.password_dont_match));
                        editTextConfirmPassword.requestFocus();
                        return;
                    }
                    else
                        {
                            isValueNull=false;
                        }
                        if(clientPassword.length()<4)
                        {
                            isValueNull=true;
                            editextPassword.setError(getString(R.string.invalid_password));
                            editextPassword.requestFocus();
                            return;
                        }
                        else
                            {
                                isValueNull=false;
                            }


    }

}
