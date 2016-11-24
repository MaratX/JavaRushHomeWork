package com.home.stream;

import java.util.List;

/**
 * Created by HMF on 23.11.2016.
 */
public class SumGetOddNamber
{

    // Old method
    public int SumOddNamber(List<Integer> collections){
        int sum = 0;
        for(int i : collections){
            if(i % 2 != 0){
                sum += i;
            }
        }
        return sum;
    }

    //New method 'Stream'
    public int SumOddNamberStream(List<Integer> collections){
        return collections.stream().filter(o -> o % 2 != 0).reduce((s1, s2) -> s1 + s2).orElse(0);
    }
 }
