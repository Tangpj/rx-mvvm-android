package com.android.rx_mvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.rx_mvvm.databinding.ActivityTestBinding;
import com.rx.mvvmlibs.ViewModel;
import com.rx.mvvmlibs.view.MvvmActivity;
import com.rx.utillibs.LogUtil;

/**
 * @ClassName: TestMvvmActivity
 * @author create by Tang
 * @date date 16/11/15 上午11:37
 * @Description: TODO
 */

public class TestMvvmActivity extends MvvmActivity{


    TestViewModel testViewModel;

    @Override
    public ViewModel onBindingViewModel() {
        LogUtil.d("");
        testViewModel = new TestViewModel(this);

        return testViewModel;
    }

    @Override
    public void init() {
        super.init();
    }
}
