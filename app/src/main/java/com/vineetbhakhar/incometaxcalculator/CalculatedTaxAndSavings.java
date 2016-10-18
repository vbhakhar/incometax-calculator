package com.vineetbhakhar.incometaxcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CalculatedTaxAndSavings extends AppCompatActivity {

    public static int tryParseInt(String str, int defaultInt) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultInt;
        }
    }

    public static int calcTax(int netValue) {
        double slab3 = netValue > 1000000 ? (netValue- 1000000) * 0.3 : 0;
        double slab2 = 0;
        double slab1 = 0;
        Log.v("netValue = ", Integer.toString(netValue) );
        if (netValue > 500000) {
                slab2 = ((netValue - 500000)>500000?500000:(netValue - 500000)) * 0.2;
        }
        else
            slab2 = 0 ;

        if (netValue > 250000) {
            slab1 = ((netValue - 250000)>250000?250000:(netValue - 250000)) * 0.1;
        }
        else slab1 = 0;
        Log.v("slab1 = ", Double.toString(slab1) );
        Log.v("slab2 = ", Double.toString(slab2) );
        Log.v("slab3 = ", Double.toString(slab3) );

        return (int) (slab1 + slab2 + slab3 + 0.5d);
    }

    public static int ded_houseRent(int houseRent, int income) {
        double a = houseRent - (income * 0.05)>0?houseRent - (income * 0.05):0;
        double b = income*0.5;
        return (int) (a<b?a:b);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calculated_tax_and_savings);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        int basicIncome = tryParseInt(extras.getString("BasicIncome", "0"), 0);
        int incomeFromInterest = tryParseInt(extras.getString("IncomeFromInterest", "0"), 0);
        int incomeFromRent = tryParseInt(extras.getString("IncomeFromRent", "0"), 0);

        int total_income = (basicIncome + incomeFromInterest + incomeFromRent);

        int savingInvestments = tryParseInt(extras.getString("SavingInvestments", "0"), 0);
        int houseRent = tryParseInt(extras.getString("HouseRent", "0"), 0);
        int healthInsurance = tryParseInt(extras.getString("HealthInsurance", "0"), 0);
        int interestOnEducationLoan = tryParseInt(extras.getString("InterestOnEducationLoan", "0"), 0);
        int donations = tryParseInt(extras.getString("Donations", "0"), 0);
        int interestOnSavings = tryParseInt(extras.getString("InterestOnSavings", "0"), 0);
        int interestOnHouseLoan = tryParseInt(extras.getString("InterestOnHouseLoan", "0"), 0);



        int deductions_savingInvesments = savingInvestments > 150000 ? 150000 : savingInvestments;
        int deductions_healthInsurance = healthInsurance > 15000 ? 15000 : healthInsurance;
        int deductions_houseRent = ded_houseRent(houseRent, basicIncome);
        Log.v("calcTax",Integer.toString(deductions_houseRent));

        int deductions_interestOnEducationLoan = interestOnEducationLoan > 300000 ? 300000: interestOnEducationLoan;
        int deductions_donations = (int) (donations*0.5);
        int deductions_interestOnSavings = interestOnSavings>10000?10000:interestOnSavings;
        int deductions_interestOnHouseLoan = interestOnHouseLoan>200000?200000:interestOnHouseLoan;

        int total_deductions = (deductions_savingInvesments + deductions_donations + deductions_healthInsurance + deductions_houseRent
                + deductions_interestOnEducationLoan + deductions_interestOnSavings + deductions_interestOnHouseLoan);

        int net_tax = calcTax(total_income - total_deductions);

        int net_savings = calcTax(total_income) - net_tax;

//        String calc_message = "You saved ₹" + Integer.toString(total_deductions) + "\nOn your net income ₹"+ Integer.toString(total_income) +
//        " tax applicable is ₹" + Integer.toString(net_savings);
        TextView calcMessage = (TextView) findViewById(R.id.calcMessage);
        calcMessage.setTextSize(30);
        calcMessage.setText("You saved ₹ " + Integer.toString(net_savings));
        TextView taxMessage = (TextView) findViewById(R.id.taxMessage);
        taxMessage.setTextSize(16);
        taxMessage.setText("On your net income of ₹"+ Integer.toString(total_income) +
                ", tax applicable is ₹" + Integer.toString(net_tax));

        if(deductions_houseRent > 0){
            String HRAMessage = "You recieved expemtion of ₹"+ Integer.toString(deductions_houseRent) +" on your house rent.";
            TextView hraMessage = (TextView) findViewById(R.id.cardText_HRA);
            hraMessage.setTextSize(20);
            hraMessage.setText(HRAMessage);
        }
        else {
            LinearLayout l = (LinearLayout) findViewById(R.id.HRA);
            l.setVisibility(LinearLayout.GONE);
        }

        if(deductions_savingInvesments > 0){
            String HRAMessage = "You recieved expemtion of ₹"+ Integer.toString(deductions_savingInvesments) +" for your saving investments.";
            TextView hraMessage = (TextView) findViewById(R.id.cardText_savingOnInvestments);
            hraMessage.setTextSize(20);
            hraMessage.setText(HRAMessage);
        }
        else {
            String HRAMessage = "You could have saved upto ₹1,50,000 by making some saving investments, " +
                    "like insurance policies or pension or provident fund schemes.";
            TextView hraMessage = (TextView) findViewById(R.id.cardText_savingOnInvestments);
            hraMessage.setTextSize(20);
            hraMessage.setText(HRAMessage);
        }

        if(deductions_interestOnEducationLoan > 0){
            String HRAMessage = "You recieved expemtion of ₹"+ Integer.toString(deductions_interestOnEducationLoan) +" by paying off your education loan interest.";
            TextView hraMessage = (TextView) findViewById(R.id.cardText_savingOnEduLoan);
            hraMessage.setTextSize(20);
            hraMessage.setText(HRAMessage);
        }
        else {
            LinearLayout l = (LinearLayout) findViewById(R.id.savingOnEduLoan);
            l.setVisibility(LinearLayout.GONE);
        }

    }
}
