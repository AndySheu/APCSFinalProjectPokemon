public class Timer {
    static void wait(int milli) {
	long start = System.currentTimeMillis();
	while(System.currentTimeMillis() - start < milli);
    }
}