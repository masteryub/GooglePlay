package com.yubing.googleplay.fragement;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yubing.googleplay.adapter.RecommendAdapter;
import com.yubing.googleplay.network.Api;
import com.yubing.googleplay.network.HiRetorfit;
import com.yubing.googleplay.utils.LogUtils;
import com.yubing.googleplay.widget.StellarMap;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yu on 2017/3/26.
 */

public class RecommendFragment extends BaseFragment {

    private List<String> mDataList;

    @Override
    protected View onCreatContentView() {
        //TextView tv= new TextView(getContext());
        //tv.setText(mDataList.toString());
        //星状图
        StellarMap stellarMap= new StellarMap(getContext());

        stellarMap.setAdapter(new RecommendAdapter(getContext(),mDataList));
        //设置先显示第一个页面  不设置就不会显示
        stellarMap.setGroup(0,false);
        //设置分布网格   纵向切15下   横向切30下
        stellarMap.setRegularity(15,30);
        return stellarMap;
    }

    @Override
    protected void startLoadData() {
    Api api= HiRetorfit.getInstans().getApi();
        Call<List<String>> listCall= api.ListRecommend();
        listCall.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Toast.makeText(getContext(), "加载数据成功", Toast.LENGTH_SHORT).show();
                mDataList = response.body();
                LogUtils.d(response.body().toString());
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
             onDataLoadedFailde();
            }
        });
    }
}
