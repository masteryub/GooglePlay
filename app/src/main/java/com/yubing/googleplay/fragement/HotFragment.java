package com.yubing.googleplay.fragement;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.yubing.googleplay.R;
import com.yubing.googleplay.network.Api;
import com.yubing.googleplay.network.HiRetorfit;
import com.yubing.googleplay.utils.LogUtils;
import com.yubing.googleplay.widget.FlowLayout;


import java.io.IOException;
import java.util.List;
import java.util.Random;

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
        //异步请求
        listCall.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {

                Toast.makeText(getContext(), "网络请求成功", Toast.LENGTH_SHORT).show();

                mBody = response.body();
                LogUtils.d(mBody.toString());
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                LogUtils.d(t.getLocalizedMessage());
                Toast.makeText(getContext(), "网络请求失败", Toast.LENGTH_SHORT).show();
                onDataLoadedFailde();
            }
        });
    }

    @Override
    protected View onCreatContentView() {
        int padding= (int) getResources().getDimension(R.dimen.padding);
        FlowLayout fl= new FlowLayout(getContext());
        for (int i = 0; i <mBody.size() ; i++) {
            final TextView tv = getTextView(padding, i);
            GradientDrawable drawable = getDrawable();


            tv.setBackgroundDrawable(drawable);
            fl.addView(tv);
        }
        return fl;
    }

    @NonNull
    private GradientDrawable getDrawable() {
        //创建一个shape
        GradientDrawable drawable= new GradientDrawable();
        drawable.setCornerRadius(8);
        //随机产生颜色
        int argb = getArgb();
        drawable.setColor(argb);


        //创建一个选择器
        StateListDrawable sta=new StateListDrawable();
        //按下去状态
        GradientDrawable passed=new GradientDrawable();
        passed.setCornerRadius(8);
        passed.setColor(Color.DKGRAY);

        sta.addState(new int[]{android.R.attr.state_pressed},passed);
        sta.addState(new int[]{},drawable);
        return drawable;
    }

    private int getArgb() {
        int alpha=255;
        int red=30+new Random().nextInt(200);
        int green=30+new Random().nextInt(200);
        int blue=30+new Random().nextInt(200);
        return Color.argb(alpha,red,green,blue);
    }

    @NonNull
    private TextView getTextView(int padding, int i) {
        final TextView tv= new TextView(getActivity());
        tv.setText(mBody.get(i));
        tv.setGravity(Gravity.CENTER);
        tv.setPadding(padding,padding,padding,padding);
        tv.setClickable(true);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),tv.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        return tv;
    }
}
