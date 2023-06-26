import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombinationFinder {
    public static List<int[]> findCombinations(int[] nums, int target) {
        List<int[]> combinations = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> mergedArray = new ArrayList<>();

        //  Find pairs that sum up to the target
        for (int num : nums) {
            int complement = target - num;
            if (map.containsKey(complement)) {
                combinations.add(new int[]{num, complement});
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Merge into a single array and sort
        for (int[] pair : combinations) {
            mergedArray.add(pair[0]);
            mergedArray.add(pair[1]);
        }
        int[] mergedArraySorted = mergedArray.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(mergedArraySorted);

        // Double the target value
        target *= 2;

        //  Find combinations equal to the doubled target value
        List<int[]> secondCombinations = new ArrayList<>();
        map.clear();
        for (int num : mergedArraySorted) {
            int complement = target - num;
            if (map.containsKey(complement) && map.get(complement) > 0) {
                secondCombinations.add(new int[]{complement, num});
                map.put(complement, map.get(complement) - 1);
                map.put(num, map.get(num) - 1);
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        return secondCombinations;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 2, -4, -6, -2, 8};
        int target = 4;
        List<int[]> secondCombinations = findCombinations(nums, target);

        System.out.println("Second Combination For \"" + (target * 2) + "\":");
        for (int[] combination : secondCombinations) {
            System.out.println(Arrays.toString(combination));
        }
    }
}
