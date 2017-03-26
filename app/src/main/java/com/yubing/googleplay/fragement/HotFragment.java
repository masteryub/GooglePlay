package com.yubing.googleplay.fragement;

import android.graphics.drawable.StateListDrawable;

import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.yubing.googleplay.R;
import com.yubing.googleplay.network.Api;
import com.yubing.googleplay.network.HiRetorfit;
import com.yubing.googleplay.utils.LogUtils;
import com.zhy.view.flowlayout.FlowLayout;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yu on 2017/3/26.
 */

public class HotFragment extends BaseFragment {

    private List<String> mBody;

    @Override
    protected void startLoadData() {
        Api api= HiRetorfit.getInstans().getApi();//获取API
        Call<List<String>> listCall=api.ListHot();//获取网络请求call

        /*try {
            Response<List<String>> response= listCall.execute();//同步请求
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        listCall.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {

                Toast.makeText(getContext(), "网络请求成功", Toast.LENGTH_SHORT).show();

                mBody = response.body();
                LogUtils.d(mBody.toString());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                LogUtils.d(t.getLocalizedMessage());
                Toast.makeText(getContext(), "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected View onCreatContentView() {

        return null;
    }
}
