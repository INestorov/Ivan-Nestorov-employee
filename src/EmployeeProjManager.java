import java.util.*;

public class EmployeeProjManager {
    private Map<Long, List<Employee>> mapProjIdEmployee;
    private EmployeePair employeePair;
    public EmployeeProjManager() {
        mapProjIdEmployee = new HashMap<>();
    }

    public Map<Long, List<Employee>> getMapProjIdEmployee() {
        return mapProjIdEmployee;
    }

    public EmployeePair getEmployeePair() {
        return employeePair;
    }

    /***
     * This function
     * @param employee
     * @return
     */
    public boolean addEmployee(Employee employee) {
        if(!mapProjIdEmployee.containsKey(employee.getProjectId()))
            mapProjIdEmployee.put(employee.getProjectId(), new ArrayList<>());
        return mapProjIdEmployee.get(employee.getProjectId()).add(employee);
    }

    public void getLongestProject() {
        if(employeePair == null) getLongestCollaboration();
        System.out.println("Employee #1, Employee #2, Project ID, Days");
        for(Map.Entry<Long, List<Employee>> entry: mapProjIdEmployee.entrySet()) {
            List<Employee> commons = entry.getValue().stream().filter(x -> x.getEmployeeId() == employeePair.getEmpId1() || x.getEmployeeId() == employeePair.getEmpId2()).toList();
            if(commons.size() == 2) {
                Long days = EmployeePair.calculateDays(commons.get(0), commons.get(1));
                System.out.println("%s, %s, %s, %s".formatted(commons.get(0).getEmployeeId(),
                        commons.get(1).getEmployeeId(), entry.getKey(), days));

            }
        }
    }
    public void getLongestCollaboration() {
        List<EmployeeLongestCollaboration> elc = new ArrayList<>();
        for(List<Employee> employees: mapProjIdEmployee.values()) {
            elc.add(computeLongestCollaboration(employees));
        }
        EmployeeLongestCollaboration employeeLongestCollaboration = elc.stream()
                .max(Comparator.comparingLong(EmployeeLongestCollaboration::getWorkingDays)).orElse(null);
        assert employeeLongestCollaboration != null;
        employeePair = employeeLongestCollaboration.getEmployeePair();
    }
    public EmployeeLongestCollaboration computeLongestCollaboration(List<Employee> employees){
        Employee[] empArr = employees.toArray(new Employee[0]);
        EmployeeLongestCollaboration elc = new EmployeeLongestCollaboration();
        for(int i = 0; i < empArr.length-1; i++) {
            for(int j = i + 1; j < empArr.length; j++){
               elc.updateIfLongerWorked(empArr[i], empArr[j]);
            }
        }
        return elc;
    }
}
