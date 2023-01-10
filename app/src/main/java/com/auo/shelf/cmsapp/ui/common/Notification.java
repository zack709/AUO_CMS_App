package com.auo.shelf.cmsapp.ui.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.NotificationBean;
import com.auo.shelf.cmsapp.ui.common.adapter.NotificationAdapter;

import java.util.ArrayList;

public class Notification {

    private Context mContext;
    private CardView rootView;
    private RecyclerView notification;
    private NotificationAdapter mAdapter;
    private ArrayList<NotificationBean> mList = new ArrayList<>();

    public Notification(Context context, CardView cardView){
        mContext = context;
        rootView = cardView;
        notification = rootView.findViewById(R.id.notification_list);

        mList.addAll(testList());
        mAdapter = new NotificationAdapter(mList);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        Drawable divider = ContextCompat.getDrawable(mContext, R.drawable.recycler_view_divider_flexy_gray_100);
        dividerItemDecoration.setDrawable(divider);
        notification.addItemDecoration(dividerItemDecoration);
        notification.setAdapter(mAdapter);
    }

    public void showNotification(){
        rootView.setVisibility(View.VISIBLE);
    }

    public void hideNotification(){
        rootView.setVisibility(View.GONE);
    }


    private ArrayList<NotificationBean> testList(){
        ArrayList<NotificationBean> list = new ArrayList<>();

        NotificationBean bean1 = new NotificationBean(NotificationBean.TYPE_PROGRAM);
        bean1.title = "Program is Publishing";
        bean1.subtitle = "2023/03/08 08:15 AM";

        NotificationBean bean2 = new NotificationBean(NotificationBean.TYPE_PROGRAM);
        bean2.title = "Air Program Failed";
        bean2.subtitle = "2023/03/08 08:05 AM";

        NotificationBean bean3 = new NotificationBean(NotificationBean.TYPE_PROGRAM);
        bean3.title = "Air Program Successfully";
        bean3.subtitle = "2023/03/08 07:50 AM";

        NotificationBean bean4 = new NotificationBean(NotificationBean.TYPE_DEVICE);
        bean4.title = "Update Available";
        bean4.subtitle = "New firmware for device";

        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);

        return list;
    }
}
