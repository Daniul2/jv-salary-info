package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate periodStart = LocalDate.parse(dateFrom.trim(), DATE_FORMATTER);
        LocalDate periodEnd = LocalDate.parse(dateTo.trim(), DATE_FORMATTER);
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom.trim())
                .append(" - ")
                .append(dateTo.trim())
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            String employeeName = names[i].trim();
            int salaryTotal = 0;

            for (String employeeRecord : data) {
                String[] employeeInfo = employeeRecord.trim().split("\\s+");
                if (employeeInfo.length < 4) {
                    continue;
                }
                String workDateString = employeeInfo[DATE_INDEX].trim();
                String nameOfEmployee = employeeInfo[NAME_INDEX].trim();
                int hoursWorked = Integer.parseInt(employeeInfo[HOURS_INDEX].trim());
                int hourlyRate = Integer.parseInt(employeeInfo[RATE_INDEX].trim());

                if (nameOfEmployee.equals(employeeName)) {
                    LocalDate workDate = LocalDate.parse(workDateString, DATE_FORMATTER);
                    if (!workDate.isBefore(periodStart) && !workDate.isAfter(periodEnd)) {
                        salaryTotal += hoursWorked * hourlyRate;
                    }
                }
            }
            report.append(employeeName)
                    .append(" - ")
                    .append(salaryTotal);
            if (i < names.length - 1) {
                report.append(System.lineSeparator());
            }
        }
        return report.toString();
    }
}
