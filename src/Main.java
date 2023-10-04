import      java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.zip.DataFormatException;

public class Main {
    public static void main(String[] args) {
        String fileLocation = new FileChoose().fileLocation();
        EmployeeProjManager epm;
        try {
            epm = parseCSV(fileLocation);
            if(epm.getMapProjIdEmployee().isEmpty()) {
                System.out.println("Data is not provided");
                return;
            }
            epm.getLongestProject();
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            e.printStackTrace();
        }
    }

    public static EmployeeProjManager parseCSV(String fileLocation) {
        EmployeeProjManager employeeProjManager = new EmployeeProjManager();
        try(BufferedReader bf = new BufferedReader(new FileReader(fileLocation))) {
            String line;
            while((line = bf.readLine()) != null) {
                String[] val = line.split(",");
                Employee employee = new Employee(Long.parseLong(val[0].trim()), Long.parseLong(val[1].trim()), dateBuilder(val[2].trim()),
                        val.length==3 || val[3]==null || val[3].toLowerCase().contains("null") ? LocalDate.now() : dateBuilder(val[3].trim()));
                employeeProjManager.addEmployee(employee);
            }
        } catch (IOException | DataFormatException | ArrayIndexOutOfBoundsException | DateTimeParseException |
                 NullPointerException | InvalidTimeframeException e) {
            e.printStackTrace();
        }
        return employeeProjManager;
    }
    public static LocalDate dateBuilder(String date) throws DataFormatException {
        String dateTrimmed = date.trim();
        String datePattern = dateFormatter(dateTrimmed);
        DateTimeFormatter df =  DateTimeFormatter.ofPattern(datePattern, Locale.ITALIAN);
        LocalDate formattedDate = LocalDate.parse(date, df);
        System.out.println(formattedDate);
        return formattedDate;
    }
    public static String dateFormatter(String date) throws DataFormatException {
        if(date.contains(".")) return dateFormatBuilder(date, "\\.");
        if(date.contains("-")) return dateFormatBuilder(date, "-");
        if(date.contains("/")) return dateFormatBuilder(date, "/");
        if(date.contains(" ")) return dateFormatBuilder(date, " ");
        throw new DataFormatException("Unsupported date format is provided.");
    }

    public static String dateFormatBuilder(String date, String delim) {
        StringBuilder sb = new StringBuilder();
        String[] val = date.split(delim);
        if(delim=="\\.")
            delim = ".";
        //Supports yyyy/mm/dd format
        if(val[0].length() == 4) {
            sb.append("yyyy");
            sb.append(delim);

            for(int i = 0; i < val[1].length(); i++) {
                if(i < 4) sb.append("M");
            }
            sb.append(delim);

            for(int i = 0; i < val[2].length(); i++) sb.append("d");
        }
        // Supports dd/mm/yyyy format
        else {
            for(int i = 0; i < val[0].length(); i++) sb.append("d");
            sb.append(delim);
            for(int i = 0; i < val[1].length(); i++) {
                if(i < 4) sb.append("M");
            }
            sb.append(delim);

            sb.append("yyyy");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
