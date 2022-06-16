import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
//        System.out.println(appendAndDelete("hackerhappy", "hackerrank", 9));
//        System.out.println(appendAndDelete("ashley", "ash", 2));
//        System.out.println(appendAndDelete("qwerasdf", "qwerbsdf", 6));
//        System.out.println(appendAndDelete("abcd", "abcdert", 10));
//        System.out.println(strangeCounter(4));
        List<List<Integer>> matrix = new ArrayList<>();
        ArrayList<Integer> row1 = new ArrayList<>();
        row1.add(1);
        row1.add(2);
        row1.add(3);
        row1.add(4);
        row1.add(5);

        ArrayList<Integer> row2 = new ArrayList<>();
        row2.add(6);
        row2.add(7);
        row2.add(8);
        row2.add(9);
        row2.add(10);


        ArrayList<Integer> row3 = new ArrayList<>();
        row3.add(11);
        row3.add(12);
        row3.add(13);
        row3.add(14);
        row3.add(15);


        ArrayList<Integer> row4 = new ArrayList<>();
        row4.add(16);
        row4.add(17);
        row4.add(18);
        row4.add(19);
        row4.add(20);

        matrix.add(row1);
        matrix.add(row2);
        matrix.add(row3);
        matrix.add(row4);
        matrixRotation(matrix, 2);
    }

    public static String appendAndDelete(String s, String t, int k) {
        if (s.equals(t) || s.length() + t.length() < k) return "Yes";
        int delete_count = Math.min(k, s.length());
        for (int i = 1; i <= delete_count; i++) {
            String _s = s.substring(0, s.length() - i);
            String _t = t.substring(0, Math.min(t.length(), _s.length()));
            if (_s.equals(_t) && i + (t.length() - _t.length()) == k) return "Yes";
        }
        return "No";
    }

    public static long strangeCounter(long t) {
        // Write your code here
        int value = 3;
        int min_time = 1;
        int max_time = 3;
        for (int i = 2; i <= t; i++) {
            min_time += value;
            value *= 2;
            max_time = min_time + value - 1;
            if (t >= min_time && t <= max_time) {
                return value - (t - min_time);
            }
        }
        return 0;
    }

    public static void matrixRotation(List<List<Integer>> matrix, int r) {
        // Write your code here
        int n = matrix.get(0).size();
        int m = matrix.size();

        int num_circle = Math.min(n, m) / 2;
        List<List<Integer>> circles = new ArrayList<>();
        for (int i = 0; i < num_circle; i++) {

            List<Integer> circle = new ArrayList<>();
            //Top
            for (int j = i; j < n - i; j++) {
                circle.add(matrix.get(i).get(j));
            }
            //Right
            for (int k = i + 1; k < m - i; k++) {
                circle.add(matrix.get(k).get(n - 1 - i));
            }
            //Bottom
            for (int h = n - 2 - i; h >= i; h--) {
                circle.add(matrix.get(m - 1 - i).get(h));
            }
            //left
            for (int q = m - 2 - i; q >= i+1; q--) {
                circle.add(matrix.get(q).get(i));
            }
            circles.add(circle);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            res.add(Arrays.asList(new Integer[n]));
        }

        for (int i = 0; i < num_circle; i++) {
            //Top
            int index_circle = r%circles.get(i).size();
            for (int j = i; j < n - i; j++) {
                if (index_circle >= circles.get(i).size()) index_circle %= circles.get(i).size();
                res.get(i).set(j, circles.get(i).get(index_circle));
                index_circle++;
            }
            //Right
            for (int k = i + 1; k < m - i; k++) {
                if (index_circle >= circles.get(i).size()) index_circle %= circles.get(i).size();
                res.get(k).set(n - 1 - i, circles.get(i).get(index_circle));
                index_circle++;
            }
            //Bottom
            for (int h = n - 2 - i; h >= i; h--) {
                if (index_circle >= circles.get(i).size()) index_circle %= circles.get(i).size();
                res.get(m - 1 - i).set(h, circles.get(i).get(index_circle));
                index_circle++;
            }
            //left
            for (int q = m - 2 - i; q >= i+1; q--) {
                if (index_circle >= circles.get(i).size()) index_circle %= circles.get(i).size();
                res.get(q).set(i, circles.get(i).get(index_circle));
                index_circle++;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(res.get(i).get(j)+ " ");
            }
            System.out.println();
        }
    }
}
