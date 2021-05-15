package com.example.uworthy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class Statisticsresult extends AppCompatActivity {
    PieChartView pieChartView;
    BarChart barChart;
    Integer picode,ccode,ecode,simofcode;
    Integer dpercent=0,rdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statisticsresult);




        Intent intent = getIntent();


        ccode = intent.getIntExtra("finalcolorcodes",0);
        picode = intent.getIntExtra("pnradiocodes",0);

        ecode= intent.getIntExtra("finalemojicodes",0);

     simofcode=ccode+picode+ecode;

        pieChartView = findViewById(R.id.chart);
        barChart = findViewById(R.id.barchart);
        // this code for piechat round

        if(simofcode==25){
            List pieData = new ArrayList<>();

            dpercent=15;
            rdata=100-15;

            pieData.add(new SliceValue(dpercent, Color.RED).setLabel("UnHappy"));
            pieData.add(new SliceValue(rdata, Color.GREEN).setLabel("happy"));
            PieChartData pieChartData = new PieChartData(pieData);
            pieChartData.setHasLabels(true).setValueLabelTextSize(14);
            pieChartData.setHasCenterCircle(true).setCenterText1("Sales in million").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
            pieChartView.setPieChartData(pieChartData);

        }
        else if(simofcode>25&&simofcode<=75){
            List pieData = new ArrayList<>();

            dpercent=20;
            rdata=100-20;
            pieData.add(new SliceValue(dpercent, Color.RED).setLabel("Unhappy"));
            pieData.add(new SliceValue(rdata, Color.GREEN).setLabel("happy"));
            PieChartData pieChartData = new PieChartData(pieData);
            pieChartData.setHasLabels(true).setValueLabelTextSize(14);
            pieChartData.setHasCenterCircle(true).setCenterText1("Sales in million").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
            pieChartView.setPieChartData(pieChartData);


        }
        else if(simofcode>75&&simofcode<=100){
            List pieData = new ArrayList<>();

            dpercent=35;
            rdata=100-35;
            pieData.add(new SliceValue(dpercent, Color.RED).setLabel("Unhappy"));
            pieData.add(new SliceValue(rdata, Color.GREEN).setLabel("happy"));
            PieChartData pieChartData = new PieChartData(pieData);
            pieChartData.setHasLabels(true).setValueLabelTextSize(14);
            pieChartData.setHasCenterCircle(true).setCenterText1("Sales in million").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
            pieChartView.setPieChartData(pieChartData);


        }
        else if(simofcode>100&&simofcode<=150){
            List pieData = new ArrayList<>();

            dpercent=44;
            rdata=100-44;

            pieData.add(new SliceValue(dpercent, Color.RED).setLabel("Unhappy"));
            pieData.add(new SliceValue(rdata, Color.GREEN).setLabel("Happy"));
            PieChartData pieChartData = new PieChartData(pieData);
            pieChartData.setHasLabels(true).setValueLabelTextSize(14);
            pieChartData.setHasCenterCircle(true).setCenterText1("Sales in million").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
            pieChartView.setPieChartData(pieChartData);

        }
        else if(simofcode>150&&simofcode<=175){
            List pieData = new ArrayList<>();

            dpercent=50;
            rdata=100-50;
            pieData.add(new SliceValue(dpercent, Color.RED).setLabel("Unhappy"));
            pieData.add(new SliceValue(rdata, Color.GREEN).setLabel("happy"));
            PieChartData pieChartData = new PieChartData(pieData);
            pieChartData.setHasLabels(true).setValueLabelTextSize(14);
            pieChartData.setHasCenterCircle(true).setCenterText1("Sales in million").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
            pieChartView.setPieChartData(pieChartData);

        }
        else if(simofcode>175&&simofcode<=200){
            List pieData = new ArrayList<>();

            dpercent=57;
            rdata=100-57;
            pieData.add(new SliceValue(dpercent, Color.RED).setLabel("Unhappy"));
            pieData.add(new SliceValue(rdata, Color.GREEN).setLabel("Happy"));
            PieChartData pieChartData = new PieChartData(pieData);
            pieChartData.setHasLabels(true).setValueLabelTextSize(14);
            pieChartData.setHasCenterCircle(true).setCenterText1("Sales in million").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
            pieChartView.setPieChartData(pieChartData);


        }
        else if(simofcode>200&&simofcode<=225){
            List pieData = new ArrayList<>();

            dpercent=64;
            rdata=100-64;
            pieData.add(new SliceValue(dpercent, Color.RED).setLabel("Unhappy"));
            pieData.add(new SliceValue(rdata, Color.GREEN).setLabel("happy"));
            PieChartData pieChartData = new PieChartData(pieData);
            pieChartData.setHasLabels(true).setValueLabelTextSize(14);
            pieChartData.setHasCenterCircle(true).setCenterText1("Sales in million").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
            pieChartView.setPieChartData(pieChartData);


        }
        else if(simofcode>225&&simofcode<=250){
            List pieData = new ArrayList<>();

            dpercent=77;
            rdata=100-77;

            pieData.add(new SliceValue(dpercent, Color.RED).setLabel("Unhappy"));
            pieData.add(new SliceValue(rdata, Color.GREEN).setLabel("Happy"));
            PieChartData pieChartData = new PieChartData(pieData);
            pieChartData.setHasLabels(true).setValueLabelTextSize(14);
            pieChartData.setHasCenterCircle(true).setCenterText1("Sales in million").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
            pieChartView.setPieChartData(pieChartData);

        }
        else if(simofcode>250&&simofcode<=275){
            List pieData = new ArrayList<>();

            dpercent=85;
            rdata=100-85;
            pieData.add(new SliceValue(dpercent, Color.RED).setLabel("unhappy"));
            pieData.add(new SliceValue(rdata, Color.GREEN).setLabel("Happy"));
            PieChartData pieChartData = new PieChartData(pieData);
            pieChartData.setHasLabels(true).setValueLabelTextSize(14);
            pieChartData.setHasCenterCircle(true).setCenterText1("User ").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
            pieChartView.setPieChartData(pieChartData);

        }
//
//        Toast.makeText(this, "dp"+dpercent, Toast.LENGTH_SHORT).show();
////        pieData.add(new SliceValue(25, Color.GRAY).setLabel("Q2: $4"));
////        pieData.add(new SliceValue(10, Color.RED).setLabel("Q3: $18"));
////        pieData.add(new SliceValue(60, Color.MAGENTA).setLabel("Q4: $28"));
//

        //this code for piechart line

        // create BarEntry for Bar Group 1
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(picode, 0));
        entries.add(new BarEntry(ccode, 1));
        entries.add(new BarEntry(ecode, 2));
//        entries.add(new BarEntry(20f, 3));
//        entries.add(new BarEntry(15f, 4));
//        entries.add(new BarEntry(19f, 5));

        BarDataSet bardataset = new BarDataSet(entries, "Cells");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Images");
        labels.add("Color");
        labels.add("Emoji");



        BarData data = new BarData(labels, bardataset);
        barChart.setData(data); // set the data and list of lables into chart

      //  barChart.setDescription("Set Bar Chart Description");  // set the description

        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

        barChart.animateY(5000);
    }


}
