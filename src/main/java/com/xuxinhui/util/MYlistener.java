package com.xuxinhui.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MYlistener implements ServletContextListener {
    private MyThread myThread;


    public void contextDestroyed(ServletContextEvent e) {

        if (myThread != null && myThread.isInterrupted()) {

            myThread.interrupt();

        }

    }


    public void contextInitialized(ServletContextEvent e) {

        String str = null;

        if (str == null && myThread == null) {

            myThread = new MyThread();

            myThread.start(); // servlet 上下文初始化时启动 socket

        }

    }

}


/**

 * 自定义一个 Class 线程类继承自线程类，重写 run() 方法，用于从后台获取并处理数据

 *

 * @author Champion.Wong

 *

 */

class MyThread extends Thread {

    public void run() {

        while (!this.isInterrupted()) {// 线程未中断执行循环

            try {

                Thread.sleep(2000); //每隔2000ms执行一次

            } catch (InterruptedException e) {

                e.printStackTrace();

            }


//           ------------------ 开始执行 ---------------------------

            System.out.println("____FUCK TIME:" + System.currentTimeMillis());

        }

    }

}