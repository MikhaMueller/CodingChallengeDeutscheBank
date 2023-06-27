package CodingChallengeDB;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tech.tablesaw.api.Table;

class CSVReaderTest {

	@Test
	public void testCSVReaderNotNull() {
		String file = "C:\\Users\\Michael Müller\\test-market1.csv";

		CSVReader csv = new CSVReader();
		Table table = csv.readCSV(file);
		assertNotNull(table);

	}

	@Test
	public void testCSVReaderTableHas4Columns() {
		String file = "C:\\Users\\Michael Müller\\test-market1.csv";

		CSVReader csv = new CSVReader();
		Table table = csv.readCSV(file);
		int a = table.columnCount();
		assertEquals(4, a);

	}

}
