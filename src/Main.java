import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        File file = new File("D:\\0.Dinh Hiep\\Du lieu cham cong\\Dulieuchamcong\\01_07_2021den31_07_2022\\01_07_2021den31_07_2022.dat");

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
//            String s = br.readLine();
//            splitUsingTokenizer(s);
            int count = 0;
            while ((st = br.readLine()) != null) {
                String s[] = splitUsingTokenizer(st);
                if (s[0].equals("8")) {
                    System.out.print(s[1] + " " +s[2]+ "\t" + s[4]+ "\t");
                    if (s[4].equals("1")){
                        System.out.println();
                    }
//                    count++;
//                    if (count == 2){
//                        System.out.println();
//                        count = 0;
//                    }
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] splitUsingTokenizer(String s) {
        String arr[] = new String[10];
        int i = 0;
        StringTokenizer st =
                new StringTokenizer(s);
        while (st.hasMoreTokens()) {
            arr[i] = st.nextToken();
            i++;
        }
        return arr;
    }
}
