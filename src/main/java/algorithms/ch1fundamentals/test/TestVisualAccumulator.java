package algorithms.ch1fundamentals.test;

import algorithms.ch1fundamentals.VisualAccumulator;
import algorithms.ch1fundamentals.impl.VisualAccumulatorImpl;
import algorithms.commons.lib.StdOut;
import algorithms.commons.lib.StdRandom;

public class TestVisualAccumulator
{
public static void main(String[] args)
{
int T = Integer.parseInt(args[0]);
VisualAccumulator a = new VisualAccumulatorImpl(T, 1.0);
for (int t = 0; t < T; t++)
    a.addDataValue(StdRandom.random());
StdOut.println(a);
}
}