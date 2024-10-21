public class Singleton {
    // Step 1: Create a private static volatile instance of the Singleton class
    private static volatile Singleton instance;

    // Step 2: Make the constructor private so it cannot be instantiated from outside the class
    private Singleton() {
        // Private constructor to prevent instantiation
    }

    // Step 3: Provide a public static method that returns the instance of the Singleton class
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // Example method to demonstrate functionality
    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }

    // Main method to test the Singleton
    public static void main(String[] args) {
        // Get the only instance of Singleton
        Singleton singleton = Singleton.getInstance();

        // Show a message
        singleton.showMessage();
    }
}
