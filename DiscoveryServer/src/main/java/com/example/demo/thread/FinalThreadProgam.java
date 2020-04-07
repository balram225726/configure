package com.example.demo.thread;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FinalThreadProgam {

	List<Boolean>checkThreadFlag;
	int max;
	
	FinalThreadProgam(int max,int numberThread){
		this.max = max;
		
		this.checkThreadFlag = IntStream.range(0,numberThread).mapToObj(i->i==0?Boolean.TRUE:Boolean.FALSE).collect(Collectors.toList());
	}
	
	public synchronized void printSequence(int currentThread,AtomicInteger number) {
		
		//System.out.println("this is boolean value here--->"+checkThreadFlag.get(currentThread));
		while(!checkThreadFlag.get(currentThread)) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(number.get()+" "+max);
		if(number.get()<=max) {
			System.out.println(Thread.currentThread().getName()+" : "+number.get());
			number.incrementAndGet();
		}
		checkThreadFlag.set(currentThread,Boolean.FALSE);
		checkThreadFlag.set(currentThread==checkThreadFlag.size()-1?0:currentThread+1, Boolean.TRUE);
		notifyAll();
	}
	
	
	public static void main(String args[]) {
		
		int max =20;
		int numberOfThread = 2;
		FinalThreadProgam th =  new FinalThreadProgam(max,numberOfThread);
		AtomicInteger atomic = new AtomicInteger(1);
		List<Runnable>task = IntStream.range(0, numberOfThread).mapToObj(i->task(i,atomic,max,th)).collect(Collectors.toList());
	
		ExecutorService executor = Executors.newFixedThreadPool(numberOfThread);
		for(int i=0;i<task.size();i++) {
			//System.out.println("executor is running now-->"+task.get(i));
			executor.submit(task.get(i));
		}
		executor.shutdown();	
	}
	
	public static Runnable task(int currentThread,AtomicInteger number,int max,FinalThreadProgam print) {
		
		return()->{
			//System.out.println("getting the number here--->"+number.get()+" "+max);
			while(number.get()<=max) {
				print.printSequence(currentThread,number);
			}
		};
	}
	
}
