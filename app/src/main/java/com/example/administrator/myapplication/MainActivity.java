package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    /**
     *
     * rx初入门,通过这个demo可以看出来rxjava中的几个重要方法的执行流程
     * 只有当上游和下游建立连接之后, 上游才会开始发送事件. 也就是调用了subscribe() 方法之后才开始发送事件.
     * ObservableEmitter： Emitter是发射器的意思，那就很好猜了，这个就是用来发出事件的，它可以发出三种类型的事件，
     * 通过调用emitter的onNext(T value)、onComplete()和onError(Throwable error)就可以分别发出next事件、complete事件和error事件
     * 并不意味着你可以随意乱七八糟发射事件，需要满足一定的规则：
     上游可以发送无限个onNext, 下游也可以接收无限个onNext.
     当上游发送了一个onComplete后, 上游onComplete之后的事件将会继续发送, 而下游收到onComplete事件之后将不再继续接收事件.
     当上游发送了一个onError后, 上游onError之后的事件将继续发送, 而下游收到onError事件之后将不再继续接收事件.
     上游可以不发送onComplete或onError.
     最为关键的是onComplete和onError必须唯一并且互斥, 即不能发多个onComplete,
     也不能发多个onError, 也不能先发一个onComplete, 然后再发一个onError, 反之亦然
     注: 关于onComplete和onError唯一并且互斥这一点, 是需要自行在代码中进行控制, 如果你的代码逻辑中违背了这个规则,
     并不一定会导致程序崩溃. 比如发送多个onComplete是可以正常运行的, 依然是收到第一个onComplete就不再接收了,
     但若是发送多个onError, 则收到第二个onError事件会导致程序会崩溃.
     在dispose以后再调用onnext方法观察者也不会接收了
     出处 http://www.jianshu.com/p/464fa025229e
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable<Integer>observable=Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1);
                Log.e("LBH","EMitter="+1);
                observableEmitter.onNext(2);
                Log.e("LBH","EMitter="+2);
                observableEmitter.onNext(3);
                Log.e("LBH","EMitter="+3);
                observableEmitter.onComplete();
                Log.e("LBH","onComplete");
            }
        });
        Observer observer=new Observer<Integer>() {
            Disposable disposable;
            @Override
            public void onSubscribe(Disposable disposable) {
                Log.e("LBH"," onSubscribe");
                this.disposable=disposable;
            }

            @Override
            public void onNext(Integer o) {
                Log.e("LBH",o+"");
                if (o==2)
                {
                    disposable.dispose();
                    Log.e("LBH","dispose");
                    Log.e("LBH","result="+disposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("LBH","  onError");
            }

            @Override
            public void onComplete() {
                Log.e("LBH"," onComplete");
            }
        };
        observable.subscribe(observer);
    }
}
