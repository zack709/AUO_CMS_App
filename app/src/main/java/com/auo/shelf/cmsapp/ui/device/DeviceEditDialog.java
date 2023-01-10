package com.auo.shelf.cmsapp.ui.device;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.DeviceBean;
import com.auo.shelf.cmsapp.bean.DeviceLabelBean;
import com.auo.shelf.cmsapp.ui.common.AUOEditText;
import com.auo.shelf.cmsapp.ui.device.adapter.DeviceItemLabelAdapter;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;


public class DeviceEditDialog extends AppCompatDialog {

    private Context mContext;
    private DeviceBean mDeviceBean;
    private AUOEditText etName;
    private TextView information, firmware;
    private RecyclerView statusList, labelList;
    private DeviceItemLabelAdapter statusAdapter, labelAdapter;
    private Button okBtn, cancelBtn;

    public DeviceEditDialog(@NonNull Context context, DeviceBean bean) {
        super(context);
        init(context, bean);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_device_edit);

        etName = findViewById(R.id.dialog_device_edit_device_name_edittext);
        etName.setText(mDeviceBean.name);
        statusList = findViewById(R.id.dialog_device_edit_device_status_list);
        labelList = findViewById(R.id.dialog_device_edit_device_label_list);
        information = findViewById(R.id.dialog_device_edit_device_information);
        firmware = findViewById(R.id.dialog_device_edit_device_firmware);
        okBtn = findViewById(R.id.dialog_device_edit_btn_ok);
        cancelBtn = findViewById(R.id.dialog_device_edit_btn_cancel);

        FlexboxLayoutManager statusLayoutManager = new FlexboxLayoutManager(mContext);
        statusLayoutManager.setFlexDirection(FlexDirection.ROW);
        statusLayoutManager.setAlignItems(AlignItems.FLEX_START);
        statusList.setLayoutManager(statusLayoutManager);
        statusList.setAdapter(statusAdapter);

        FlexboxLayoutManager labelLayoutManager = new FlexboxLayoutManager(mContext);
        labelLayoutManager.setFlexDirection(FlexDirection.ROW);
        labelLayoutManager.setAlignItems(AlignItems.FLEX_START);
        labelList.setLayoutManager(labelLayoutManager);
        labelList.setAdapter(labelAdapter);

        information.setText(mDeviceBean.resolution);
        okBtn.setOnClickListener(okClick);
        cancelBtn.setOnClickListener(cancelClick);
    }

    @Override
    public void show() {
        super.show();
        getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }

    private void init(Context context, DeviceBean bean){
        mContext = context;
        mDeviceBean = bean;
        setCanceledOnTouchOutside(false);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        statusAdapter = new DeviceItemLabelAdapter();
        for (int i=0; i<mDeviceBean.labelList.size(); i++){
            DeviceLabelBean labelBean = mDeviceBean.labelList.get(i);
            if (labelBean.type != DeviceLabelBean.TYPE_CUSTOM){
                statusAdapter.addItem(labelBean);
            }
        }

        labelAdapter = new DeviceItemLabelAdapter();
        for (int i=0; i<mDeviceBean.labelList.size(); i++){
            DeviceLabelBean labelBean = mDeviceBean.labelList.get(i);
            if (labelBean.type == DeviceLabelBean.TYPE_CUSTOM){
                labelAdapter.addItem(labelBean);
            }
        }
    }

    private View.OnClickListener okClick = v -> {
        dismiss();
    };

    private View.OnClickListener cancelClick = v -> {
        dismiss();
    };
}
