package com.cn.test.CAS机制;
/**
 *  从思想上来说，synchronized属于悲观锁，悲观的认为程序中的并发情况严重，所以严防死守，
 *  CAS属于乐观锁，乐观地认为程序中的并发情况不那么严重，所以让线程不断去重试更新。
 *	在java中除了上面提到的Atomic系列类，以及Lock系列类夺得底层实现，甚至在JAVA1.6以上版本，
 *  synchronized转变为重量级锁之前，也会采用CAS机制。
 *	CAS的缺点：
 *	1） CPU开销过大
 *		在并发量比较高的情况下，如果许多线程反复尝试更新某一个变量，却又一直更新不成功，循环往复，会给CPU带来很到的压力。
 *	2） 不能保证代码块的原子性
 *		CAS机制所保证的知识一个变量的原子性操作，而不能保证整个代码块的原子性。
 *		比如需要保证3个变量共同进行原子性的更新，就不得不使用synchronized了。
 *	3） ABA问题
 *		这是CAS机制最大的问题所在。（后面有介绍）
 *  1. java语言CAS底层如何实现？
 *	利用unsafe提供的原子性操作方法。
 *	2.什么事ABA问题？怎么解决？
 *	当一个值从A变成B，又更新回A，普通CAS机制会误判通过检测。
 *	利用版本号比较可以有效解决ABA问题。
 *
 *
*/
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class CasTest {
	private static int count = 0;
	//原子操作类
	private static AtomicInteger count2 = new AtomicInteger(0);
	@Test
	public void test1() throws InterruptedException{
		//启动两个线程，每个线程中让静态变量count循环累加100次。
		String method = "method1";
		new addCountThread(method).start();
		new addCountThread(method).start();
		Thread.sleep(1000);
		System.out.println("count:"+count);
		
		count = 0;
		method = "method2";
		new addCountThread(method).start();
		new addCountThread(method).start();
		Thread.sleep(1000);
		System.out.println("count:"+count);
		
		method = "method3";
		new addCountThread(method).start();
		new addCountThread(method).start();
		Thread.sleep(1000);
		System.out.println("count2:"+count2.get());
	}
	
	public static class addCountThread extends Thread{
		String method;
		public addCountThread(String method){
			this.method = method;
		}
		@Override
		public void run() {
			System.out.println("============addCountThread.run()=================");
			try {
				Thread.sleep(10);
				if("method1".equals(method)){
					method1();
				}else if("method2".equals(method)){
					method2();
				}else if("method3".equals(method)){
					method3();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		//不加锁的情况下开启2个线程，由于线程不安全，最终的count结果是低于2000
		private void method1(){
			for(int i=0;i<1000;i++){
				count++;
			}
		}
		/**加锁的情况下开启2个线程，最终的count结果等于2000，但使用synchronized效率比较低
		加了同步锁之后，count自增的操作变成了原子性操作，所以最终输出一定是count=2000，代码实现了线程安全。
		虽然synchronized确保了线程安全，但是在某些情况下，这并不是一个最优秀的选择。
		synchronized关键字会让没有得到锁资源的线程进入BLOCKED状态，而后在争夺到锁资源后恢复为RUNNABLE状态，
		这个过程中涉及到操作系统用户模式和内核模式的转换，代价比较高。
		尽管JAVA 1.6为synchronized做了优化，增加了从偏向锁到轻量级锁再到重量级锁的过过度，
		但是在最终转变为重量级锁之后，性能仍然比较低。所以面对这种情况，我们就可以使用java中的“原子操作类”。
		所谓原子操作类，指的是java.util.concurrent.atomic包下，一系列以Atomic开头的包装类。
		如AtomicBoolean，AtomicUInteger，AtomicLong。
		它们分别用于Boolean，Integer，Long类型的原子性操作。*/
		private void method2(){
			for(int i=0;i<1000;i++){
				synchronized (CasTest.class) {
					count++;
				}
			}
		}
		
		private void method3(){
			for(int i=0;i<1000;i++){
				count2.incrementAndGet();
			}
		}
	}
}
