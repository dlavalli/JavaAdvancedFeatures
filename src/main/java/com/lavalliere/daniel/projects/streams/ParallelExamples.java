package com.lavalliere.daniel.projects.streams;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;
import org.springframework.core.annotation.SynthesizingMethodParameter;

import java.net.PasswordAuthentication;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@IsDemoable
public class ParallelExamples  implements Demoable {

    public ParallelExamples convertListToParallelStreamAndPrintElements() {
        System.out.println("convertListToParallelStreamAndPrintElements");
        List.of(1,2,3,4,5).parallelStream().forEachOrdered(System.out::println);
        return this;
    }

    public ParallelExamples compareExecutionTimeBetweenSequentialAndParallelStreams() {
        System.out.println("compareExecutionTimeBetweenSequentialAndParallelStreams");
        /*
            Feature                 forEach()                       forEachOrdered
            Sequential stream       Process elements in order       Process elements in order
            Parallel stream         Non-deterministic, order is     Deterministic; strictly preserve
                                    not guaranteed                  source order
            Performance overhead    Minimal; highly efficient       High in parallel streams due to
                                                                    thread coordination
        */

        List<Integer> intSource = IntStream.rangeClosed(1, 10000000).boxed().toList();

        Instant startInstant = Instant.now();
        intSource.stream().map(n -> n+1).forEach(n -> {}); // Noop
        Instant endInstant = Instant.now();
        System.out.println("Millis in sequential execution: " + Duration.between(startInstant, endInstant).toMillis());

        startInstant = Instant.now();
        intSource.parallelStream().map(n -> n+1).forEachOrdered(n -> {}); // Noop
        endInstant = Instant.now();
        System.out.println("Millis in parallel execution: " + Duration.between(startInstant, endInstant).toMillis());

        return this;
    }

    public ParallelExamples sumElementsWithParallelStream() {
        System.out.println("sumElementsWithParallelStream");
        List<Integer> intSource = IntStream.rangeClosed(1, 1000000).boxed().toList();
        long sum = intSource.parallelStream().mapToLong(Integer::longValue).sum();
        System.out.println("Sum of elements in parallel stream: " + sum);
        return this;
    }

    public ParallelExamples averageTimeSortingListSequentiallyAndParallel() {
        System.out.println("averageTimeSortingListSequentiallyAndParallel");

        // Generate list to test sorting on
        List<Integer> intSource = ThreadLocalRandom.current()
            .ints(1000000, 1, 1000000)
            .boxed()
            .collect(Collectors.toList());

        /*
           List<Integer> numbers = new ArrayList<>();
           Random random = new Random();
           for(int i=0; i < 1000000; i++) {
                numbers.add(random.nestInt(1000000));
           }
         */

        long totalTime = 0;
        Instant startInstant = null;
        Instant endInstant = null;

        for(int i = 0; i < 10; i++) {
            startInstant = Instant.now();
            intSource.stream().sorted().forEach(n -> {}); // Noop
            endInstant = Instant.now();
            totalTime += Duration.between(startInstant, endInstant).toNanos();
        }
        double averageTime = totalTime / (double)10;
        System.out.println("Average millis sorting with sequential execution: " + averageTime);

        totalTime = 0;
        for(int i = 0; i < 10; i++) {
            startInstant = Instant.now();
            intSource.parallelStream().sorted().forEachOrdered(n -> {}); // Noop
            endInstant = Instant.now();
            totalTime += Duration.between(startInstant, endInstant).toNanos();
        }
        averageTime = totalTime / (double)10;
        System.out.println("Average millis sorting with parallel execution: " + averageTime);

        return this;
    }

    public ParallelExamples filterEvenNumbersWithParallelStream() {
        System.out.println("filterEvenNumbersWithParallelStream");
        List.of(1,2,3,4,5,6,7,8,9,10).parallelStream().filter(n -> n%2==0).forEachOrdered(System.out::println);
        return this;
    }

    @Override
    public void demo() {
        new ParallelExamples()
            .convertListToParallelStreamAndPrintElements()
            .compareExecutionTimeBetweenSequentialAndParallelStreams()
            .sumElementsWithParallelStream()
            .averageTimeSortingListSequentiallyAndParallel()
            .filterEvenNumbersWithParallelStream()
        ;
    }
}
