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
import com.auo.shelf.cmsapp.bean.ShelfBean;
import com.auo.shelf.cmsapp.databinding.FragmentShelfPlayerManagerBinding;
import com.auo.shelf.cmsapp.json.ApiResponse;
import com.auo.shelf.cmsapp.json.ShelfList;
import com.auo.shelf.cmsapp.ui.shelf.adapter.PlayerAdapter;
import com.auo.shelf.cmsapp.ui.shelf.adapter.ShelfAdapter;

import java.io.IOException;
import java.io.InputStream;

public class PlayerManager extends Fragment {

    private FragmentShelfPlayerManagerBinding binding;
    private ShelfViewModel shelfViewModel;
    private PlayerAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shelfViewModel = new ViewModelProvider(getActivity()).get(ShelfViewModel.class);

        binding = FragmentShelfPlayerManagerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ShelfBean shelfBean = shelfViewModel.getSelectedShelf();
        if (shelfBean != null) {
            String shelfName = getString(R.string.fragment_shelf_player_manager_title, shelfBean.name);
            binding.fragmentShelfPlayerManagerTitle.setText(shelfName);
        }

        mAdapter = new PlayerAdapter(this);
        mAdapter.setList(shelfBean);
        binding.fragmentShelfManagerPlayerList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.fragmentShelfManagerPlayerList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        binding.fragmentShelfManagerPlayerList.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void gotoBinding(){
        NavHostFragment.findNavController(PlayerManager.this).navigate(R.id.action_PlayerManager_to_PlayerBinding);
    }
}