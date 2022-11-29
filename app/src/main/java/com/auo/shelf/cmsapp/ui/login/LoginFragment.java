package com.auo.shelf.cmsapp.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


import com.auo.shelf.cmsapp.config.MainConfig;
import com.auo.shelf.cmsapp.MainActivity;
import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.databinding.FragmentLoginBinding;
import com.auo.shelf.cmsapp.json.ApiResponse;
import com.auo.shelf.cmsapp.utility.HttpConnector;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

public class LoginFragment extends Fragment {

    private static final String LOGIN_PATH = "/User/Token";

    private FragmentLoginBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        binding.fragmentLoginBtnLogin.setOnClickListener(loginBtnClick);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getActivity() != null){
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null){
                actionBar.hide();
            }
        }

//        SharedPreferences pref = getActivity().getSharedPreferences("account", Context.MODE_PRIVATE);
//        binding.fragmentLoginEtAccount.setText(pref.getString("account", ""));
//        binding.fragmentLoginEtPassword.setText(pref.getString("password", ""));
//        if (pref.getBoolean("remember", false)){
//            Runnable r = () -> binding.fragmentLoginSwitchRemember.setChecked(true);
//            new Handler().post(r);
//
//        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private final View.OnClickListener loginBtnClick = v -> {
//        binding.fragmentLoginBtnLogin.setVisibility(View.GONE);
//        binding.fragmentLoginProgress.setVisibility(View.VISIBLE);
//        if (loginCheck(v)){
////            doLogin(MainActivity.accountBean.account, MainActivity.accountBean.password);
//            // Fake Login
//            MainActivity.accountBean.token = "12345";
//            ((MainActivity) getActivity()).setLoginAccount();
//            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_Login_to_Dashboard);
//        }
    };

//    private boolean loginCheck(View view){
//        String account = binding.fragmentLoginEtAccount.getText().toString().trim();
//        String password = binding.fragmentLoginEtPassword.getText().toString().trim();
//
//        if (account.length() == 0){
//            Snackbar.make(view, getText(R.string.fragment_login_error_empty_account), Snackbar.LENGTH_SHORT).show();
//            binding.fragmentLoginEtAccount.requestFocus();
//            return false;
//        }
//
//        if (password.length() == 0){
//            Snackbar.make(view, getText(R.string.fragment_login_error_empty_password), Snackbar.LENGTH_SHORT).show();
//            binding.fragmentLoginEtPassword.requestFocus();
//            return false;
//        }
//
//        MainActivity.accountBean.account = account;
//        MainActivity.accountBean.password = password;
//
//        if (getActivity() != null) {
//            SharedPreferences pref = getActivity().getSharedPreferences("account", Context.MODE_PRIVATE);
//            if (binding.fragmentLoginSwitchRemember.isChecked()) {
//                pref.edit()
//                        .putString("account", account)
//                        .putString("password", password)
//                        .putBoolean("remember", true)
//                        .apply();
//            } else {
//                pref.edit().clear().apply();
//            }
//        }
//        return true;
//    }
//
//    private String doLogin(String account, String password){
//        HttpConnector.HttpCallback callback = new HttpConnector.HttpCallback() {
//
//            @Override
//            public void onFailure(String errorMessage) {
//                Snackbar.make(binding.fragmentLoginLayout, getText(R.string.fragment_login_error_empty_account), Snackbar.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onSuccess(ApiResponse apiResponse) {
//                if (getActivity() != null) {
////                    MainActivity.accountBean.token = apiResponse.Payload;
//                    ((MainActivity) getActivity()).setLoginAccount();
//                    getActivity().runOnUiThread(() -> NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_Login_to_Dashboard));
//                }
//            }
//        };
//
//        HttpConnector connector = new HttpConnector(MainConfig.SERVER_HOST + LOGIN_PATH, callback);
//        if (account != null && password != null){
//            Request request = new Request();
//            request.userAccount = account;
//            request.userPassword = password;
//            Moshi moshi = new Moshi.Builder().build();
//            JsonAdapter<Request> jsonAdapter = moshi.adapter(Request.class);
//
//            connector.post(jsonAdapter.toJson(request));
//        }
//        return "";
//    }
//
//    static class Request{
//        public String userAccount;
//        public String userPassword;
//        public String locale = "zh-TW";
//    }
}