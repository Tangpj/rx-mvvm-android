package com.rx.mvvmlibs.module;

import android.app.Activity;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.Log;
import android.widget.FrameLayout;


import com.rx.mvvmlibs.IErrorInfo;
import com.rx.mvvmlibs.IModel;
import com.rx.mvvmlibs.IViewModel;
import com.rx.mvvmlibs.Model;
import com.rx.mvvmlibs.R;
import com.rx.mvvmlibs.ViewModel;
import com.rx.mvvmlibs.bean.ErrorBean;
import com.rx.mvvmlibs.bean.ProgressBean;
import com.rx.mvvmlibs.databinding.ActivityMvvmBinding;
import com.rx.mvvmlibs.databinding.ContentMvvmBinding;
import com.rx.mvvmlibs.databinding.DefaultProgressBinding;
import com.rx.mvvmlibs.databinding.ErrorBinding;
import com.rx.mvvmlibs.view.MvvmActivity;
import com.rx.utillibs.LogUtil;

import dagger.Module;
import dagger.Provides;

/**
 * @ClassName: ViewModelModule
 * @author create by Tang
 * @date date 16/11/16 上午10:41
 * @Description: TODO
 */

@Module
public class ViewModelModule {

    private MvvmActivity activity;
    private IViewModel viewModel;
    private IErrorInfo errorInfo;

    private ActivityMvvmBinding activityMvvmBinding;
    private ContentMvvmBinding contentMvvmBinding;

    public ViewModelModule(ViewModel viewModel, MvvmActivity activity){
        this.activity = activity;
        this.viewModel = viewModel;
        this.errorInfo = viewModel;
    }

    @Provides
    public ActivityMvvmBinding providesActivityMvvmBinding(){
        activityMvvmBinding = DataBindingUtil
                .setContentView(activity, R.layout.activity_mvvm);
        return activityMvvmBinding;
    }

    @Provides
    public ContentMvvmBinding providesContentMvvmBinding(){
        LogUtil.d(getClass(), "providesContentMvvmBinding: ");
        contentMvvmBinding = activityMvvmBinding.contentMvvm;
        return contentMvvmBinding;
    }

    @Provides
    ViewDataBinding providesChildBinding(){
        LogUtil.d(getClass(),"providesChildBinding: ");
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);

        ViewDataBinding childBinding = activity.onCreateView(activity.getLayoutInflater()
                ,contentMvvmBinding.mvvmFrameLayout);
        if (childBinding != null )
            contentMvvmBinding.mvvmFrameLayout
                    .addView(childBinding.getRoot(),lp);
        return childBinding;
    }


    @Provides
    public ErrorBinding providesErrorBinding(){
        return contentMvvmBinding.mvvmError;
    }

    @Provides
    public DefaultProgressBinding providesProgressBinding(){
        return contentMvvmBinding.mvvmDefaultProgress;
    }

    @Provides
    public ErrorBean providesError(){
        return new ErrorBean(errorInfo.setErrorImageResource(),errorInfo.setErrorString());
    }

    @Provides
    public IModel providesModel(){
        return new Model<>(viewModel);
    }

    @Provides
    public ProgressDialog providesProgressDialog(){
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setTitle("加载中");
        return dialog;
    }
}