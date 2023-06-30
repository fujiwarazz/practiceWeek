package com.peels.utils;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Author peelsannaw
 * @create 30/06/2023 09:32
 */

@UtilityClass
public class FunctionUtil {

    public <T>Predicate<T> distinctByKey(Function<? super T,?>keyExa){
        Map<Object,Boolean> map = new HashMap<>();
        return t -> map.putIfAbsent(keyExa.apply(t),Boolean.TRUE) == null;
    }
}
