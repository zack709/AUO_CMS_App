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
import com.auo.shelf.cmsapp.databinding.FragmentQuickLayoutContentBinding;
import com.auo.shelf.cmsapp.ui.quick.adapter.LayoutElementAdapter;

import java.util.ArrayList;

public class LayoutContentFragment extends Fragment {

    private FragmentQuickLayoutContentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        QuickPublishViewModel slideshowViewModel =
//                new ViewModelProvider(this).get(QuickPublishViewModel.class);

        binding = FragmentQuickLayoutContentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textSlideshow;
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fragmentQuickLayoutContentPrev.setOnClickListener(prevClick);
        binding.fragmentQuickLayoutContentNext.setOnClickListener(nextClick);

        ArrayList<String> list = new ArrayList<>();
        list.add("Layout1");
        list.add("Layout2");
        list.add("Layout3");
        LayoutElementAdapter adapter = new LayoutElementAdapter(this, list);
        binding.fragmentQuickLayoutContentElementView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.fragmentQuickLayoutContentElementView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        binding.fragmentQuickLayoutContentElementView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private View.OnClickListener prevClick = v -> {
//        NavHostFragment.findNavController(LayoutContentFragment.this).navigate(R.id.action_quick_layout_content_to_select);
    };

    private View.OnClickListener nextClick = v -> {
//        NavHostFragment.findNavController(LayoutContentFragment.this).navigate(R.id.action_quick_layout_content_to_select);
    };
}