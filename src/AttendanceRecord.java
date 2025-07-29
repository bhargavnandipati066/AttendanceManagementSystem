import java.util.Date;

public class AttendanceRecord {
    private int studentId;
    private Date date;
    private boolean present;

    public AttendanceRecord(int studentId, Date date, boolean present) {
        this.studentId = studentId;
        this.date = date;
        this.present = present;
    }

    public int getStudentId() {
        return studentId;
    }

    public Date getDate() {
        return date;
    }

    public boolean isPresent() {
        return present;
    }
}
