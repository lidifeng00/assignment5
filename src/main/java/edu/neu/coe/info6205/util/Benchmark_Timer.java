/*
 * Copyright (c) 2018. Phasmid Software
 */

package edu.neu.coe.info6205.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import org.junit.Test;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.GenericSort;
import edu.neu.coe.info6205.sort.simple.InsertionSort;

import static edu.neu.coe.info6205.util.Utilities.formatWhole;

/**
 * This class implements a simple Benchmark utility for measuring the running time of algorithms.
 * It is part of the repository for the INFO6205 class, taught by Prof. Robin Hillyard
 * <p>
 * It requires Java 8 as it uses function types, in particular, UnaryOperator&lt;T&gt; (a function of T => T),
 * Consumer&lt;T&gt; (essentially a function of T => Void) and Supplier&lt;T&gt; (essentially a function of Void => T).
 * <p>
 * In general, the benchmark class handles three phases of a "run:"
 * <ol>
 *     <li>The pre-function which prepares the input to the study function (field fPre) (may be null);</li>
 *     <li>The study function itself (field fRun) -- assumed to be a mutating function since it does not return a result;</li>
 *     <li>The post-function which cleans up and/or checks the results of the study function (field fPost) (may be null).</li>
 * </ol>
 * <p>
 * Note that the clock does not run during invocations of the pre-function and the post-function (if any).
 *
 * @param <T> The generic type T is that of the input to the function f which you will pass in to the constructor.
 */
public class Benchmark_Timer<T> implements Benchmark<T> {

    /**
     * Calculate the appropriate number of warmup runs.
     *
     * @param m the number of runs.
     * @return at least 2 and at most m/10.
     */
    static int getWarmupRuns(int m) {
        return Integer.max(2, Integer.min(10, m / 10));
    }

    /**
     * Run function f m times and return the average time in milliseconds.
     *
     * @param supplier a Supplier of a T
     * @param m        the number of times the function f will be called.
     * @return the average number of milliseconds taken for each run of function f.
     */
    @Override
    public double runFromSupplier(Supplier<T> supplier, int m) {
        logger.info("Begin run: " + description + " with " + formatWhole(m) + " runs");
        // Warmup phase
        final Function<T, T> function = t -> {
            fRun.accept(t);
            return t;
        };
        new Timer().repeat(getWarmupRuns(m), supplier, function, fPre, null);

        // Timed phase
        return new Timer().repeat(m, supplier, function, fPre, fPost);
    }

    /**
     * Constructor for a Benchmark_Timer with option of specifying all three functions.
     *
     * @param description the description of the benchmark.
     * @param fPre        a function of T => T.
     *                    Function fPre is run before each invocation of fRun (but with the clock stopped).
     *                    The result of fPre (if any) is passed to fRun.
     * @param fRun        a Consumer function (i.e. a function of T => Void).
     *                    Function fRun is the function whose timing you want to measure. For example, you might create a function which sorts an array.
     *                    When you create a lambda defining fRun, you must return "null."
     * @param fPost       a Consumer function (i.e. a function of T => Void).
     */
    public Benchmark_Timer(String description, UnaryOperator<T> fPre, Consumer<T> fRun, Consumer<T> fPost) {
        this.description = description;
        this.fPre = fPre;
        this.fRun = fRun;
        this.fPost = fPost;
    }

    /**
     * Constructor for a Benchmark_Timer with option of specifying all three functions.
     *
     * @param description the description of the benchmark.
     * @param fPre        a function of T => T.
     *                    Function fPre is run before each invocation of fRun (but with the clock stopped).
     *                    The result of fPre (if any) is passed to fRun.
     * @param fRun        a Consumer function (i.e. a function of T => Void).
     *                    Function fRun is the function whose timing you want to measure. For example, you might create a function which sorts an array.
     */
    public Benchmark_Timer(String description, UnaryOperator<T> fPre, Consumer<T> fRun) {
        this(description, fPre, fRun, null);
    }

    /**
     * Constructor for a Benchmark_Timer with only fRun and fPost Consumer parameters.
     *
     * @param description the description of the benchmark.
     * @param fRun        a Consumer function (i.e. a function of T => Void).
     *                    Function fRun is the function whose timing you want to measure. For example, you might create a function which sorts an array.
     *                    When you create a lambda defining fRun, you must return "null."
     * @param fPost       a Consumer function (i.e. a function of T => Void).
     */
    public Benchmark_Timer(String description, Consumer<T> fRun, Consumer<T> fPost) {
        this(description, null, fRun, fPost);
    }

    /**
     * Constructor for a Benchmark_Timer where only the (timed) run function is specified.
     *
     * @param description the description of the benchmark.
     * @param f           a Consumer function (i.e. a function of T => Void).
     *                    Function f is the function whose timing you want to measure. For example, you might create a function which sorts an array.
     */
    public Benchmark_Timer(String description, Consumer<T> f) {
        this(description, null, f, null);
    }

    private final String description;
    private final UnaryOperator<T> fPre;
    private final Consumer<T> fRun;
    private final Consumer<T> fPost;

    final static LazyLogger logger = new LazyLogger(Benchmark_Timer.class);


@Test
public static void main(String[] args) {

	sortTest4();
	sortTest3();
	sortTest();
	sortTest2();
	sortTest5();
	sortTest6();
	sortTest7();
	sortTest8();
	sortTest9();
	sortTest10();
	sortTest11();
	sortTest12();
	sortTest13();
	sortTest14();
	sortTest15();
	sortTest16();
	sortTest17();
	sortTest18();
	sortTest19();
	sortTest20();
	
}

public static void sortTest() {
	final List<Integer> list = new ArrayList<>();
	int length =500;
	for(int i=0; i<length/2; i++) {
		int num= (int) (Math.random()*499);
		list.add(num);
	}
	for(int i=length/2; i<length/2; i++) {
		list.add(i);
	}
    Integer[] xs = list.toArray(new Integer[0]);
    InsertionSort is =new InsertionSort();
    Benchmark_Timer a=new Benchmark_Timer<>(
            "testWaitPeriods",
            t -> {
            	is.sort(xs);
            },null);
    double time= a.run(xs,1000);
    System.out.println("partially-ordered");
    System.out.println(time);
}
public static void sortTest2() {
	final List<Integer> list = new ArrayList<>();
	int length =500;
	for(int i=length; i>0; i--) {
		list.add(i);
	}
    Integer[] xs = list.toArray(new Integer[0]);
    InsertionSort is =new InsertionSort();
    Benchmark_Timer a=new Benchmark_Timer<>(
            "testWaitPeriods",
            t -> {
            	is.sort(xs);
            },null);
    double time= a.run(xs,1000);
    System.out.println("reverse-ordered");
    System.out.println(time);
}
public static void sortTest3() {
	final List<Integer> list = new ArrayList<>();
	int length =500;
	for(int i=0; i<length; i++) {
		list.add(i);
	}
    Integer[] xs = list.toArray(new Integer[0]);
    InsertionSort is =new InsertionSort();
    Benchmark_Timer a=new Benchmark_Timer<>(
            "testWaitPeriods",
            t -> {
            	is.sort(xs);
            },null);
    double time= a.run(xs,1000);
    System.out.println("ordered");
    System.out.println(time);
}
public static void sortTest4() {
	final List<Integer> list = new ArrayList<>();
	int length =500;
	for(int i=0; i<length; i++) {
		int num= (int) (Math.random()*999)+1;
		list.add(num);
	}
    Integer[] xs = list.toArray(new Integer[0]);
    InsertionSort is =new InsertionSort();
    Benchmark_Timer a=new Benchmark_Timer<>(
            "testWaitPeriods",
            t -> {
            	is.sort(xs);
            },null);
    double time= a.run(xs,1000);
    System.out.println("random");
    System.out.println(time);
}


public static void sortTest5() {
	final List<Integer> list = new ArrayList<>();
	int length =1000;
	for(int i=0; i<length; i++) {
		int num= (int) (Math.random()*1999)+1;
		list.add(num);
	}
    Integer[] xs = list.toArray(new Integer[0]);
    InsertionSort is =new InsertionSort();
    Benchmark_Timer a=new Benchmark_Timer<>(
            "testWaitPeriods",
            t -> {
            	is.sort(xs);
            },null);
    double time= a.run(xs,1000);
    System.out.println("random with 2 times");
    System.out.println(time);
}
public static void sortTest6() {
	final List<Integer> list = new ArrayList<>();
	int length =1000;
	for(int i=0; i<length; i++) {
		list.add(i);
	}
    Integer[] xs = list.toArray(new Integer[0]);
    InsertionSort is =new InsertionSort();
    Benchmark_Timer a=new Benchmark_Timer<>(
            "testWaitPeriods",
            t -> {
            	is.sort(xs);
            },null);
    double time= a.run(xs,1000);
    System.out.println("ordered with 2 times");
    System.out.println(time);
}
public static void sortTest7() {
	final List<Integer> list = new ArrayList<>();
	int length =1000;
	for(int i=0; i<length/2; i++) {
		int num= (int) (Math.random()*999);
		list.add(num);
	}
	for(int i=length/2; i<length; i++) {
		list.add(i);
	}
    Integer[] xs = list.toArray(new Integer[0]);
    InsertionSort is =new InsertionSort();
    Benchmark_Timer a=new Benchmark_Timer<>(
            "testWaitPeriods",
            t -> {
            	is.sort(xs);
            },null);
    double time= a.run(xs,1000);
    System.out.println("partially-ordered with 2 times");
    System.out.println(time);
}
public static void sortTest8() {
	final List<Integer> list = new ArrayList<>();
	int length =1000;
	for(int i=length; i>0; i--) {
		list.add(i);
	}
    Integer[] xs = list.toArray(new Integer[0]);
    InsertionSort is =new InsertionSort();
    Benchmark_Timer a=new Benchmark_Timer<>(
            "testWaitPeriods",
            t -> {
            	is.sort(xs);
            },null);
    double time= a.run(xs,1000);
    System.out.println("reverse-ordered with 2 times");
    System.out.println(time);
}
public static void sortTest9() {
	final List<Integer> list = new ArrayList<>();
	int length =2000;
	for(int i=0; i<length; i++) {
		int num= (int) (Math.random()*1999)+1;
		list.add(num);
	}
    Integer[] xs = list.toArray(new Integer[0]);
    InsertionSort is =new InsertionSort();
    Benchmark_Timer a=new Benchmark_Timer<>(
            "testWaitPeriods",
            t -> {
            	is.sort(xs);
            },null);
    double time= a.run(xs,1000);
    System.out.println("random with 2 times");
    System.out.println(time);
}
public static void sortTest10() {
	final List<Integer> list = new ArrayList<>();
	int length =2000;
	for(int i=0; i<length; i++) {
		list.add(i);
	}
    Integer[] xs = list.toArray(new Integer[0]);
    InsertionSort is =new InsertionSort();
    Benchmark_Timer a=new Benchmark_Timer<>(
            "testWaitPeriods",
            t -> {
            	is.sort(xs);
            },null);
    double time= a.run(xs,1000);
    System.out.println("ordered with 3 times");
    System.out.println(time);
}
public static void sortTest11() {
	final List<Integer> list = new ArrayList<>();
	int length =2000;
	for(int i=0; i<length/2; i++) {
		int num= (int) (Math.random()*999);
		list.add(num);
	}
	for(int i=length/2; i<length; i++) {
		list.add(i);
	}
    Integer[] xs = list.toArray(new Integer[0]);
    InsertionSort is =new InsertionSort();
    Benchmark_Timer a=new Benchmark_Timer<>(
            "testWaitPeriods",
            t -> {
            	is.sort(xs);
            },null);
    double time= a.run(xs,1000);
    System.out.println("partially-ordered with 3 times");
    System.out.println(time);
}
public static void sortTest12() {
	final List<Integer> list = new ArrayList<>();
	int length =2000;
	for(int i=length; i>0; i--) {
		list.add(i);
	}
    Integer[] xs = list.toArray(new Integer[0]);
    InsertionSort is =new InsertionSort();
    Benchmark_Timer a=new Benchmark_Timer<>(
            "testWaitPeriods",
            t -> {
            	is.sort(xs);
            },null);
    double time= a.run(xs,1000);
    System.out.println("reverse-ordered with 3 times");
    System.out.println(time);
}
public static void sortTest13() {
	final List<Integer> list = new ArrayList<>();
	int length =4000;
	for(int i=0; i<length; i++) {
		int num= (int) (Math.random()*1999)+1;
		list.add(num);
	}
    Integer[] xs = list.toArray(new Integer[0]);
    InsertionSort is =new InsertionSort();
    Benchmark_Timer a=new Benchmark_Timer<>(
            "testWaitPeriods",
            t -> {
            	is.sort(xs);
            },null);
    double time= a.run(xs,1000);
    System.out.println("random with 4 times");
    System.out.println(time);
}
public static void sortTest14() {
	final List<Integer> list = new ArrayList<>();
	int length =4000;
	for(int i=0; i<length; i++) {
		list.add(i);
	}
    Integer[] xs = list.toArray(new Integer[0]);
    InsertionSort is =new InsertionSort();
    Benchmark_Timer a=new Benchmark_Timer<>(
            "testWaitPeriods",
            t -> {
            	is.sort(xs);
            },null);
    double time= a.run(xs,1000);
    System.out.println("ordered with 4 times");
    System.out.println(time);
}
public static void sortTest15() {
	final List<Integer> list = new ArrayList<>();
	int length =4000;
	for(int i=0; i<length/2; i++) {
		int num= (int) (Math.random()*999);
		list.add(num);
	}
	for(int i=length/2; i<length; i++) {
		list.add(i);
	}
    Integer[] xs = list.toArray(new Integer[0]);
    InsertionSort is =new InsertionSort();
    Benchmark_Timer a=new Benchmark_Timer<>(
            "testWaitPeriods",
            t -> {
            	is.sort(xs);
            },null);
    double time= a.run(xs,1000);
    System.out.println("partially-ordered with 4 times");
    System.out.println(time);
}
public static void sortTest16() {
	final List<Integer> list = new ArrayList<>();
	int length =4000;
	for(int i=length; i>0; i--) {
		list.add(i);
	}
    Integer[] xs = list.toArray(new Integer[0]);
    InsertionSort is =new InsertionSort();
    Benchmark_Timer a=new Benchmark_Timer<>(
            "testWaitPeriods",
            t -> {
            	is.sort(xs);
            },null);
    double time= a.run(xs,1000);
    System.out.println("reverse-ordered with 4 times");
    System.out.println(time);
}
public static void sortTest17() {
	final List<Integer> list = new ArrayList<>();
	int length =8000;
	for(int i=0; i<length; i++) {
		int num= (int) (Math.random()*1999)+1;
		list.add(num);
	}
    Integer[] xs = list.toArray(new Integer[0]);
    InsertionSort is =new InsertionSort();
    Benchmark_Timer a=new Benchmark_Timer<>(
            "testWaitPeriods",
            t -> {
            	is.sort(xs);
            },null);
    double time= a.run(xs,1000);
    System.out.println("random with 5 times");
    System.out.println(time);
}
public static void sortTest18() {
	final List<Integer> list = new ArrayList<>();
	int length =8000;
	for(int i=0; i<length; i++) {
		list.add(i);
	}
    Integer[] xs = list.toArray(new Integer[0]);
    InsertionSort is =new InsertionSort();
    Benchmark_Timer a=new Benchmark_Timer<>(
            "testWaitPeriods",
            t -> {
            	is.sort(xs);
            },null);
    double time= a.run(xs,1000);
    System.out.println("ordered with 5 times");
    System.out.println(time);
}
public static void sortTest19() {
	final List<Integer> list = new ArrayList<>();
	int length =8000;
	for(int i=0; i<length/2; i++) {
		int num= (int) (Math.random()*999);
		list.add(num);
	}
	for(int i=length/2; i<length; i++) {
		list.add(i);
	}
    Integer[] xs = list.toArray(new Integer[0]);
    InsertionSort is =new InsertionSort();
    Benchmark_Timer a=new Benchmark_Timer<>(
            "testWaitPeriods",
            t -> {
            	is.sort(xs);
            },null);
    double time= a.run(xs,1000);
    System.out.println("partially-ordered with 5 times");
    System.out.println(time);
}
public static void sortTest20() {
	final List<Integer> list = new ArrayList<>();
	int length =8000;
	for(int i=length; i>0; i--) {
		list.add(i);
	}
    Integer[] xs = list.toArray(new Integer[0]);
    InsertionSort is =new InsertionSort();
    Benchmark_Timer a=new Benchmark_Timer<>(
            "testWaitPeriods",
            t -> {
            	is.sort(xs);
            },null);
    double time= a.run(xs,1000);
    System.out.println("reverse-ordered with 5 times");
    System.out.println(time);
}
}