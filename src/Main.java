import java.io.*;
import java.util.Random;

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
        int[] aux = new int[array.length];
        mergeSort(array, aux, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int[] aux, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, aux, left, middle);
            mergeSort(array, aux, middle + 1, right);
            merge(array, aux, left, middle, right);
        }
    }

    private void merge(int[] array, int[] aux, int left, int middle, int right) {
        System.arraycopy(array, left, aux, left, right - left + 1);

        int i = left, j = middle + 1, k = left;
        while (i <= middle && j <= right) {
            if (aux[i] <= aux[j]) {
                array[k++] = aux[i++];
            } else {
                array[k++] = aux[j++];
            }
        }

        while (i <= middle) {
            array[k++] = aux[i++];
        }

        while (j <= right) {
            array[k++] = aux[j++];
        }
    }
}

class QuickSort implements SortAlgorithm {
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivotIndex = medianOfThree(array, low, high);
        swap(array, pivotIndex, high);
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    private int medianOfThree(int[] array, int low, int high) {
        int mid = (low + high) / 2;
        if (array[low] > array[mid]) {
            swap(array, low, mid);
        }
        if (array[low] > array[high]) {
            swap(array, low, high);
        }
        if (array[mid] > array[high]) {
            swap(array, mid, high);
        }
        return mid;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}


public class Main {
    public static void main(String[] args) {
        int[] sizes = {100, 500, 1000, 5000, 20000, 50000, 100000, 500000};
        String[] orders = {"ascending", "descending", "random"};
        SortAlgorithm[] algorithms = {
                new InsertionSort(),
                new SelectionSort(),
                new BubbleSort(),
                new MergeSort(),
                new QuickSort()
        };
        String[] algorithmNames = {
                "InsertionSort",
                "SelectionSort",
                "BubbleSort",
                "MergeSort",
                "QuickSort"
        };
        int numTests = 1;

        try (FileWriter writer = new FileWriter("final_results.csv")) {
            writer.write("Algorithm,Size,Order,Time\n");

            for (int i = 0; i < algorithms.length; i++) {
                SortAlgorithm algorithm = algorithms[i];
                String algorithmName = algorithmNames[i];

                for (int size : sizes) {
                    for (String order : orders) {
                        long totalTime = 0;

                        for (int test = 0; test < numTests; test++) {
                            int[] array = generateArray(size, order);
                            long startTime = System.nanoTime();
                            algorithm.sort(array);
                            long endTime = System.nanoTime();
                            totalTime += (endTime - startTime);
                        }

                        double averageTime = (totalTime / numTests) / 1_000_000.0;
                        writer.write(algorithmName + "," + size + "," + order + "," + averageTime + "\n");
                        System.out.println(algorithmName + "," + size + "," + order + "," + averageTime + " ended");
                    }
                }
            }
            System.out.println("\n\nTests ended!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] generateArray(int size, String order) {
        int[] array = new int[size];
        switch (order) {
            case "ascending":
                for (int i = 0; i < size; i++) {
                    array[i] = i + 1;
                }
                break;
            case "descending":
                for (int i = 0; i < size; i++) {
                    array[i] = size - i;
                }
                break;
            case "random":
                Random random = new Random();
                for (int i = 0; i < size; i++) {
                    array[i] = random.nextInt(size) + 1;
                }
                break;
        }
        return array;
    }
}
