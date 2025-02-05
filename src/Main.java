import java.io.*;
import java.util.Random;

/*interface SortAlgorithm {
    void sort(int[] array);

    int getSwapCount();

    int getComparisonCount();
}

class InsertionSort implements SortAlgorithm {
    private int swapCount = 0;
    private int comparisonCount = 0;

    public void sort(int[] array) {
        swapCount = 0;
        comparisonCount = 0;
        int n = array.length;
        for (int j = 1; j < n; j++) {
            int key = array[j];
            int i = j - 1;
            while (i >= 0 && array[i] > key) {
                comparisonCount++;
                array[i + 1] = array[i];
                i = i - 1;
                swapCount++;
            }
            comparisonCount++;
            array[i + 1] = key;
        }
    }

    public int getSwapCount() {
        return swapCount;
    }

    public int getComparisonCount() {
        return comparisonCount;
    }
}

class SelectionSort implements SortAlgorithm {
    private int swapCount = 0;
    private int comparisonCount = 0;

    public void sort(int[] array) {
        swapCount = 0;
        comparisonCount = 0;
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisonCount++;
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
                swapCount++;
            }
        }
    }

    public int getSwapCount() {
        return swapCount;
    }

    public int getComparisonCount() {
        return comparisonCount;
    }
}

class BubbleSort implements SortAlgorithm {
    private int swapCount = 0;
    private int comparisonCount = 0;

    public void sort(int[] array) {
        swapCount = 0;
        comparisonCount = 0;
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                comparisonCount++;
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                    swapCount++;
                }
            }
            if (!swapped) break;
        }
    }

    public int getSwapCount() {
        return swapCount;
    }

    public int getComparisonCount() {
        return comparisonCount;
    }
}

class MergeSort implements SortAlgorithm {
    private int swapCount = 0;
    private int comparisonCount = 0;

    public void sort(int[] array) {
        swapCount = 0;
        comparisonCount = 0;
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    private void merge(int[] array, int left, int middle, int right) {
        int leftIndex = left, rightIndex = middle + 1, auxIndex = 0;
        int[] auxArray = new int[right - left + 1];

        while (leftIndex <= middle && rightIndex <= right) {
            comparisonCount++;
            if (array[leftIndex] <= array[rightIndex]) {
                auxArray[auxIndex] = array[leftIndex];
                leftIndex++;
            } else {
                auxArray[auxIndex] = array[rightIndex];
                rightIndex++;
            }
            auxIndex++;
        }

        while (leftIndex <= middle) {
            auxArray[auxIndex] = array[leftIndex];
            auxIndex++;
            leftIndex++;
        }

        while (rightIndex <= right) {
            auxArray[auxIndex] = array[rightIndex];
            auxIndex++;
            rightIndex++;
        }

        for (auxIndex = 0; auxIndex < auxArray.length; auxIndex++) {
            array[left + auxIndex] = auxArray[auxIndex];
            swapCount++;
        }
    }

    public int getSwapCount() {
        return swapCount;
    }

    public int getComparisonCount() {
        return comparisonCount;
    }
}

class QuickSort implements SortAlgorithm {
    private static final int INSERTION_SORT_THRESHOLD = 10;
    private int swapCount = 0;
    private int comparisonCount = 0;

    public void sort(int[] array) {
        swapCount = 0;
        comparisonCount = 0;
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int low, int high) {
        while (low < high) {
            if (high - low < INSERTION_SORT_THRESHOLD) {
                insertionSort(array, low, high);
                break;
            } else {
                int pi = partition(array, low, high);
                if (pi - low < high - pi) {
                    quickSort(array, low, pi - 1);
                    low = pi + 1;
                } else {
                    quickSort(array, pi + 1, high);
                    high = pi - 1;
                }
            }
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            comparisonCount++;
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        swapCount++;
    }

    private void insertionSort(int[] array, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= low && array[j] > key) {
                comparisonCount++;
                array[j + 1] = array[j];
                j--;
                swapCount++;
            }
            comparisonCount++;
            array[j + 1] = key;
        }
    }

    public int getSwapCount() {
        return swapCount;
    }

    public int getComparisonCount() {
        return comparisonCount;
    }
}*/

interface SortAlgorithm {
    void sort(int[] array);

}

class InsertionSort implements SortAlgorithm {
    public void sort(int[] array) {
        int n = array.length;
        for (int j = 1; j < n; j++) {
            int key = array[j];
            int i = j - 1;
            while (i >= 0 && array[i] > key) {
                array[i + 1] = array[i];
                i = i - 1;
            }
            array[i + 1] = key;
        }
    }
}

class SelectionSort implements SortAlgorithm {
    public void sort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
}

class BubbleSort implements SortAlgorithm {
    public void sort(int[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}

class MergeSort implements SortAlgorithm {
    public void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    private void merge(int[] array, int left, int middle, int right) {
        int leftIndex = left, rightIndex = middle + 1, auxIndex = 0;
        int[] auxArray = new int[right - left + 1];

        while (leftIndex <= middle && rightIndex <= right) {
            if (array[leftIndex] <= array[rightIndex]) {
                auxArray[auxIndex] = array[leftIndex];
                leftIndex++;
            } else {
                auxArray[auxIndex] = array[rightIndex];
                rightIndex++;
            }
            auxIndex++;
        }

        while (leftIndex <= middle) {
            auxArray[auxIndex] = array[leftIndex];
            auxIndex++;
            leftIndex++;
        }

        while (rightIndex <= right) {
            auxArray[auxIndex] = array[rightIndex];
            auxIndex++;
            rightIndex++;
        }

        for (auxIndex = 0; auxIndex < auxArray.length; auxIndex++) {
            array[left + auxIndex] = auxArray[auxIndex];
        }
    }
}

class QuickSort implements SortAlgorithm {
    private static final int INSERTION_SORT_THRESHOLD = 10;

    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int low, int high) {
        while (low < high) {
            if (high - low < INSERTION_SORT_THRESHOLD) {
                insertionSort(array, low, high);
                break;
            } else {
                int pi = partition(array, low, high);
                if (pi - low < high - pi) {
                    quickSort(array, low, pi - 1);
                    low = pi + 1;
                } else {
                    quickSort(array, pi + 1, high);
                    high = pi - 1;
                }
            }
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void insertionSort(int[] array, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= low && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
}

public class Main {

    private static final int TEST_RUNS = 5;

    public static void testSortAlgorithm(SortAlgorithm algorithm, String algorithmName, FileWriter csvWriter) throws IOException {
        int[] sizes = {100, 500, 1000, 5000, 20000/*, 50000, 100000, 500000*/};
        String[] orders = {"random", "ascending", "descending"};
        long seed = 42;
        for (int size : sizes) {
            for (String order : orders) {
                double totalDuration = 0;
                /*int totalSwaps = 0;
                int totalComparisons = 0;*/

                for (int run = 0; run < TEST_RUNS; run++) {
                    int[] array = generateArray(size, order, seed + run);
                    long startTime = System.nanoTime();
                    algorithm.sort(array);
                    long endTime = System.nanoTime();
                    totalDuration += (endTime - startTime) / 1_000_000.0;
                    /*totalSwaps += algorithm.getSwapCount();
                    totalComparisons += algorithm.getComparisonCount();*/
                }

                double avgDuration = totalDuration / TEST_RUNS;
                /*int avgSwaps = totalSwaps / TEST_RUNS;
                int avgComparisons = totalComparisons / TEST_RUNS;*/

                csvWriter.append(String.format("%s,%d,%s,%.3f\n", algorithmName, size, order, avgDuration));
            }
        }
    }

    public static void main(String[] args) {
        try {
            File csvFile = new File("sort_results.csv");

            try (FileWriter csvWriter = new FileWriter(csvFile)) {
                testSortAlgorithm(new InsertionSort(), "InsertionSort", csvWriter);
                testSortAlgorithm(new SelectionSort(), "SelectionSort", csvWriter);
                testSortAlgorithm(new MergeSort(), "BubbleSort", csvWriter);
                testSortAlgorithm(new BubbleSort(), "MergeSort", csvWriter);
                testSortAlgorithm(new QuickSort(), "QuickSort", csvWriter);
                System.out.println("Tests ended");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] generateArray(int size, String order, long seed) {
        Random random = new Random(seed);
        int[] array = new int[size];
        if (order.equals("random")) {
            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(size);
            }
        } else if (order.equals("ascending")) {
            for (int i = 0; i < size; i++) {
                array[i] = i + 1;
            }
        } else if (order.equals("descending")) {
            for (int i = 0; i < size; i++) {
                array[i] = size - i;
            }
        }
        return array;
    }

    /*public static int[] generateArray(int size, String order, long seed) {
        Random random = new Random(seed);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
        if (order.equals("ascending")) {
            Arrays.sort(array);
        } else if (order.equals("descending")) {
            Arrays.sort(array);
            for (int i = 0; i < size / 2; i++) {
                int temp = array[i];
                array[i] = array[size - i - 1];
                array[size - i - 1] = temp;
            }
        }
        return array;
    }*/
}
