package Contest.Feb_28_02;

import java.util.*;

public class Feb_28_02 {
  static void main() {}

  public int[] minDistinctFreqPair(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }
    if (map.size() < 2) return new int[] {-1, -1};

    List<Integer> freq = new ArrayList<>(map.keySet());
    Collections.sort(freq);
    int n = freq.size();
    for (int i = 0; i < n; i++) {
      int x = freq.get(i);
      for (int j = i + 1; j < n; j++) {
        int y = freq.get(j);
        if (!map.get(x).equals(map.get(y))) {
          return new int[] {x, y};
        }
      }
    }
    return new int[] {-1, -1};
  }

  ///  que b

  public String mergeCharacters(String s, int k) {
    // for this que my approce  is to have a string bugger and a hash map we check if we have a
    // indes again
    //           // we will merge tjem fromthe left if the size is  at most k if not we will updat
    // the index of that value what you same
    //        int subtract =0;
    //                // abba 1
    //      // a=0
    //      // subtract =0
    //      // b=1 b=2 b-b-sub=> 2-1-0 => 1 sub++
    //      // 3-0-1=2
    //      StringBuilder stringBuilder = new StringBuilder();
    //      Map<Character,Integer> map= new HashMap<>();
    //
    //      for(int i=0;i<s.length();i++){
    //          char ch = s.charAt(i);
    //          if(map.containsKey(ch)){
    //              if(i-map.get(ch)-subtract<=k){
    //                  subtract++;
    //              }
    //              else{
    //                  map.put(ch,i);
    //                  stringBuilder.append(ch);
    //              }
    //
    //          }else {
    //              map.put(ch,i);
    //          }
    //      }
    //      return stringBuilder.toString();

    // yybyzybz
    // 1 3
    // ybyzy
      // yy

    // aprch for the above one

    Map<Character, Integer> map = new HashMap<>();
    Set<Integer> removed = new HashSet<>();

    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);

      if (map.containsKey(ch)) {
        int prev = map.get(ch);
        int distance = 0;
        for (int j = prev ; j < i; j++) {
          if (!removed.contains(j)) {
            distance++;
          }
        }
        if (distance <= k) {
          removed.add(i);
          continue;
        }else{
            map.put(ch,i);
        }
      }else{
          map.put(ch, i);
      }

    }

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if (!removed.contains(i)) {
        result.append(s.charAt(i));
      }
    }

    return result.toString();
  }
}
