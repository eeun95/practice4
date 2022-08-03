package main;

import chap07.Calculator;
import chap07.RecCalculator;
import config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAspect {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        System.out.println("1");
        // calculator 빈의 실제 타입은 Calculator를 상속한 프록시 타입이므로 RecCalculator로 받으면 타입 변환이 불가하여 익셉션 발생
        //Calculator cal = ctx.getBean(Calculator.class);
        RecCalculator cal = ctx.getBean("calculator", RecCalculator.class);
        System.out.println("2");
        long fiveFact = cal.factorial(5);
        System.out.println("cal.factorial(5) = " + fiveFact);
        System.out.println(cal.getClass().getName());
        ctx.close();
    }
}
