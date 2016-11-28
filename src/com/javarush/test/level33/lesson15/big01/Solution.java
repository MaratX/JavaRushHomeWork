package com.javarush.test.level33.lesson15.big01;



import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by HMF on 28.11.2016.
 */
public class Solution
{

    public Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> result = new HashSet<>();
        for(String s : strings) {
            result.add(shortener.getId(s));
        }
        return result;
    }
    public Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> result = new HashSet<>();
        for(Long key : keys) {
            result.add(shortener.getString(key));
        }
        return result;
    }
    public void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> testStrings = new HashSet<>();
        for (long i = 0; i < elementsNumber; i++) {
            testStrings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        long t1 = new Date().getTime();
        Set<Long> keys = getIds(shortener, testStrings);
        Helper.printMessage(String.valueOf(new Date().getTime() - t1));
        long t2 = new Date().getTime();
        Set<String> values = getStrings(shortener, keys);
        Helper.printMessage(String.valueOf(new Date().getTime() - t2));
        if (values.equals(testStrings)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }
    public static void main(String[] args) {
        new Solution().testStrategy(new HashMapStorageStrategy(), 10000L);
    }
}
