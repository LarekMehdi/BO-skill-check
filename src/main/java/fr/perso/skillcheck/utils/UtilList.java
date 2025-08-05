package fr.perso.skillcheck.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class UtilList {
    
    /*
     * COLLECT
     */

    public static <U, V> List<V> collect(List<U> list, Function<? super U, ? extends V> t) {
        if (list == null || t == null) return null;
        List<V> list1 = new ArrayList<>();
        for (U u : list)
            list1.add(u != null ? t.apply(u) : null);
        return list1;
    }

    public static <U, V> List<V> collect(List<U> list, Function<? super U, ? extends V> t, Predicate<? super U> f) {
        if (list == null || t == null) return null;
        List<V> list1 = new ArrayList<>();
        for (U u : list)
            if (f != null && f.test(u)) list1.add(u != null ? t.apply(u) : null);
        return list1;
    }
}
