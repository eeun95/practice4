package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

// 공통 기능에 필요한 코드

@Aspect
public class ExeTimeAspect {

    @Pointcut("execution(public * chap07..*(..))")
    // 공통 기능을 적용할 대상 설정 - chap07 패키지와 그 하위 패키지에 위치한 타입의 public 메서드를 Pointcut으로 설정
    private void publicTarget() {
    }

    @Around("publicTarget()")
    // publicTarget() 메서드에 정의한 Pointcut에 공통 기능을 적용
    // 즉 chap07 패키지나 그 하위 패키지에 속한 빈 객체의 public 메서드에 @Around가 붙은 measure() 메서드를 적용
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        // ProceedingJoinPoint 파라미터는 프록시 대상 객체의 메서드를 호출할 때 사용

        long start = System.nanoTime();
        try {
            Object result = joinPoint.proceed();
            // proceed() 메서드를 사용하여 실제 대상 객체의 메서드를 호출
            // 이 메서드를 호출하면 대상 객체의 메서드가 실행되므로 이 코드 이전과 이후에 공통 기능을 위한 코드를 위치시키면 됨

            return result;
        } finally {
            long finish = System.nanoTime();
            Signature sig = joinPoint.getSignature();
            System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
                    joinPoint.getTarget().getClass().getSimpleName(),
                    sig.getName(), Arrays.toString(joinPoint.getArgs()),
                    (finish - start));

            // getSignature() - 호출한 메서드의 시그니처
            // getTarget() - 대상 객체
            // getArgs() - 인자목록
        }
    }
}
