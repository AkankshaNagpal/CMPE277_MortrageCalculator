package com.example.akankshanagpal.mortcalcwithfragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.util.*;



/**
 * Created by akankshanagpal on 9/21/15.
 */
public class InputFragment extends Fragment implements View.OnClickListener {

    EditText etHomeLoan,etDownPayment,etTerm,etInterestRate, etPropTaxRate;
    Button btnCalculate,btnReset;
    HashMap<String,String> inputForCalc = new HashMap();
    OnButtonClickListener onButtonClickListener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_fragment_layout,container,false);

        //Initialise EditText and Buttons
        etHomeLoan = (EditText)view.findViewById(R.id.etHomeLoan);
        etDownPayment = (EditText)view.findViewById(R.id.etDownPayment);
        etTerm = (EditText)view.findViewById(R.id.etTerm);
        etInterestRate = (EditText)view.findViewById(R.id.etInterestRate);
        etPropTaxRate = (EditText)view.findViewById(R.id.etPropTaxRate);

        btnCalculate = (Button)view.findViewById(R.id.btnCalculate);
        btnReset = (Button)view.findViewById(R.id.btnReset);

        btnCalculate.setOnClickListener(this);
        btnReset.setOnClickListener(this);

        return view;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCalculate:
                ArrayList<String> validTermValues = new ArrayList<String>(
                        Arrays.asList("15", "20", "25", "30", "40"));

                String homeLoan = etHomeLoan.getText().toString();
                try {
                    double homeD = Double.parseDouble(homeLoan);
                } catch (Exception e) {
                    etHomeLoan.setText("Enter valid Home Value!");
                }
                String downPayment = etDownPayment.getText().toString();
                try {
                    double downpaymentD = Double.parseDouble(downPayment);
                } catch (Exception e) {
                    etDownPayment.setText("Enter valid down payment");
                }
                String term = etTerm.getText().toString();
                try {
                    double termD = Double.parseDouble(term);
                } catch (Exception e) {
                    etTerm.setText("Enter valid term");
                }
                String intRate = etInterestRate.getText().toString();
                try {
                    double intRateD = Double.parseDouble(intRate);
                }
                catch (NumberFormatException nfe)
                {
                    etInterestRate.setText("Enter valid interest rate");
                }

                String propTaxRate = etPropTaxRate.getText().toString();
                try {
                    double propTaxRateD = Double.parseDouble(propTaxRate);
                }
                catch (NumberFormatException nfe)
                {
                    etPropTaxRate.setText("Enter valid prop tax rate");
                }


                if (homeLoan.equals("") || homeLoan.contains("Enter")){
                    etHomeLoan.setText("Enter valid Home Value");
                } else if (downPayment.equals("") || downPayment.contains("Enter")) {
                    etDownPayment.setText("Enter valid downpayment");
                } else if (!validTermValues.contains(term) || term.equals("") || !term.matches("[0-9]+")) {
                    etTerm.setText("Enter Valid Term");
                } else if (intRate.contains("Enter") || intRate.equals("") ) {
                    etInterestRate.setText("Enter valid Interest Rate");
                } else if (propTaxRate.equals("") || propTaxRate.contains("Enter")) {
                    etPropTaxRate.setText("Enter valid property tax rate");
                } else {
                    inputForCalc.put("homeloan", homeLoan);
                    inputForCalc.put("downpayment", downPayment);
                    inputForCalc.put("term", term);
                    inputForCalc.put("interestRate", intRate);
                    inputForCalc.put("propTaxRate", propTaxRate);

                    onButtonClickListener.calculateClicked(inputForCalc);
                }
                break;

            case R.id.btnReset:
                etHomeLoan.setText("");
                etInterestRate.setText("");
                etDownPayment.setText("");
                etPropTaxRate.setText("");
                etTerm.setText("");
                onButtonClickListener.resetClicked();
                break;

        }
    }

        public interface OnButtonClickListener {
            public void calculateClicked(HashMap<String, String> inputValues);
            public void resetClicked();
        }

        @Override
        public void onAttach (Activity activity){
            super.onAttach(activity);
            try {
                onButtonClickListener = (OnButtonClickListener) activity;
            } catch (Exception e) {

            }
        }

    }

