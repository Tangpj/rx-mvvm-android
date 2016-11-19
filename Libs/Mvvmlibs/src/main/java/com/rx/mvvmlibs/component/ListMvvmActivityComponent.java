package com.rx.mvvmlibs.component;

import com.rx.mvvmlibs.module.ListMvvmActivityModule;
import com.rx.mvvmlibs.view.ListMvvmActivity;

import dagger.Component;

/**
 * @ClassName: ListMvvmActivityComponent
 * @author create by Tang
 * @date date 16/11/19 下午3:54
 * @Description: TODO
 */

@Component(modules = ListMvvmActivityModule.class)
public interface ListMvvmActivityComponent {

    void inject(ListMvvmActivity listMvvmActivity);
}
