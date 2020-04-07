package com.example.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintSecond {

	public static void main(String args[]) {
		
		int max = 1000;
		AtomicInteger atomic = new AtomicInteger(1);
		XYZ xyz = new XYZ(max);
		
		
		
		Runnable first = ()->{
			while(atomic.get()<=max) {
				xyz.first(atomic);
			}
		};
		
		Runnable second =()->{
			while(atomic.get()<=max) {
				xyz.second(atomic);
			}
		};
		
		Runnable third =()->{
			while(atomic.get()<=max) {
				xyz.third(atomic);
			}
		};
		
		Runnable four =()->{
			while(atomic.get()<=max) {
				xyz.four(atomic);
			}
		};
		Runnable five =()->{
			while(atomic.get()<=max) {
				xyz.five(atomic);
			}
		};
		
		Thread one = new Thread(first,"T1");
		one.start();
		Thread two = new Thread(second,"T2");
		two.start();
		Thread three = new Thread(third,"T3");
		three.start();
		
		Thread foor = new Thread(four,"T4");
		foor.start();
		
		Thread fived = new Thread(five,"T5");
		fived.start();
			
	}

}



class XYZ {

	boolean flag1 = true;
	boolean flag2 = false;
	boolean flag3 = false;
	boolean flag4 = false;
	boolean flag5 = false;
	int max;

	XYZ(int max) {
		this.max = max;
	}

	public synchronized void first(AtomicInteger atomic) {
		while(!flag1) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		flag1=false;
		flag2=true;
		if(atomic.get()<=max) {
			System.out.println(Thread.currentThread().getName()+" "+atomic.get());
			atomic.incrementAndGet();
			notifyAll();
		}
	}

	public synchronized void second(AtomicInteger atomic) {
		while(!flag2) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		flag2=false;
		flag3=true;
		if(atomic.get()<=max) {
			System.out.println(Thread.currentThread().getName()+" "+atomic.get());
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
			notifyAll();
		}
	}

	
}