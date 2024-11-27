package google.l4;

/*
Round 1
We have a file with the following format each line: startIP, endIP, cityName.
Question: Write a function that takes as input an IP address and outputs its associated cityName.
Example:
File format:
startIP, endIP, cityName
1.0.1.1, 1.0.1.10, NYC
1.0.1.20, 1.0.1.30, SF
...
If the input is 1.0.1.9, the output should be NYC.
Write code for the function.
 */


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CityFinder {

  public static List<List<String>> fileReader(String fileName) {

    try {
      System.out.println("Looking for file at: " + Paths.get(fileName).toAbsolutePath());
      List<String> contents= Files.readAllLines(Paths.get(fileName));
      List<List<String>> ipMap = new ArrayList<>();
      for(String s: contents) {
        ipMap.add(List.of(s.split(", ")));
      }
      return ipMap;
    } catch(IOException e) {
      System.out.println("Can't read file " + e);
      return null;
    }
  }

  static Comparator<List<String>> c = new Comparator<List<String>>() {
    @Override
    public int compare(List<String> o1, List<String> o2) {
      return comp(o1.get(0), o2.get(0));

    }
  };

  static int comp(String o1, String o2) {
    System.out.println(o1 + " " + o2);
    String[] s1 = o1.split("\\.");
    String[] s2 = o2.split("\\.");
    System.out.println(Arrays.toString(s1) + " " + Arrays.toString(s2));
    if(Integer.parseInt(s1[0]) <  Integer.parseInt(s2[0])) {
      return -1;
    } else if(Integer.parseInt(s1[0]) ==  Integer.parseInt(s2[0])
        && Integer.parseInt(s1[1]) <  Integer.parseInt(s2[1])
    ) {
      return -1;
    } else if(
        Integer.parseInt(s1[0]) ==  Integer.parseInt(s2[0])
            && Integer.parseInt(s1[1]) ==  Integer.parseInt(s2[1])
            && Integer.parseInt(s1[2]) <  Integer.parseInt(s2[2])
    ) {
      return -1;
    } else if(
        Integer.parseInt(s1[0]) ==  Integer.parseInt(s2[0])
            && Integer.parseInt(s1[1]) ==  Integer.parseInt(s2[1])
            && Integer.parseInt(s1[2]) ==  Integer.parseInt(s2[2])
            && Integer.parseInt(s1[3]) <  Integer.parseInt(s2[3])
    ) {
      return -1;
    } else if(
        Integer.parseInt(s1[0]) ==  Integer.parseInt(s2[0])
            && Integer.parseInt(s1[1]) ==  Integer.parseInt(s2[1])
            && Integer.parseInt(s1[2]) ==  Integer.parseInt(s2[2])
            && Integer.parseInt(s1[3]) ==  Integer.parseInt(s2[3])
    ) {
      return 0;
    }
    else return 1;
  }

  public static String findCity(List<List<String>> ipMap, String ip) {
    System.out.println(ipMap);
    Collections.sort(ipMap, c);

    int start = 0;
    int end = ipMap.size()-1;

    while(start <= end) {
      int mid = (start+end)/2;
      int c1 = comp(ip, ipMap.get(mid).get(0));
      int c2 = comp(ip, ipMap.get(mid).get(1));
      if(c1 >= 0 && c2 <= 0) {
        return ipMap.get(mid).get(2);
      } else if(c1 < 0) {
        end = mid-1;
      } else {
        start = mid+1;
      }
    }

    return "Cannot find city";

  }

  public static void main(String[] args) {
    List<List<String>> content = fileReader("src/google/l4/file.txt");
    System.out.println(findCity(content, "1.0.1.21") );

  }

}
