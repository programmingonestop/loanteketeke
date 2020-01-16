package com.kenyadevelopers.loanteketeke;

public class Client
{
    String name,email;
    boolean hasLoanArreas;
    boolean isLogOut=true;
    int phoneNumber,passWord,LoanBorrowed,loanStrength;
    String dateBorrowed,dateToPay;

    public Client(String name, String email,
                  Boolean hasLoanArreas, int phoneNumber,
                  int passWord, int loanBorrowed, int loanStrength/*,int loanLimit*/,String dateBorrowed,String dateToPay)
    {
        this.name = name;
        this.email = email;
        this.hasLoanArreas = hasLoanArreas;
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        LoanBorrowed = loanBorrowed;
        this.loanStrength = loanStrength;
        this.dateBorrowed=dateBorrowed;
        this.dateToPay=dateToPay;
    }

    public Boolean getLogOut() {
        return isLogOut;
    }

    public void setLogOut(Boolean logOut) {
        isLogOut = logOut;
    }

    public String getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getHasLoanArreas() {
        return hasLoanArreas;
    }

    public void setHasLoanArreas(Boolean hasLoanArreas) {
        this.hasLoanArreas = hasLoanArreas;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPassWord() {
        return passWord;
    }

    public void setPassWord(int passWord) {
        this.passWord = passWord;
    }

    public int getLoanBorrowed() {
        return LoanBorrowed;
    }

    public void setLoanBorrowed(int loanBorrowed) {
        LoanBorrowed = loanBorrowed;
    }

    public int getLoanStrength() {
        return loanStrength;
    }

    public void setLoanStrength(int loanStrength) {
        this.loanStrength = loanStrength;
    }

    public String  getDateToPay() {
        return dateToPay;
    }

    public void setDateToPay(String dateToPay) {
        this.dateToPay = dateToPay;
    }
}
