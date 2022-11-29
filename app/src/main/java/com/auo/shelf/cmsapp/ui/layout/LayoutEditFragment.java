package com.auo.shelf.cmsapp.ui.layout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.databinding.FragmentQuickLayoutEditBinding;
import com.auo.shelf.cmsapp.ui.quick.adapter.LayoutElementAdapter;

import java.util.ArrayList;

public class LayoutEditFragment extends Fragment {

    private FragmentQuickLayoutEditBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        QuickPublishViewModel slideshowViewModel =
//                new ViewModelProvider(this).get(QuickPublishViewModel.class);

        binding = FragmentQuickLayoutEditBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textSlideshow;
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}