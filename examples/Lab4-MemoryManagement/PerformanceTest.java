public class PerformanceTest {

    private static class SampleObject {
        private final int value;
        private final byte[] data = new byte[64];

        SampleObject(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Performance Measurement =====");
        MemoryMonitor.printMemoryReport("Start");

        int[] objectCounts = {10, 100, 1_000, 100_000, 1_000_000};

        System.out.println();
        System.out.printf("%-12s %-14s %-18s%n", "Objects", "Used Memory", "Execution Time");
        System.out.println("--------------------------------------------------");

        for (int count : objectCounts) {
            runAllocationTest(count);
        }

        System.out.println();
        System.out.println("Additional measurements:");
        measureLoopExecution();
        measureArrayAllocation();
        measureLargeByteArray();
    }

    private static void runAllocationTest(int count) {
        MemoryMonitor.triggerGarbageCollection();
        long memoryBefore = MemoryMonitor.getUsedMemoryBytes();
        long startTime = System.nanoTime();

        // TODO: allocate SampleObject[count], fill each slot
        SampleObject[] dummyObj = new SampleObject[count];

        for(int i = 0; i < count; i++){
            dummyObj[i] = new SampleObject(i);
        }

        // TODO: measure elapsed ms + memoryUsed; printf row; null array + GC
        double elapsedMillis = (System.nanoTime() - startTime) / 1_000_000;

        Runtime runtime = Runtime.getRuntime();
        float usedMemory = runtime.totalMemory() - runtime.freeMemory();

        System.out.printf("%-12d %-14s %-18s\n", count, usedMemory, elapsedMillis);

        dummyObj = null;
        System.gc();
//        throw new UnsupportedOperationException("TODO");
    }

    private static void measureLoopExecution() {
        // TODO: loop 10_000_000 iterations summing i into sum; print elapsed ms

        int sum = 0;

        long startTime = System.nanoTime();

        for(int i = 0; i < 10_000_000; i++){
            sum += 1;
        }
        double elapsedMillis = (System.nanoTime() - startTime) / 1_000_000;
        System.out.printf("Elapsed Time: %.3f ms", elapsedMillis);

        //throw new UnsupportedOperationException("TODO");
    }

    private static void measureArrayAllocation() {
        // TODO: allocate int[1_000_000], fill with i, print elapsed ms
        long startTime = System.nanoTime();

        int size = 1_000_000;
        int[] arr = new int[size];
        for(int i = 0; i < size; i++){
            arr[i] = i;
        }
        double elapsedMillis = (System.nanoTime() - startTime) / 1_000_000;
        System.out.printf("Elapsed Time: %.3f ms", elapsedMillis);
//        throw new UnsupportedOperationException("TODO");
    }

    private static void measureLargeByteArray() {
        MemoryMonitor.printMemoryReport("Before Large byte[]");
        // TODO: allocate 10 MB byte[]; print After report; null + GC; print After Releasing
        byte[] data = new byte[1_048_576];
        MemoryMonitor.printMemoryReport("After Allocating Large byte[]");
        data = null;
        System.gc();
        MemoryMonitor.printMemoryReport("After Releasing Large byte[]");


        //throw new UnsupportedOperationException("TODO");
    }
}
