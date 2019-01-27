public class PrintEvenToComplete {
    // create an array
    private final static int SIZE = 15;
    private int[] arrayOfInts = new int[SIZE];

    public PrintEvenToComplete() {
        // fill the array with ascending integer values
        for (int i = 0; i < SIZE; i++) {
            arrayOfInts[i] = i;
        }
    }
    public void printEven() {
        // print out values of even indices of the array
        Iterator iterator = this.new Iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.getNext() + " ");
        }
    }

    // inner class implements the Iterator pattern
    private class Iterator {
        // start stepping through the array from the beginning
        private int next = 0;

        // complete this inner class

    }

    public static void main(String s[]) {
        // fill the array with integer values and print out only
        // values of even indices
        PrintEvenToComplete test = new PrintEvenToComplete();
        test.printEven();
    }
}
