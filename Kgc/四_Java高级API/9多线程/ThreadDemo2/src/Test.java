
public class Test {
	public static void main(String[] args) {
		Movie movie = new Movie();
		Thread producer = new Thread(new Producer(movie)); // 生产者
		Thread consumer = new Thread(new Consumer(movie)); // 消费者
		producer.start();
		consumer.start();
	}
}
