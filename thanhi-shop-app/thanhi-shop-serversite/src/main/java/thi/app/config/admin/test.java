package thi.app.config.admin;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Moi nhap so tao:");
        int soTao = sc.nextInt();
        if (soTao < 1 || soTao > 100) {
            System.out.println("So tao nhap vao khong dung!");
            return;
        }


        int[] a = new int[soTao];
        int le =0;
        int chan = 0;
        for (int i = 0; i < soTao; i++) {
            double random = Math.random() * 100 + 1;
            int num = (int)random;
            if(num <= 10) {
                a[i] = 100;
                le++;
            }
            else {
                a[i] = 200;
                chan++;
            }

        }

        for (int i = 0; i < soTao; i++) {
            System.out.print(a[i]);
        }
        if (le%2 != 0 || chan %2 != 0) {
            System.out.println("No");
        } else {
            System.out.println("YES");
        }

        System.out.println(1%2 == 0);
    }
}
