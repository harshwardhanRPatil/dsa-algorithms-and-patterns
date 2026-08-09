package Presum;




public class DifferenceArray {
    static void main(){

    }

    /*
        https://leetcode.com/problems/corporate-flight-bookings/
        Note -: how we ideanty it a prefix sum so we know i am doing a a work reeat for q1 to q2 soninsted of a for  loop
                i have a arrya that i store the add value and then use it to find the and
     */

    public int[] corpFlightBookings(int[][] bookings, int n) {
            int[] diff= new int[n];


            for(int[] data: bookings){
                int a =data[0]-1;
                int b=data[1];
                int value=data[2];

                diff[a]+=value;
                if(b<n){
                    diff[b]-=value;
                }
            }
            for(int i=1;i<n;i++){
                diff[i]=diff[i]+diff[i-1];
            }
            return diff;
    }
    public boolean carPooling(int[][] trips, int capacity) {
        int maxDistance = 0;

        for (int[] trip : trips) {
            maxDistance = Math.max(maxDistance, trip[2]);
        }

        int[] diff = new int[maxDistance + 2];

        for(int[] data: trips){
            int a =data[0]-1;
            int b=data[1];
            int value=data[2];

            diff[a]+=value;
            if(b<maxDistance){
                diff[b]-=value;
            }
        }
        for(int i=1;i<maxDistance;i++){
            diff[i]=diff[i]+diff[i-1];
        }
        return true;
    }
}
