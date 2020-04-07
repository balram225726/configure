package com.example.demo.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class PrintThird {

	public static void main(String args[]) {
		
		int max=20;
		Abcc abc = new Abcc(max);
		AtomicInteger atomic = new AtomicInteger(1);
		
		Runnable one = ()->{
			while(atomic.get()<=max) {
			abc.first(atomic);}
		};
		Runnable two = ()->{
			while(atomic.get()<=max) {
			abc.second(atomic);}
		};
		
		Runnable th = ()->{
			while(atomic.get()<=max) {
			abc.third(atomic);
			}
		};
		
		Runnable  four = ()->{
			while(atomic.get()<=max) {
			abc.four(atomic);}
		};
		
		Runnable  fiv = ()->{
			while(atomic.get()<=max) {
			abc.five(atomic);
			}
		};
		
		Thread onee = new Thread(one);
		onee.start();

		Thread twoo = new Thread(two);
		twoo.start();
		
		Thread three = new Thread(th);
		three.start();
		
		Thread fff = new Thread(four);
		fff.start();

		Thread five = new Thread(fiv);
		five.start();

		

	}

}

class Abcc {

	boolean flag1 = true;
	boolean flag2 = false;
	boolean flag3 = false;
	boolean flag4 = false;
	boolean flag5 = false;
	int max;
	AtomicInteger atomic;

	public Abcc(int max) {
		this.max = max;
	}

	public synchronized void first(AtomicInteger atomic) {

		while (!flag1) {

			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		flag1 = false;
		flag2 = true;
		if(atomic.get()<=max) {
		System.out.println(Thread.currentThread().getName() + " " + atomic.get());
		atomic.incrementAndGet();
		notifyAll();
		}
	}

	public synchronized void second(AtomicInteger atomic) {

		while (!flag2) {

			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		flag2 = false;
		flag3 = true;
		if(atomic.get()<=max) {
		System.out.println(Thread.currentThread().getName() + " " + atomic.get());
		atomic.incrementAndGet();
		notifyAll();
		}
	}
	
public synchronized void third(AtomicInteger atomic) {
		
		while(!flag3) {
			
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		flag3=false;
		flag4=true;
		if(atomic.get()<=max) {
		System.out.println(Thread.currentThread().getName()+" "+atomic.get());
		atomic.incrementAndGet();
		notifyAll();
		}
	}

public synchronized void four(AtomicInteger atomic) {
	
	while(!flag4) {
		
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	flag4=false;
	flag5=true;
	if(atomic.get()<=max) {
	System.out.println(Thread.currentThread().getName()+" "+atomic.get());
	atomic.incrementAndGet();
	notifyAll();
	}
}

public synchronized void five(AtomicInteger atomic) {
	
	while(!flag5) {
		
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	flag5=false;
	flag1=true;
	if(atomic.get()<=max) {
	System.out.println(Thread.currentThread().getName()+" "+atomic.get());
	atomic.incrementAndGet();
	notifyAll();}
}

}