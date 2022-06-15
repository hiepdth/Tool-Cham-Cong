public class AttendanceModel {
    public String date;
    public String time;
    public String type;

    public AttendanceModel(String date, String time, String type) {
        this.date = date;
        this.time = time;
        this.type = type;
    }

    @Override
    public String toString() {
        return date + " " + time + "\t" + type + "\t";
    }
}
