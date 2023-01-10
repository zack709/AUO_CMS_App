package com.auo.shelf.cmsapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.auo.shelf.cmsapp.bean.AccountBean;
import com.auo.shelf.cmsapp.ui.common.DropDownEditText;
import com.auo.shelf.cmsapp.ui.common.Notification;
import com.auo.shelf.cmsapp.ui.common.SlideMenu;
import com.auo.shelf.cmsapp.utility.Utility;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.auo.shelf.cmsapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String[] CAMERA_PERMISSION = new String[]{Manifest.permission.CAMERA};
    private static final int CAMERA_REQUEST_CODE = 10;

    public static AccountBean accountBean = new AccountBean();
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private TextView loginAccountView;
    private Notification mNotification;
    private DrawerLayout mDrawerLayout;
    private SlideMenu mSlideMenu;
    private boolean isShowNotification = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);

        binding.appBarMain.actionBarMenu.setOnClickListener(menuClick);

//        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        View headerView = navigationView.getHeaderView(0);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_dashboard,
                R.id.nav_shelf_manager,
                R.id.nav_quick_layout_select,
                R.id.nav_login,
                R.id.nav_device_management,
                R.id.nav_device_edit_label
        ).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Request camera permissions
        if (!allPermissionsGranted()) {
            requestPermissions(CAMERA_PERMISSION, CAMERA_REQUEST_CODE);
        }

        // Notification
        initNotification();

        // Slide Menu
        initSlideMenu();

    }

    private boolean allPermissionsGranted(){
        return checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void setLoginAccount(){
        if (MainActivity.accountBean.account != null){
            loginAccountView.setText(MainActivity.accountBean.account);
        }
    }

    private View.OnClickListener menuClick = view -> {
        binding.drawerLayout.openDrawer(Gravity.LEFT);
    };

    private void initNotification(){
        mNotification = new Notification(this, binding.appBarMain.contentMain.notification.notificationView);
        binding.appBarMain.actionBarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShowNotification) {
                    mNotification.showNotification();
                }else{
                    mNotification.hideNotification();
                }
                isShowNotification = !isShowNotification;
            }
        });
    }

    private void initSlideMenu(){
        mSlideMenu = new SlideMenu(this, binding.navMenu);
    }

    public void closeSlideMenu(){
        binding.drawerLayout.closeDrawers();
    }
}