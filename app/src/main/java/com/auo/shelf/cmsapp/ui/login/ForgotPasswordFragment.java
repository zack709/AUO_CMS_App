package com.auo.shelf.cmsapp.ui.login;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.databinding.FragmentForgotPasswordBinding;


public class ForgotPasswordFragment extends Fragment {

    private static final String LOGIN_PATH = "/User/Token";

    private FragmentForgotPasswordBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fragmentForgotPasswordLabelBackToLogin.setOnClickListener(backClick);
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

    private final View.OnClickListener backClick = v -> {
        NavHostFragment.findNavController(ForgotPasswordFragment.this).navigate(R.id.action_forgot_password_to_login);
    };
}