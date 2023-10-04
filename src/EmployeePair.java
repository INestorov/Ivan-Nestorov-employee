import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class EmployeePair {
    private long empId1;
    private long empId2;
    private long projectId;
    private long days;

    public EmployeePair(){
    }

    public EmployeePair(long empId1, long empId2, long projectId, long days) {
        this.empId1 = empId1;
        this.empId2 = empId2;
        this.projectId = projectId;
        this.days = days;
    }

    public long getEmpId1() {
        return empId1;
    }

    public long getEmpId2() {
        return empId2;
    }
    public long getProjectId() {
        return projectId;
    }

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        this.days = days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePair that = (EmployeePair) o;
        return empId1 == that.empId1 && empId2 == that.empId2 && projectId == that.projectId && days == that.days;
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId1, empId2, projectId, days);
    }

    @Override
    public String toString() {
        return "EmployeePair{" +
                "empId1=" + empId1 +
                ", empId2=" + empId2 +
                ", projectId=" + projectId +
                ", days=" + days +
                '}';
    }

    public static long calculateDays(Employee employee1, Employee employee2){
        LocalDate fromDateEmp1 = employee1.getDateFrom();
        LocalDate toDateEmp1 = employee1.getDateTo();
        LocalDate fromDateEmp2 = employee2.getDateFrom();
        LocalDate toDateEmp2 = employee2.getDateTo();

        if(!(fromDateEmp1.isBefore(toDateEmp2) && fromDateEmp2.isBefore(toDateEmp1))) return 0;

        LocalDate overLappingBegging =  fromDateEmp1.isBefore(fromDateEmp2) ? fromDateEmp2 : fromDateEmp1;
        LocalDate overLappingEnd = toDateEmp1.isBefore(toDateEmp2) ? toDateEmp1 : toDateEmp2;
        return ChronoUnit.DAYS.between(overLappingBegging, overLappingEnd) + 1;
    }
}
