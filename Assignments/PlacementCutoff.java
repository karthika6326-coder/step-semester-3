package Assignments;
public class PlacementCutoff {

    static int kthHighest(int[] marks, int k) {
        int target = marks.length - k;
        int low = 0;
        int high = marks.length - 1;

        while (low <= high) {
            int pivot = partition(marks, low, high);

            if (pivot == target)
                return marks[pivot];
            else if (pivot < target)
                low = pivot + 1;
            else
                high = pivot - 1;
        }

        return -1;
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }

        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;

        return i;
    }

    public static void main(String[] args) {
        int[] marks = {72, 95, 60, 88, 79};
        int k = 2;

        System.out.println(k + "nd Highest: " + kthHighest(marks, k));
    }
}