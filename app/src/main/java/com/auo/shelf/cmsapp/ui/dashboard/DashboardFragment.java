package com.auo.shelf.cmsapp.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.auo.shelf.cmsapp.MainActivity;
import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.DashboardBean;
import com.auo.shelf.cmsapp.databinding.FragmentDashboardBinding;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private final DashboardBean dashboardBean = new DashboardBean();

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

//        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.fragment_dashboard_title);


//        if (MainActivity.accountBean.token == null) {
//            NavHostFragment.findNavController(DashboardFragment.this).navigate(R.id.action_dashboard_to_login);
//        }else{
            // 上方五格
//            Dashboard dashboard = new Dashboard();
//            ApiResponse respPlaybackSummary = Utility.loadJson(getContext(), R.raw.dashboard_playback_summary);
//            if (respPlaybackSummary != null){
//                if (dashboard.fromPlaybackSummaryJson(respPlaybackSummary.payload, dashboardBean)){
//                    initPlaybackSummary();
//                }
//            }
//
            // 左邊PieChart
//            ApiResponse respActivityStatistics = Utility.loadJson(getContext(), R.raw.dashboard_activity_statistics);
//            if (respActivityStatistics != null){
//                if (dashboard.fromActivityStatistics(respActivityStatistics.payload, dashboardBean)){
//                    initPlayerPieChart();
//                }
//            }
//
//            // 右邊PieChart
//            ApiResponse respMediaStatistics = Utility.loadJson(getContext(), R.raw.dashboard_media_statistics);
//            if (respMediaStatistics != null){
//                if (dashboard.fromMediaStatistics(respMediaStatistics.payload, dashboardBean)){
////                    initMediaPieChart();
//                }
//            }
//        }
    }

    private void initPlaybackSummary(){
//        binding.fragmentDashboardInfoDelivered.setText("" + dashboardBean.playbackDelivered);
//        binding.fragmentDashboardInfoWaitingDelivery.setText("" + dashboardBean.playbackWaitingDelivery);
//        binding.fragmentDashboardInfoCurrentPlaying.setText("" + dashboardBean.playbackCurrentPlaying);
//        binding.fragmentDashboardInfoPlayedSeconds.setText("" + dashboardBean.playbackPlayedSeconds);
//        binding.fragmentDashboardInfoMediaStorage.setText("" + dashboardBean.playbackMediaStorage + " MB");
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

        CustomPieChartRenderer renderer = new CustomPieChartRenderer(pieChart, pieChart.getAnimator(), pieChart.getViewPortHandler());
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
    }
//
//    private void initMediaPieChart(){
//        ArrayList<PieEntry> entry = new ArrayList<>();
//        entry.add(new PieEntry(dashboardBean.mediaImageCount, "Image"));
//        entry.add(new PieEntry(dashboardBean.mediaVideoCount, "Video"));
//
//        PieDataSet pieDataSet = new PieDataSet(entry, "");
//
//        ArrayList<Integer> colors = new ArrayList<>();
//        colors.add(Color.parseColor("#6ef0d0"));
//        colors.add(Color.parseColor("#ffefc5"));
//
//        pieDataSet.setColors(colors);
//        pieDataSet.setValueTextSize(10);
//        pieDataSet.setValueFormatter(new ValueFormatter(){
//            @Override
//            public String getFormattedValue(float value) {
//                return String.valueOf((int) value);
//            }
//        });
//
//        PieData pieData = new PieData(pieDataSet);
//        pieData.setDataSet(pieDataSet);
//        binding.fragmentDashboardMediaStatistics.setData(pieData);
//
//        Description description = new Description();
//        description.setText("");
//        binding.fragmentDashboardMediaStatistics.setDescription(description);
//        binding.fragmentDashboardMediaStatistics.setCenterText("Media\nStatistics");
//        binding.fragmentDashboardMediaStatistics.setEntryLabelColor(Color.BLACK);
//        binding.fragmentDashboardMediaStatistics.notifyDataSetChanged();
//        binding.fragmentDashboardMediaStatistics.invalidate();
//    }

//    private void initPieChart(){
//        ArrayList<PieEntry> entrie1 = new ArrayList<>();
//        entrie1.add(new PieEntry(20, "AAA"));
//        entrie1.add(new PieEntry(20, "BBB"));
//        PieDataSet pieDataSet1 = new PieDataSet(entrie1, "");
//
//        ArrayList<Integer> colors1 = new ArrayList<>();
//        colors1.add(Color.parseColor("#304567"));
//        colors1.add(Color.parseColor("#309967"));
//
//        pieDataSet1.setColors(colors1);
//
//        PieData pieData1 = new PieData(pieDataSet1);
//        pieData1.setDataSet(pieDataSet1);
//        binding.fragmentDashboardPiechart1.setData(pieData1);
//        Description desp1 = new Description();
//        desp1.setText("");
//        binding.fragmentDashboardPiechart1.setDescription(desp1);
//        binding.fragmentDashboardPiechart1.setCenterText("Chart1");
//
//        ArrayList<PieEntry> entrie2 = new ArrayList<>();
//        entrie2.add(new PieEntry(20, "CCC"));
//        entrie2.add(new PieEntry(20, "DDD"));
//        PieDataSet pieDataSet2 = new PieDataSet(entrie2, "");
//
//        ArrayList<Integer> colors2 = new ArrayList<>();
//        colors2.add(Color.parseColor("#304567"));
//        colors2.add(Color.parseColor("#309967"));
//
//        pieDataSet2.setColors(colors2);
//
//        PieData pieData2 = new PieData(pieDataSet2);
//        pieData2.setDataSet(pieDataSet2);
//        binding.fragmentDashboardPiechart2.setData(pieData2);
//        Description desp2 = new Description();
//        desp2.setText("");
//        binding.fragmentDashboardPiechart2.setDescription(desp2);
//        binding.fragmentDashboardPiechart2.setCenterText("Chart2");
//
//        DataTable dataTable = binding.dataTable;
//        DataTableHeader header = new DataTableHeader.Builder()
//                .item("AAA", 5)
//                .item("BBB", 3)
//                .item("CCC", 7)
//                .item("DDD", 3)
//                .item("EEE", 2)
//                .item("FFF", 1)
//                .item("GGG", 5)
//                .build();
//        ArrayList<DataTableRow> rows = new ArrayList<>();
//        Random r = new Random();
//        for(int i=0;i<20;i++) {
//            int random = r.nextInt(i + 1);
//            int randomDiscount = r.nextInt(20);
//            DataTableRow row = new DataTableRow.Builder()
//                    .value("Product #" + i)
//                    .value(String.valueOf(random))
//                    .value(String.valueOf(random * 1000).concat("$"))
//                    .value(String.valueOf(randomDiscount).concat("%"))
//                    .value(String.valueOf(randomDiscount).concat("%"))
//                    .value(String.valueOf(randomDiscount).concat("%"))
//                    .value(String.valueOf(randomDiscount).concat("%"))
//                    .build();
//            rows.add(row);
//        }
//
//        dataTable.setHeader(header);
//        dataTable.setRows(rows);
//
//        if (getActivity() != null)
//            dataTable.inflate(getActivity());
//
//    }
}