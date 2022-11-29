package com.auo.shelf.cmsapp.ui.shelf;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.databinding.FragmentShelfManagerBinding;
import com.auo.shelf.cmsapp.json.ApiResponse;
import com.auo.shelf.cmsapp.json.ShelfList;

import com.auo.shelf.cmsapp.ui.quick.LayoutSelectFragment;
import com.auo.shelf.cmsapp.ui.shelf.adapter.ShelfAdapter;

import java.io.IOException;
import java.io.InputStream;

public class ShelfManager extends Fragment {

    private FragmentShelfManagerBinding binding;
    private ShelfViewModel shelfViewModel;
    private ShelfAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shelfViewModel = new ViewModelProvider(getActivity()).get(ShelfViewModel.class);

        binding = FragmentShelfManagerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textGallery;
//        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new ShelfAdapter(this);

        // For Test
        InputStream is = getContext().getResources().openRawResource(R.raw.shelf_list);
        ShelfList shelfList = new ShelfList();
        try {
            byte[] b = new byte[is.available()];
            is.read(b);
            ApiResponse response = new ApiResponse();
            if (response.fromJson(new String(b))){
                if (shelfList.fromJson(response.payload)){
                    mAdapter.setList(shelfList.getList());
                    shelfViewModel.setShelfList(shelfList);
                    binding.fragmentShelfManagerShelfList.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.fragmentShelfManagerShelfList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                    binding.fragmentShelfManagerShelfList.setAdapter(mAdapter);
                }else{
                    Log.d("Zack", "Error Parse Template JSON");
                }
            }else{
                Log.d("Zack", "Error Parse Response JSON");
            }

        } catch (IOException e) {
            Log.d("Zack", "Error Load JSON File");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void gotoPlayerManager(int pkey){
        shelfViewModel.setSelectPkey(pkey);
        NavHostFragment.findNavController(ShelfManager.this).navigate(R.id.action_ShelfManager_to_PlayerManager);
    }
}