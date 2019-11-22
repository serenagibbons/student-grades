package com.example.studentgrades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {

    double percentA, percentB, percentC, percentD, percentF;

    // bar chart objects
    BarChart chart;
    ArrayList<BarEntry> barEntry;
    ArrayList<String> barEntryLabels;
    BarDataSet barDataSet;
    BarData barData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        if (b != null) {
            percentA = b.getDouble("A students");
            percentB = b.getDouble("B students");
            percentC = b.getDouble("C students");
            percentD = b.getDouble("D students");
            percentF = b.getDouble("F students");

        }

        drawBarChart();
    }

    public void addBarValues() {

        barEntry.add(new BarEntry((float) percentA, 0));
        barEntry.add(new BarEntry((float) percentB, 1));
        barEntry.add(new BarEntry((float) percentC, 2));
        barEntry.add(new BarEntry((float) percentD, 3));
        barEntry.add(new BarEntry((float) percentF, 4));
    }

    public void addBarEntryLabels() {

        barEntryLabels.add("A");
        barEntryLabels.add("B");
        barEntryLabels.add("C");
        barEntryLabels.add("D");
        barEntryLabels.add("F");
    }

    public void drawBarChart() {
        chart = findViewById(R.id.chart1);
        barEntry = new ArrayList<>();
        barEntryLabels = new ArrayList<>();

        addBarValues();
        addBarEntryLabels();

        barDataSet = new BarDataSet(barEntry, "Student Grades");
        barData = new BarData(barEntryLabels, barDataSet);
        barDataSet.setColors(ColorTemplate.PASTEL_COLORS);
        chart.setData(barData);
        chart.setContentDescription(null);
        chart.animateY(3000);
    }

    public void goBack(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
