package com.auo.shelf.cmsapp.ui.quick;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.auo.shelf.cmsapp.MainActivity;
import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.bean.Template;
import com.auo.shelf.cmsapp.databinding.FragmentQuickLayoutSelectBinding;
import com.auo.shelf.cmsapp.json.ApiResponse;
import com.auo.shelf.cmsapp.json.Templates;
import com.auo.shelf.cmsapp.ui.quick.adapter.LayoutAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class LayoutSelectFragment extends Fragment {

    private FragmentQuickLayoutSelectBinding binding;
    private QuickPublishSetting quickPublishSetting = new QuickPublishSetting();
    private QuickPublishViewModel quickPublishViewModel;
    private Templates templates;
    private LayoutAdapter mAdatper;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        quickPublishViewModel = new ViewModelProvider(getActivity()).get(QuickPublishViewModel.class);

        binding = FragmentQuickLayoutSelectBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fragmentQuickLayoutSelectNext.setOnClickListener(nextClick);
        mAdatper = new LayoutAdapter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.fragment_quick_publish_title);

        // For Test
        InputStream is = getContext().getResources().openRawResource(R.raw.template_list);
        Templates templates = new Templates();
        try {
            byte[] b = new byte[is.available()];
            is.read(b);
            ApiResponse response = new ApiResponse();
            if (response.fromJson(new String(b))){
                if (templates.fromJson(response.payload)){
                    mAdatper = new LayoutAdapter(getParentFragment());
                    this.templates = templates;
                    mAdatper.setTemplates(templates);
                    binding.fragmentQuickLayoutSelectPager.setAdapter(mAdatper);

                    CircleIndicator3 indicator = binding.fragmentQuickLayoutSelectPagerIndicator;
                    indicator.setViewPager(binding.fragmentQuickLayoutSelectPager);
                    mAdatper.registerAdapterDataObserver(indicator.getAdapterDataObserver());

                    binding.fragmentQuickLayoutSelectPager.registerOnPageChangeCallback(pageChangeCallback);
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
        mAdatper = null;
    }

    private View.OnClickListener nextClick = v -> {
        NavHostFragment.findNavController(LayoutSelectFragment.this).navigate(R.id.action_quick_layout_select_to_device_select);
    };

    private ViewPager2.OnPageChangeCallback pageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
        Template template = templates.getList().get(position);
        binding.fragmentQuickLayoutSelectTitle.setText(template.name);
        quickPublishSetting.setTemplate(template);
        quickPublishViewModel.setQuickPublihSetting(quickPublishSetting);
        }
    };

}