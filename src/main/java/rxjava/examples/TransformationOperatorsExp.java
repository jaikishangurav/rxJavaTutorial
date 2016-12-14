package rxjava.examples;

import rx.Observable;

public class TransformationOperatorsExp {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Transformation example using map operator");
        Observable<Integer> mapObs = Observable.range(0, 4);
        mapObs.map(v -> v + 3).subscribe(new PrintSubscriber("Map"));

        System.out.println("\nConvert string to integer using map operator");
        Observable<String> mapObs1 = Observable.just("1", "2", "3", "4");
        mapObs1.map(Integer::parseInt).subscribe(new PrintSubscriber("Map"));

        System.out.println("\nTransformation example using flatmap operator");
        Observable<Integer> flatMapObs = Observable.range(1, 3);
        flatMapObs.flatMap(i -> Observable.range(0, i)).subscribe(new PrintSubscriber("FlatMap"));

        System.out.println("\nTransformation example using concatMap operator");
        Observable<Integer> concatMapObs = Observable.just(100, 150);
        concatMapObs.concatMap(i -> Observable.range(1, i).map(v -> i).take(3)).subscribe(System.out::println, System.out::println,
                () -> System.out.println("Completed"));

    }

}
