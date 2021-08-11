public class test3 {
    public String fun2 (String s) {
        return s + " concatinated";
    }
    
    public boolean fun3 (int n) {
        int rev = 0, rem, temp;
        temp = n;
  
        for( ;n != 0; n /= 10 ) {
            rem = n % 10;
            rev = rev* 10 + rem;
        }
  
        if (temp == rev)
            return true;
        else
            return false;
    }

    public int fun4(int n) {
        int reversenum =0;
        
        for( ;n != 0; ) {
            reversenum = reversenum * 10;
            reversenum = reversenum + n % 10;
            n = n / 10;
        }

        test1 obj = new test1();
        return obj.fun1(reversenum);
    }

    public String fun5 (String s) {
        String n = "string";

        int i = 0;
        while (i < s.length()) {
            n = n + s;
            i += 1;
        }
        
        return n;
    }
}
