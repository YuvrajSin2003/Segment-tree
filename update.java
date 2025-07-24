public class update {

    static int tree[];

    public static void init(int n) {
        tree = new int[4 * n];
    }

    public static int buildST(int arr[], int i, int start, int end) {
        if (start == end) {
            tree[i] = arr[start];
            return arr[start];
        }
        int mid = (start + end) / 2;
        int left = buildST(arr, 2 * i + 1, start, mid);
        int right = buildST(arr, 2 * i + 2, mid + 1, end);
        tree[i] = left + right;
        return tree[i];
    }

    public static int getSumUtil(int i, int si, int sj, int qi, int qj) {
        if (qj < si || qi > sj) { // No overlap
            return 0;
        }
        if (qi <= si && qj >= sj) { // Complete overlap
            return tree[i];
        }
        // Partial overlap
        int mid = (si + sj) / 2;
        int left = getSumUtil(2 * i + 1, si, mid, qi, qj);
        int right = getSumUtil(2 * i + 2, mid + 1, sj, qi, qj);
        return left + right;
    }

    public static int getSum(int arr[], int qi, int qj) {
        int n = arr.length;
        return getSumUtil(0, 0, n - 1, qi, qj);
    }

    public static void updateUtil(int i, int si, int sj, int idx, int diff) {
        if (idx < si || idx > sj) {
            return;
        }
        tree[i] += diff;
        if (si != sj) {
            int mid = (si + sj) / 2;
            updateUtil(2 * i + 1, si, mid, idx, diff);
            updateUtil(2 * i + 2, mid + 1, sj, idx, diff);
        }
    }

    public static void update(int arr[], int idx, int newValue) {
        int n = arr.length;
        int diff = newValue - arr[idx];
        arr[idx] = newValue;
        updateUtil(0, 0, n - 1, idx, diff);
    }

    public static void main(String args[]) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8};
        int n = arr.length;
        init(n);
        buildST(arr, 0, 0, n - 1);
        
        System.out.println("Sum from index 2 to 5: " + getSum(arr, 2, 5)); // 3+4+5+6 = 18

        // Update index 3 by adding 2 (i.e., 4 -> 6)
        update(arr, 3, arr[3] + 1); 
        
        System.out.println("Sum from index 2 to 5 after update: " + getSum(arr, 2, 5)); // 3+6+5+6 = 20
    }
}
