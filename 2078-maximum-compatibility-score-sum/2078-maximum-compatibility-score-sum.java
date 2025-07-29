import java.util.ArrayList;

class Solution {
    private ArrayList<ArrayList<Integer >> permutations;
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int n = students.length;
        permutations = new ArrayList<>();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = i;

        getPermutations(arr, 0);

        int maxi = 0;
        for (ArrayList<Integer> curr : permutations) {
            int idx = 0;
            int currentScore = 0;
            for (int i = 0; i < curr.size(); i++) {
                for (int j = 0; j < students[idx].length; j++) {
                    if (students[idx][j] == mentors[curr.get(i)][j])
                        currentScore++;
                }
                idx++;
            }
            maxi = Math.max(maxi, currentScore);
        }
        return maxi;
    }

    private void getPermutations(int arr[], int index) {
        if (index >= arr.length) {
            ArrayList<Integer> current = new ArrayList<>();
            for (int ele : arr)
                current.add(ele);
            permutations.add(new ArrayList<>(current));
            return;
        }

        for (int i =  index; i < arr.length; i++) {
            swap(arr, index, i);
            getPermutations(arr, index + 1);
            swap(arr, index, i);
        }
    }

    private void swap(int arr[], int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}