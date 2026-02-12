class Solution {
    public int maxMinHeight(int[] arr, int k, int w) {
        int n = arr.length;
        long low = Integer.MAX_VALUE, high;
        
        for (int x : arr) low = Math.min(low, x);
        high = low + k;
        
        long ans = low;
        
        while (low <= high) {
            long mid = (low + high) / 2;
            if (canMake(arr, k, w, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return (int) ans;
    }
    
    private boolean canMake(int[] arr, int k, int w, long target) {
        int n = arr.length;
        long[] diff = new long[n + 1];
        long used = 0, curr = 0;
        
        for (int i = 0; i < n; i++) {
            curr += diff[i];
            long height = arr[i] + curr;
            
            if (height < target) {
                long need = target - height;
                used += need;
                if (used > k) return false;
                
                curr += need;
                if (i + w < diff.length)
                    diff[i + w] -= need;
            }
        }
        return true;
    }
}
