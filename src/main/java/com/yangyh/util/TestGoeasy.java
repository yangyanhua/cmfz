package com.yangyh.util;

import com.alibaba.fastjson.JSONObject;
import io.goeasy.GoEasy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestGoeasy {
    @Test
      public void tewsr(){
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io/publish", "BC-af7eea05933942ecab30ec68d1a08c93");
        goEasy.publish("my_channel", "Hello, GoEasy!");
    }

    @Test
    public void tew2() throws InterruptedException {
        while (true){
            List<Integer> list = new ArrayList<>();
            //创建生成随机数对象
            Random random = new Random();
            for(int i =0;i<6;i++){
                int i1 = random.nextInt(1000);
                list.add(i1);
            }
            JSONObject object = new JSONObject();
            object.put("data",list);
            String s = JSONObject.toJSONString(object);
            GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io/publish", "BC-af7eea05933942ecab30ec68d1a08c93");
            goEasy.publish("my_channel", s);
            Thread.sleep(5000);
        }
    }
}
