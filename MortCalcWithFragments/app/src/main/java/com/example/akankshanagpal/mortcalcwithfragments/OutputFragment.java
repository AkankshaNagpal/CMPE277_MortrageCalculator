package com.example.akankshanagpal.mortcalcwithfragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by akankshanagpal on 9/21/15.
 */
public class OutputFragment extends Fragment {

    TextView tvMontlyPaymentAmount, tvTotalInterest, tvTotalTax, tvPayOffdate;
    private static DecimalFormat df2 = new DecimalFormat(".##");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.output_fragment_layout, container, false);

        tvMontlyPaymentAmount = (TextView) view.findViewById(R.id.tvMonPayAmount);
        tvTotalInterest = (TextView) view.findViewById(R.id.tvTotalInterest);
        tvTotalTax = (TextView) view.findViewById(R.id.tvTotalTax);
        tvPayOffdate = (TextView) view.findViewById(R.id.tvPayoffdate);
        return view;
    }

    public void updateResult(HashMap<String, String> inputVal) {
        double homeloan = Double.parseDouble(inputVal.get("homeloan"));
        double downpayment = Double.parseDouble(inputVal.get("downpayment"));
        double term = Double.parseDouble(inputVal.get("term"));
        double intRate = Double.parseDouble(inputVal.get("interestRate"));
        double proptaxRate = Double.parseDouble(inputVal.get("propTaxRate"));


        tvMontlyPaymentAmount.setText(calculateMonthlyPayment(homeloan, downpayment, term, intRate, proptaxRate) + " $");
        tvTotalInterest.setText(String.valueOf(calculateTotalInterest(homeloan, downpayment, term, intRate, proptaxRate)) + " $");
        tvTotalTax.setText(String.valueOf(calculateTotalPropertyTax(homeloan,proptaxRate,term)) + " $");
        tvPayOffdate.setText(String.valueOf(calculateTotalPayOffDate(term)));

    }

    public void clearResult()
    {
        tvMontlyPaymentAmount.setText("");
        tvTotalInterest.setText("");
        tvPayOffdate.setText("");
        tvTotalTax.setText("");
    }

    public String calculateMonthlyPayment(double homeloan, double downpayment, double term, double roI, double propTaxRate) {
        //Calculation of MonthlyPayment
        double principal = homeloan - downpayment;
        double roi = (roI / 100) / 12;
        double n = term * 12;
        double powerResult = (double) Math.pow((1 + roi), n);
        double monthlyMortPayment = principal * ((roi * powerResult) / (powerResult - 1));
        double totalPropertyTax = (homeloan * propTaxRate * term) / 100;
        double monthlyPropertyTax = totalPropertyTax / n;
        double totalMonthlyPayment = monthlyPropertyTax + monthlyMortPayment;
        String totalMonthlyPay = df2.format(totalMonthlyPayment);

        return totalMonthlyPay;
    }

    //Calculation of Total Property tax
    public String calculateTotalPropertyTax(double homeloan, double propTaxRate, double term) {
        double totalPropertyTax = (homeloan * propTaxRate * term) / 100;
        String totalPropTax = df2.format(totalPropertyTax);
        return totalPropTax;
    }

    //Calculation of Total Interest Paid
    public String calculateTotalInterest(double homeloan, double downpayment, double term, double roI, double propTaxRate)
    {
        double principal = homeloan - downpayment;
        double roi = (roI / 100) / 12;
        double n = term * 12;
        double powerResult = (double) Math.pow((1 + roi), n);
        double monthlyMortPayment = principal * ((roi * powerResult) / (powerResult - 1));
        double totalPropertyTax = (homeloan * propTaxRate * term) / 100;
        double monthlyPropertyTax = totalPropertyTax / n;
        double totalMonthlyPayment = monthlyPropertyTax + monthlyMortPayment;
        double interestPaid = totalMonthlyPayment * n - principal - totalPropertyTax;
        String interest = df2.format(interestPaid);
        return interest;
    }

    //Calculation of pay off date
    public String calculateTotalPayOffDate(double term)
    {
        Calendar now = Calendar.getInstance();
        int termYears = (int) term;
        now.add(Calendar.YEAR, termYears);
        String payoffDate = ((now.get(Calendar.MONTH)) + "-" + now.get(Calendar.YEAR));

        return payoffDate;
    }



}
