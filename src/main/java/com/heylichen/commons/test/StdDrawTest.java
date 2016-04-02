package com.heylichen.commons.test;

import algorithms.StdDraw;
import algorithms.StdRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by lc on 2016/4/2.
 */
public class StdDrawTest {
    private Logger logger = LoggerFactory.getLogger(StdDrawTest.class);
    public  static void main(String[] args){
        sortedArrayOfRandomValues();
    }
    public static void functionValues(){
        int N = 100 ;

        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N*N);
        StdDraw.setPenRadius(.01);
        for (int i = 1; i <= N; i++)
        {
            StdDraw.point(i, i);
            StdDraw.point(i, i*i);
            StdDraw.point(i, i*Math.log(i));
        }



        N = 50;
        double[] a = new double[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.random();
        for (int i = 0; i < N; i++)
        {
            double x = 1.0*i/N;
            double y = a[i]/2.0;
            double rw = 0.5/N;
            double rh = a[i]/2.0;
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }
public static void arrayOfRandomValues(){
    int N = 50;
    double[] a = new double[N];
    for (int i = 0; i < N; i++)
        a[i] = StdRandom.random();
    for (int i = 0; i < N; i++)
    {
        double x = 1.0*i/N;
        double y = a[i]/2.0;
        double rw = 0.5/N;
        double rh = a[i]/2.0;
        StdDraw.filledRectangle(x, y, rw, rh);
    }
}
    public static void sortedArrayOfRandomValues(){
        int N = 50;
        double[] a = new double[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.random();
        Arrays.sort(a);
        for (int i = 0; i < N; i++)
        {
            double x = 1.0*i/N;
            double y = a[i]/2.0;
            double rw = 0.5/N;
            double rh = a[i]/2.0;
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }


}
