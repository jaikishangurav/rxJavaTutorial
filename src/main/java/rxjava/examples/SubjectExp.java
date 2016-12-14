package rxjava.examples;

import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

public class SubjectExp {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Subscribe to Stream of integer using PublishSubject****");
        PublishSubject<Integer> publishSubject = PublishSubject.create();
        publishSubject.onNext(1);
        publishSubject.onNext(2);
        publishSubject.subscribe(System.out::println);
        publishSubject.onNext(3);
        publishSubject.onNext(4);

        // ReplaySubject
        System.out.println("\nSubscribe to Stream of integer using ReplaySubject****");
        ReplaySubject<Integer> replaySubject = ReplaySubject.create();
        replaySubject.subscribe(v -> System.out.println("Early : " + v));
        replaySubject.onNext(0);
        replaySubject.onNext(1);
        replaySubject.onNext(2);
        replaySubject.onNext(3);
        replaySubject.subscribe(v -> System.out.println("Late : " + v));
        replaySubject.onNext(4);

        System.out.println("\nSubscribe to Stream of integer using BehaviorSubject****");
        BehaviorSubject<Integer> behaviorSubject = BehaviorSubject.create();
        behaviorSubject.onNext(0);
        behaviorSubject.onNext(1);
        behaviorSubject.onNext(2);
        behaviorSubject.subscribe(System.out::println);
        behaviorSubject.onNext(3);
        behaviorSubject.onCompleted();
        behaviorSubject.subscribe(v -> System.out.println("Late: " + v), e -> System.out.println("Error"),
                () -> System.out.println("Completed"));

        System.out.println("\nSubscribe to Stream of integer using AsyncSubject****");
        AsyncSubject<Integer> asyncSubject = AsyncSubject.create();
        asyncSubject.subscribe(System.out::println);
        asyncSubject.onNext(0);
        asyncSubject.onNext(1);
        asyncSubject.onNext(2);
        asyncSubject.onCompleted();

    }

}
