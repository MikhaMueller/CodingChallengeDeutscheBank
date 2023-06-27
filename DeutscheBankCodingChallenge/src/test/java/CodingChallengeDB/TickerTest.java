package CodingChallengeDB;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tech.tablesaw.api.DateTimeColumn;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

class TickerTest {

	Table TickerDummyTable;

	@BeforeEach
	public void setup() {
		StringColumn sc = StringColumn.create("Company_Ticker",
				new String[] { "ABC", "TRX", "TRX", "MEGA", "NGL", "ABC", "MEGA", "ABC", "TRX", "NGL", "MEGA", "NGL" });
		double[] dc = { 12.34, 23.45, 5.4, 4.67, 10.65, 14.50, 3.89, 8.99, 24.64, 7.89, 1.89, 13.46 };
		DoubleColumn DC = DoubleColumn.create("Price", dc);
		int[] ic = { 5, 2, 6, 12, 45, 23, 21, 35, 42, 13, 34, 17 };
		IntColumn IC = IntColumn.create("number of securities traded", ic);

		List<LocalDateTime> date = new ArrayList<>();
		LocalDateTime date1 = LocalDateTime.parse("2022-06-07T18:45:44");
		LocalDateTime date2 = LocalDateTime.parse("2022-06-06T17:45:44");
		LocalDateTime date3 = LocalDateTime.parse("2022-06-05T18:45:44");
		LocalDateTime date4 = LocalDateTime.parse("2022-06-07T18:45:44");
		LocalDateTime date5 = LocalDateTime.parse("2022-06-05T18:45:44");
		LocalDateTime date6 = LocalDateTime.parse("2022-06-07T18:45:44");
		LocalDateTime date7 = LocalDateTime.parse("2022-06-05T18:45:44");
		LocalDateTime date8 = LocalDateTime.parse("2022-06-07T18:45:44");
		LocalDateTime date9 = LocalDateTime.parse("2022-06-06T18:45:44");
		LocalDateTime date10 = LocalDateTime.parse("2022-06-05T18:45:44");
		LocalDateTime date11 = LocalDateTime.parse("2022-06-07T18:45:44");
		LocalDateTime date12 = LocalDateTime.parse("2022-06-07T18:45:44");
		date.add(date1);
		date.add(date2);
		date.add(date3);
		date.add(date4);
		date.add(date5);
		date.add(date6);
		date.add(date7);
		date.add(date8);
		date.add(date9);
		date.add(date10);
		date.add(date11);
		date.add(date12);

		TickerDummyTable = Table.create("DummyTable", DateTimeColumn.create("Date_Time", date), sc, DC, IC);
		// System.out.println(TickerDummyTable);

	}

	@Test
	public void testcreateAllTickerTableMax() {
		AllTicker all = new AllTicker();
		Table sumall = all.createTickerTable(TickerDummyTable);

		assertEquals(14.5, sumall.doubleColumn(1).getDouble(0));

	}

	@Test
	public void testcreateTRXTickerTableMin() {
		TRX_Ticker trx = new TRX_Ticker();
		Table sumTRX = trx.createTickerTable(TickerDummyTable);

		assertEquals(23.45, sumTRX.doubleColumn(2).getDouble(0));

	}

	@Test
	public void testcreateABCTickerTableFirst() {
		ABC_Ticker abc = new ABC_Ticker();
		Table sumABC = abc.createTickerTable(TickerDummyTable);

		assertEquals(12.34, sumABC.doubleColumn(3).getDouble(0));

	}

	@Test
	public void testcreateNGLTickerTableLast() {
		NGL_Ticker ngl = new NGL_Ticker();
		Table sumNGL = ngl.createTickerTable(TickerDummyTable);

		assertEquals(7.89, sumNGL.doubleColumn(4).getDouble(0));

	}

	@Test
	public void testcreateMEGATickerTableDailytradevol() {
		MEGA_Ticker mega = new MEGA_Ticker();
		Table sumMEGA = mega.createTickerTable(TickerDummyTable);
		System.out.println(sumMEGA);
		assertEquals(301, 76, sumMEGA.doubleColumn(5).getDouble(0));

	}

	// Es werden nicht alle aggregate values von jeweils allen Ticker-Methoden
	// getestet, da sie nahezu gleich sind.

}
