public class StringBuilderComparison {
    private static final int ITERATIONS = 50_000;

    static String withString() {
        String result = "";
        for (int i = 0; i < ITERATIONS; i++) {
            // TODO: result += "x";  (each update creates another String)
            result += "x";
        }
        return result;
    }

    static String withBuilder() {
        // Initial capacity avoids repeated buffer growth.
        // TODO: StringBuilder result = new StringBuilder(ITERATIONS);
        StringBuilder result = new StringBuilder(ITERATIONS);
        for (int i = 0; i < ITERATIONS; i++) {
            // TODO: result.append('x');
            result.append('x');
        }
        // TODO: return result.toString();
        return result.toString();
    }

    public static void main(String[] args) {
        // TODO: time withString() with System.nanoTime()
        // TODO: time withBuilder() with System.nanoTime()
        // TODO: printf both lengths and ms (stringNanos / 1_000_000.0)

        float start_time = System.nanoTime();
        String result1 = withString();
        float mid_time = System.nanoTime();
        String result2 = withBuilder();
        float end_time = System.nanoTime();

        System.out.printf("String 1 is %d characters long and took %.4f ms\n", result1.length(), (mid_time-start_time)/1_000_000.00);
        System.out.printf("String 2 is %d characters long and took %.4f ms\n", result2.length(), (end_time-mid_time)/1_000_000.00);
    }
}