package com.example.yashoda.doctorsfeeapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.yashoda.doctorsfeeapplication.CommonUtils.handleException;

public class AddFeesActivity extends AppCompatActivity {

    Connectivity connectivity = new Connectivity();

    Context context = AddFeesActivity.this;

    ProgressDialog progressDialog;

    EditText etAddFeesDate;
    EditText etAddFeesReason;
    EditText etAddFeesTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fees);

        Button btnAdd= findViewById(R.id.btnAddingOnAddFees);
        findViews();
        createSaveButton(btnAdd, etAddFeesDate, etAddFeesReason, etAddFeesTotal);

    }

    private void findViews() {
        etAddFeesDate = findViewById(R.id.etAddFeesDate);
        etAddFeesReason = findViewById(R.id.etAddFeesReason);
        etAddFeesTotal = findViewById(R.id.etAddFeesTotal);
    }

    private void createSaveButton(Button btnAdd, final EditText etAddFeesDate, final EditText etAddFeesReason, final EditText etAddFeesTotal)
    {
        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Date date;
                try
                {
                    date = new SimpleDateFormat("dd/MM/yyyy").parse(etAddFeesDate.getText().toString());
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                final String reason = etAddFeesReason.getText().toString();
                final Double total = Double.parseDouble(etAddFeesTotal.getText().toString());

                progressDialog = ProgressDialog.show(context,
                        "Logging in",
                        "Please be patient....", false);
                new Thread(new Runnable() {
                    public void run()
                    {
                        try {
                            //Save(date, reason, total);
                        }
                        catch (final Exception e)
                        {
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
            });
    }

    /* private String Save(Date date, String reason, Double total) throws SQLException
    {
       ResultSet rs;
        rs = connectivity.insertUpdateOrDelete(getPatientQuery(pName, surname, emailAddress, password, iDNumber, dateOfBirth, cellNumber, bloodType));
        rs.rowin
        rs = connectivity.insertUpdateOrDelete(getEmergencyQuery(emergencyType, emergencyName, emergencyNumber));

        return null;
    }*/

    private String getFeesQuery(Date date, String reason, Double total)
    {
        return "INSERT INTO FROM FEES F WHERE F.DATETIME = '" + date + "' F.REASON = '" + reason + "' F.TOTAL = '" + total + "'";
    }


}

