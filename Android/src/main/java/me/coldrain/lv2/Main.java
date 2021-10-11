package me.coldrain.lv2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 * me.coldrain.lv2.Main
 * Lv3
 *
 * @author 寒雨
 * @since 2021/10/8 22:42
 **/
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("输入n值:");
        int n = scan.nextInt();
        System.out.println("输入k值:");
        int k = scan.nextInt();
        execute(n, k);
    }

    static void execute(int n, int k) {
        if (n <= 0 || k <= 0) {
            throw new RuntimeException("incorrect input number");
        }
        BigDecimal under = BigDecimal.valueOf(1);
        BigDecimal upper = BigDecimal.valueOf(1);
        for (int i = 1; i <= k; i++) {
            under = under.multiply(BigDecimal.valueOf(i));
            upper = upper.multiply(BigDecimal.valueOf(n - i + 1));
        }
        // 四舍五入，保留20位小数
        BigDecimal chance = under.divide(upper, 20, RoundingMode.HALF_DOWN);
        System.out.println("计算结果: " + chance);
    }
}
