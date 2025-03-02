package edu.neu.coe.info6205.sort.par;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * TODO tidy it up a bit.
 */
class ParSort {

    public static int cutoff = 1000;

    public static void sort(int[] array, int from, int to) {
        if (to - from < cutoff) Arrays.sort(array, from, to);
        else {
            CompletableFuture<int[]> parsort1 = parsort(array, from, from + (to - from) / 2); // TO IMPLEMENT
            CompletableFuture<int[]> parsort2 = parsort(array, from + (to - from) / 2, to); // TO IMPLEMENT
            CompletableFuture<int[]> parsort = parsort1.thenCombine(parsort2, (xs1, xs2) -> {
                int[] result = new int[xs1.length + xs2.length];
                int i = 0, j = 0;
                for(int a = 0; a < result.length; a++) {
                	if(i >= xs1.length)
                		result[a] = xs2[j++];
                	else if(j >= xs2.length)
                		result[a] = xs1[i++];
                	else if(xs1[i] > xs2[j])
                		result[a] = xs2[j++];
                	else
                		result[a] = xs1[i++];
                }
                // TO IMPLEMENT
                return result;
            });

            parsort.whenComplete((result, throwable) -> System.arraycopy(result, 0, array, from, result.length));
     //      System.out.println("# threads: "+ Main.p.getRunningThreadCount());
            parsort.join();
        }
    }

    private static CompletableFuture<int[]> parsort(int[] array, int from, int to) {
        return CompletableFuture.supplyAsync(
                () -> {
                    int[] result = new int[to - from];
                    // TO IMPLEMENT
                    System.arraycopy(array, from, result, 0, result.length);
                    int mid = (to -from)/2;
                    if(array[mid] < array[mid+1]) return result;
                    else
                    sort(result, 0, to - from);
                    return result;
                }, Main.p
        );
    }
}