package com.example.smartclass.adapter;

import android.content.res.Resources;
import android.graphics.Paint;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.smartclass.Formatter.PercentageAxisValueFormatter;
import com.example.smartclass.Formatter.StringAxisValueFormatter;
import com.example.smartclass.R;
import com.example.smartclass.bean.AttendanceProfileBean;
import com.example.smartclass.bean.DateAndPercentageBean;
import com.example.smartclass.bean.StudentInformationBean;
import com.example.smartclass.bean.StudentsWithAttendanceProblemsBean;
import com.example.smartclass.manager.LineChartManager;
import com.example.smartclass.util.CircleBarView;
import com.example.smartclass.util.CircleBarViewUtil;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YangFan
 * On 2019/1/31
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 * Description: 近期记录中班级页面的 ExpandableListAdapter
 */
public class ClassRecentRecordExpandableListAdapter extends BaseExpandableListAdapter {

    private Fragment parentFragment;

    private List<String> groupString;
    private List<AttendanceProfileBean> groupAttendanceProfileBeans;
    private ArrayList[] childAttendanceStatistics;
    private ArrayList[] childStudentStatusStatistics;
    private StudentsWithAttendanceProblemsBean[] childStudentsWithAttendanceProblems;


    public ClassRecentRecordExpandableListAdapter(Fragment fragment, List<String> groupString, List<AttendanceProfileBean> groupAttendanceProfileBeans){
        parentFragment = fragment;
        this.groupString = groupString;
        this.groupAttendanceProfileBeans = groupAttendanceProfileBeans;

        childAttendanceStatistics = new ArrayList[groupString.size()];
        childStudentStatusStatistics = new ArrayList[groupString.size()];
        childStudentsWithAttendanceProblems = new StudentsWithAttendanceProblemsBean[groupString.size()];
    }

    public ClassRecentRecordExpandableListAdapter(Fragment fragment, List<String> groupString, List<AttendanceProfileBean> groupAttendanceProfileBeans,
                                                  ArrayList[] childAttendanceStatistics, ArrayList[] childStudentStatusStatistics,
                                                  StudentsWithAttendanceProblemsBean[] childStudentsWithAttendanceProblems){
        parentFragment = fragment;
        this.groupString = groupString;
        this.groupAttendanceProfileBeans = groupAttendanceProfileBeans;
        this.childAttendanceStatistics = childAttendanceStatistics;
        this.childStudentStatusStatistics = childStudentStatusStatistics;
        this.childStudentsWithAttendanceProblems = childStudentsWithAttendanceProblems;
    }

    @Override
    public int getGroupCount() {
        return groupString.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupString.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        GroupViewHolder groupViewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_list_view_group_class,
                    parent, false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        }else{
            groupViewHolder = (GroupViewHolder)convertView.getTag();
        }
        groupViewHolder.setTabTitle(groupString.get(groupPosition));
        groupViewHolder.showAttendanceProfile(groupAttendanceProfileBeans.get(groupPosition));
        return convertView;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildViewHolder childViewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_list_view_child_class,
                    parent, false);
            childViewHolder = new ChildViewHolder(convertView, parentFragment);
            convertView.setTag(childViewHolder);
        }else{
            childViewHolder = (ChildViewHolder)convertView.getTag();
        }

            childViewHolder.showAttendanceLineChart(childAttendanceStatistics[groupPosition]);
            childViewHolder.showClassStatusLineChart(childStudentStatusStatistics[groupPosition]);

        if(childStudentsWithAttendanceProblems[groupPosition] != null){
            childViewHolder.showProblemStudentsList(childStudentsWithAttendanceProblems[groupPosition]);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public boolean isAlreadyLoaded(int groupPosition){
        return childAttendanceStatistics[groupPosition] != null;
    }

    public void bindDataToChildView(int position, ArrayList childAttendanceStatistics, ArrayList childStudentStatusStatistics,
                                    StudentsWithAttendanceProblemsBean studentsWithAttendanceProblemsBean){

        this.childAttendanceStatistics[position] = childAttendanceStatistics;
        this.childStudentStatusStatistics[position] = childStudentStatusStatistics;
        this.childStudentsWithAttendanceProblems[position] = studentsWithAttendanceProblemsBean;
    }


    static class GroupViewHolder{

        @BindView(R.id.expandableListItemTitle)
        TextView expandableListItemTitle;
        @BindView(R.id.classRecentRecordCircleProgressBar)
        CircleBarView circleBarView;
        @BindView(R.id.classRecentRecordProgressText)
        TextView classRecentRecordProgressText;

        @BindView(R.id.recentRecordPersonOfLateTextView)
        TextView recentRecordPersonOfLateTextView;
        @BindView(R.id.recentRecordOverallPersonOfLateTextView)
        TextView recentRecordOverallPersonOfLateTextView;
        @BindView(R.id.recentRecordPersonOfAbsenteeTextView)
        TextView recentRecordPersonOfAbsenteeTextView;
        @BindView(R.id.recentRecordOverallPersonOfAbsenteeTextView)
        TextView recentRecordOverallPersonOfAbsenteeTextView;
        @BindView(R.id.recentRecordPersonOfLeaveEarlyTextView)
        TextView recentRecordPersonOfLeaveEarlyTextView;
        @BindView(R.id.recentRecordOverallPersonOfLeaveEarlyTextView)
        TextView recentRecordOverallPersonOfLeaveEarlyTextView;
        @BindView(R.id.recentRecordPersonOfAbnormalTextView)
        TextView recentRecordPersonOfAbnormalTextView;
        @BindView(R.id.recentRecordOverallPersonOfAbnormalTextView)
        TextView recentRecordOverallPersonOfAbnormalTextView;

        private boolean isFirst = true;

        GroupViewHolder(View view){
            ButterKnife.bind(this, view);
        }

        public void showAttendanceProfile(AttendanceProfileBean bean) {

            setAttendanceProfileText(bean);
            if(isFirst){
                CircleBarViewUtil.setAttendanceCircleBarView(circleBarView, bean.getCurrent_attendance(), classRecentRecordProgressText, 3000);
                isFirst = false;
            }else{
                CircleBarViewUtil.setAttendanceCircleBarView(circleBarView, bean.getCurrent_attendance(), classRecentRecordProgressText, 0);
            }
        }

        private void setTabTitle(String title){

            expandableListItemTitle.setText(title);
        }

        private void setAttendanceProfileText(AttendanceProfileBean bean){

            String currentStudents = String.valueOf(bean.getTotal_students());
            recentRecordOverallPersonOfAbnormalTextView.setText(currentStudents);
            recentRecordOverallPersonOfAbsenteeTextView.setText(currentStudents);
            recentRecordOverallPersonOfLateTextView.setText(currentStudents);
            recentRecordOverallPersonOfLeaveEarlyTextView.setText(currentStudents);

            recentRecordPersonOfLateTextView.setText(formatAttendanceProfileText(bean.getLate()));
            recentRecordPersonOfAbsenteeTextView.setText(formatAttendanceProfileText(bean.getAbsent()));
            recentRecordPersonOfLeaveEarlyTextView.setText(formatAttendanceProfileText(bean.getEarly()));
            recentRecordPersonOfAbnormalTextView.setText(formatAttendanceProfileText(bean.getQingjia()));
        }

        private String formatAttendanceProfileText(int num){
            return String.valueOf(num) + "人";
        }
    }

    static class ChildViewHolder {

        @BindView(R.id.recentClassAttendanceLineChart)
        LineChart recentClassAttendanceLineChart;
        @BindView(R.id.recentClassStateLineChart)
        LineChart recentClassStateLineChart;
        @BindView(R.id.firstTabRecyclerView)
        RecyclerView firstTabRecyclerView;
        @BindView(R.id.secondTabRecyclerView)
        RecyclerView secondTabRecyclerView;
        @BindView(R.id.thirdTabRecyclerView)
        RecyclerView thirdTabRecyclerView;
        @BindView(R.id.fourthTabRecyclerView)
        RecyclerView fourthTabRecyclerView;

        @BindView(R.id.tabLeftView)
        RelativeLayout tabLeftView;
        @BindView(R.id.tabRightView)
        RelativeLayout tabRightView;
        @BindView(R.id.tabLeftText)
        TextView tabLeftText;
        @BindView(R.id.tabRightText)
        TextView tabRightText;

        @BindView(R.id.tabFirstView)
        RelativeLayout tabFirstView;
        @BindView(R.id.tabSecondView)
        RelativeLayout tabSecondView;
        @BindView(R.id.tabThirdView)
        RelativeLayout tabThirdView;
        @BindView(R.id.tabFourthView)
        RelativeLayout tabFourthView;

        @BindView(R.id.tabFirstText)
        TextView tabFirstText;
        @BindView(R.id.tabSecondText)
        TextView tabSecondText;
        @BindView(R.id.tabThirdText)
        TextView tabThirdText;
        @BindView(R.id.tabFourthText)
        TextView tabFourthText;

        @BindView(R.id.leftOfFirstTabOfClassRecentRecord)
        View leftOfFirstTabOfClassRecentRecord;
        @BindView(R.id.rightOfFirstTabOfClassRecentRecord)
        View rightOfFirstTabOfClassRecentRecord;
        @BindView(R.id.leftOfSecondTabOfClassRecentRecord)
        View leftOfSecondTabOfClassRecentRecord;
        @BindView(R.id.leftMidOfSecondTabOfClassRecentRecord)
        View leftMidOfSecondTabOfClassRecentRecord;
        @BindView(R.id.rightMidOfSecondTabOfClassRecentRecord)
        View rightMidOfSecondTabOfClassRecentRecord;
        @BindView(R.id.rightOfSecondTabOfClassRecentRecord)
        View rightOfSecondTabOfClassRecentRecord;

        @BindView(R.id.tabTitleDetailsText)
        RelativeLayout tabTitleDetailsText;
        @BindView(R.id.tabPromptText)
        RelativeLayout tabPromptText;

        private Fragment parentFragment;
        private int currentSelectTab = Integer.MIN_VALUE;
        private boolean selectLeftOfFirstTab = true;

        private LineData attendanceLineData;
        private String[] attendanceXAxisValue;
        private float attendanceMinOfYAxis = Integer.MAX_VALUE;
        private float attendanceMaxOfYAxis = Integer.MIN_VALUE;

        private LineData stateLineData;
        private String[] stateXAxisValue;
        private float stateMinOfYAxis = Integer.MAX_VALUE;
        private float stateMaxOfYAxis = Integer.MIN_VALUE;

        private static final int FIRST_PAGE = 0;
        private static final int SECOND_PAGE = 1;
        private static final int THIRD_PAGE = 2;
        private static final int FOURTH_PAGE = 3;

        ChildViewHolder(View view, Fragment fragment){
            ButterKnife.bind(this, view);
            parentFragment = fragment;
            initView();
        }

        private void initView(){

            initTitles();
            initTabCustomViewList();
        }

        private void initTitles() {

            Resources resources = parentFragment.getResources();
            String[] titleArray = resources.getStringArray(R.array.overall_student_recent_status_titles);
            tabLeftText.setText(titleArray[0]);
            tabRightText.setText(titleArray[1]);

            titleArray = resources.getStringArray(R.array.attendance_statistics_details_titles);
            tabFirstText.setText(titleArray[0]);
            tabSecondText.setText(titleArray[1]);
            tabThirdText.setText(titleArray[2]);
            tabFourthText.setText(titleArray[3]);
        }

        private void initTabCustomViewList(){

            final List<TextView> tabTextList = new ArrayList<>();
            tabTextList.add(tabFirstText);
            tabTextList.add(tabSecondText);
            tabTextList.add(tabThirdText);
            tabTextList.add(tabFourthText);

            final List<View> tabUnderLineList = new ArrayList<>();
            tabUnderLineList.add(leftOfSecondTabOfClassRecentRecord);
            tabUnderLineList.add(leftMidOfSecondTabOfClassRecentRecord);
            tabUnderLineList.add(rightMidOfSecondTabOfClassRecentRecord);
            tabUnderLineList.add(rightOfSecondTabOfClassRecentRecord);

            final List<RecyclerView> tabRecyclerView = new ArrayList<>();
            tabRecyclerView.add(firstTabRecyclerView);
            tabRecyclerView.add(secondTabRecyclerView);
            tabRecyclerView.add(thirdTabRecyclerView);
            tabRecyclerView.add(fourthTabRecyclerView);
            initViewOnClickListener(tabTextList, tabUnderLineList, tabRecyclerView);
        }

        private void initViewOnClickListener(List<TextView> tabTextList, List<View> tabUnderLineList, List<RecyclerView> tabRecyclerView){

            initFirstTabViewOnClickListener();
            initSecondTabViewOnClickListener(tabTextList, tabUnderLineList, tabRecyclerView);
        }

        private void initFirstTabViewOnClickListener(){

            leftOfFirstTabOfClassRecentRecord.bringToFront();
            rightOfFirstTabOfClassRecentRecord.bringToFront();

            tabLeftView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!selectLeftOfFirstTab){
                        tabLeftText.setTextColor(parentFragment.getResources().getColor(R.color.colorThemeTitle));
                        tabRightText.setTextColor(parentFragment.getResources().getColor(R.color.colorTextColorHint));

                        leftOfFirstTabOfClassRecentRecord.setVisibility(View.VISIBLE);
                        rightOfFirstTabOfClassRecentRecord.setVisibility(View.INVISIBLE);

                        recentClassAttendanceLineChart.setVisibility(View.VISIBLE);
                        recentClassStateLineChart.setVisibility(View.INVISIBLE);

                        selectLeftOfFirstTab = true;
                    }
                }
            });

            tabRightView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (selectLeftOfFirstTab){
                        tabRightText.setTextColor(parentFragment.getResources().getColor(R.color.colorThemeTitle));
                        tabLeftText.setTextColor(parentFragment.getResources().getColor(R.color.colorTextColorHint));

                        rightOfFirstTabOfClassRecentRecord.setVisibility(View.VISIBLE);
                        leftOfFirstTabOfClassRecentRecord.setVisibility(View.INVISIBLE);

                        recentClassStateLineChart.setVisibility(View.VISIBLE);
                        recentClassStateLineChart.setVisibility(View.INVISIBLE);

                        selectLeftOfFirstTab = false;
                    }
                }
            });
        }

        private void initSecondTabViewOnClickListener(final List<TextView> tabTextList, final List<View> tabUnderLineList, final List<RecyclerView> tabRecyclerView){

            leftOfSecondTabOfClassRecentRecord.bringToFront();
            leftMidOfSecondTabOfClassRecentRecord.bringToFront();
            rightMidOfSecondTabOfClassRecentRecord.bringToFront();
            rightOfSecondTabOfClassRecentRecord.bringToFront();

            tabFirstView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeTabTextViewAndRecyclerView(tabTextList, tabUnderLineList, tabRecyclerView, FIRST_PAGE);

                }
            });

            tabSecondView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeTabTextViewAndRecyclerView(tabTextList, tabUnderLineList, tabRecyclerView, SECOND_PAGE);
                }
            });

            tabThirdView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeTabTextViewAndRecyclerView(tabTextList, tabUnderLineList, tabRecyclerView, THIRD_PAGE);
                }
            });

            tabFourthView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeTabTextViewAndRecyclerView(tabTextList, tabUnderLineList, tabRecyclerView, FOURTH_PAGE);
                }
            });
        }

        private void changeTabTextViewAndRecyclerView(List<TextView> tabTextList, List<View> tabUnderLineList, List<RecyclerView> tabRecyclerView,
                                                    int clickedTabPosition){

            tabTitleDetailsText.setVisibility(View.VISIBLE);
            tabPromptText.setVisibility(View.GONE);
            if(currentSelectTab != clickedTabPosition){
                currentSelectTab = clickedTabPosition;
                tabTextList.get(currentSelectTab).setTextColor(parentFragment.getResources().getColor(R.color.colorThemeTitle));
                tabUnderLineList.get(currentSelectTab).setVisibility(View.VISIBLE);
                tabRecyclerView.get(currentSelectTab).setVisibility(View.VISIBLE);
                for(int i = 0; i < tabTextList.size(); i++){
                    if(i != currentSelectTab){
                        tabTextList.get(i).setTextColor(parentFragment.getResources().getColor(R.color.colorTextColorHint));
                        tabUnderLineList.get(i).setVisibility(View.INVISIBLE);
                        tabRecyclerView.get(i).setVisibility(View.INVISIBLE);
                    }
                }
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private void showAttendanceLineChart(ArrayList arrayList) {

            setRecentAttendanceStatistics(arrayList);
            initAttendanceChartView();
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private void showClassStatusLineChart(ArrayList arrayList) {

            setRecentStateClassStatistics(arrayList);
            initStateChartView();
        }

        private void showProblemStudentsList(StudentsWithAttendanceProblemsBean bean) {

            initRecyclerView(firstTabRecyclerView, bean.getLate());
            initRecyclerView(secondTabRecyclerView, bean.getAbsent());
            initRecyclerView(thirdTabRecyclerView, bean.getEarly());
            initRecyclerView(fourthTabRecyclerView, bean.getQingjia());
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private void initAttendanceChartView() {

            LineChartManager attendanceLineChartManager = new LineChartManager(recentClassAttendanceLineChart);
            attendanceLineChartManager.setChartData(attendanceLineData);
            attendanceLineChartManager.setMinOfYAxis(attendanceMinOfYAxis);
            attendanceLineChartManager.setMaxOfYAxis(attendanceMaxOfYAxis);
            setXAxisValueFormatter(attendanceLineChartManager, attendanceXAxisValue);
            setYAxisValueFormatter(attendanceLineChartManager);
            attendanceLineChartManager.initChartView();
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private void initStateChartView() {

            LineChartManager stateLineChartManager = new LineChartManager(recentClassStateLineChart);
            stateLineChartManager.setChartData(stateLineData);
            stateLineChartManager.setMinOfYAxis(stateMinOfYAxis);
            stateLineChartManager.setMaxOfYAxis(stateMaxOfYAxis);
            setXAxisValueFormatter(stateLineChartManager, stateXAxisValue);
            setYAxisValueFormatter(stateLineChartManager);
            stateLineChartManager.initChartView();
        }

        private void setXAxisValueFormatter(LineChartManager lineChartManager, String[] XAxisValue){

            StringAxisValueFormatter xAxisValueFormatter = new StringAxisValueFormatter(XAxisValue);
            lineChartManager.setXAxisValueFormatter(xAxisValueFormatter);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private void setYAxisValueFormatter(LineChartManager lineChartManager){

            PercentageAxisValueFormatter percentageAxisValueFormatter = new PercentageAxisValueFormatter();
            lineChartManager.setYAxisValueFormatter(percentageAxisValueFormatter);
        }

        private void setRecentAttendanceStatistics(ArrayList chartData){

            List<Entry> chartDataList = new ArrayList<>();
            List<ILineDataSet> dataSets = new ArrayList<>();

            attendanceXAxisValue = new String[chartData.size()];
            DateAndPercentageBean bean;

            for(int i = 0; i < chartData.size(); i++){

                bean = (DateAndPercentageBean)chartData.get(i);
                attendanceXAxisValue[i] = bean.getDate();
                chartDataList.add(new Entry(i, bean.getPercent()));
                attendanceMinOfYAxis = Math.min(attendanceMinOfYAxis, bean.getPercent());
                attendanceMaxOfYAxis = Math.max(attendanceMaxOfYAxis, bean.getPercent());
            }

            LineDataSet lineDataSet = new LineDataSet(chartDataList, "recentAttendanceStatistics");
            dataSets.add(lineDataSet);
            attendanceLineData = new LineData(dataSets);
        }

        private void setRecentStateClassStatistics(ArrayList chartData){

            List<Entry> chartDataList = new ArrayList<>();
            List<ILineDataSet> dataSets = new ArrayList<>();

            stateXAxisValue = new String[chartData.size()];
            DateAndPercentageBean bean;

            for(int i = 0; i < chartData.size(); i++){

                bean = (DateAndPercentageBean)chartData.get(i);
                stateXAxisValue[i] = bean.getDate();
                chartDataList.add(new Entry(i, bean.getPercent()));
                stateMinOfYAxis = Math.min(stateMinOfYAxis, bean.getPercent());
                stateMaxOfYAxis= Math.max(stateMaxOfYAxis, bean.getPercent());
            }

            LineDataSet lineDataSet = new LineDataSet(chartDataList, "recentStateClassStatistics");
            dataSets.add(lineDataSet);
            stateLineData = new LineData(dataSets);
        }

        private void initRecyclerView(RecyclerView recyclerView, ArrayList<StudentInformationBean> listData){

            StudentsWithAttendanceProblemsRecyclerViewAdapter recyclerViewAdapter = new StudentsWithAttendanceProblemsRecyclerViewAdapter(listData);
            recyclerView.setLayoutManager(new LinearLayoutManager(parentFragment.getActivity()));
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    }
}
