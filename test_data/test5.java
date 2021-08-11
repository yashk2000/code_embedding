public class test5 {
    public String fun2 (String s) {
        return s + " concatinated";
    }

    public boolean fun3 (int n) {
        int rev = 0, rem, temp;
        temp = n;
  
        for( ;n != 0; n /= 10 ) {
            rem = n % 10;

            int a = rev;
            for (int i = 0; i < 10; ++i) {
                rev += a;
            }
            rev += rem;
        }

        return temp == rev;
    }

    public int fun4(int n) {
        int reversenum =0;
        
        for( ;n != 0; ) {

            int a = reversenum;
            for(int i = 0; i < 10; ++i) {
                reversenum += a;
            }
            reversenum = reversenum + n % 10;
            n = n / 10;
        }

        test1 obj = new test1();
        return obj.fun1(reversenum);
    }

    public String fun5 (String s) {
        String n = "string";
        for (int i = 0; i < s.length(); ++i) {
            n = n + s + "_";
        }

        return n;
    }

    public String fun5_1 (String s) {
        String n = "string";
        for (int i = 0; i < s.length(); ++i) {
            n = n + s + "~";
        }

        return n;
    }

    public String fun5_2 (String s) {
        String n = "string";
        for (int i = 0; i < s.length(); ++i) {
            n = n + s + s + "$";
        }

        return n;
    }
}
