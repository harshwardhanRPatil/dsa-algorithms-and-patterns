package Contest;

public class May_09_05 {


    // https://leetcode.com/problems/score-validator/
    public int[] scoreValidator(String[] events) {

        int counter=0;
        int ans=0;

        for(String i : events){
            if(i.equals("W")){
                counter++;
            }else if(i.equals("WD") || i.equals("NB")){
                ans++;
            }else{
                ans+=Integer.parseInt(i);
            }
            if(counter==10) break;
        }
        return new int[]{ans,counter};
    }

    // https://leetcode.com/problems/minimum-flips-to-make-binary-string-coherent/description/

    public int minFlips(String s) {
        return dfs(s.toCharArray(), 0);
    }

    public int dfs(char[] arr, int index) {

        // if whole string valid
        if (index == arr.length) {

            if (isValid(arr)) {
                return 0;
            }

            return (int)1e9;
        }

        // choice 1 -> do not flip
        int notFlip = dfs(arr, index + 1);

        // choice 2 -> flip current bit
        arr[index] = (arr[index] == '0') ? '1' : '0';

        int flip = 1 + dfs(arr, index + 1);

        // backtrack
        arr[index] = (arr[index] == '0') ? '1' : '0';

        return Math.min(flip, notFlip);
    }

    public boolean isValid(char[] arr) {

        String s = new String(arr);

        return !hasSubsequence(s, "011")
                && !hasSubsequence(s, "110");
    }

    public boolean hasSubsequence(String s, String target) {

        int j = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == target.charAt(j)) {
                j++;
            }

            if (j == target.length()) {
                return true;
            }
        }

        return false;
    }

    static void main(){

    }
}
