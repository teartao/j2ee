package com.haycm.algorithm;

/**
 * @Author taolei
 * @Date 16/4/19.
 * @Desc 销售人员销售某款产品,销量25为达标,每件100元;
 * 销量在26-50 时,每件提成20%,即26-50的部分每件为100*1.2元;
 * 同样类推至51-75,超过50的部分每件为100*1.2*1.2元;
 * 即销量每增加25,提成多增加20%,问:写一段代码,输入为销售人员销售数量,输出为总销售额并打印
 */
public class TestCalc {
    /**
     * 普通计算方式
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(calc(74));
    }

    static double calc(int count) {
        double total = 0;
        if (count % 25 != 0) {
            total = calcLeft(count, total);
        }

        return calc25(count - count % 25, total);
    }

    /**
     * 计算多出25倍数的提成
     */

    static Double calcLeft(int n, double total) {
        int rate = n / 25 - 1;//获取倍数
        int left = n % 25; //获取个数
        return calcTotal(left, rate, total);
    }

    /**
     * 计算25倍数的提成
     */
    static Double calc25(int n, double total) {
        int rate = n / 25 - 1;//取模 获取倍数
        total = calcTotal(25, rate, total);
        if (n - 25 != 0) {
            total = calc25(n - 25, total);
        }
        return total;
    }

    static Double calcTotal(int n, int rate, double total) {
        return total + 25 * (100 * Math.pow(1.2, rate));
    }
}

/**
 * 推导公式后计算方式:

 n%25  取余数，获得n除以25得到的余数 n<25余数为n
 n/25  取倍数，获得n除以25得到的倍数 n<25倍数为0
 sum = f(n)
 n=1   sum = (n%25)*100* 1.2^(n/25)   n/25=0
 n=26  sum = 25*100*(1.2^(n/25 -1)) + (n%25)*100* 1.2^(n/25)   n/25=1
 n=51  sum = 25*100*(1.2^(n/25 -2)) + 25*100*(1.2^(n/25 -1)) + (n%25)*100* 1.2^(n/25)   n/25=2
 n=76  sum = 25*100*(1.2^(n/25 -3)) + 25*100*(1.2^(n/25 -2)) + 25*100*(1.2^(n/25 -1))+ (n%25)*100* 1.2^(n/25)  n/25=3
 ...
 =25*100*( (1.2^(n/25 -3)) + 1.2^(n/25 -2) + 1.2^(n/25 -1) ) + ... + (n%25)*100* 1.2^(n/25)   n>25
 =25*100 ( 1.2^1 + 1.2^2 + 1.2^3 +...+ 1.2^(n/25-1) ) +(n%25)*100* 1.2^(n/25)      【 n/25 >=1 即 n>25】

 */
class Test2 {
    public static void main(String[] args) {
        System.out.println(calc(26));
    }

    static double calc(int n) {
        double tiLv = 0;
        if (n < 25) {
            return calc25(n);
        }
        int beiShu = n / 25;
        for (int i = 0; i < beiShu; i++) {
            tiLv += Math.pow(1.2, i);
        }
        return calc25(n - n/25*25) + 25 * 100 * tiLv;
    }

    static double calc25(int n) {
        return (n % 25) * 100 * Math.pow(1.2, (n / 25));
    }
}
