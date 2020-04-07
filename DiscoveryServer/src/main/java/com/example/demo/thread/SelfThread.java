package com.example.demo.thread;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SelfThread {
	
	int max;
	List<Boolean>booleanArray;
	
	public SelfThread(int max,int numberOfThread) {
		this.max = max;
		booleanArray = IntStream.range(0, numberOfThread).mapToObj(i->i==0?Boolean.TRUE:Boolean.FALSE).collect(Collectors.toList());
		
	}
	
	public synchronized void pringSequence(int max,int currentThread,AtomicInteger atomic) {
		
		while(!booleanArray.get(currentThread)){
			
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(atomic.get()<=max) {
			System.out.println(Thread.currentThread().getName()+" : "+atomic.get());
			atomic.incrementAndGet();
		}
		booleanArray.set(currentThread, Boolean.FALSE);
		booleanArray.set(currentThread==booleanArray.size()-1?0:currentThread+1, Boolean.TRUE);
		notifyAll();
	}
	
	public static void main(String args[]) {
		
		int max = 100;
		int totalNumberOfThread =5;
		
		SelfThread th = new SelfThread(max,totalNumberOfThread);
		AtomicInteger atomic =  new AtomicInteger(1);
		
		List<Runnable>taskTh = IntStream.range(0,totalNumberOfThread).mapToObj(i->task(i,max,atomic,th)).collect(Collectors.toList());
		
		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		for(int i=0;i<taskTh.size();i++) {
			executor.submit(taskTh.get(i));
		}
		executor.shutdown();
		
	}
	
	public static Runnable task(int currentThread,int max,AtomicInteger atomic,SelfThread obj) {
		return ()->{
			while(atomic.get()<=max) {
				obj.pringSequence(max, currentThread, atomic);
			}
		};
	}
	
}
