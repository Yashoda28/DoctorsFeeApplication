package com.example.yashoda.doctorsfeeapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.yashoda.doctorsfeeapplication.Tables.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.yashoda.doctorsfeeapplication.CommonUtils.handleException;

public class MainActivity extends AppCompatActivity {

    Connectivity connectivity = new Connectivity();

    Context context = MainActivity.this;

    ProgressDialog progressDialog;

    Spinner spinner;
    ArrayList<Patient> patient;
    List<String> pNames;
    //final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
    //SharedPreferences.Editor editor = sharedPref.edit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinPatientName);
        spinner.getOnItemSelectedListener();

        progressDialog = ProgressDialog.show(context,
                "Logging in",
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

        Button btnAddNewRecord = findViewById(R.id.btnAddRecordOnMain);
        Button btnViewFees = findViewById(R.id.btnViewFeesOnMain);
        Button btnViewPatientDetails = findViewById(R.id.btnViewPatientDetailsOnMain);

        createAddNewRecordBtn(btnAddNewRecord);
        createViewFeesBtn(btnViewFees);
        createViewPatientDetailsBtn(btnViewPatientDetails);
    }

    private void populateArray() throws SQLException {
        ResultSet rs = connectivity.getResultSet(getPatientNameQuery());
        patient = new ArrayList<>();
        while (rs.next()) {
            Patient info = new Patient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getDate(7), rs.getString(8), rs.getString(9));
            patient.add(info);
        }
        populateSpinner();
        progressDialog.cancel();
    }

    private void populateSpinner() {
        runOnUiThread(new Runnable() {
            public void run() {
                pNames = new ArrayList<>();
                for (int i = 0; i < patient.size(); i++) {
                    pNames.add(patient.get(i).getPatientName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, pNames);
                spinner.setAdapter(adapter);
                try {
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            for (int i = 0; i < patient.size(); i++) {
                                if (patient.get(i).getPatientName().equals(spinner.getSelectedItem().toString())) {
                                    String name = spinner.getSelectedItem().toString();
                                    //editor.putString("key1", name);
                                }
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } catch (Exception e) {
                    Log.e("Error Message", e.getMessage());
                }
            }
        });
    }

    private void createAddNewRecordBtn(Button btnAddNewRecord) {
        btnAddNewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AddFeesActivity.class));
            }
        });
    }

    private void createViewFeesBtn(Button btnViewFees) {
        btnViewFees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ViewFeesActivity.class));
            }
        });
    }

    private void createViewPatientDetailsBtn(Button btnViewPatientDetails) {
        btnViewPatientDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ViewPatientDetailsActivity.class));
            }
        });
    }

    private String getPatientNameQuery() {
        return "SELECT * FROM PATIENT";
    }

}
