package com.auo.shelf.cmsapp.ui.device;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.DeviceBean;
import com.auo.shelf.cmsapp.bean.DeviceLabelBean;
import com.auo.shelf.cmsapp.databinding.FragmentDeviceManagerBinding;
import com.auo.shelf.cmsapp.ui.common.DropDownEditText;
import com.auo.shelf.cmsapp.ui.device.adapter.DeviceAdapter;
import com.auo.shelf.cmsapp.ui.device.adapter.DeviceItemLabelAdapter;
import com.auo.shelf.cmsapp.ui.device.adapter.DeviceSearchAdapter;
import com.auo.shelf.cmsapp.utility.Utility;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeviceManagementFragment extends Fragment {

    private FragmentDeviceManagerBinding binding;
    private DeviceViewModel mDeviceViewModel;
    private DeviceAdapter mAdapter;
    private ArrayList<DeviceBean> mList = new ArrayList<>();
    private PopupWindow mPopupWindow;
    private TextView menuItemSelect, menuItemSelectAllUpdatable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDeviceManagerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fragmentDeviceLayout.setOnTouchListener(onTouchListener);

        mDeviceViewModel = new ViewModelProvider(getActivity()).get(DeviceViewModel.class);
        mDeviceViewModel.createLabelAdapter(getContext());

        binding.fragmentDeviceSearchInput.isDropDown(true);
        binding.fragmentDeviceSearchInput.setRightIconRes(R.drawable.icon_search_white);
        binding.fragmentDeviceSearchInput.setFocusedRightIconRes(R.drawable.icon_close_white);

        binding.fragmentDeviceBottomMenu.fragmentDeviceManagementMenuIconDevice.setBackgroundResource(R.drawable.bottom_menu_selected);
        binding.fragmentDeviceBottomMenu.fragmentDeviceManagementMenuIconEditLabel.setBackgroundColor(0x00000000);
        binding.fragmentDeviceBottomMenu.fragmentDeviceManagementMenuEditLabel.setOnClickListener(editLabelClick);

        mAdapter = new DeviceAdapter();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binding.fragmentDeviceList.setLayoutManager(layoutManager);
        binding.fragmentDeviceList.setAdapter(mAdapter);

        binding.fragmentDeviceSearchInput.setAdapter(mDeviceViewModel.getLabelAdapter());
        binding.fragmentDeviceSearchInput.setOnLabelSelectedListener(labelSelectedListener);

        binding.fragmentDeviceOptionMenuImage.setImageRes(
                R.drawable.icon_menu_white,
                R.drawable.icon_menu_white
        );

        binding.fragmentDeviceOptionMenu.setOnClickListener(menuClick);

        loadDeviceList();
        loadLabelList();
        initPopupMenu();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshList();
        refreshLabelList();
    }

    private void loadDeviceList(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {

            mList.clear();

            //背景處理邏輯程式區塊
            mList.add(new DeviceBean(getContext(),"Left Shelf 1", "16:9 | 3840x160"));
            mList.add(new DeviceBean(getContext(),"Left Shelf 2", "16:9 | 3840x160"));
            mList.add(new DeviceBean(getContext(),"Left Shelf 3", "16:9 | 3840x160"));
            mList.add(new DeviceBean(getContext(),"Right Shelf 1", "16:9 | 1920x160"));
            mList.add(new DeviceBean(getContext(),"Right Shelf 2", "16:9 | 1920x160"));
            mList.add(new DeviceBean(getContext(),"Right Shelf 3", "16:9 | 1920x160"));

            mAdapter.setList(mList);

//            ArrayList<DeviceBean> oldList = mAdapter.getList();
//            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new MyCallback(oldList, mList));
//            mAdapter.setList(mList);
//            result.dispatchUpdatesTo(mAdapter);

            handler.post(() -> {
                //處理 UI 邏輯程式區塊
                refreshList();
            });
        });
    }

    private void loadLabelList(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {

            ArrayList<DeviceLabelBean> labelList = new ArrayList<>();
            labelList.add(new DeviceLabelBean(getContext(), DeviceLabelBean.TYPE_ONLINE));
            labelList.add(new DeviceLabelBean(getContext(), DeviceLabelBean.TYPE_OFFLINE));
            labelList.add(new DeviceLabelBean(getContext(), DeviceLabelBean.TYPE_UPDATABLE));

            //背景處理邏輯程式區塊
            labelList.add(new DeviceLabelBean("Hsinchu"));
            labelList.add(new DeviceLabelBean("ATC/L3B"));
            labelList.add(new DeviceLabelBean("Staff Canteen Shop"));
            labelList.add(new DeviceLabelBean("Starbucks"));
            labelList.add(new DeviceLabelBean("Lobby"));

            mDeviceViewModel.setLabelList(labelList);

//            ArrayList<DeviceBean> oldList = mAdapter.getList();
//            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new MyCallback(oldList, mList));
//            mAdapter.setList(mList);
//            result.dispatchUpdatesTo(mAdapter);

            handler.post(() -> {
                //處理 UI 邏輯程式區塊
                mDeviceViewModel.getLabelAdapter().notifyDataSetChanged();
            });
        });
    }

    private void refreshList(){
        if (mList.size() > 0) {
            mAdapter.notifyDataSetChanged();
            binding.fragmentDeviceList.setVisibility(View.VISIBLE);
            binding.fragmentDeviceEmptyList.setVisibility(View.GONE);
        }else{
            binding.fragmentDeviceList.setVisibility(View.GONE);
            binding.fragmentDeviceEmptyList.setVisibility(View.VISIBLE);
        }
    }

    private void refreshLabelList(){
        mDeviceViewModel.getLabelAdapter().notifyDataSetChanged();
    }

    private void initPopupMenu(){
        View view = LayoutInflater.from(getContext()) .inflate(R.layout.popup_menu_device_select, null);
        menuItemSelect = view.findViewById(R.id.popup_menu_device_select_item_select);
        menuItemSelectAllUpdatable = view.findViewById(R.id.popup_menu_device_select_item_select_all_updatable);
        mPopupWindow = new PopupWindow(view);
        mPopupWindow.setWidth(Utility.convertDpToPixel(getContext(), 184));
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setElevation(20);
        mPopupWindow.setOnDismissListener(popupOnDismiss);

        menuItemSelect.setOnClickListener(selectClick);
        menuItemSelectAllUpdatable.setOnClickListener(selectAllUpdatableClick);
    }

    private View.OnClickListener editLabelClick = v -> {
        NavHostFragment.findNavController(DeviceManagementFragment.this).navigate(R.id.action_device_management_to_edit_label);
    };

    private LabelView.OnCloseListener onCloseListener = () -> {
        // reset list
    };

    private DropDownEditText.OnLabelSelectedListener labelSelectedListener = new DropDownEditText.OnLabelSelectedListener() {
        @Override
        public void onLabelSelected(DeviceLabelBean deviceLabelBean) {
            binding.fragmentDeviceSearchLabel.setDeviceLabelBean(deviceLabelBean);
            binding.fragmentDeviceSearchLabel.setVisibility(View.VISIBLE);
            binding.fragmentDeviceSearchLabel.setOnCloseListener(onCloseListener);
        }
    };

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            if (!(view instanceof DropDownEditText)){
                binding.fragmentDeviceSearchInput.clearFocus();
                Utility.hideKeyboard(getContext(), binding.fragmentDeviceSearchInput);
            }
            return false;
        }
    };

    private View.OnClickListener menuClick = view -> {
        if (!binding.fragmentDeviceOptionMenuImage.getFocused()){
            mPopupWindow.showAsDropDown(binding.fragmentDeviceOptionMenu);
        }else{
            mPopupWindow.dismiss();
        }
        binding.fragmentDeviceOptionMenuImage.setFocused(!binding.fragmentDeviceOptionMenuImage.getFocused());
    };

    private PopupWindow.OnDismissListener popupOnDismiss = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            binding.fragmentDeviceOptionMenuImage.setFocused(false);
        }
    };

    private View.OnClickListener selectClick = view -> {
        mPopupWindow.dismiss();
    };

    private View.OnClickListener selectAllUpdatableClick = view -> {
        mPopupWindow.dismiss();
    };
}