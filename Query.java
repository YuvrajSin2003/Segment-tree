public class Query {

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
        // No overlap
        if (qj < si || qi > sj) {
            return 0;
        }
        // Complete overlap
        if (qi <= si && qj >= sj) {
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

    public static void main(String args[]) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8};
        int n = arr.length;
        init(n);
        buildST(arr, 0, 0, n - 1);
        System.out.println("Sum from index 2 to 5: " + getSum(arr, 2, 5)); // Expected 3+4+5+6 = 18
    }
}
