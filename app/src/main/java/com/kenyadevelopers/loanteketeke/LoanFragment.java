package com.kenyadevelopers.loanteketeke;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kenyadevelopers.loanteketeke.databases.DataBaseManager;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoanFragment extends Fragment {

    private Toolbar toolBar;
    private static final String PAYMENT="payment_schedule";
    private Button buttonApplyLoan;
    private TextView textViewAmountDisplay;
    private TextView textViewDurationDisplay;
    private Button buttonTotalDue;
    private Button buttonIntrest;
    private Button buttonPrincipal;
    private final int VALUE_PAID=222;
    public static final String USER_PAYMENT_AMOUNT ="amount_payment";
    public static final String PHONE_NO_RECEIVED="phone_no_rec";
    private TextView textViewAlert;
    private static final int MAX_RANK_LIMIT=35;
    private LinearLayout linearLayout;

    public static LoanFragment newInstance(String phoneNumber)
    {
        Bundle bundle=new Bundle();
        bundle.putString(PHONE_NO_RECEIVED,phoneNumber);
        LoanFragment fragment = new LoanFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.teketeke,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.payment_schedule:

                //check teketeke of loan not taken
                if(LoanManager.getLoanManager().hasLoanArrears(getContext()))
                {
                    PaymentOptionsDialogFragment podf=PaymentOptionsDialogFragment.newInstance(LoanManager.getLoanManager().getClient(getContext()).getLoanBorrowed());
                    podf.setTargetFragment(this,VALUE_PAID);
                    podf.show(getFragmentManager(),PAYMENT);

                }
                else
                    {
                        Toast.makeText(getContext(),getString(R.string.no_loan_taken),Toast.LENGTH_LONG).show();

                    }

                    break;
            case R.id.logOut:
                Client client=LoanManager.getLoanManager().getClient(getContext());
                client.setLogOut(true);
                Intent intent=MainActivity.newIntent(getContext());
                Toast.makeText(getContext(), getString(R.string.log_out), Toast.LENGTH_SHORT).show();
                startActivity(intent);

                break;
            default:
                Toast.makeText(getContext(),getString(R.string.no_loan_taken),Toast.LENGTH_LONG).show();


        }
        return super.onOptionsItemSelected(item);
    }

    public LoanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_loan, container, false);
        setUpViews(view);
        updateViews();
        AppCompatActivity appCompat=(AppCompatActivity)getActivity();
        appCompat.setSupportActionBar(toolBar);

        setListeners();
        return view;
    }

    private void setUpViews(View view)
    {
        linearLayout=(LinearLayout)view.findViewById(R.id.linear_layout_root);
        toolBar= view.findViewById(R.id.toolbar);
        buttonApplyLoan = view.findViewById(R.id.button_apply);
        textViewAmountDisplay= view.findViewById(R.id.textview_amount_display_loan_fragment);
        textViewDurationDisplay= view.findViewById(R.id.textview_duration_display_loan_fragment);
        buttonPrincipal= view.findViewById(R.id.button_principal_loanpage);
        buttonIntrest= view.findViewById(R.id.button_intrest_loan_page);
        buttonTotalDue= view.findViewById(R.id.button_total_due_loanpage);
        textViewAlert= view.findViewById(R.id.textview_loan_alert);

    }

    private void updateViews()
    {
        LoanManager loanManager=LoanManager.getLoanManager();
        int loanLimit=loanManager.getLoanRank(getContext());
        updateLoanDisplay(loanLimit);

    }

    private void setListeners()
    {
        buttonApplyLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                /*
                when called what should happen?
                check if there is arrears/amount borrowed
                check loan limit
                update the database with loan borrowed amount
                update the database with loan borrowed date
                update the database with repayment date
                change textview to read...You have an outstanding loan as displayed bellow
                update round button with proper values
                */

                if(LoanManager.getLoanManager().hasLoanArrears(getContext()))
                {
                    Toast.makeText(getContext(), getString(R.string.have_arrears), Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        Client client=LoanManager.getLoanManager().getClient(getContext());
                        int loanLimit=LoanManager.getLoanManager().getLoanRank(getContext());
                        client.setHasLoanArreas(true);

                        int totalAmountDue=(int)((float)110/100*loanLimit);
                        client.setLoanBorrowed(totalAmountDue);
                        client.setDateToPay(LoanManager.getLoanManager().determineRepaymentDay().toString());

                        client.setDateBorrowed(new Date().toString());

                        DataBaseManager.getDataBaseManager(getContext()).upadateUserInfor(client);

                        updateLoanDisplay(totalAmountDue);

                        Toast.makeText(getContext(),getString(R.string.loan_granted), Toast.LENGTH_SHORT).show();

                        AlertDialog alertDialog=new AlertDialog.Builder(getContext()).setPositiveButton(getString(R.string.ok),null).setTitle(getString(R.string.amount_disbursed))
                                .setMessage(getString(R.string.amount_disbursed_message)).create();
                        alertDialog.show();
                    }
            }
        });

    }

    private void updateLoanDisplay(int loanLimit)
    {
        Client client=LoanManager.getLoanManager().getClient(getContext());

        if(client.hasLoanArreas)
        {
            linearLayout.setBackground(getResources().getDrawable(R.drawable.button_round_loan_qualification_arrears));

            int LoanArreas=LoanManager.getLoanManager().loanBorrowed(getContext());
            textViewAlert.setTextColor(getResources().getColor(R.color.baby_orange));
            textViewAlert.setText(R.string.outstanding_loan);
            textViewAmountDisplay.setText(Integer.toString(LoanArreas));

            int[]durationDisplay=LoanManager.getLoanManager().getRepaymentDateFromDb(getContext());
            String month=LoanManager.getLoanManager().checkMonth(durationDisplay[0]);
            textViewDurationDisplay.setText(month+" "+durationDisplay[1]);
            buttonPrincipal.setText(Integer.toString(LoanManager.getLoanManager().getLoanLimit(getContext())));
            buttonIntrest.setText(Integer.toString((int)((float)10/100*LoanManager.getLoanManager().getLoanLimit(getContext()))));
            buttonTotalDue.setText(Integer.toString(LoanManager.getLoanManager().loanBorrowed(getContext())));



        }
        else
            {
                int[]duration=LoanManager.getLoanManager().getLoanDuration();
                String month_in_words=LoanManager.getLoanManager().checkMonth(duration[0]);

                textViewDurationDisplay.setText(month_in_words+" "+duration[1]);

                buttonPrincipal.setText(Integer.toString(loanLimit));

                float interest=((float)10/100*loanLimit);

                int theInterest=(int)interest;
                buttonIntrest.setText(Integer.toString(theInterest));

                int totalDue=(int)interest+loanLimit;

                buttonTotalDue.setText(Integer.toString(totalDue));

                textViewAmountDisplay.setText(Integer.toString(loanLimit));

                textViewAlert.setText(getString(R.string.loan_qualified));
                textViewAlert.setBackgroundColor(getResources().getColor(R.color.window_background));
                linearLayout.setBackground(getResources().getDrawable(R.drawable.button_round_loan_qualification));
                textViewAlert.setTextColor(getResources().getColor(R.color.blue_ivy));



                client.setDateBorrowed(new Date().toString());
                client.setDateToPay(LoanManager.getLoanManager().determineRepaymentDay().toString());

                DataBaseManager.getDataBaseManager(getContext()).upadateUserInfor(client);
            }




    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        String amountPaid=data.getStringExtra(USER_PAYMENT_AMOUNT);

        int loanBorrowed=LoanManager.getLoanManager().loanBorrowed(getContext());
        int currentBorrowedLoan=loanBorrowed-(Integer.parseInt(amountPaid));

        if(currentBorrowedLoan==0)
        {
            Client client=LoanManager.getLoanManager().getClient(getContext());
            client.setLoanBorrowed(currentBorrowedLoan);
            client.setHasLoanArreas(false);

            int loanStrength=client.getLoanStrength();
            if(loanStrength<35)
            {
                int increasedRank=loanStrength+1;
                client.setLoanStrength(increasedRank);
            }
            else
                {
                    //do nothing the client has reached the maximum borrow limit
                }

            DataBaseManager.getDataBaseManager(getContext()).upadateUserInfor(client);
            updateLoanDisplay(LoanManager.getLoanManager().getLoanLimit(getContext()));
        }
        else
            {
                Client client=LoanManager.getLoanManager().getClient(getContext());
                client.setLoanBorrowed(currentBorrowedLoan);
                DataBaseManager.getDataBaseManager(getContext()).upadateUserInfor(client);
                updateLoanDisplay(currentBorrowedLoan);
            }
    }
}
