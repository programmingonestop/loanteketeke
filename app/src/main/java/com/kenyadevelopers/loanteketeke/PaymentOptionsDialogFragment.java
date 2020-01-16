package com.kenyadevelopers.loanteketeke;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentOptionsDialogFragment extends DialogFragment
{
    private static final String ARG_AMOUNT_BORROWED="amount_borr";
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final EditText editText=new EditText(getContext());
        editText.setHint(getString(R.string.amount_for_payment));
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        AlertDialog alertDialog=new AlertDialog.Builder(getContext()).setTitle(R.string.make_payment)
                .setView(editText).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        int amount=getArguments().getInt(ARG_AMOUNT_BORROWED);
                        Editable value=editText.getText();
                        try {

                            Integer.parseInt(value.toString());
                        }catch (NumberFormatException ex)
                        {
                            editText.setError(getString(R.string.field_error));
                            editText.requestFocus();
                            Toast.makeText(getContext(), getString(R.string.field_error), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(Integer.parseInt(value.toString())<=amount)
                        {
                            String amount_payment=value.toString();

                            Intent intent=new Intent();
                            intent.putExtra(LoanFragment.USER_PAYMENT_AMOUNT,amount_payment);
                            getTargetFragment().onActivityResult(getTargetRequestCode(),Activity.RESULT_OK,intent);
                        }
                        else
                            {
                                Toast.makeText(getContext(),getString(R.string.value_high_than_borrowed),Toast.LENGTH_SHORT).show();
                            }
                    }
                }).create();
        return alertDialog;
    }

    public static PaymentOptionsDialogFragment newInstance(int amountBorrowed)
    {
        Bundle bundle=new Bundle();
        bundle.putInt(ARG_AMOUNT_BORROWED,amountBorrowed);
        PaymentOptionsDialogFragment fragment = new PaymentOptionsDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    public PaymentOptionsDialogFragment()
    {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
