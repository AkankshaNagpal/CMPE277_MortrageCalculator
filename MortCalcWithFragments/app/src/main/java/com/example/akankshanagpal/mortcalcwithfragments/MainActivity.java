package com.example.akankshanagpal.mortcalcwithfragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.*;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements InputFragment.OnButtonClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void calculateClicked(HashMap<String,String> inputValues)
    {
        OutputFragment outputFragment = (OutputFragment)getFragmentManager().findFragmentById(R.id.output_frag);
        if(outputFragment == null)
        {
         Intent intent = new Intent(this,OutputActivity.class);
            double homeloan = Double.parseDouble(inputValues.get("homeloan"));
            double downpayment = Double.parseDouble(inputValues.get("downpayment"));
            double term = Double.parseDouble(inputValues.get("term"));
            double interestRate = Double.parseDouble(inputValues.get("interestRate"));
            double propTaxRate = Double.parseDouble(inputValues.get("propTaxRate"));
            intent.putExtra("homeloan",homeloan);
            intent.putExtra("downPayment",downpayment);
            intent.putExtra("term",term);
            intent.putExtra("interestRate",interestRate);
            intent.putExtra("propTaxRate",propTaxRate);
            startActivity(intent);
        }
        else
        {
            outputFragment.updateResult(inputValues);
        }
    }

    @Override
    public void resetClicked()
    {
        OutputFragment outputFragment = (OutputFragment)getFragmentManager().findFragmentById(R.id.output_frag);
        if(outputFragment != null)
        {
            outputFragment.clearResult();
        }

    }



}
