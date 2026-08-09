package Contest;

public class July_18_07 {

    public static String rearrangeString(String s, char x, char y) {
        char[] arr = s.toCharArray();
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            if (arr[left] != x) {
                left++;
            } else if (arr[right] != y) {
                right--;
            } else {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }

        return new String(arr);
    }



    public static void main(){
    System.out.println(rearrangeString("aabc",'a','c'));
    }
}
