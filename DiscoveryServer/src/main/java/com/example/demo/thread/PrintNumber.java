package com.example.demo.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class PrintNumber {
	
	public static void main(String args[]) {

		System.out.println("program is running now");
		
		int  max =20;
		Abc abc = new Abc(max);
		AtomicInteger atomic = new AtomicInteger(1);
			
		PrintSequenceRunnable one1 = new PrintSequenceRunnable("T1",abc,max,atomic);
		PrintSequenceRunnable two1 = new PrintSequenceRunnable("T2",abc,max,atomic);
		PrintSequenceRunnable three1 = new PrintSequenceRunnable("T3",abc,max,atomic);
		PrintSequenceRunnable four1 = new PrintSequenceRunnable("T4",abc,max,atomic);
		PrintSequenceRunnable five1 = new PrintSequenceRunnable("T5",abc,max,atomic);
		
		Thread one = new Thread(one1,"T1");
		Thread two = new Thread(two1,"T2");
		Thread three = new Thread(three1,"T3");
		Thread four = new Thread(four1,"T4");
		Thread five = new Thread(five1,"T5");

		one.start();
		two.start();
		three.start();
		four.start();
		five.start();

	}
}
class PrintSequenceRunnable implements Runnable {

	public int maxNumber ;
	public Abc abc;
	String threadName;
	AtomicInteger atomic;
	

	PrintSequenceRunnable(String name,Abc abc,int maxNumber,AtomicInteger atomic) {
		
		this.abc=abc;
		this.threadName=name;
		this.maxNumber=maxNumber;
		this.atomic=atomic;
		
	}

	@Override
	public void run() {
		
		if("T1".equalsIgnoreCase(threadName)) {
			while(atomic.get()<=maxNumber) {
				abc.firstThread(atomic);
			}
		}
		else if("T2".equalsIgnoreCase(threadName)) {
			while(atomic.get()<=maxNumber) {
				abc.secondThread(atomic);
			}
		}
		else if("T3".equalsIgnoreCase(threadName)) {
			while(atomic.get()<=maxNumber) {
				abc.thirdThread(atomic);
			}
		}
		else if("T4".equalsIgnoreCase(threadName)) {
			while(atomic.get()<=maxNumber) {
				abc.fourThread(atomic);
			}
		}
		if("T5".equalsIgnoreCase(threadName)) {
			while(atomic.get()<=maxNumber) {
				abc.fiveThread(atomic);
			}
		}
		
	}
}


class Abc{
	
	boolean flag1=true;
	boolean flag2= false;
	boolean flag3=false;
	boolean flag4=false;
	boolean flag5=false;
	int max;
	public Abc(int max) {
		this.max=max;
	}
	
	public synchronized void firstThread(AtomicInteger number) {
		while(!flag1) {
			try {
				wait();
				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		flag1=false;
		flag2 = true;
		notifyAll();
		if(number.get()<=max) {
			System.out.println(Thread.currentThread().getName()+" "+number.get());
			number.incrementAndGet();
		}
	}
	
	public synchronized void secondThread(AtomicInteger number) {
		while(!flag2) {
			try {
				wait();
				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		flag2=false;
		flag3 = true;
		notifyAll();
		if(number.get()<=max) {
			System.out.println(Thread.currentThread().getName()+" "+number.get());
			number.incrementAndGet();
		}
	}
	
	public synchronized void thirdThread(AtomicInteger number) {
		while(!flag3) {
			try {
				wait();
				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		flag3=false;
		flag4 = true;
		notifyAll();
		if(number.get()<=max) {
			System.out.println(Thread.currentThread().getName()+" "+number.get());
			number.incrementAndGet();
		}
	}
	
	public synchronized void fourThread(AtomicInteger number) {
		while(!flag4) {
			try {
				wait();
				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		flag4=false;
		flag5 = true;
		notifyAll();
		if(number.get()<=max) {
			System.out.println(Thread.currentThread().getName()+" "+number.get());
			number.incrementAndGet();
		}
	}
	
	public synchronized void fiveThread(AtomicInteger number) {
		while(!flag5) {
			try {
				wait();
				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		flag5=false;
		flag1 = true;
		notifyAll();
		if(number.get()<=max) {
			System.out.println(Thread.currentThread().getName()+" "+number.get());
			number.incrementAndGet();
		}
	}
	
}
