import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
//        File file = new File("D:\\0.Dinh Hiep\\Du lieu cham cong\\Dulieuchamcong\\7-9-2020\\Thang_8.dat");
        File file = new File("D:\\0.Dinh Hiep\\Du lieu cham cong\\Dulieuchamcong\\01_05_2021den31_05_2022\\01_05_2021den31_05_2022.dat");

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            String temp = "";
            ArrayList<AttendanceModel> arr = new ArrayList<>();
            while ((st = br.readLine()) != null) {
                String[] s = splitUsingTokenizer(st);
                if (s[0].equals("8")) {
                    if (!temp.equals(s[1])){
                        System.out.print(temp + "\t");
                        temp = s[1];
                        arr.forEach(model -> System.out.print(model.toString()));
                        System.out.println();
                        arr.clear();

                    }
                    arr.add(new AttendanceModel(s[1], s[2], s[4]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String[] splitUsingTokenizer(String s) {
        String[] arr = new String[10];
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
