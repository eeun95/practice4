package chap07;

public class ExeTimeCalculator implements Calculator{

    private Calculator delegate;

    // 객체를 생성자를 통해 받아와서
    public ExeTimeCalculator(Calculator delegate) {
        this.delegate = delegate;
    }

    @Override
    public long factorial(long num) {
        long start = System.nanoTime();
        // 핵심 기능은 다른 객체에 위임함
        long result = delegate.factorial(num);
        long end = System.nanoTime();
        System.out.printf("%s.factorial(%d) 실행 시간 = %d\n",
                delegate.getClass().getSimpleName(),
                num, (end - start));
        return result;
    }
}
