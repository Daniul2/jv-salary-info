package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate periodStart = LocalDate.parse(dateFrom.trim(), DATE_FORMATTER);
        LocalDate periodEnd = LocalDate.parse(dateTo.trim(), DATE_FORMATTER);
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom.trim())
                .append(" - ")
                .append(dateTo.trim())
                .append(System.lineSeparator());

        for (String employeeName : names) {
            int salaryTotal = 0;

            for (String dates : data) {
                String[] employeeInfo = dates.split(" ");
                String startDateWork = employeeInfo[0];
                String nameOfEmployee = employeeInfo[1];
                int hoursWorked = Integer.parseInt(employeeInfo[2]);
                int hourlyRate = Integer.parseInt(employeeInfo[3]);

                if (nameOfEmployee.equals(employeeName)) {
                    LocalDate workDate = LocalDate.parse(startDateWork, DATE_FORMATTER);
                    if (!workDate.isBefore(periodStart) && !workDate.isAfter(periodEnd)) {
                        salaryTotal += hoursWorked * hourlyRate;
                    }
                }
            }
            report.append(employeeName)
                    .append(" - ")
                    .append(salaryTotal)
                    .append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
