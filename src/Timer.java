public class Timer {
    // Pre: int milli
    // Post: Does not continue until milli milliseconds have passed
    static void wait(int milli) {
	long start = System.currentTimeMillis();
	while(System.currentTimeMillis() - start < milli);
    }
}
