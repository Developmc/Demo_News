package com.clement.example.demo_news.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**Base Fragment
 * Created by clement on 16/11/9.
 */

public abstract class BaseFragment extends Fragment {
    protected abstract int getFragmentLayoutResId();
    protected abstract void initVariables();
    protected abstract void initViews();
    protected abstract void initBehavior();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getFragmentLayoutResId(), container, false);
        //using butter knife
        ButterKnife.bind(this, rootView);
        initVariables();
        initViews();
        initBehavior();
        return rootView;
    }
}
