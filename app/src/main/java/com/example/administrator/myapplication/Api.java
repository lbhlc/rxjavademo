package com.example.administrator.myapplication;

import com.example.administrator.myapplication.model.AccountRspModel;
import com.example.administrator.myapplication.model.LoginModel;
import com.example.administrator.myapplication.model.RspModel;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/9/15.
 */

public interface Api {
    @POST("account/login")
    Observable<RspModel<AccountRspModel>> login(@Body LoginModel request);



}
