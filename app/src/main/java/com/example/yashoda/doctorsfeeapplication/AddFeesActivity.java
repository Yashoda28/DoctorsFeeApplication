package com.example.yashoda.doctorsfeeapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
    SharedPreferences sharedPref;
    String patientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fees);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        patientID = sharedPref.getString(Constants.patientID, "10");

        Button btnAdd = findViewById(R.id.btnAddingOnAddFees);
        findViews();
        createSaveButton(btnAdd, etAddFeesDate, etAddFeesReason, etAddFeesTotal);

    }

    private void findViews() {
        etAddFeesDate = findViewById(R.id.etAddFeesDate);
        etAddFeesReason = findViewById(R.id.etAddFeesReason);
        etAddFeesTotal = findViewById(R.id.etAddFeesTotal);
    }

    private void createSaveButton(Button btnAdd, final EditText etAddFeesDate, final EditText etAddFeesReason, final EditText etAddFeesTotal) {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                try {
                    date = new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(etAddFeesDate.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                final String reason = etAddFeesReason.getText().toString();
                final Double total = Double.parseDouble(etAddFeesTotal.getText().toString());

                progressDialog = ProgressDialog.show(context,
                        "Loading",
                        "Please be patient....", false);
                final String finalDate = date;
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Save(finalDate,reason,total);
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
        });
    }

    private boolean Save(String date, String reason, Double total) throws SQLException {
        int rowsInserted = connectivity.insertUpdateOrDelete(getFeesQuery(date,reason,total, patientID));
        progressDialog.cancel();
        return true;
    }

    private String getFeesQuery(String date, String reason, Double total, String patientID) {
        return "INSERT INTO FEES (DATETIME,REASON,TOTAL,PATIENTID)" +
                "VALUES(" + date + "," + reason + "," + total + "," + patientID + ")";
    }


}

