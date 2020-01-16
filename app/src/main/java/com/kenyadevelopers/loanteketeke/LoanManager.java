package com.kenyadevelopers.loanteketeke;

import android.content.Context;

import com.kenyadevelopers.loanteketeke.databases.DataBaseManager;

import java.util.Calendar;
import java.util.Date;

public class LoanManager
{
    private static LoanManager loanManager;
    private static final int STAGE_1_LOAN_LIMIT=20000;
    private static final int STAGE_2_LOAN_LIMIT=30000;
    private static final int STAGE_3_LOAN_LIMIT=40000;
    private static final int STAGE_4_LOAN_LIMIT=50000;
    private static final int STAGE_5_LOAN_LIMIT=60000;
    private static final int STAGE_6_LOAN_LIMIT=70000;

    private LoanManager()
    {

    }

    public static LoanManager getLoanManager()
    {
        if(loanManager==null)
        {
            loanManager=new LoanManager();
            return loanManager;
        }
        return loanManager;

    }
    public int loanBorrowed(Context context)
    {
        return getClient(context).getLoanBorrowed();
    }

    public boolean hasLoanArrears(Context context)
    {
        return getClient(context).hasLoanArreas;
    }
    public int[] getLoanDuration()
    {
        Date date=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int month=calendar.get(Calendar.MONTH)+1;
        int dateOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
        int []dateFormated={month,dateOfMonth};

        return  dateFormated;

    }
    public int getLoanStrength(Context context)
    {
        return getClient(context).getLoanStrength();
    }
    public int getLoanRank(Context context)
    {
        Client client=getClient(context);

        switch (client.getLoanStrength())
        {
            case 1:
                return STAGE_1_LOAN_LIMIT;
            case 2:
                return STAGE_1_LOAN_LIMIT;
            case 3:
                return STAGE_1_LOAN_LIMIT;
            case 4:
                return STAGE_1_LOAN_LIMIT;
            case 5:
                return STAGE_1_LOAN_LIMIT;
            case 6:
                return STAGE_2_LOAN_LIMIT;
            case 7:
                return STAGE_2_LOAN_LIMIT;
            case 8:
                return STAGE_2_LOAN_LIMIT;
            case 9:
                return STAGE_2_LOAN_LIMIT;
            case 10:
                return STAGE_2_LOAN_LIMIT;
            case 11:
                return STAGE_3_LOAN_LIMIT;
            case 12:
                return STAGE_3_LOAN_LIMIT;
            case 13:
                return STAGE_3_LOAN_LIMIT;
            case 14:
                return STAGE_3_LOAN_LIMIT;
            case 15:
                return STAGE_3_LOAN_LIMIT;
            case 16:
                return STAGE_3_LOAN_LIMIT;
            case 17:
                return STAGE_3_LOAN_LIMIT;
            case 18:
                return STAGE_3_LOAN_LIMIT;
            case 19:
                return STAGE_1_LOAN_LIMIT;
            case 20:
                return STAGE_3_LOAN_LIMIT;
            case 21:
                return STAGE_4_LOAN_LIMIT;
            case 22:
                return STAGE_4_LOAN_LIMIT;
            case 23:
                return STAGE_4_LOAN_LIMIT;
            case 24:
                return STAGE_4_LOAN_LIMIT;
            case 25:
                return STAGE_4_LOAN_LIMIT;
            case 26:
                return STAGE_5_LOAN_LIMIT;
            case 27:
                return STAGE_5_LOAN_LIMIT;
            case 28:
                return STAGE_5_LOAN_LIMIT;
            case 29:
                return STAGE_5_LOAN_LIMIT;
            case 30:
                return STAGE_5_LOAN_LIMIT;
            case 31:
                return STAGE_6_LOAN_LIMIT;
            case 32:
                return STAGE_6_LOAN_LIMIT;
            case 33:
                return STAGE_6_LOAN_LIMIT;
            case 34:
                return STAGE_6_LOAN_LIMIT;
            case 35:
                return STAGE_6_LOAN_LIMIT;

            default:
                    return 0;
        }
    }

    public  Client getClient(Context context)
    {
        Client client=DataBaseManager.getDataBaseManager(context).queryUserInfo(null,null);
        return client;
    }
    public int getLoanLimit(Context context)
    {
        Client client=DataBaseManager.getDataBaseManager(context).queryUserInfo(null,null);
        int LoanLimit=getLoanRank(context);
        return LoanLimit;
    }

    public Date determineRepaymentDay()
    {
        Date date=new Date();
        Calendar calendar=Calendar.getInstance();


        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);

        date=new Date(year,month+1,day);
        return date;
    }
    public String checkMonth(int i) {
        switch (i) {
            case 0:
                return "Jan";

            case 1:
                return "Feb";

            case 2:
                return "Mar";


            case 3:
                return "Apr";


            case 4:
                return "May";

            case 5:
                return "Jun";


            case 6:
                return "Jul";

            case 7:
                return "Aug";

            case 8:
                return "Sep";

            case 9:
                return "Oct";

            case 10:
                return "Nov";

            case 11:
                return "Dec";
            default:
                return null;

        }
    }
    public int[] getRepaymentDateFromDb(Context context)
    {
        Client client=getClient(context);
        String repaymentDate=client.getDateToPay();
        Date date=new Date(repaymentDate);

        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);

        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);

        int[]repayment_day={month,day};

        return repayment_day;
    }
}
