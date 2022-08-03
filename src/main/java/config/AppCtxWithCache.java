package config;

import aspect.CacheAspect;
import aspect.ExeTimeAspect;
import chap07.Calculator;
import chap07.RecCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
// @Aspect 어노테이션을 붙인 클래스를 공통 기능으로 적용하기 위해 붙여줌
// 이 어노테이션을 추가하면 스프링은 @Aspect 어노테이션이 붙은 빈 객체를 찾아서 빈 객체의 @Pointcut 설정과 @Around 설정 사용
// @EnableAspectJAutoProxy

// 빈 객체가 인터페이스를 상속할 때 인터페이스가 아닌 클래스를 이용해서 프록시를 생성하고 싶다면
// proxyTargetClass 속성을 true로 지정하면 됨
@EnableAspectJAutoProxy(proxyTargetClass = true)

public class AppCtxWithCache {

    @Bean
    public ExeTimeAspect exeTimeAspect() {
        return new ExeTimeAspect();
    }

    @Bean
    // AOP 적용 시 RecCalculator가 상속받은 Calculator 인터페이스를 이용해서 프록시 생성
    public Calculator calculator() {
        return new RecCalculator();
    }

    @Bean
    public CacheAspect cacheAspect() {
        return new CacheAspect();
    }

}
