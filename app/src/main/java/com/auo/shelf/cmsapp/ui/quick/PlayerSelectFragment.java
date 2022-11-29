package com.auo.shelf.cmsapp.ui.quick;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.auo.shelf.cmsapp.MainActivity;
import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.Player;
import com.auo.shelf.cmsapp.bean.PlayerGroup;
import com.auo.shelf.cmsapp.bean.PlayerSelectBean;
import com.auo.shelf.cmsapp.bean.Template;
import com.auo.shelf.cmsapp.databinding.FragmentQuickPlayerSelectBinding;
import com.auo.shelf.cmsapp.json.ApiResponse;
import com.auo.shelf.cmsapp.json.PlayerGroups;
import com.auo.shelf.cmsapp.ui.quick.adapter.PlayerSelectAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class PlayerSelectFragment extends Fragment {

    private FragmentQuickPlayerSelectBinding binding;
    private QuickPublishViewModel quickPublishViewModel;
    private QuickPublishSetting quickPublishSetting;
    private PlayerSelectAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle bundle) {

        quickPublishViewModel = new ViewModelProvider(getActivity()).get(QuickPublishViewModel.class);
        quickPublishSetting = quickPublishViewModel.getQuickPublishSetting();

        binding = FragmentQuickPlayerSelectBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);

        mAdapter = new PlayerSelectAdapter(this);

        // For Test
        InputStream is = getContext().getResources().openRawResource(R.raw.player_list);
        PlayerGroups playerGroups = new PlayerGroups();
        ArrayList<PlayerSelectBean> itemList = new ArrayList<>();
        try {
            byte[] b = new byte[is.available()];
            is.read(b);
            ApiResponse response = new ApiResponse();
            if (response.fromJson(new String(b))){
                if (playerGroups.fromJson(response.payload)){
                    ArrayList<PlayerGroup> playerGroupList;
                    playerGroupList = playerGroups.getList();
                    for (int i=0; i<playerGroupList.size(); i++){
                        PlayerGroup playerGroup = playerGroupList.get(i);
                        itemList.add(new PlayerSelectBean(playerGroup.id, PlayerSelectBean.TYPE_GROUP, playerGroup.name));

                        for (int j=0; j<playerGroup.players.size(); j++){
                            Player player = playerGroup.players.get(j);
                            itemList.add(new PlayerSelectBean(player.id, PlayerSelectBean.TYPE_DEVICE, player.name));
                        }
                    }

                    mAdapter.setItemList(itemList);
                    binding.fragmentQuickDeviceSelectCheckView.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.fragmentQuickDeviceSelectCheckView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                    binding.fragmentQuickDeviceSelectCheckView.setAdapter(mAdapter);
                }else{
                    Log.d("Zack", "Error Parse Player JSON");
                }
            }else{
                Log.d("Zack", "Error Parse Response JSON");
            }

        } catch (IOException e) {
            Log.d("Zack", "Error Load JSON File");
        }

        binding.fragmentQuickDeviceSelectPrev.setOnClickListener(prevClick);
        binding.fragmentQuickDeviceSelectNext.setOnClickListener(nextClick);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private View.OnClickListener prevClick = v -> {
        NavHostFragment.findNavController(PlayerSelectFragment.this).navigate(R.id.action_quick_device_select_to_layout_select);
    };

    private View.OnClickListener nextClick = v -> {
        ArrayList<PlayerSelectBean> itemList = mAdapter.getItemList();
        ArrayList<PlayerSelectBean> checkedList = new ArrayList<>();
        for (int i=0; i<itemList.size(); i++){
            PlayerSelectBean bean = itemList.get(i);
            if (bean.isChecked){
                checkedList.add(bean);
            }
        }

        quickPublishSetting.setPlayerList(checkedList);
        quickPublishViewModel.setQuickPublihSetting(quickPublishSetting);
        NavHostFragment.findNavController(PlayerSelectFragment.this).navigate(R.id.action_quick_device_select_to_datetime);
    };

    public void setNextButtonEnabled(boolean enabled){
        binding.fragmentQuickDeviceSelectNext.setEnabled(enabled);
    }
}