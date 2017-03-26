package com.yubing.googleplay.fragement;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.yubing.googleplay.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yu on 2017/3/26.
 */

public abstract class BaseFragment extends Fragment {
    @BindView(R.id.prograss_bar)
    ProgressBar mPrograssBar;
    @BindView(R.id.load_erron)
    LinearLayout mLoadErron;
    @BindView(R.id.fragment_content)
    FrameLayout mFragmentContent;
    @BindView(R.id.retry)
    Button mRetry;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_base, null);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //当时图创建完成之后开始加载数据
        startLoadData();
    }

    /**
     * 每个子类都去重写此方法加载数据
     */
    protected void startLoadData() {
    }

    /**
     * 获取数据成功之后
     */
    protected void onDataLoadedSuccess() {
        mPrograssBar.setVisibility(View.GONE);
        mFragmentContent.addView(onCreatContentView());
    }

    protected void onDataLoadedFailde() {
        mPrograssBar.setVisibility(View.GONE);
        mLoadErron.setVisibility(View.VISIBLE);
    }

    protected abstract View onCreatContentView();


    @OnClick(R.id.retry)
    public void onClick() {
        mLoadErron.setVisibility(View.GONE);
        mPrograssBar.setVisibility(View.VISIBLE);
        startLoadData();
    }
}