package com.auo.shelf.cmsapp.ui.quick;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.auo.shelf.cmsapp.MainActivity;
import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.PlayerSelectBean;
import com.auo.shelf.cmsapp.databinding.FragmentQuickPreviewBinding;

import java.util.ArrayList;
import java.util.Calendar;


public class QuickPreviewFragment extends Fragment {

    private FragmentQuickPreviewBinding binding;
    private QuickPublishViewModel quickPublishViewModel;
    private QuickPublishSetting quickPublishSetting;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        quickPublishViewModel = new ViewModelProvider(getActivity()).get(QuickPublishViewModel.class);
        quickPublishSetting = quickPublishViewModel.getQuickPublishSetting();

        binding = FragmentQuickPreviewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fragmentQuickPreviewTemplateName.setText(quickPublishSetting.getTemplate().name);
        ArrayList<PlayerSelectBean> playerList = quickPublishSetting.getPlayerList();
        String players = "";
        for (int i=0; i<playerList.size(); i++){
            if (i > 0) players += "\n";
            players += playerList.get(i).name;
        }
        binding.fragmentQuickPreviewPlayerList.setText(players);
        binding.fragmentQuickPreviewStartTime.setText(quickPublishSetting.getStartTime());
        binding.fragmentQuickPreviewEndTime.setText(quickPublishSetting.getEndTime());

        binding.fragmentQuickPreviewPrev.setOnClickListener(prevClick);
//        binding.fragmentQuickDatetimePrev.setOnClickListener(prevClick);
//        binding.fragmentQuickDatetimeStartPicker.setOnClickListener(startClick);
//        binding.fragmentQuickDatetimeEndPicker.setOnClickListener(endClick);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private View.OnClickListener prevClick = v -> {
        NavHostFragment.findNavController(QuickPreviewFragment.this).navigate(R.id.action_quick_preview_to_datetime);
    };

    private View.OnClickListener nextClick = v -> {
//        NavHostFragment.findNavController(DeviceSelectFragment.this).navigate(R.id.action_quick_layout_select_to_content);
    };


}