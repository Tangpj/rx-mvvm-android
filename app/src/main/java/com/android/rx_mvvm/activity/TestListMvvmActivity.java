package com.android.rx_mvvm.activity;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.rx_mvvm.BaiduApi;
import com.android.rx_mvvm.TestAdapter;
import com.android.rx_mvvm.bean.NuoMiCategoryBean;
import com.rx.mvvmlibs.ListViewModel;
import com.rx.mvvmlibs.Result;
import com.rx.mvvmlibs.view.BindingListAdapter;
import com.rx.mvvmlibs.view.ListMvvmActivity;
import com.rx.utillibs.LogUtil;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @ClassName: TestListMvvmActivity
 * @author create by Tang
 * @date date 16/11/21 下午5:23
 * @Description: TODO
 */

public class TestListMvvmActivity extends ListMvvmActivity {

    @Override
    public ListViewModel onBindingViewModel() {
        return new ListViewModel(this) {

            @Override
            public Observable setApiInterface(Retrofit retrofit) {
                return retrofit.create(BaiduApi.class).rxGetCategory();
            }

            @Override
            public BindingListAdapter setAdapter() {
                LogUtil.d("setAdapter");
                TestAdapter adapter = new TestAdapter();
                adapter.setOnItemClickListener(new BindingListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object o) {
                        refreshIndexPage(position);
                    }
                });
                return adapter;
            }

            @Override
            public RecyclerView.LayoutManager setLayoutManager(Context context) {
                LogUtil.d("setLayoutManager");
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                return linearLayoutManager;
            }

            @Override
            public void init() {
                super.init();
                viewModelWrapper.model
                        .getBuilder()
                        .addHeader("apikey","05cecef32508c4bd5853a0fed178e322");
                setCount(7);
            }
        };
    }
}
