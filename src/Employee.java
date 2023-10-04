import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private long employeeId;
    private long projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    public Employee() {
    }
    public Employee(long employeeId, long projectId, LocalDate dateFrom, LocalDate dateTo) throws InvalidTimeframeException {
        if(dateFrom.isAfter(dateTo)) throw new InvalidTimeframeException("Start date - %s is before end date - %s".formatted(dateFrom, dateTo));
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public long getProjectId() {
        return projectId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId && projectId == employee.projectId && Objects.equals(dateFrom, employee.dateFrom) && Objects.equals(dateTo, employee.dateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, projectId, dateFrom, dateTo);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", projectId=" + projectId +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
