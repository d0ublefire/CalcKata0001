import java.util.Scanner;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args)  throws Exception{
        Scanner sc = new Scanner(System.in);
        String input1 = sc.nextLine().replaceAll(" ", "");
        System.out.println(calc(input1));

    }
    public static String calc(String input) throws Exception{
        String deistvie;
        String res;
        int a;
        int b;
        int c;
        boolean isRoman = false;
        String[] operandi = input.split("[+\\-*/]");
        if(operandi.length != 2) throw new Exception("Допустимо только два операнда");
        deistvie = proverkaDeistvia(input);
        if(deistvie == null) throw new Exception("Необходимо указать для операндов только одно действие: + - / *");
        if(Rom.isRom(operandi[0]) && Rom.isRom(operandi[1])) {
            a = Rom.zamenaNaArab(operandi[0]);
            b = Rom.zamenaNaArab(operandi[1]);
            isRoman = true;
        } else if (isArab(operandi[0]) && isArab(operandi[1])) {
            a = Integer.parseInt(operandi[0]);
            b = Integer.parseInt(operandi[1]);
        } else {
            throw new Exception("Оба операнда должны быть или только римскими или только арабскими числами");
        }

        if (a > 10 || a < 1 || b > 10 || b < 1) {
            throw new Exception("Операнд может быть только от 1 до 10 включительно");
        }
        int arabian = reshenie(a, b, deistvie);
        if (isRoman) {
            if (arabian < 1) {
                throw new Exception("Ответ для римских чисел не может быть меньше 1");
            }
            String otvet = "";
            int x2 = arabian / 10;
            int x1 = arabian % 10;
            otvet += x2 == 10 ? "C" : x2 == 9 ? "XC" : x2 == 8 ? "LXXX" : x2 == 7 ? "LXX" : x2 == 6 ? "LX" : x2 == 5 ? "L" : x2 == 4 ? "XL" : x2 == 3 ? "XXX" : x2 == 2 ? "XX" : x2 == 1 ? "X" : "";
            otvet += x1 == 9 ? "IX" : x1 == 8 ? "VIII" : x1 == 7 ? "VII" : x1 == 6 ? "VI" : x1 == 5 ? "V" : x1 == 4 ? "IV" : x1 == 3 ? "III" : x1 == 2 ? "II" : x1 == 1 ? "I" : "";
            res = otvet;
        } else {
            res = String.valueOf(arabian);
        }
        return res;
    }
    static String proverkaDeistvia(String input) {
        char [] znaki = {'+', '*', '/' ,'-'};
        int count = 0;
        for (char ch: znaki) {
            count += input.length() - input.replace(String.valueOf(ch), "").length();
        }
        if (count == 1) {
            for (char ch: znaki) {
                String x = String.valueOf(ch);
                if (input.contains(x)) {
                    return x;
                }
            }
        }
        return null;
    }
    public static boolean isArab(String cifra) {
        int arab;
        if (cifra == null || cifra.equals("")) return false;
        try {
            arab = Integer.parseInt(cifra);
            return true;
        } catch (NumberFormatException e) {
            System.out.print("");
        }
        return false;
    }
    static int reshenie(int a, int b, String znak) {
        if (znak.equals("+")) return a + b;
        else if (znak.equals("-")) return a - b;
        else if (znak.equals("*")) return a * b;
        else return a / b;
    }
}
class Rom {
    static String[] romArr = new  String[] {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    public static boolean isRom(String cifra) {
        for(int i = 0; i < romArr.length; i++) {
            if(cifra.equals(romArr[i])) return true;
        } return false;
    }
    public static int zamenaNaArab(String r) {
        for (int i = 0; i < romArr.length; i++) {
            if (r.equals(romArr[i])) return i;
        } return -1;
    }
}
