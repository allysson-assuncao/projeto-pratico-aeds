package other_algorithms;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

interface SortAlgorithm {
    void sort(int[] array);
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

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

class CountingSort implements SortAlgorithm {
    public void sort(int[] array) {
        int max = Arrays.stream(array).max().getAsInt();
        int[] count = new int[max + 1];
        int[] output = new int[array.length];

        for (int num : array) {
            count[num]++;
        }

        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            output[count[array[i]] - 1] = array[i];
            count[array[i]]--;
        }

        System.arraycopy(output, 0, array, 0, array.length);
    }
}

class RadixSort implements SortAlgorithm {
    public void sort(int[] array) {
        int max = Arrays.stream(array).max().getAsInt();
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(array, exp);
        }
    }

    private void countingSortByDigit(int[] array, int exp) {
        int[] output = new int[array.length];
        int[] count = new int[10];

        for (int num : array) {
            count[(num / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, array, 0, array.length);
    }
}

class BucketSort implements SortAlgorithm {
    public void sort(int[] array) {
        int max = Arrays.stream(array).max().getAsInt();
        int numBuckets = (int) Math.sqrt(array.length);
        int[][] buckets = new int[numBuckets][];

        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new int[0];
        }

        for (int num : array) {
            int bucketIndex = (num * numBuckets) / (max + 1);
            buckets[bucketIndex] = append(buckets[bucketIndex], num);
        }

        for (int i = 0; i < numBuckets; i++) {
            Arrays.sort(buckets[i]);
        }

        int index = 0;
        for (int[] bucket : buckets) {
            for (int num : bucket) {
                array[index++] = num;
            }
        }
    }

    private int[] append(int[] array, int num) {
        int[] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[array.length] = num;
        return newArray;
    }
}

public class OtherAlgorithms {
    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000, 1000000};
        String[] algorithmNames = {"QuickSort", "CountingSort", "RadixSort", "BucketSort"};
        SortAlgorithm[] algorithms = {
            new QuickSort(),
            new CountingSort(),
            new RadixSort(),
            new BucketSort()
        };
        int numTests = 2;

        try (FileWriter writer = new FileWriter("other_results.csv")) {
            writer.write("Algorithm,Size,TimeMillis\n");

            for (int i = 0; i < algorithms.length; i++) {
                SortAlgorithm algorithm = algorithms[i];
                String algorithmName = algorithmNames[i];

                for (int size : sizes) {
                    long totalTime = 0;

                    for (int test = 0; test < numTests; test++) {
                        int[] array = generateArray(size);
                        long startTime = System.nanoTime();
                        algorithm.sort(array);
                        long endTime = System.nanoTime();
                        totalTime += (endTime - startTime) / 1_000_000;
                    }

                    double averageTime = totalTime / numTests;
                    writer.write(algorithmName + "," + size + "," + averageTime + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] generateArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
        return array;
    }
}