package com.echo.configcenter.utils;

public class MyThread extends Thread{
    public void startP(){
        super.start();
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
    }


}
