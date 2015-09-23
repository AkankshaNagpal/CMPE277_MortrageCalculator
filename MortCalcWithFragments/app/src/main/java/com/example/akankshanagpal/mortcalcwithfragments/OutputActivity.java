package com.example.akankshanagpal.mortcalcwithfragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import java.util.HashMap;


public class OutputActivity extends AppCompatActivity {

    HashMap<String,String> inputVal = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outout_activity_layout);

        Intent intent = getIntent();
        double homeloan  = intent.getDoubleExtra("homeloan", -1);
        double downpayment = intent.getDoubleExtra("downPayment", -1);
        double term = intent.getDoubleExtra("term", -1);
        double interestRate = intent.getDoubleExtra("interestRate",-1);
        double propTax = intent.getDoubleExtra("propTaxRate",-1);

        inputVal.put("homeloan",String.valueOf(homeloan));
        inputVal.put("downpayment",String.valueOf(downpayment));
        inputVal.put("term",String.valueOf(term));
        inputVal.put("interestRate",String.valueOf(interestRate));
        inputVal.put("propTaxRate",String.valueOf(propTax));


        OutputFragment outputFragment = (OutputFragment)getFragmentManager().findFragmentById(R.id.output_frag);
        outputFragment.updateResult(inputVal);

    }


}
