import java.util.Objects;

public class EmployeeLongestCollaboration {
    private EmployeePair employeeWorkingPair;

    public EmployeeLongestCollaboration() {
        this.employeeWorkingPair = new EmployeePair();
        employeeWorkingPair.setDays(0);
    }

    public Long getWorkingDays(){
        return employeeWorkingPair.getDays();
    }

    public EmployeePair getEmployeePair() {
        return employeeWorkingPair;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeLongestCollaboration that = (EmployeeLongestCollaboration) o;
        return Objects.equals(employeeWorkingPair, that.employeeWorkingPair);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeWorkingPair);
    }

    @Override
    public String toString() {
        return employeeWorkingPair.toString();
    }

    /***
     * Updates the current pair, if longer duration is found.
     */
    public void updateIfLongerWorked(Employee employee1, Employee employee2) {
        long daysTogether = EmployeePair.calculateDays(employee1, employee2);
        if(daysTogether > employeeWorkingPair.getDays()) updatePair(employee1, employee2, daysTogether);
    }

    /***
     * Updates the EmployeePair, by providing the two employees, duration and project ID.
     */
    private void updatePair(Employee employee1, Employee employee2, long daysTogether) {
        employeeWorkingPair = new EmployeePair(employee1.getEmployeeId(), employee2.getEmployeeId(),
                employee1.getProjectId(), daysTogether);
    }
}