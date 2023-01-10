package com.auo.shelf.cmsapp.ui.device;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.DeviceLabelBean;
import com.auo.shelf.cmsapp.databinding.FragmentDeviceEditLabelBinding;
import com.auo.shelf.cmsapp.ui.device.adapter.DeviceLabelAdapter;
import com.auo.shelf.cmsapp.utility.Utility;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeviceLabelEditFragment extends Fragment {

    private FragmentDeviceEditLabelBinding binding;
    private ArrayList<DeviceLabelBean> mList = new ArrayList<>();
    private DeviceLabelAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDeviceEditLabelBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fragmentDeviceBottomMenu.fragmentDeviceManagementMenuIconEditLabel.setBackgroundResource(R.drawable.bottom_menu_selected);
        binding.fragmentDeviceBottomMenu.fragmentDeviceManagementMenuIconDevice.setBackgroundColor(0x00000000);
        binding.fragmentDeviceBottomMenu.fragmentDeviceManagementMenuDevice.setOnClickListener(deviceManagementClick);

        binding.fragmentDeviceEditLabelNewLabelDoneView.setImageRes(
                R.mipmap.icon_done_gray,
                R.drawable.icon_done_white
        );
        binding.fragmentDeviceEditLabelNewLabel.setFocusedDoneView(binding.fragmentDeviceEditLabelNewLabelDoneView);
        binding.fragmentDeviceEditLabelNewLabelDoneButton.setOnClickListener(addLabelClick);

        mAdapter = new DeviceLabelAdapter(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        Drawable divider = ContextCompat.getDrawable(getContext(), R.drawable.recycler_view_divider_gray_400);
        dividerItemDecoration.setDrawable(divider);
        binding.fragmentDeviceEditLabelList.addItemDecoration(dividerItemDecoration);
        loadList();
        binding.fragmentDeviceEditLabelList.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void syncList(ArrayList<DeviceLabelBean> list){
        mList.clear();
        mList.addAll(list);
    }

    private View.OnClickListener deviceManagementClick = v -> {
        NavHostFragment.findNavController(DeviceLabelEditFragment.this).navigate(R.id.action_device_edit_label_to_management);
    };

    private View.OnClickListener addLabelClick = v -> {
        String newLabel = binding.fragmentDeviceEditLabelNewLabel.getText().toString();
        if (newLabel.trim().length() > 0) {
            // 新增Label
            mList.add(new DeviceLabelBean(newLabel));
            mAdapter.setList(mList);
            mAdapter.notifyItemInserted(mList.size() - 1);
            binding.fragmentDeviceEditLabelNewLabel.setText("");
        }

        Utility.hideKeyboard(getContext(), binding.fragmentDeviceEditLabelNewLabel);
        binding.fragmentDeviceEditLabelNewLabel.clearFocus();
    };

    private void loadList(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {

            mList.clear();

            //背景處理邏輯程式區塊
            mList.add(new DeviceLabelBean("Hsinchu"));
            mList.add(new DeviceLabelBean("ATC/L3B"));
            mList.add(new DeviceLabelBean("Staff Canteen Shop"));
            mList.add(new DeviceLabelBean("Starbucks"));
            mList.add(new DeviceLabelBean("Lobby"));

            mAdapter.setList(mList);

//            ArrayList<DeviceBean> oldList = mAdapter.getList();
//            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new MyCallback(oldList, mList));
//            mAdapter.setList(mList);
//            result.dispatchUpdatesTo(mAdapter);

            handler.post(() -> {
                //處理 UI 邏輯程式區塊
                mAdapter.notifyDataSetChanged();
            });
        });
    }
}