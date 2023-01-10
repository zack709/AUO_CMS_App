package com.auo.shelf.cmsapp.ui.common;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.auo.shelf.cmsapp.MainActivity;
import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.SlideMenuBean;
import com.auo.shelf.cmsapp.databinding.NavMenuBinding;

import java.util.ArrayList;

public class SlideMenu {

    private MainActivity mainActivity;
    private NavMenuBinding binding;
    private ArrayList<SlideMenuBean> menuList = new ArrayList<>();
    private NavController navController;

    public SlideMenu(MainActivity activity, NavMenuBinding binding){
        mainActivity = activity;
        this.binding = binding;
        navController = Navigation.findNavController(mainActivity, R.id.nav_host_fragment_content_main);
        init();
    }

    private void init(){
        SlideMenuBean dashboard = new SlideMenuBean();
        dashboard.item = binding.navMenuDashboard;
        dashboard.navResId = R.id.nav_dashboard;
        menuList.add(dashboard);

        SlideMenuBean airProgram = new SlideMenuBean();
        airProgram.item = binding.navMenuAirProgram;
//        airProgram.navResId = R.id.nav;
        menuList.add(airProgram);

        SlideMenuBean program = new SlideMenuBean();
        program.item = binding.navMenuProgramManagement;
//        program.navResId = R.id.nav_dashboard;
        menuList.add(program);

        SlideMenuBean createContent = new SlideMenuBean();
        createContent.item = binding.navMenuCreateContent;
//        createContent.navResId = R.id.nav_dashboard;
        menuList.add(createContent);

        SlideMenuBean content = new SlideMenuBean();
        content.item = binding.navMenuContentManagement;
//        content.navResId = R.id.nav_dashboard;
        menuList.add(content);

        SlideMenuBean device = new SlideMenuBean();
        device.item = binding.navMenuDeviceManagement;
        device.navResId = R.id.nav_device_management;
        menuList.add(device);

        for (int i=0; i<menuList.size(); i++){
            menuList.get(i).item.setOnClickListener(itemClick);
        }
    }

    private View.OnClickListener itemClick = view -> {
        SlideMenuBean bean = null;
        for (int i=0; i<menuList.size(); i++){
            menuList.get(i).item.setBackgroundColor(0x00000000);
            if (menuList.get(i).item.getId() == view.getId()){
                bean = menuList.get(i);
            }
        }

        bean.item.setBackgroundResource(R.drawable.border_nav_menu);
        if (bean.navResId != 0) navController.navigate(bean.navResId);
        mainActivity.closeSlideMenu();
    };
}
