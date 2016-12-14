package rxjava.examples;

import java.util.concurrent.TimeUnit;

import rx.Observable;

public class BlockingObservableExp {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Example of non blocking observable****");
        Observable<Long> nonBlockingObs = Observable.interval(100, TimeUnit.MILLISECONDS);
        nonBlockingObs.take(5).forEach(v -> System.out.println("Non blocking:" + v));
        System.out.println("Subscribed Non blocking");

        System.out.println("\nExample of blocking observable****");
        Observable<Long> blockingObs = Observable.interval(100, TimeUnit.MILLISECONDS);
        blockingObs.take(5).toBlocking().forEach(v -> System.out.println("Blocking:" + v));
        System.out.println("Subscribed blocking");

        System.out.println("\nExample of first operator****");
        Observable<Long> firstObs = Observable.interval(100, TimeUnit.MILLISECONDS);
        Long firstVal = firstObs.take(5).toBlocking().first(i -> i > 2);
        System.out.println(firstVal);

        System.out.println("\nExample of last operator****");
        Observable<Long> lastObs = Observable.interval(100, TimeUnit.MILLISECONDS);
        Long lastVal = lastObs.take(5).toBlocking().last(i -> i < 4);
        System.out.println(lastVal);

        System.out.println("\nExample of single operator****");
        Observable<Integer> singleObs = Observable.just(10);
        Integer singleVal = singleObs.toBlocking().single();
        System.out.println(singleVal);
    }

}
