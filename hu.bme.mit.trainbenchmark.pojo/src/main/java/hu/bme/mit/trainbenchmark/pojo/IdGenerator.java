package hu.bme.mit.trainbenchmark.pojo;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

	private static final AtomicLong generator = new AtomicLong(0);
	
	public static long next() {
		return generator.incrementAndGet();
	}
}
