//package com.bingdou.dnfscript.tools;
//
//import java.util.concurrent.TimeUnit;
//
//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
//import io.reactivex.rxjava3.annotations.NonNull;
//import io.reactivex.rxjava3.core.Observable;
//import io.reactivex.rxjava3.core.Observer;
//import io.reactivex.rxjava3.disposables.Disposable;
//
///**
// * Rxjava2.x实现轮询定时器.
// *
// * @author xuzhuyun
// */
//public class RxTimerUtil {
//
//    private static Disposable mDisposable;
//
//    /**
//     * milliseconds毫秒后执行next操作
//     */
//    public static void timer(long milliseconds, final IRxNext next) {
//        Observable.timer(milliseconds, TimeUnit.MILLISECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable disposable) {
//                        mDisposable = disposable;
//                    }
//
//                    @Override
//                    public void onNext(@NonNull Long number) {
//                        if (next != null) {
//                            next.doNext(number);
//                        }
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        //取消订阅
//                        cancel();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        //取消订阅
//                        cancel();
//                    }
//                });
//    }
//
//
//    /**
//     * 每隔milliseconds毫秒后执行next操作
//     */
//    public static void interval(long milliseconds, final IRxNext next) {
//        Observable.interval(milliseconds, TimeUnit.MILLISECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable disposable) {
//                        mDisposable = disposable;
//                    }
//
//                    @Override
//                    public void onNext(@NonNull Long number) {
//                        if (next != null) {
//                            next.doNext(number);
//                        }
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
//
//
//    /**
//     * 取消订阅
//     */
//    public static void cancel() {
//        if (mDisposable != null && !mDisposable.isDisposed()) {
//            mDisposable.dispose();
//            Logger.e("====Rx定时器取消======");
//        }
//    }
//
//    public interface IRxNext {
//        void doNext(long number);
//    }
//}
