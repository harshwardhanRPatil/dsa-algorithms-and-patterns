package DailyQue;

public class ConcatenatedBinary {
    static void main(){

    }
    public int concatenatedBinary(int n) {

        // nive approch
//        StringBuilder stringBuilder= new StringBuilder();
//
//        for(int i=1;i<=n;i++){
//            stringBuilder.append(Integer.toBinaryString(i));
//        }
//        long result = 0;
//        long MOD = 1_000_000_007;
//
//        System.out.println(stringBuilder.toString());
//        for (int i = 0; i < stringBuilder.length(); i++) {
//            result=(result*2+(stringBuilder.charAt(i)-'0'))%MOD;
//        }
//        return (int) result;
        // aproch from chatgpt for imoprove the memory
        StringBuilder stringBuilder= new StringBuilder();
        long result = 0;
        int bitLength = 0;
        long MOD = 1_000_000_007;
        for(int i=1;i<=n;i++){

      // this part tell that we jhave to up th bit ex
      // if we are 1 we only 1 bit is we are on 2 we have 2 bit thne we are on 4 then 3 bit
      // 100 &11 ==0 bit+1
      // 110 <<
      // 0<1 -> 00+1 ==> 1
            // 1<2 ==> 100+10 =>110
            // 110<<2 =>11000+11=>11011

      System.out.println();
            if ((i & (i - 1)) == 0) {
                bitLength++;
            }
            result = ((result<<bitLength)+i) %MOD;
        }
        return (int) result;
    }
}
