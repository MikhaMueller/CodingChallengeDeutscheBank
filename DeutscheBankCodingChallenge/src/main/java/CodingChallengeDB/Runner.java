package CodingChallengeDB;

import java.time.format.DateTimeFormatter;

import tech.tablesaw.api.DateColumn;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

public class Runner {

	public static void main(String[] args) {

		String file = "C:\\Users\\Michael Müller\\test-market1.csv";

		CSVReader csv = new CSVReader();
		Table table = csv.readCSV(file);

		// All Ticker Table
		AllTicker all = new AllTicker();
		Table sumall = all.createTickerTable(table);
		System.out.println(sumall);
		DateColumn coldate = sumall.dateColumn(0);

		// TRX Table
		TRX_Ticker trx = new TRX_Ticker();
		Table sumTRX = trx.createTickerTable(table);
		
		Table TRXtab = trx.createTRXwithMissingValues(sumTRX, coldate);
		System.out.println(TRXtab);
		// ABC Table
		ABC_Ticker abc = new ABC_Ticker();
		Table sumABC = abc.createTickerTable(table);
		System.out.println(sumABC);
		// NGL Table
		NGL_Ticker ngl = new NGL_Ticker();
		Table sumNGL = ngl.createTickerTable(table);
		
		Table NGLtab = ngl.createNGLwithMissingValues(sumNGL, coldate);
		System.out.println(NGLtab);

		// MEGA Table
		MEGA_Ticker mega = new MEGA_Ticker();
		Table sumMEGA = mega.createTickerTable(table);
		System.out.println(sumMEGA);

		// Index Table

		Index index = new Index();
		// First create the Columns of the table
		DoubleColumn colMax = index.createIndexColumn(sumTRX, sumABC, sumNGL, sumMEGA, 1);
		DoubleColumn colMin = index.createIndexColumn(sumTRX, sumABC, sumNGL, sumMEGA, 2);
		DoubleColumn colFirst = index.createIndexColumn(sumTRX, sumABC, sumNGL, sumMEGA, 3);
		DoubleColumn colLast = index.createIndexColumn(sumTRX, sumABC, sumNGL, sumMEGA, 4);
		DoubleColumn colVol = index.createIndexColumn(sumTRX, sumABC, sumNGL, sumMEGA, 5);

		Table indexTab = Table.create("Index", coldate, colMax, colMin, colFirst, colLast, colVol);
		System.out.println(indexTab);

	}

}
