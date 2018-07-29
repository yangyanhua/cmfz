package com.yangyh.util;

import com.yangyh.annotation.LogAnntation;
import com.yangyh.annotation.Logging;
import com.yangyh.entity.Admin;
import com.yangyh.entity.Journal;
import com.yangyh.service.JournalService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class Loging {
        @Autowired
        private JournalService journalService;


    //定义切点
    @Pointcut("execution(* com.xuxinhui.service.*.*(..))")
    public void pc(){}


    //@Around("pc()")//第一种方式：调用切点,或者直接在里面且切点表达式
    @Around("@annotation(com.yangyh.annotation.Logging)") //第二种 直接指定注解权限定名,那个方法用了此注解就能以切入点切入
    public Object Mylogging(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("来了吗日志记录？");
        //记录谁 在什么时间 做了什么
        String result="参数：";
        String ok="";
        String add="管理员： ";
        //获取谁 管理员
        ServletRequestAttributes requestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
       HttpSession session = requestAttributes.getRequest().getSession();
       Admin admin = (Admin) session.getAttribute("admin");
       if(admin!=null){
           System.out.println(admin.getName());
           add+=admin.getName();
        //那到前时间
       }
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:SS");
        String s = format.format(date);

      //  result+=s+" 时,做了:";
        //做了什么事？//调了什么方法？
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        //获取参数
        Object[] args = proceedingJoinPoint.getArgs();

        Logging log = method.getAnnotation(Logging.class);
        String value = log.value();
        //操作参数
        LogAnntation[] parameters = log.parameters();
        //result+=value+",操作,参数为：";
        if(args!=null){
            if(args.length>0){
                for (int i = 0; i < parameters.length; i++) {
                    LogAnntation parameter = parameters[i];
                    String s1 = String.valueOf(args[i]);
                    result +=parameters[i].value()+",值为"+s1+"";
                }

            }
        }


        //这件事的执行结果？没抛异常就成功,抛则失败
        try {
            Object proceed = proceedingJoinPoint.proceed();
             ok="执行成功";
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
             ok="执行失败";
            return null;
        }finally {
           Journal js = new Journal();
            js.setAdmin(add);
            js.setTime(new Date());
            js.setParameter(result);
            js.setAction(value);
            js.setResult(ok);
            journalService.Serviceadd(js);
            //把日志存入数据库
            System.out.println(result);
            System.out.println(add);
        }


    }
}


