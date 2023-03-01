public class Palindrome {
    /*
    Выводит последовательность true/false для строк переданных через аргументы при запуске
    */
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            System.out.println(isPalindrome(s));
        }
    }

    public static String reverseString(String s){
    //Переворачивает строки
        String str1 = "";
            for (int i=0; i<s.length(); i++){
                str1 += s.charAt(s.length()-i-1);
            }
        return str1;
    }

    public static boolean isPalindrome(String s) {
    //Является ли строка палиндромом(читается одинаково с обоих сторон)
        return s.equals(reverseString(s));}



}
