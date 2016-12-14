package rxjava.examples;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import rx.Observable;

public class CombiningSequencesExp {

    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO Auto-generated method stub
        System.out.println("Example of concat operator****");
        Observable<Integer> concatObs1 = Observable.range(1, 3);
        Observable<Integer> concatObs2 = Observable.range(10, 3);
        Observable.concat(concatObs1, concatObs2).subscribe(System.out::println);

        System.out.println("\nExample of merge operator****");
        Observable<Long> mergeObs1 = Observable.interval(2, TimeUnit.SECONDS);
        Observable<Long> mergeObs2 = Observable.interval(1, TimeUnit.SECONDS);
        Observable.merge(mergeObs1.map(i -> "First"), mergeObs2.map(i -> "Second")).take(6).subscribe(System.out::println);

        Thread.sleep(10000);

        System.out.println("\nExample of zip operator****");
        Observable.zip(Observable.interval(150, TimeUnit.MILLISECONDS).doOnNext(i -> System.out.println("Left emits:" + i)),
                Observable.interval(100, TimeUnit.MILLISECONDS).doOnNext(i -> System.out.println("right emits:" + i)),
                (i1, i2) -> i1 + "-" + i2).take(6).subscribe(System.out::println);

        Thread.sleep(10000);

        System.out.println("\nExample 2 of zip operator****");
        Observable
                .zip(Observable.interval(150, TimeUnit.MILLISECONDS), Observable.interval(100, TimeUnit.MILLISECONDS),
                        Observable.interval(050, TimeUnit.MILLISECONDS), (i1, i2, i3) -> i1 + "-" + i2 + "-" + i3)
                .take(6).subscribe(System.out::println);

        Thread.sleep(10000);

    }

}
