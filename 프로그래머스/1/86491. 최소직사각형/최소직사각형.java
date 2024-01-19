class Solution {
    public int solution(int[][] sizes) {
        int n = sizes.length;
        int h = sizes[0][0];
        int w = sizes[0][1];
        
        for(int i = 1; i < n; i++) {
            int sum1 = Math.max(h, sizes[i][0]) * Math.max(w, sizes[i][1]);
            int sum2 = Math.max(h, sizes[i][1]) * Math.max(w, sizes[i][0]);
            if(sum1 < sum2) {
                h = Math.max(h, sizes[i][0]);
                w = Math.max(w, sizes[i][1]);
            } else {
                h = Math.max(h, sizes[i][1]);
                w = Math.max(w, sizes[i][0]);
            }
        }
        
        return h * w;
    }
}