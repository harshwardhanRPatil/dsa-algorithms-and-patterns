package Contest;

import java.security.Key;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class April_12_04 {
    static void main(){

    }

    public int[] findDegrees(int[][] matrix) {
        int[] ans= new int[matrix.length];
        Map<Integer,Integer> map= new HashMap<>();

        for(int i =0;i<matrix.length;i++){
            for(int j =0;j<matrix.length;j++){
                if(matrix[i][j]==1){
                    map.put(j,map.getOrDefault(j,0)+1);
                }
            }
        }
        for(int i:map.keySet()){
            ans[i]=map.getOrDefault(i,0);
        }

        return ans;
    }

    public double[] internalAngles(int[] sides) {
        int a=sides[0];
        int b=sides[1];
        int c=sides[2];
        if((a+b<=c) || (a+c<=b) || (c+b<=a)){
            return  new double[0];
        }

        double sideA=Math.toDegrees(Math.acos((b*b+c*c-a*a)/(2.0*b*c)));
        double sideB=Math.toDegrees(Math.acos((a*a+c*c-b*b)/(2.0*a*c)));

        double sideC=Math.toDegrees(Math.acos((b*b+a*a-c*c)/(2.0*b*a)));

        double[] ans= new double[]{sideA,sideB,sideC};;
        Arrays.sort(ans);
        return ans;
    }

//    public int longestBalanced(String s) {
//
//    }
}
