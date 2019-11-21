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
            Toast.makeText(this, "Invalid input data", Toast.LENGTH_LONG).show();

            // clear edit text fields
            num.setText("");
            a.setText("");
            b.setText("");
            c.setText("");
            d.setText("");
            f.setText("");

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
                .setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // clear edit text fields
                        num.setText("");
                        a.setText("");
                        b.setText("");
                        c.setText("");
                        d.setText("");
                        f.setText("");
                    }
                });
        AlertDialog alert = adb.create();
        alert.show();
    }

}