package com.example.studentgrades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText num, a, b, c, d, f;
    Button compute;

    double percentA, percentB, percentC, percentD, percentF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num = findViewById(R.id.edit_total);
        a = findViewById(R.id.edit_AStudents);
        b = findViewById(R.id.edit_BStudents);
        c = findViewById(R.id.edit_CStudents);
        d = findViewById(R.id.edit_DStudents);
        f = findViewById(R.id.edit_FStudents);
        compute = findViewById(R.id.btn_compute);
    }

    public void computeStats(View view) {
        double total, numA, numB, numC, numD, numF;

        // get user input data
        try {
            total = Double.parseDouble(num.getText().toString());
            numA = Double.parseDouble(a.getText().toString());
            numB = Double.parseDouble(b.getText().toString());
            numC = Double.parseDouble(c.getText().toString());
            numD = Double.parseDouble(d.getText().toString());
            numF = Double.parseDouble(f.getText().toString());
        }
        catch (NumberFormatException e) {
            // toast error message
            Toast.makeText(this, getResources().getString(R.string.toast_empty), Toast.LENGTH_LONG).show();
            return;
        }

        if ((numA + numB + numC + numD + numF) != total) {
            // toast error message
            Toast.makeText(this, getResources().getString(R.string.toast_invalidNum), Toast.LENGTH_LONG).show();
            return;
        }

        percentA = numA/total*100;
        percentB = numB/total*100;
        percentC = numC/total*100;
        percentD = numD/total*100;
        percentF = numF/total*100;

        // show dialog
        AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
        adb.setMessage("Student Grade Distribution:" +
                "\nA Students: " + percentA + "%" +
                "\nB Students: " + percentB + "%" +
                "\nC Students: " + percentC + "%" +
                "\nD Students: " + percentD + "%" +
                "\nF Students: " + percentF + "%")
                .setCancelable(false)
                .setPositiveButton("View chart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // start BarChartActivity
                        Intent intent = new Intent(getApplicationContext(), BarChartActivity.class);
                        intent.putExtra("A students", percentA);
                        intent.putExtra("B students", percentB);
                        intent.putExtra("C students", percentC);
                        intent.putExtra("D students", percentD);
                        intent.putExtra("F students", percentF);

                        startActivity(intent);
                    }
                })
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // do nothing
                    }
                });
        AlertDialog alert = adb.create();
        alert.show();
    }

    public void clearData (View view) {
     // clear edit text fields
        num.setText("");
        a.setText("");
        b.setText("");
        c.setText("");
        d.setText("");
        f.setText("");
    }
}