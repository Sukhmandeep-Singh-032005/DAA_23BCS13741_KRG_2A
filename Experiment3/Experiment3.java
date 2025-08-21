// To find frequency of elements in a given array in O(1) time complexity.

import java.util.HashMap;
import java.util.Map;

public class Experiment3 {
    public static void main(String[] args) {
        int arr[] = {1, 3, 2, 1, 4, 3, 2, 1};
        int n = arr.length;

        HashMap<Integer, Integer> freq = new HashMap<>();

        for (int i = 0; i < n; i++) {
            freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
        }

        System.out.println("Element   Frequency");
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            System.out.printf("%7d   %9d%n", entry.getKey(), entry.getValue());
        }
    }
}
