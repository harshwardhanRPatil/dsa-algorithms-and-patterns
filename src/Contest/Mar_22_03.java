package Contest;

public class Mar_22_03 {
    static void main(){

    }
    public boolean uniformArray(int[] nums1) {
        int odd=0;
        int even=0;
        int n=nums1.length;
        for(int i=0;i<n;i++){
            if(((nums1[i]&1)==0)){
                even++;
            }else{
                return true;
            }
        }
        if(even==n || odd==n) return true;

        return true;
    }
    public boolean uniformArrayII(int[] nums1) {
        int odd=0;
        int even=0;

        int evenMin=Integer.MAX_VALUE;


        int n=nums1.length;
        for(int i=0;i<n;i++){
            if(((nums1[i]&1)==0)){
                even++;
            }else{
                odd++;
            }
            evenMin=Math.min(evenMin,nums1[i]);
        }
        if(even==n || odd==n) return true;

        if(((evenMin&1)==0)){
            for(int i=0;i<n;i++){
                if(((nums1[i])&1)==1) return false;
            }
        }

        return true;
    }
}
