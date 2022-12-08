package com.auo.shelf.cmsapp.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

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
        binding.fragmentLoginButtonLogin.setOnClickListener(loginBtnClick);
        binding.fragmentLoginLabelForgotPassword.setOnClickListener(forgotPasswordClick);

        binding.fragmentLoginInputAccount.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus){
                binding.fragmentLoginInputAccount.addTextChangedListener(accountTextWatcher);
            }else{
                binding.fragmentLoginInputAccount.removeTextChangedListener(accountTextWatcher);
            }
        });

        binding.fragmentLoginInputPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus){
                binding.fragmentLoginInputPassword.addTextChangedListener(passwordTextWatcher);
            }else{
                binding.fragmentLoginInputPassword.removeTextChangedListener(passwordTextWatcher);
            }
        });
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

        SharedPreferences pref = getActivity().getSharedPreferences("account", Context.MODE_PRIVATE);
        binding.fragmentLoginInputAccount.setText(pref.getString("account", ""));
        binding.fragmentLoginInputPassword.setText(pref.getString("password", ""));
        if (pref.getBoolean("remember", false)){
            Runnable r = () -> binding.fragmentLoginCheckboxRemember.setChecked(true);
            new Handler().post(r);
        }
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

    private final TextWatcher accountTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            checkInput(binding.fragmentLoginInputAccount, binding.fragmentLoginAccountError, R.string.fragment_login_error_empty_username);
        }
    };

    private final TextWatcher passwordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            checkInput(binding.fragmentLoginInputPassword, binding.fragmentLoginPasswordError, R.string.fragment_login_error_empty_password);
        }
    };

    private final View.OnClickListener loginBtnClick = v -> {
//        binding.fragmentLoginButtonLogin.setVisibility(View.GONE);
//        binding.fragmentLoginProgress.setVisibility(View.VISIBLE);
        if (loginCheck()){
//            doLogin(MainActivity.accountBean.account, MainActivity.accountBean.password);
            // Fake Login
            MainActivity.accountBean.token = "12345";
            ((MainActivity) getActivity()).setLoginAccount();
            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_login_to_dashboard);
        }
    };

    private final View.OnClickListener forgotPasswordClick = v ->{
        NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_login_to_forgot_password);
    };

    private boolean loginCheck(){
        boolean error = false;
        String account = binding.fragmentLoginInputAccount.getText().toString().trim();
        String password = binding.fragmentLoginInputPassword.getText().toString().trim();

        if (!checkInput(binding.fragmentLoginInputAccount, binding.fragmentLoginAccountError, R.string.fragment_login_error_empty_username)) error = true;
        if (!checkInput(binding.fragmentLoginInputPassword, binding.fragmentLoginPasswordError, R.string.fragment_login_error_empty_password)) error = true;

        if (error) return false;

        MainActivity.accountBean.account = account;
        MainActivity.accountBean.password = password;

        if (getActivity() != null) {
            SharedPreferences pref = getActivity().getSharedPreferences("account", Context.MODE_PRIVATE);
            if (binding.fragmentLoginCheckboxRemember.isChecked()) {
                pref.edit()
                        .putString("account", account)
                        .putString("password", password)
                        .putBoolean("remember", true)
                        .apply();
            } else {
                pref.edit().clear().apply();
            }
        }
        return true;
    }

    private String doLogin(String account, String password){
        HttpConnector.HttpCallback callback = new HttpConnector.HttpCallback() {

            @Override
            public void onFailure(String errorMessage) {
//                Snackbar.make(binding.fragmentLoginLayout, getText(R.string.fragment_login_error_empty_account), Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(ApiResponse apiResponse) {
                if (getActivity() != null) {
//                    MainActivity.accountBean.token = apiResponse.Payload;
                    ((MainActivity) getActivity()).setLoginAccount();
                    getActivity().runOnUiThread(() -> NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_login_to_dashboard));
                }
            }
        };

        HttpConnector connector = new HttpConnector(MainConfig.SERVER_HOST + LOGIN_PATH, callback);
        if (account != null && password != null){
            Request request = new Request();
            request.userAccount = account;
            request.userPassword = password;
            Moshi moshi = new Moshi.Builder().build();
            JsonAdapter<Request> jsonAdapter = moshi.adapter(Request.class);

            connector.post(jsonAdapter.toJson(request));
        }
        return "";
    }

    private boolean checkInput(EditText inputView, TextView messageView, int errorTextRes){
        if (inputView.length() == 0){
            inputView.setBackgroundResource(R.drawable.edit_text_error);
            inputView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.mipmap.ic_error, 0);
            messageView.setText(errorTextRes);
            inputView.requestFocus();
            return false;
        }else{
            inputView.setBackgroundResource(R.drawable.edit_text_normal);
            inputView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
            messageView.setText("");
            return true;
        }
    }

    static class Request{
        public String userAccount;
        public String userPassword;
        public String locale = "zh-TW";
    }
}