package rxjava.examples;

import rx.Observable;

public class RxOperatorExp {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Applying filter operation to filter even numbers***");
        Observable<Integer> intObs = Observable.range(0, 10);
        intObs.filter(v -> v % 2 == 0).subscribe(v -> System.out.println("Received :" + v), e -> System.out.println("Error :" + e),
                () -> System.out.println("Completed"));

        System.out.println("\nApplying distinct operation to based on key***");
        Observable<String> distinctObs = Observable.create(o -> {
            o.onNext("first");
            o.onNext("second");
            o.onNext("third");
            o.onNext("fourth");
            o.onNext("fifth");
        });
        distinctObs.distinct(v -> v.charAt(0)).subscribe(v -> System.out.println("Received :" + v), e -> System.out.println("Error :" + e),
                () -> System.out.println("Completed"));

        System.out.println("\ntake operation accepts only values specidied in take(n) call***");
        Observable<Integer> takeObs = Observable.range(0, 5);
        takeObs.take(3).subscribe(v -> System.out.println("Received :" + v), e -> System.out.println("Error :" + e),
                () -> System.out.println("Completed"));

        System.out.println("\nskip operation skips  values specidied in skip(n) call***");
        Observable<Integer> skipObs = Observable.range(0, 5);
        takeObs.skip(3).subscribe(v -> System.out.println("Received :" + v), e -> System.out.println("Error :" + e),
                () -> System.out.println("Completed"));
    }

}
