package CodingChallengeDB;

import static tech.tablesaw.aggregate.AggregateFunctions.first;
import static tech.tablesaw.aggregate.AggregateFunctions.last;
import static tech.tablesaw.aggregate.AggregateFunctions.max;
import static tech.tablesaw.aggregate.AggregateFunctions.min;
import static tech.tablesaw.aggregate.AggregateFunctions.sum;

import tech.tablesaw.api.DateColumn;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

public class NGL_Ticker implements Ticker {

	// Als erstes wird die Tablle nach den jeweiligen Ticker gefiltert. Und dann
	// wird eine zusammengefasste Tabelle erstellt(mit Maximum,Minimum
	// Erster,Letzter und Summe des Preises), welche nach Datum sortiert ist. Es
	// wird eine weitere Tablelle erstellt mit der Summe von number of
	// securities traded. Dessen Spalte wird mit einer Summe von Preisen Spalte
	// mutlipliziert um die Daily trading volume Spalte zu erhalten und einzufügen.
	@Override
	public Table createTickerTable(Table table) {
		Table NGLtab = table.where(table.stringColumn("Company_Ticker").isEqualTo("NGL"));
		Table sumNGL = NGLtab.summarize("Price", max, min, first, last, sum)
				.by(NGLtab.dateTimeColumn("Date_Time").date());
		sumNGL.setName("NGL TABLE");

		Table sumOfTradesNGL = NGLtab.summarize("number of securities traded", sum)
				.by(NGLtab.dateTimeColumn("Date_Time").date());
		DoubleColumn sumPriceNGL = sumNGL.doubleColumn(5);
		DoubleColumn sumTradesNGL = sumOfTradesNGL.doubleColumn(1);
		DoubleColumn dailyTradeVolNGL = sumPriceNGL.multiply(sumTradesNGL);
		sumNGL.addColumns(dailyTradeVolNGL);
		sumNGL.column(6).setName("Daily trade Volume");
		sumNGL.removeColumns(5);
		return sumNGL;
	}

	// Hier werden Spalten erstellt mit den Datum wo kein Trade des Tickers
	// stattfindet und aus den Spalten eine Table erstellt.

	public Table createNGLwithMissingValues(Table sumNGL, DateColumn datecolumn) {
		double[] max = { sumNGL.doubleColumn(1).getDouble(0), 0.00, sumNGL.doubleColumn(1).getDouble(1),
				sumNGL.doubleColumn(1).getDouble(2), sumNGL.doubleColumn(1).getDouble(3),
				sumNGL.doubleColumn(1).getDouble(4), sumNGL.doubleColumn(1).getDouble(5) };

		double[] min = { sumNGL.doubleColumn(2).getDouble(0), 0.00, sumNGL.doubleColumn(2).getDouble(1),
				sumNGL.doubleColumn(2).getDouble(2), sumNGL.doubleColumn(2).getDouble(3),
				sumNGL.doubleColumn(2).getDouble(4), sumNGL.doubleColumn(2).getDouble(5) };

		double[] first = { sumNGL.doubleColumn(3).getDouble(0), 0.00, sumNGL.doubleColumn(3).getDouble(1),
				sumNGL.doubleColumn(3).getDouble(2), sumNGL.doubleColumn(3).getDouble(3),
				sumNGL.doubleColumn(3).getDouble(4), sumNGL.doubleColumn(3).getDouble(5) };

		double[] last = { sumNGL.doubleColumn(4).getDouble(0), 0.00, sumNGL.doubleColumn(4).getDouble(1),
				sumNGL.doubleColumn(4).getDouble(2), sumNGL.doubleColumn(4).getDouble(3),
				sumNGL.doubleColumn(4).getDouble(4), sumNGL.doubleColumn(4).getDouble(5) };

		double[] vol = { sumNGL.doubleColumn(5).getDouble(0), 0.00, sumNGL.doubleColumn(5).getDouble(1),
				sumNGL.doubleColumn(5).getDouble(2), sumNGL.doubleColumn(5).getDouble(3),
				sumNGL.doubleColumn(5).getDouble(4), sumNGL.doubleColumn(5).getDouble(5) };

		DoubleColumn colMax = DoubleColumn.create("Highest Price", max);
		DoubleColumn colMin = DoubleColumn.create("Lowest Price", min);
		DoubleColumn colFirst = DoubleColumn.create("Open Price", first);
		DoubleColumn colLast = DoubleColumn.create("Close Price", last);
		DoubleColumn colVol = DoubleColumn.create("Daily trading Volume", vol);
		Table sumTab = Table.create("NGL", datecolumn, colMax, colMin, colFirst, colLast, colVol);

		return sumTab;
	}

}
