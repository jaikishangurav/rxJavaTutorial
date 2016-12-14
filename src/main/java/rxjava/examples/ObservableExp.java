package rxjava.examples;

import java.util.concurrent.FutureTask;

import rx.Observable;
import rx.Subscription;

public class ObservableExp {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Example of Observable.just()****");
        Observable<String> justObs = Observable.just("one", "two", "three", "four");
        justObs.subscribe(v -> System.out.println("Received:" + v), e -> System.out.println(e), () -> System.out.println("Completed"));

        System.out.println("\nExample of Observable.create()****");
        Observable<String> createdObs = Observable.create(o -> {
            o.onNext("Hello");
            o.onCompleted();
        });
        createdObs.subscribe(System.out::println, e -> System.out.println(e), () -> System.out.println("Completed"));

        System.out.println("\nConverting FutureTask into Observable****");
        FutureTask<Integer> task = new FutureTask<>(() -> {
            Thread.sleep(3000);
            return 28;
        });
        new Thread(task).start();

        Observable<Integer> futureObs = Observable.from(task);
        Subscription subscription = futureObs.subscribe(v -> System.out.println("Received :" + v), e -> System.out.println("Error :" + e),
                () -> System.out.println("Completed"));

        System.out.println("\nConverting Integer array into Observable****");
        Integer[] in = new Integer[] { 1, 2, 3 };

        Observable<Integer> integerObs = Observable.from(in);
        Subscription arraySub = integerObs.subscribe(v -> System.out.println("Received :" + v), e -> System.out.println("Error :" + e),
                () -> System.out.println("Completed"));
    }

}
