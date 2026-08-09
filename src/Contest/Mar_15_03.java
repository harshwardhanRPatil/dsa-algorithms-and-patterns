package Contest;

public class Mar_15_03 {
    static void main(){

    }

    public int countCommas(int n) {
        int thousand=1000;

        if(n<thousand) return 0;

        return n-thousand+1;
    }

    public long countCommas(long n) {

        if(n<1000) return 0;

        int ans=0;
        long start =1000;
        long boundry=start*1000-1;
        int commo=1;

        while(start<=n){
            boundry=Math.min(n,boundry);
            ans+=(boundry-start+1)*commo;

            commo++;

            start=start*1000;
            boundry=start*1000-1;

        }
        return ans;
    }
}
