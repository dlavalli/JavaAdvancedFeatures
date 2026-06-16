package com.lavalliere.daniel.projects.streams;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.PrimitiveIterator;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@IsDemoable
public class PrimitiveExamples implements Demoable {

    public record MinMax(Double min, Double max) {}
    public record MinMaxOptional(Optional<Double> min, Optional<Double> max) {}

    public PrimitiveExamples generateRangeOfNumbersFrom1To100() {
         System.out.println("generateRangeOfNumbersFrom1To100");
        IntStream.rangeClosed(1, 10).forEach(System.out::println);
         return this;
    }

    public PrimitiveExamples convertIntListToIntStreamAndSum() {
        System.out.println("convertIntListToIntStreamAndSum");
        int sum = List.of(1,2,3,4,5).stream()
            .mapToInt(Integer::intValue)
            .sum();
        System.out.println("sum: " + sum);
        return this;
    }

    public PrimitiveExamples averageFromIntStream() {
        System.out.println("averageFromIntStream");
        Double total = IntStream.of(10,20,30,40,50).average().orElse(0.0);
        System.out.println("total: " + total);
        return this;
    }

    public PrimitiveExamples sumOf10FirstNaturalQuareNumbers() {
        System.out.println("sumOf10FirstNaturalQuareNumbers");
        int total = IntStream.of(1,2,3,4,5,6,7,8,9,10)
            .map(n -> n*n)
            .sum();
        System.out.println("total: " + total);
        return this;
    }

    public PrimitiveExamples findMinAndMaxOfDoubleStream() {
        System.out.println("findMinAndMaxOfDoubleStream");

        // Returns Double NOT Optional<Object>
        MinMax minmax = Stream.of(10.5, 20.3, 30.7, 40.1, 50.9)
            .collect(
                Collectors.teeing(
                    Collectors.reducing(Double.MAX_VALUE, Double::min),
                    Collectors.reducing(Double.MIN_VALUE, Double::max),
                    (MinMax::new)
                )
            );

        // Returns Optional<Object> NOT Double
        // MinMaxOptional minmax = Stream.of(10.5, 20.3, 30.7, 40.1, 50.9)
        //     .collect(
        //         Collectors.teeing(
        //             // Collectors.minBy(Double::compareTo),
        //             // Collectors.maxBy(Double::compareTo),
        //             // Collectors.minBy(Comparator.comparingDouble(Double::doubleValue)),
        //             // Collectors.maxBy(Comparator.comparingDouble(Double::doubleValue)),
        //             Collectors.minBy(Comparator.naturalOrder()),
        //             Collectors.maxBy(Comparator.naturalOrder()),
        //             (MinMaxOptional::new)
        //         )
        //     );
        System.out.println("minmax: " + minmax);
        return this;
    }

    @Override
    public void demo() {
        new PrimitiveExamples()
            .generateRangeOfNumbersFrom1To100()
            .convertIntListToIntStreamAndSum()
            .averageFromIntStream()
            .sumOf10FirstNaturalQuareNumbers()
            .findMinAndMaxOfDoubleStream()
        ;
    }
}
