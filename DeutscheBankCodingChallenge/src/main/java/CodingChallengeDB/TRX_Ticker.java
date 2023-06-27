package CodingChallengeDB;

import static tech.tablesaw.aggregate.AggregateFunctions.first;
import static tech.tablesaw.aggregate.AggregateFunctions.last;
import static tech.tablesaw.aggregate.AggregateFunctions.max;
import static tech.tablesaw.aggregate.AggregateFunctions.min;
import static tech.tablesaw.aggregate.AggregateFunctions.sum;

import tech.tablesaw.api.DateColumn;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

public class TRX_Ticker implements Ticker {

	// Als erstes wird die Tablle nach den jeweiligen Ticker gefiltert. Und dann
	// wird eine zusammengefasste Tabelle erstellt(mit Maximum,Minimum
	// Erster,Letzter und Summe des Preises), welche nach Datum sortiert ist. Es
	// wird eine weitere Tablelle erstellt mit der Summe von number of
	// securities traded. Dessen Spalte wird mit einer Summe von Preisen Spalte
	// mutlipliziert um die Daily trading volume Spalte zu erhalten und einzufügen.
	@Override
	public Table createTickerTable(Table table) {
		Table TRXtab = table.where(table.stringColumn("Company_Ticker").isEqualTo("TRX"));
		Table sumTRX = TRXtab.summarize("Price", max, min, first, last, sum)
				.by(TRXtab.dateTimeColumn("Date_Time").date());
		sumTRX.setName("TRX TABLE");

		Table sumOfTrades = TRXtab.summarize("number of securities traded", sum)
				.by(TRXtab.dateTimeColumn("Date_Time").date());
		DoubleColumn sumPriceTRX = sumTRX.doubleColumn(5);
		DoubleColumn sumTradesTRX = sumOfTrades.doubleColumn(1);
		DoubleColumn dailyTradeVol = sumPriceTRX.multiply(sumTradesTRX);
		sumTRX.addColumns(dailyTradeVol);
		sumTRX.column(6).setName("Daily trade Volume");
		sumTRX.removeColumns(5);
		return sumTRX;
	}

	// Hier werden Spalten erstellt mit den Datum wo kein Trade des Tickers
	// stattfindet und aus den Spalten eine Table erstellt.

	public Table createTRXwithMissingValues(Table sumTRX, DateColumn datecolumn) {
		double[] max = { sumTRX.doubleColumn(1).getDouble(0), 0.00, sumTRX.doubleColumn(1).getDouble(1),
				sumTRX.doubleColumn(1).getDouble(2), sumTRX.doubleColumn(1).getDouble(3),
				sumTRX.doubleColumn(1).getDouble(4), 0.00 };

		double[] min = { sumTRX.doubleColumn(2).getDouble(0), 0.00, sumTRX.doubleColumn(2).getDouble(1),
				sumTRX.doubleColumn(2).getDouble(2), sumTRX.doubleColumn(2).getDouble(3),
				sumTRX.doubleColumn(2).getDouble(4), 0.00 };

		double[] first = { sumTRX.doubleColumn(3).getDouble(0), 0.00, sumTRX.doubleColumn(3).getDouble(1),
				sumTRX.doubleColumn(3).getDouble(2), sumTRX.doubleColumn(3).getDouble(3),
				sumTRX.doubleColumn(3).getDouble(4), 0.00 };

		double[] last = { sumTRX.doubleColumn(4).getDouble(0), 0.00, sumTRX.doubleColumn(4).getDouble(1),
				sumTRX.doubleColumn(4).getDouble(2), sumTRX.doubleColumn(4).getDouble(3),
				sumTRX.doubleColumn(4).getDouble(4), 0.00 };

		double[] vol = { sumTRX.doubleColumn(5).getDouble(0), 0.00, sumTRX.doubleColumn(5).getDouble(1),
				sumTRX.doubleColumn(5).getDouble(2), sumTRX.doubleColumn(5).getDouble(3),
				sumTRX.doubleColumn(5).getDouble(4), 0.00 };

		DoubleColumn colMax = DoubleColumn.create("Highest Price", max);
		DoubleColumn colMin = DoubleColumn.create("Lowest Price", min);
		DoubleColumn colFirst = DoubleColumn.create("Open Price", first);
		DoubleColumn colLast = DoubleColumn.create("Close Price", last);
		DoubleColumn colVol = DoubleColumn.create("Daily trading Volume", vol);
		Table sumTab = Table.create("TRX", datecolumn, colMax, colMin, colFirst, colLast, colVol);

		return sumTab;

	}
}
