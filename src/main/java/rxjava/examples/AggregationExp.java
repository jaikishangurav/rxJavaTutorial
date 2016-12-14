package rxjava.examples;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;

public class AggregationExp {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Count operation for counting values emitted****");
        Observable<Integer> countObs = Observable.range(0, 5);
        countObs.subscribe(new PrintSubscriber("Values"));
        countObs.count().subscribe(new PrintSubscriber("Count"));

        System.out.println("\nFirst operation for getting first value that matches predicate****");
        Observable<Integer> firstObs = Observable.range(0, 5);
        firstObs.first(v -> v > 3).subscribe(new PrintSubscriber("Values"));

        System.out.println("\nExample of reduce operation****");
        Observable<Integer> reduceObs = Observable.range(0, 5);
        firstObs.reduce((i1, i2) -> i1 + i2).subscribe(new PrintSubscriber("Sum"));
        firstObs.reduce((i1, i2) -> i1 < i2 ? i1 : i2).subscribe(new PrintSubscriber("Min"));

        Observable<String> reduceObs1 = Observable.just("Rx", "is", "easy");
        reduceObs1.reduce(0, (acc, next) -> acc + 1).subscribe(new PrintSubscriber("Count"));

        // Scan operation
        System.out.println("\nExample of scan operation****");
        Observable<Integer> scanObs = Observable.range(0, 5);
        scanObs.scan((i1, i2) -> i1 + i2).subscribe(new PrintSubscriber("Sum"));

        Subject<Integer, Integer> scanSubject = ReplaySubject.create();
        scanSubject.subscribe(new PrintSubscriber("Values"));
        scanSubject.scan((i1, i2) -> i1 < i2 ? i1 : i2).distinctUntilChanged().subscribe(new PrintSubscriber("Min"));
        scanSubject.onNext(2);
        scanSubject.onNext(3);
        scanSubject.onNext(1);
        scanSubject.onNext(4);
        scanSubject.onCompleted();

        System.out.println("\nFrom aggregation to collections****");
        System.out.println("Converting Observable to List****");
        Observable<Integer> listObs = Observable.range(0, 5);
        listObs.reduce(new ArrayList<Integer>(), (acc, value) -> {
            acc.add(value);
            return acc;
        }).subscribe(v -> System.out.println(v));

        System.out.println("\nUsing collect to convert Observable to list****");
        Observable<Integer> collectObs = Observable.range(10, 5);
        collectObs.collect(() -> new ArrayList<Integer>(), (list, value) -> list.add(value)).subscribe((list) -> System.out.println(list));

        System.out.println("\nConverting Observable to list using toList ****");
        Observable<Integer> toListObs = Observable.range(10, 5);
        collectObs.toList().subscribe(new PrintSubscriber("To List"));

        System.out.println("\nUsing sortedList to print list elements in descending order ****");
        Observable<Integer> sotedListObs = Observable.range(10, 5);
        sotedListObs.toSortedList((i1, i2) -> i2 - i1).subscribe(v -> System.out.println(v));

        System.out.println("\nConverting Observable to Map****");
        Observable<Person> mapObs = Observable.just(new Person("Will", 25), new Person("Nick", 40), new Person("Saul", 35));
        mapObs.toMap(person -> person.name, person -> person.age).subscribe((map) -> System.out.println(map));
    }

}

class PrintSubscriber extends Subscriber {
    private final String name;

    public PrintSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onCompleted() {
        System.out.println(name + ": Completed");
    }

    @Override
    public void onError(Throwable e) {
        System.out.println(name + ": Error: " + e);
    }

    @Override
    public void onNext(Object v) {
        System.out.println(name + ": " + v);
    }
}

class Person {
    public final String name;

    public final Integer age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
