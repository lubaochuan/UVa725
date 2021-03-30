// UVa 725 - Division
// https://onlinejudge.org/index.php?option=onlinejudge&Itemid=8&page=show_problem&problem=666
//https://www.comp.nus.edu.sg/~stevenha/myteaching/competitive_programming/cp1.pdf page 39
// this solution pass the UVa judge at onlinejudge.org
// lessons learned:
// - enumerate and filter
// - print an empty line between outputs (no empty line after the last output)

import java.util.Scanner;
import java.util.Arrays;

class Main {
  public static void main(String[] args) {
    /*
    find abcde / fghij = N
    fghij can only be from 01234 to 98765
    get abcde from fghij * N and then check if all
digits are different.
    2 <= N <= 79
    */
    Scanner scan = new Scanner(System.in);
    int N = scan.nextInt();
    boolean first = true;
    while(N != 0){
      //System.out.println(N);

      int[] digits = new int[10];
      String result = "";
      boolean found = false;
      for(int fghij=1234; fghij<=98765; fghij++){
        int abcde = fghij * N;
        if(abcde>99999) continue;
        //System.out.println("try "+abcde+" / "+fghij);
        int temp = abcde;
        for(int i=0; i<5; i++){
          digits[i] = temp % 10;
          temp = temp/10;
        }
        temp = fghij;
        for(int i=5; i<=9; i++){
          digits[i] = temp % 10;
          temp = temp/10;
        }
        Arrays.sort(digits);
        boolean unique = true;
        for(int i=0; i<9; i++){
          if(digits[i]==digits[i+1]){
            unique = false;
            break;
          }
        }
        if(unique){
          found = true;
          if(fghij < 10000){
            result += abcde+" / 0"+fghij+" = "+N+"\n";
          }else{
            result += abcde+" / "+fghij+" = "+N+"\n";
          }
        }
      }
    
      if(first){
        first = false;
      }else{
        System.out.println(); // print empty line between output blocks
      }
      if(!found){
        System.out.println("There are no solutions for "+N+".");
      }else{
        System.out.print(result);
        result = "";
      }
      // read next input
      N = scan.nextInt();
    }// end of while
  }
}

class Test {
  public static void main(String[] args) {
    String result = Arrays.toString(int2digits(1234));
    System.out.println(result);
    int[] digits = new int[]{2, 3, 1, 4};
    Arrays.sort(digits);
    System.out.println(Arrays.toString(digits));
  }

  static int[] int2digits(int n){
    int[] digits = new int[5];
    for(int i=4; i>=0; i--){
      digits[i] = n % 10;
      n = n/10;
    }
    return digits;
  }

  static boolean unique(int[] digits){
    boolean unique = true;
    for(int i=0; i<10; i++){
      if(digits[i]==digits[i+1]){
        unique = false;
        break;
      }
    }
    return unique;
  }
}