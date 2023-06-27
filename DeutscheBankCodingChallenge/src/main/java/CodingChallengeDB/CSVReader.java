package CodingChallengeDB;

import java.time.format.DateTimeFormatter;

import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

public class CSVReader {

	// CSV wird eingelesen und aus den Daten eine Table gebaut. Mithilfe von
	// Tablesaw.
	public Table readCSV(String file) {
		DateTimeFormatter formatte = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		CsvReadOptions csvReadOptions = CsvReadOptions.builder(file).separator(';').header(false)
				.dateTimeFormat(formatte).build();

		Table table = Table.read().usingOptions(csvReadOptions);
		table.column("C0").setName("Date_Time");
		table.column("C1").setName("Company_Ticker");
		table.column("C2").setName("Price");
		table.column("C3").setName("number of securities traded");
		return table;
	}

}
