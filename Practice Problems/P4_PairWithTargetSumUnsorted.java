import java.util.HashSet;

public class P4_PairWithTargetSumUnsorted {

    public static boolean hasPairBruteForce(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {

                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean hasPairUsingHashSet(int[] nums, int target) {

        HashSet<Integer> seen = new HashSet<>();

        for (int num : nums) {

            int complement = target - num;

            if (seen.contains(complement)) {
                return true;
            }

            seen.add(num);
        }

        return false;
    }

    public static void main(String[] args) {

        int[] nums = {2, 7, 11, 15};
        int target = 9;

        boolean bruteForceResult = hasPairBruteForce(nums, target);
        boolean hashSetResult = hasPairUsingHashSet(nums, target);

        System.out.println("Brute Force: " + bruteForceResult);
        System.out.println("HashSet: " + hashSetResult);
    }
}