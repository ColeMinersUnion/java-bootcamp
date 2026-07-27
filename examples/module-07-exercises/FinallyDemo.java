public class FinallyDemo {
    static void transfer(boolean fail) {
        System.out.println("Transfer started.");

        try {
            if (fail) {
                // Simulate a recoverable service failure.
                throw new IllegalStateException(
                        "Transfer service unavailable");
            }
            System.out.println("Transfer completed.");
        } catch (IllegalStateException ex) { // TODO: catch IllegalStateException
            System.out.println("Handled: " + ex.getClass().getSimpleName());
        } finally {
            System.out.println("Cleanup: release transfer session.");
        }
    }

    public static void main(String[] args) {
        transfer(false); // success path
        System.out.println("---");
        transfer(true);  // failure path
    }
}