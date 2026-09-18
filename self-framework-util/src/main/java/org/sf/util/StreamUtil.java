package org.sf.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream流工具类
 *
 * @author zhuxiao
 */
@UtilityClass
public class StreamUtil {

    public static <E> Optional<E> firstOpt(Collection<E> eCollection) {
        Objects.requireNonNull(eCollection);
        return eCollection.stream().findFirst();
    }

    public static <E> E first(Collection<E> eCollection) {
        return firstOpt(eCollection).orElse(null);
    }

    public static <E> E any(Collection<E> eCollection, Supplier<E> supplier) {
        return anyOpt(eCollection).orElse(supplier.get());
    }

    public static <E> E any(Collection<E> eCollection, Predicate<E> predicate) {
        Objects.requireNonNull(eCollection);
        return eCollection.stream().filter(predicate).findAny().orElse(null);
    }

    public static <E> boolean anyMatch(Collection<E> eCollection, Predicate<E> predicate) {
        return eCollection.stream().anyMatch(predicate);
    }

    public static <E> Optional<E> anyOpt(Collection<E> eCollection) {
        Objects.requireNonNull(eCollection);
        return eCollection.stream().findAny();
    }

    public static <E> void forEach(Stream<E> stream, Predicate<E> filter, Consumer<E> consumer) {
        Objects.requireNonNull(stream);
        Objects.requireNonNull(filter);
        Objects.requireNonNull(consumer);
        stream.filter(filter).forEach(consumer);
    }

    public static <E> Integer sumInteger(Collection<E> eCollection, Function<E, Integer> function) {
        return reduce(eCollection, 0, function, Integer::sum);
    }

    public static <E> BigDecimal sum(Collection<E> eCollection, Function<E, BigDecimal> function) {
        Objects.requireNonNull(eCollection);
        Objects.requireNonNull(function);
        return reduce(eCollection, BigDecimal.ZERO, function, BigDecimal::add);
    }

    public static <E> BigDecimal sumFilter(Collection<E> eCollection, Predicate<E> filter, Function<E, BigDecimal> function) {
        Objects.requireNonNull(eCollection);
        Objects.requireNonNull(filter);
        Objects.requireNonNull(function);
        return eCollection.stream().filter(filter).map(function).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static <E, N extends Number> N reduce(Collection<E> eCollection, N init, Function<E, N> function, BinaryOperator<N> accumulator) {
        Objects.requireNonNull(eCollection);
        Objects.requireNonNull(function);
        return eCollection.stream().map(function).reduce(init, accumulator);
    }

    public static <E, K, N extends Number> Map<K, N> toMap(Collection<E> eCollection, Function<E, K> keyMapper, Function<E, N> valueMapper, BinaryOperator<N> accumulator) {
        Objects.requireNonNull(eCollection);
        Objects.requireNonNull(keyMapper);
        Objects.requireNonNull(valueMapper);
        Objects.requireNonNull(accumulator);
        return eCollection.stream().collect(Collectors.toMap(keyMapper, valueMapper, accumulator));
    }

    public static <E, K> Map<K, E> toMap(Stream<E> stream, Function<? super E, ? extends K> keyMapper) {
        Objects.requireNonNull(stream);
        Objects.requireNonNull(keyMapper);
        return stream.collect(Collectors.toMap(keyMapper, Function.identity()));
    }

    public static <E, K, V> Map<K, V> toMap(Collection<E> eCollection, Function<? super E, ? extends K> keyMapper, Function<? super E, ? extends V> valueMapper) {
        Objects.requireNonNull(eCollection);
        Objects.requireNonNull(keyMapper);
        Objects.requireNonNull(valueMapper);
        return eCollection.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public static <E, K> Map<K, E> toMap(Collection<E> eCollection, Function<? super E, ? extends K> keyMapper) {
        Objects.requireNonNull(eCollection);
        Objects.requireNonNull(keyMapper);
        return toMap(eCollection.stream(), keyMapper);
    }

    public static <E> void forEach(Collection<E> eCollection, Consumer<E> consumer) {
        Objects.requireNonNull(eCollection);
        forEach(eCollection.stream(), e -> true, consumer);
    }

    public static <E> List<E> filter(Collection<E> collection, Predicate<E> filter) {
        Objects.requireNonNull(collection);
        Objects.requireNonNull(filter);
        return collection.stream().filter(filter).toList();
    }


    public static <E, K> Map<K, List<E>> group(Collection<E> collection, Function<E, K> keyFunction) {
        Objects.requireNonNull(collection);
        Objects.requireNonNull(keyFunction);
        return collection.stream().collect(Collectors.groupingBy(keyFunction));
    }

    public static <E, T> List<T> map(Collection<E> collection, Function<E, T> keyFunction) {
        Objects.requireNonNull(collection);
        Objects.requireNonNull(keyFunction);
        return collection.stream().map(keyFunction).toList();
    }

    public static <E, T> List<T> map2(Collection<E> collection, Function<E, T> keyFunction) {
        Objects.requireNonNull(collection);
        Objects.requireNonNull(keyFunction);
        return collection.stream().map(keyFunction).collect(Collectors.toCollection(ArrayList::new));
    }

    public static <E, T> List<T> map(Collection<E> collection, Predicate<E> filter ,Function<E, T> etFunction) {
        Objects.requireNonNull(collection);
        Objects.requireNonNull(etFunction);
        Objects.requireNonNull(filter);
        return collection.stream().filter(filter).map(etFunction).toList();
    }
}
