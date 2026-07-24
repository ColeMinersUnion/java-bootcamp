public class GarbageCollectionDemo {

    private static class DemoObject {
        private final String label;
        private final byte[] payload = new byte[128];

        DemoObject(String label) {
            this.label = label;
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Garbage Collection Demonstration =====");
        long startTime = System.nanoTime();

        MemoryMonitor.printMemoryReport("Before Allocation");
        int objectsCreated = 0;
        DemoObject[] objects = new DemoObject[100000];
        System.out.println("Creating Objects...");
        // TODO: fill objects[i] = new DemoObject("Object-" + i)
        for (int i = 0; i < 100000; i++){
            objects[i] = new DemoObject("Object-"+i);
            objectsCreated++;
        }
        System.out.printf("There were %d Objects created.\n", objectsCreated);
        // TODO: print Objects Created count; printMemoryReport After Allocation
        MemoryMonitor.printMemoryReport("After Allocation");

        // TODO: set objects = null; trigger GC; print After GC report + elapsed ms
        objects = null;
        System.gc();

        MemoryMonitor.printMemoryReport("After Garbage Collection");
        double elapsedMillis = (System.nanoTime() - startTime) / 1_000_000;

        System.out.printf("This demo took %.2f ms\n", elapsedMillis);
        // Tip: elapsedMillis = (System.nanoTime() - startTime) / 1_000_000
//        throw new UnsupportedOperationException("TODO");
        //I assume I'm supposed to commnet that out when I'm finished.
    }
}
