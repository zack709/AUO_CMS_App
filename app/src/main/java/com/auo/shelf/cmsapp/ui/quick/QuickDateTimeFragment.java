package com.auo.shelf.cmsapp.ui.quick;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.auo.shelf.cmsapp.MainActivity;
import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.PlayerSelectBean;
import com.auo.shelf.cmsapp.databinding.FragmentQuickDatetimeBinding;

import java.util.Calendar;


public class QuickDateTimeFragment extends Fragment {

    private FragmentQuickDatetimeBinding binding;
    private QuickPublishViewModel quickPublishViewModel;
    private QuickPublishSetting quickPublishSetting;
    private String startDateTime, endDateTime;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        quickPublishViewModel = new ViewModelProvider(getActivity()).get(QuickPublishViewModel.class);
        quickPublishSetting = quickPublishViewModel.getQuickPublishSetting();

        binding = FragmentQuickDatetimeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fragmentQuickDatetimePrev.setOnClickListener(prevClick);
        binding.fragmentQuickDatetimeNext.setOnClickListener(nextClick);
        binding.fragmentQuickDatetimeStartPicker.setOnClickListener(startClick);
        binding.fragmentQuickDatetimeEndPicker.setOnClickListener(endClick);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private View.OnClickListener prevClick = v -> {
        NavHostFragment.findNavController(QuickDateTimeFragment.this).navigate(R.id.action_quick_datetime_to_device_select);
    };

    private View.OnClickListener nextClick = v -> {
        quickPublishSetting.setStartTime(startDateTime);
        quickPublishSetting.setEndTime(endDateTime);

        quickPublishViewModel.setQuickPublihSetting(quickPublishSetting);

        NavHostFragment.findNavController(QuickDateTimeFragment.this).navigate(R.id.action_quick_datetime_to_preview);
    };

    private View.OnClickListener startClick = v ->{
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);      //取得現在的日期年月日
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hourOfDay = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        new DatePickerDialog(v.getContext(), (dateVIew, syear, smonth, sday) -> {
            String monStr = (smonth < 10) ? "0" + smonth : "" + smonth;
            String dayStr = (sday < 10) ? "0" + sday : "" + sday;
            startDateTime = syear + "/" + monStr + "/" + dayStr;
            new TimePickerDialog(v.getContext(), (timeView, shour, sminute) -> {
                String hourStr = (shour < 10) ? "0" + shour : "" + shour;
                String minStr = (sminute < 10) ? "0" + sminute : "" + sminute;
                startDateTime += " " + hourStr + ":" + minStr;
                binding.fragmentQuickDatetimeStartText.setText(startDateTime);

                binding.fragmentQuickDatetimeNext.setEnabled(checkTime());
            }, hourOfDay, minute,true).show();
        }, year, month, day).show();

    };

    private View.OnClickListener endClick = v ->{
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);      //取得現在的日期年月日
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hourOfDay = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        new DatePickerDialog(v.getContext(), (dateVIew, syear, smonth, sday) -> {
            String monStr = (smonth < 10) ? "0" + smonth : "" + smonth;
            String dayStr = (sday < 10) ? "0" + sday : "" + sday;
            endDateTime = syear + "/" + monStr + "/" + dayStr;
            new TimePickerDialog(v.getContext(), (timeView, shour, sminute) -> {
                String hourStr = (shour < 10) ? "0" + shour : "" + shour;
                String minStr = (sminute < 10) ? "0" + sminute : "" + sminute;
                endDateTime += " " + hourStr + ":" + minStr;
                binding.fragmentQuickDatetimeEndText.setText(endDateTime);

                binding.fragmentQuickDatetimeNext.setEnabled(checkTime());
            }, hourOfDay, minute,true).show();
        }, year, month, day).show();
    };

    private boolean checkTime(){
        if (binding.fragmentQuickDatetimeStartText.getText().length() > 0
            && binding.fragmentQuickDatetimeEndText.getText().length() > 0){



            return true;
        }
        return false;
    }
}