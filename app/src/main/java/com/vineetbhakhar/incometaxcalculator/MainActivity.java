package com.vineetbhakhar.incometaxcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "abcd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner sexSpinner = (Spinner) findViewById(R.id.sex_spinner);
        ArrayAdapter<CharSequence> sexAdapter = ArrayAdapter.createFromResource(this,
                R.array.sex_array, android.R.layout.simple_spinner_item);
        sexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexSpinner.setAdapter(sexAdapter);

        Spinner ageSpinner = (Spinner) findViewById(R.id.age_spinner);
        ArrayAdapter<CharSequence> ageAdapter = ArrayAdapter.createFromResource(this,
                R.array.age_array, android.R.layout.simple_spinner_item);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageSpinner.setAdapter(ageAdapter);

        Spinner fySpinner = (Spinner) findViewById(R.id.fy_spinner);
        ArrayAdapter<CharSequence> fyAdapter = ArrayAdapter.createFromResource(this,
                R.array.fy_array, android.R.layout.simple_spinner_item);
        fyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fySpinner.setAdapter(fyAdapter);
    }


    public void calculateTax (View view){
        if (view.getId() == R.id.btnCalculateTax){

            Intent intent = new Intent (this, CalculatedTaxAndSavings.class);
            Bundle extras = new Bundle();


            EditText basic_income = (EditText) findViewById(R.id.input_taxableIncome);
            String basicIncome = basic_income.getText().toString();
            EditText income_from_interest = (EditText) findViewById(R.id.input_IncomeInterest);
            String incomeFromIntrest = income_from_interest.getText().toString();
            EditText income_from_rent = (EditText) findViewById(R.id.input_RentIncome);
            String incomeFromRent = income_from_rent.getText().toString();

            EditText saving_investments = (EditText) findViewById(R.id.input_savingInvestments);
            String savingInvestments = saving_investments.getText().toString();
            EditText house_rent = (EditText) findViewById(R.id.input_houseRent);
            String houseRent = house_rent.getText().toString();
            EditText health_insurance = (EditText) findViewById(R.id.input_healthInsurance);
            String healthInsurance = health_insurance.getText().toString();
            EditText interest_on_education_loan = (EditText) findViewById(R.id.input_EducationLoanIntrest);
            String interestOnEducationLoan = interest_on_education_loan.getText().toString();
            EditText donations = (EditText) findViewById(R.id.input_Donations);
            String Donations = donations.getText().toString();
            EditText interest_on_savings = (EditText) findViewById(R.id.input_IntrestOnSaving);
            String interestOnSavings = interest_on_savings.getText().toString();
            EditText interest_on_house_Loan = (EditText) findViewById(R.id.input_HomeLoanIntrest);
            String interestOnHouseLoan = income_from_interest.getText().toString();

//        if (income=NULL) income =0;
//            int deductions = tryParseInt(input_deductions.getText().toString(),0);
//            int netDeductions = (deductions>150000)?150000:deductions;
//            int num = basic_income - netDeductions;
//            num = calcTax(num);
            extras.putString("BasicIncome", basicIncome);
            extras.putString("IncomeFromInterest", incomeFromIntrest);
            extras.putString("IncomeFromRent", incomeFromRent);

            extras.putString("SavingInvestments", savingInvestments);
            extras.putString("HouseRent", houseRent);
            extras.putString("HealthInsurance", healthInsurance);
            extras.putString("InterestOnEducationLoan", interestOnEducationLoan);
            extras.putString("Donations", Donations);
            extras.putString("InterestOnSavings", interestOnSavings);
            extras.putString("InterestOnHouseLoan", interestOnHouseLoan);

            intent.putExtras(extras);
            startActivity(intent);

        }
    }
}
