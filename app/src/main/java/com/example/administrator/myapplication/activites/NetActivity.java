package com.example.administrator.myapplication.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication.Api;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.model.AccountRspModel;
import com.example.administrator.myapplication.model.LoginModel;
import com.example.administrator.myapplication.model.RspModel;
import com.example.administrator.myapplication.net.GetClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class NetActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button;
    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        retrofit=GetClient.create();
    }

    @Override
    public void onClick(View view) {
       Api api=retrofit.create(Api.class);
       api.login(new LoginModel("lbh","123456")).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<RspModel<AccountRspModel>>() {
                   @Override
                   public void onSubscribe(Disposable disposable) {

                   }

                   @Override
                   public void onNext(RspModel<AccountRspModel> accountRspModelRspModel) {

                   }

                   @Override
                   public void onError(Throwable throwable) {

                       Log.e("LBH","登录失败");
                   }

                   @Override
                   public void onComplete() {
                       Log.e("LBH","登录成功");
                   }
               });

    }
}
