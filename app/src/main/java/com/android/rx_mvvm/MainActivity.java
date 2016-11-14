package com.android.rx_mvvm;

import android.app.Application;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.rx.mvvmlibs.Model;
import com.rx.mvvmlibs.Result;
import com.rx.mvvmlibs.RxMvvmApplication;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Observable observable;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                test();
            }
        });

        retrofit = RxMvvmApplication.getInstance().getRetrofit();
        observable = retrofit.create(ApiNuoMi.class).rxGetCategory();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void test(){
        Model<List<NuoMiCategoryBean>> model = new Model<List<NuoMiCategoryBean>>(null) {
            @Override
            protected Observable setApiInterface() {
                return RxMvvmApplication.getInstance().getRetrofit().create(ApiNuoMi.class).rxGetCategory();
            }

            @Override
            public void resultData(List<NuoMiCategoryBean> nuoMiCategoryBean) {
                Log.d(TAG, "resultData: " + nuoMiCategoryBean.get(0).cat_name.get());
            }

        };
        model.enqueueRequest();

        TestBean testBean = new TestBean();
        testBean.testBoolean.set(true);
        testBean.testFloat.set(1.27F);
        testBean.testString.set("hello test");
        testBean.testInt.set(10000);
        RxMvvmApplication.getInstance().getGson().toJson(testBean);
    }
}
