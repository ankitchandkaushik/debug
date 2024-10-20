import java.util.Stack;

public class ExpressionEval {

  static long eval(String num) {
    int n = num.length();
    int sign = 1;
    long value = 0;

    Stack<Long> st= new Stack<>();

    for(int i=0; i<n; ++i) {
      char c = num.charAt(i);
      if(c >= '0' && c <= '9') {
        value*=10;
        value+=(c-'0');
      } else  {
        if(sign == 1 || sign == -1) {
          value*=sign;
        } else {
          long temp = st.pop();
          value*=temp;

        }
        st.push(value);
        value = 0;
        if(c == '+') {
          sign = 1;
        } else if(c == '-') {
          sign = -1;
        } else {
          sign = 0;
        }
      }
    }

    if(sign == 1 || sign == -1) {
      value*=sign;
    } else {
      long temp = st.pop();
      value*=temp;
    }
    st.push(value);

    long ans = 0;

    while(!st.empty()) {
      ans+=(st.pop());
    }

    System.out.println(num + " " + ans);

    return ans;
  }

  public static void main(String[] args) {
    System.out.println(eval("2147483648"));
  }
}
