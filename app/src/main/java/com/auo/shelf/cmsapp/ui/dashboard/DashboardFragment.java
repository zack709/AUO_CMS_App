package com.auo.shelf.cmsapp.ui.dashboard;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.auo.shelf.cmsapp.MainActivity;
import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.DashboardBean;
import com.auo.shelf.cmsapp.bean.DashboardProgramBean;
import com.auo.shelf.cmsapp.databinding.FragmentDashboardBinding;
import com.auo.shelf.cmsapp.ui.dashboard.adapter.DropAdapter;
import com.auo.shelf.cmsapp.ui.dashboard.adapter.OrgAdapter;
import com.auo.shelf.cmsapp.ui.dashboard.adapter.ProgramAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private final DashboardBean dashboardBean = new DashboardBean();
    private ProgramAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        DashboardViewModel homeViewModel =
//                new ViewModelProvider(this).get(DashboardViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Content
        initContentChart();
        initContentSpinner();

        // Schedule
        initScheduleChart();
        initScheduleSpinner();

        // Device
        initDeviceSpinner();
        initDeviceChart();

        // Program
        initProgramSpinner();
        initProgramList();

    }

    private void initContentChart(){
        PieChart pieChart = binding.cardContent.fragmentDashboardCardContentChart;

        ArrayList<PieEntry> entry = new ArrayList<>();
        entry.add(new PieEntry(30, getString(R.string.fragment_dashboard_card_content_chart_entry_image)));
        entry.add(new PieEntry(20, getString(R.string.fragment_dashboard_card_content_chart_entry_video)));
        entry.add(new PieEntry(35, getString(R.string.fragment_dashboard_card_content_chart_entry_others)));
        entry.add(new PieEntry(15, getString(R.string.fragment_dashboard_card_content_chart_entry_free)));

        PieDataSet pieDataSet = new PieDataSet(entry, "");

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getActivity().getColor(R.color.amber_300));
        colors.add(getActivity().getColor(R.color.purple_300));
        colors.add(getActivity().getColor(R.color.blue_300));
        colors.add(getActivity().getColor(R.color.grey_300));

        pieDataSet.setColors(colors);
        pieDataSet.setDrawValues(false);
//        pieDataSet.setValueTextSize(16);
//        pieDataSet.setValueTextColor(getActivity().getColor(R.color.white));
//        pieDataSet.setValueFormatter(new ValueFormatter(){
//            @Override
//            public String getFormattedValue(float value) {
//                return String.valueOf((int) value);
//            }
//        });

        PieData pieData = new PieData(pieDataSet);
        pieData.setDataSet(pieDataSet);
        pieChart.setData(pieData);

        Description description = new Description();
        description.setText("");

        pieChart.setDescription(description);
        pieChart.setDrawEntryLabels(false);

        pieChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        pieChart.getLegend().setTextColor(getActivity().getColor(R.color.white));
        pieChart.getLegend().setXEntrySpace(16);
        pieChart.getLegend().setTextSize(14);

        CustomPieChartRenderer renderer = new CustomPieChartRenderer(pieChart, pieChart.getAnimator(), pieChart.getViewPortHandler(), R.mipmap.icon_storage);
        pieChart.setRenderer(renderer);

        pieChart.setRotationEnabled(false);
        pieChart.setTouchEnabled(false);
        pieChart.setHoleRadius(70);
        pieChart.setHoleColor(getActivity().getColor(R.color.flexy_grey_500));
        pieChart.invalidate();
    }

    private void initScheduleChart(){
        LineChart lineChart = binding.cardSchedule.fragmentDashboardCardScheduleChart;

        ArrayList<Entry> scheduleEntry = new ArrayList<>();
        scheduleEntry.add(new Entry(1,250));
        scheduleEntry.add(new Entry(2,400));
        scheduleEntry.add(new Entry(3,380));
        scheduleEntry.add(new Entry(4,180));
        scheduleEntry.add(new Entry(5,70));
        scheduleEntry.add(new Entry(6,40));
        scheduleEntry.add(new Entry(7,60));

        LineDataSet scheduleDataSet = new LineDataSet(scheduleEntry, getString(R.string.fragment_dashboard_card_schedule_chart_entry_schedule));
        scheduleDataSet.setLineWidth(2.5f);
        scheduleDataSet.setCircleRadius(4.5f);
//        scheduleDataSet.setHighLightColor(getContext().getColor(R.color.green_500));
        scheduleDataSet.setColor(getContext().getColor(R.color.green_300));
        scheduleDataSet.setCircleColor(getContext().getColor(R.color.green_300));
        scheduleDataSet.setCircleHoleColor(getContext().getColor(R.color.green_500));
        scheduleDataSet.setDrawValues(false);
        scheduleDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        LineData lineData = new LineData();
        lineData.addDataSet(scheduleDataSet);

        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setTextColor(getContext().getColor(R.color.white));
        xAxis.setDrawGridLines(false);



        lineChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        lineChart.getLegend().setTextColor(getActivity().getColor(R.color.white));
        lineChart.getLegend().setXEntrySpace(8);
        lineChart.getLegend().setTextSize(14);

        lineChart.invalidate();

    }

    private void initDeviceChart(){
        PieChart pieChart = binding.cardDevice.fragmentDashboardCardDeviceChart;

        ArrayList<PieEntry> entry = new ArrayList<>();
        entry.add(new PieEntry(60, getString(R.string.fragment_dashboard_card_device_chart_entry_online)));
        entry.add(new PieEntry(40, getString(R.string.fragment_dashboard_card_device_chart_entry_offline)));

        PieDataSet pieDataSet = new PieDataSet(entry, "");

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getActivity().getColor(R.color.green_300));
        colors.add(getActivity().getColor(R.color.blue_300));

        pieDataSet.setColors(colors);
        pieDataSet.setDrawValues(false);
//        pieDataSet.setValueTextSize(16);
//        pieDataSet.setValueTextColor(getActivity().getColor(R.color.white));
//        pieDataSet.setValueFormatter(new ValueFormatter(){
//            @Override
//            public String getFormattedValue(float value) {
//                return String.valueOf((int) value);
//            }
//        });

        PieData pieData = new PieData(pieDataSet);
        pieData.setDataSet(pieDataSet);
        pieChart.setData(pieData);

        Description description = new Description();
        description.setText("");

        pieChart.setDescription(description);
        pieChart.setDrawEntryLabels(false);

        pieChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        pieChart.getLegend().setTextColor(getActivity().getColor(R.color.white));
        pieChart.getLegend().setXEntrySpace(16);
        pieChart.getLegend().setTextSize(14);

        CustomPieChartRenderer renderer = new CustomPieChartRenderer(pieChart, pieChart.getAnimator(), pieChart.getViewPortHandler(), R.mipmap.icon_computer);
        pieChart.setRenderer(renderer);

        pieChart.setRotationEnabled(false);
        pieChart.setTouchEnabled(false);
        pieChart.setHoleRadius(70);
        pieChart.setHoleColor(getActivity().getColor(R.color.flexy_grey_500));
        pieChart.invalidate();
    }

    private void initContentSpinner(){
        ArrayList<String> list = new ArrayList<>();
        list.add("Hsinchu");
        list.add("ATC/L3B");
        list.add("Staff Canteen Shop");
        list.add("Starbucks");
        list.add("Lobby");
        OrgAdapter adapter = new OrgAdapter(getContext(), list);
        binding.cardContent.fragmentDashboardCardContentSpinner.setAdapter(adapter);
        binding.cardContent.fragmentDashboardCardContentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectedIndex(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initScheduleSpinner(){
        ArrayList<String> listData = new ArrayList<>();
        listData.add("By Times");
        listData.add("By Length");
        listData.add("By Data Traffic");
        DropAdapter adapterData = new DropAdapter(getContext(), listData);
        binding.cardSchedule.fragmentDashboardCardScheduleSpinnerDataType.setAdapter(adapterData);
        binding.cardSchedule.fragmentDashboardCardScheduleSpinnerDataType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapterData.setSelectedIndex(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> listDate = new ArrayList<>();
        listDate.add("By Day");
        listDate.add("By Week");
        listDate.add("By Month");
        DropAdapter adapterDate = new DropAdapter(getContext(), listDate);
        binding.cardSchedule.fragmentDashboardCardScheduleSpinnerDateType.setAdapter(adapterDate);
        binding.cardSchedule.fragmentDashboardCardScheduleSpinnerDateType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapterDate.setSelectedIndex(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initDeviceSpinner(){
        ArrayList<String> list = new ArrayList<>();
        list.add("All Version");
        list.add("0.9");
        list.add("0.8");
        list.add("0.7");
        list.add("0.6");
        DropAdapter adapter = new DropAdapter(getContext(), list);
        binding.cardDevice.fragmentDashboardCardDeviceSpinner.setAdapter(adapter);
        binding.cardDevice.fragmentDashboardCardDeviceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectedIndex(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initProgramSpinner(){
        ArrayList<String> list = new ArrayList<>();
        list.add("Hsinchu");
        list.add("ATC/L3B");
        list.add("Staff Canteen Shop");
        list.add("Starbucks");
        list.add("Lobby");
        OrgAdapter adapter = new OrgAdapter(getContext(), list);
        binding.cardProgram.fragmentDashboardCardProgramSpinner.setAdapter(adapter);
        binding.cardProgram.fragmentDashboardCardProgramSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectedIndex(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initProgramList(){

        ArrayList<DashboardProgramBean> list = new ArrayList<>();
        DashboardProgramBean bean = new DashboardProgramBean();
        bean.imageUri = "https://live.staticflickr.com/7588/27112871283_3f0a337c5f_z.jpg";
        bean.name = "1. Program 2023/02/28";
        bean.runTime = "300 Hours";
        bean.views = "1200 Views";
        bean.amongDevice = "Among 6 Devices";
        list.add(bean);

        mAdapter = new ProgramAdapter();
        mAdapter.setList(list);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        Drawable divider = ContextCompat.getDrawable(getContext(), R.drawable.recycler_view_divider_flexy_gray_100);
        dividerItemDecoration.setDrawable(divider);
        binding.cardProgram.fragmentDashboardCardProgramList.addItemDecoration(dividerItemDecoration);
        binding.cardProgram.fragmentDashboardCardProgramList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}