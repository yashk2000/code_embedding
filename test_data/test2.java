public class test2 {
    public String fun2 (String s) {
        return s + " concatinated";
    }

    public boolean fun3 (int n) {
        int rem, rev = 0, temp; 
        temp = n;
 
        while( n != 0 ) {
            rem = n % 10;
            rev = rev * 10 + rem;
            n = n / 10;
        }
 
        if (temp == rev)
            return true;
        else
            return false;
    }

    public int fun4 (int n) {
        int reversenum = 0;
        
        while( n != 0 ) {
            reversenum = reversenum * 10;
            reversenum = reversenum + n % 10;
            n /= 10;
        }

        test1 obj = new test1();
        return obj.fun1(reversenum);
    }

    public String fun5 (String s) {
        String n = "string";
        for (int i = 0; i < s.length(); ++i) {
            n = n + s;
        }

        return n;
    }
}
