package Assignments;
public class AttendanceDisorderScore {

    static long countInversions(int[] roll) {
        return mergeSort(roll, 0, roll.length - 1);
    }

    static long mergeSort(int[] arr, int left, int right) {
        long count = 0;

        if (left < right) {
            int mid = (left + right) / 2;

            count += mergeSort(arr, left, mid);
            count += mergeSort(arr, mid + 1, right);
            count += merge(arr, left, mid, right);
        }

        return count;
    }

    static long merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];

        int i = left;
        int j = mid + 1;
        int k = 0;

        long count = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                count += mid - i + 1;
            }
        }

        while (i <= mid)
            temp[k++] = arr[i++];

        while (j <= right)
            temp[k++] = arr[j++];

        for (i = 0; i < temp.length; i++)
            arr[left + i] = temp[i];

        return count;
    }

    public static void main(String[] args) {
        int[] roll = {2, 4, 1, 3, 5};

        System.out.println("Inversions: " + countInversions(roll));

        System.out.print("Sorted: ");
        for (int x : roll)
            System.out.print(x + " ");
    }
}
