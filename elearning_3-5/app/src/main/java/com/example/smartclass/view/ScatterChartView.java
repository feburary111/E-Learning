package com.example.smartclass.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartclass.R;
import com.example.smartclass.base.BaseChartView;
import com.example.smartclass.bean.ScatterCoordinateBean;
import com.example.smartclass.manager.ScatterChartManager;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/2/16
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class ScatterChartView extends Fragment implements BaseChartView {

    @BindView(R.id.scatterChartView)
    ScatterChart scatterChart;
    private ScatterData scatterData;
    private Entry averageValue;

    public static final String CURRENT_CLASS_STATISTICS = "currentClassStatistics";

    public static ScatterChartView newInstance() {

        Bundle args = new Bundle();

        ScatterChartView fragment = new ScatterChartView();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.view_scatter_chart, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    private void setScatterData(){

        ArrayList<Entry> yVals = new ArrayList<>();

        yVals.add(new Entry(1,1));
        yVals.add(new Entry(1,3));
        yVals.add(new Entry(3,1));
        yVals.add(new Entry(3,3));
        yVals.add(new Entry(2,2));

        float avX = 0;
        float avY = 0;

        for(int i = 0; i < yVals.size(); i++){
            avX += yVals.get(i).getX();
        }

        for(int i = 0; i < yVals.size(); i++){
            avY += yVals.get(i).getY();
        }

        avX /= yVals.size();
        avY /= yVals.size();

        ScatterDataSet set1 = new ScatterDataSet(yVals, "优秀");
        ArrayList<IScatterDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        ScatterData data = new ScatterData(dataSets);
        scatterData = data;

        averageValue =  new Entry(avX, avY);
    }

    @Override
    public void setChartData(ArrayList chartData, String dataType) {

        if(CURRENT_CLASS_STATISTICS.equals(dataType)){
            setCurrentClassStatistics(chartData);
        }
    }

    @Override
    public void initChartView() {

        ScatterChartManager scatterChartManager = new ScatterChartManager(scatterChart);
        scatterChartManager.setChartData(scatterData);
        //scatterChartManager.setLimitLine(averageValue);
        scatterChartManager.initChartView();
    }

    @Override
    public void updateChartData() {

    }

    private void setCurrentClassStatistics(ArrayList chartData){

        List<Entry> chartDataList = new ArrayList<>();
        List<IScatterDataSet> dataSets = new ArrayList<>();

        ScatterCoordinateBean bean;
        for(int i = 0; i < chartData.size(); i++){

            bean = (ScatterCoordinateBean)chartData.get(i);
            chartDataList.add(new Entry(bean.getFocus_index(), bean.getMark()));
        }

        setAverageValue(chartDataList);
        ScatterDataSet scatterDataSet = new ScatterDataSet(chartDataList, "currentClassStatistics");
        dataSets.add(scatterDataSet);
        setScatterData(dataSets);
    }

    private void setAverageValue(List<Entry> chartDataList){

        float avX = 0;
        float avY = 0;
        for(int i = 0; i < chartDataList.size(); i++){
            avX += chartDataList.get(i).getX();
            avY += chartDataList.get(i).getY();
        }

        avX /= chartDataList.size();
        avY /= chartDataList.size();
        averageValue =  new Entry(avX, avY);
    }

    private void setScatterData(List<IScatterDataSet> dataSets){

        scatterData = new ScatterData(dataSets);
    }
}
