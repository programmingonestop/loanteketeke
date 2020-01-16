package com.kenyadevelopers.loanteketeke.databases;

public class DataBaseSchema
{
    public static class UserDataTable
    {
        public static final String TABLE_NAME="user_data";

        public static class Columns
        {
            public static final String NAME="name";
            public static final String EMAIL="email";
            public static final String PHONE_NUMBER="phoneNumber";
            public static final String PASSWORD="passWord";
            public static final String LOAN_BORROWED="loanBorrowed";
            public static final String LOAN_STRENGTH="loanStrength";
            public static final String HAS_LOAN_ARREARS ="hasLoanArrears";
            public static final String LOAN_BORROWED_DATE="loan_borrowed_date";
            public static final String LOAN_REPAYMENT_DATE="loan_repayment_date";
            public static final String LOGIN_STATUS="login_status";

        }
    }
}
