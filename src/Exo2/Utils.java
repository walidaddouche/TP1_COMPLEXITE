package Exo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Utils {

    List<List<Integer>> subsets(int[] nums) {

        // Creating List class object
        // Declaring. object of integer List
        List<List<Integer>> output = new ArrayList<>();

        int n = nums.length;

        // Increase the size by using left shift (1 * 2^n)
        int size = 1 << n;

        for (int i = 0; i < size; i++) {
            List<Integer> curr = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // right shift i and j i.e. i/2^j
                if (((i >> j) & 1) == 1) {
                    // Add it to subset
                    curr.add(nums[j]);
                }
            }
            // Adding the subset
            output.add(curr);
        }
        return output;
    }



}
