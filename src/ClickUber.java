///*
//Interviewer joined the call and pasted the problem statement without any introduction:
//
//
//Develop a feature to determine if an ad has had X impressions without a click over a Y day rolling window.
//
//
//An add event can contain will look like as follow:
//
//
//{
//id: int,
//timestamp: int,
//eventType: click | impression
//}
//Sample test case:
//x = 3, y = 3
//
//
//timestamp: 100, id: 1, type: impression
//timestamp: 101, id: 1, type: impression
//timestamp: 102, id: 1, type: click
//timestamp: 104, id: 1, type: impression
//timestamp: 106, id: 1, type: impression
//timestamp: 107, id: 1, type: impression
//timestamp: 109, id: 1, type: click
//
//
//The impression events need to be counted between two click events.
//So, in the above sample for ad event id - 1
//at timestamp 102, impression count in last y mins = 2
//at timestamp 106, impression count in last y mins = 2
//at timestamp 107, impression count in last y mins = 2
//at timestamp 109, impression count in last y mins = 1
//maxImpressionCount = 2, which is less than 3 so answer is false for this case.
//
//
//
// */
//
//import java.util.ArrayDeque;
//import java.util.Deque;
//
//public class ClickUber {
//
//  boolean fun(int[][] events, int x, int y) {
//    int n = events.length;
//    if(x > y) return false;
//    if(n < x) return false;
//
//    Deque<Integer> dq = new ArrayDeque<>();
//
//    for(int []event: events) {
//      if(event[1] == 1) {
//        while(!dq.isEmpty() && dq.peekFirst() < (event[0]-y-1)) {
//          dq.pollFirst();
//        }
//        dq.addLast(event[0]);
//
//      } else {
//        int counter = 0;
//        int prev = -1;
//        while(!dq.isEmpty() ) {
//          if(counter == 0) {
//            prev = dq.pollFirst();
//            counter++;
//          } else {
//            if()
//          }
//
//        }
//      }
//    }
//  }
//}
