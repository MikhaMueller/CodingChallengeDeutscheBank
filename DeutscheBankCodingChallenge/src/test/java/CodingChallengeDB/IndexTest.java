package CodingChallengeDB;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tech.tablesaw.api.DateColumn;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

class IndexTest {

	@Test
	public void testIndexCreation() {
		String file = "C:\\Users\\Michael Müller\\test-market1.csv";

		CSVReader csv = new CSVReader();
		Table table = csv.readCSV(file);

		// All Ticker Table
		AllTicker all = new AllTicker();
		Table sumall = all.createTickerTable(table);
		
		DateColumn coldate = sumall.dateColumn(0);

		// TRX Table
		TRX_Ticker trx = new TRX_Ticker();
		Table sumTRX = trx.createTickerTable(table);
		
		Table TRXtab = trx.createTRXwithMissingValues(sumTRX, coldate);
		
		// ABC Table
		ABC_Ticker abc = new ABC_Ticker();
		Table sumABC = abc.createTickerTable(table);
		
		// NGL Table
		NGL_Ticker ngl = new NGL_Ticker();
		Table sumNGL = ngl.createTickerTable(table);
		
		Table NGLtab = ngl.createNGLwithMissingValues(sumNGL, coldate);
		

		// MEGA Table
		MEGA_Ticker mega = new MEGA_Ticker();
		Table sumMEGA = mega.createTickerTable(table);
		

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
		double sum1= 4038.11*0.2+1000.09*0.1+5197.55*0.4+3015.11*0.3;
		double sum2= 4038.11*0.2+1000.17*0.1+5197.55*0.4+3213.44*0.3;
		assertEquals(sum1,indexTab.doubleColumn(1).getDouble(0));
		assertEquals(sum2, indexTab.doubleColumn(1).getDouble(1));
	}

}
