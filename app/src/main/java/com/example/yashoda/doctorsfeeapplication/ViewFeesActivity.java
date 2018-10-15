package com.example.yashoda.doctorsfeeapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.yashoda.doctorsfeeapplication.Tables.Fees;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.yashoda.doctorsfeeapplication.CommonUtils.handleException;

public class ViewFeesActivity extends AppCompatActivity
{
    Connectivity connectivity = new Connectivity();

    Context context = ViewFeesActivity.this;

    ProgressDialog progressDialog;

    List<Double> total= new ArrayList<>();
    List<Date> dateTime= new ArrayList<>();
    List<String> reason = new ArrayList<>();
    SharedPreferences sharedPref;
    String patientID;
    TextView dateTV;
    TextView reasonTV;
    TextView totalTV;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fees);

        dateTV = findViewById(R.id.txtDate);
        reasonTV = findViewById(R.id.txtReason);
        totalTV = findViewById(R.id.txtTotal);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        patientID = sharedPref.getString(Constants.patientID,"Keshav");

        progressDialog = ProgressDialog.show(context,
                "Loading",
                "Please be patient....", false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    connectivity.connect();
                    populateArray();
                } catch (final Exception e) {
                    progressDialog.cancel();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            handleException(context, e, e.getMessage());
                        }
                    });
                }
            }
        }).start();
    }

    private void populateArray() throws SQLException
    {
        ResultSet rs = connectivity.getResultSet(getFeesQuery());
        while (rs.next())
        {
            reason.add(rs.getString(3));
            dateTime.add(rs.getDate(2));
            total.add(rs.getDouble(4));
        }
        populateTextView();
        progressDialog.cancel();
    }

    private void populateTextView()
    {
        runOnUiThread(new Runnable()
        {
            public void run()
            {
                dateTV.setMaxWidth(30);
                totalTV.setMaxWidth(30);
                reasonTV.setMaxWidth(30);
                for (int i = 0; i < total.size(); i++) {
                    dateTV.append("\n"+dateTime.get(i).toString());
                    reasonTV.append("\n"+reason.get(i));
                    totalTV.append("\n"+total.get(i).toString());
                }
            }
        });
    }

    private String getFeesQuery()
    {
        return "SELECT * FROM FEES";
    }
}
