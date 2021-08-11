public class test4 {
    public String fun2 (String s) {
        return s + " concatinated";
    }

    public boolean fun3 (int n) {
        int rem, rev = 0, temp; 
        temp = n;
 
        while(true) {
            rem = n % 10;
            rev = rev * 10 + rem;
            n = n / 10;

            if (n == 0) {
                break;
            }
        }
 
        if (temp == rev)
            return true;
        else
            return false;
    }

    public int fun4 (int n) {
        int reversenum = 0;
        
        while( true ) {
            reversenum = reversenum * 10;
            reversenum = reversenum + n % 10;
            n /= 10;

            if (n == 0) {
                break;
            }
        }

        test1 obj = new test1();
        return obj.fun1(reversenum);
    }

    public String fun5 (String s) {
        String n = "string";

        int i = 0;
        while (true) {
            n = n + s;
            i += 1;

            if (i == s.length())
                break;
        }
        
        return n;
    }
}
