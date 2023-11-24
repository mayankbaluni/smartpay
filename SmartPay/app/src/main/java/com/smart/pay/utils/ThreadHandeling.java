package com.smart.pay.utils;

/**
 * Created by Sandeep Londhe on 31-08-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */
public class ThreadHandeling implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
    }
}

