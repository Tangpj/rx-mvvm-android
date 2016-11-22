package com.rx.mvvmlibs.view.iview;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rx.mvvmlibs.ViewModel;


/**
 * @ClassName: BindingViewModel
 * @author create by Tang
 * @date date 16/11/15 下午3:12
 * @Description: BindingViewModel
 */

public interface BindingViewModel {

    /**
     * @Method: onCreateView
     * @author create by Tang
     * @date date 16/11/15 下午4:01
     * @Description: 创建子Activity布局
     */
    ViewDataBinding onCreateBinding(LayoutInflater inflater, ViewGroup parent);

    /**
     * @Method: onCreateViewModel
     * @author create by Tang
     * @date date 16/11/15 下午4:02
     * @Description: 绑定ViewModel
     * 必须在该方法中生成ViewModel实例
     */
    ViewModel onBindingViewModel();

    /**
     * @Method: init
     * @author create by Tang
     * @date date 16/11/18 下午5:16
     * @Description: 可以在这里做一些初始化操作d
     */
    void init();

}