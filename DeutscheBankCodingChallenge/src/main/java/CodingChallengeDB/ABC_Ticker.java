package CodingChallengeDB;

import static tech.tablesaw.aggregate.AggregateFunctions.first;
import static tech.tablesaw.aggregate.AggregateFunctions.last;
import static tech.tablesaw.aggregate.AggregateFunctions.max;
import static tech.tablesaw.aggregate.AggregateFunctions.min;
import static tech.tablesaw.aggregate.AggregateFunctions.sum;

import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

public class ABC_Ticker implements Ticker {

	// Als erstes wird die Tablle nach den jeweiligen Ticker gefiltert. Und dann
	// wird eine zusammengefasste Tabelle erstellt(mit Maximum,Minimum
	// Erster,Letzter und Summe des Preises), welche nach Datum sortiert ist. Es
	// wird eine weitere Tablelle erstellt mit der Summe von number of
	// securities traded. Dessen Spalte wird mit einer Summe von Preisen Spalte
	// mutlipliziert um die Daily trading volume Spalte zu erhalten und einzufügen.
	@Override
	public Table createTickerTable(Table table) {
		Table ABCtab = table.where(table.stringColumn("Company_Ticker").isEqualTo("ABC"));
		Table sumABC = ABCtab.summarize("Price", max, min, first, last, sum)
				.by(ABCtab.dateTimeColumn("Date_Time").date());
		sumABC.setName("ABC TABLE");

		Table sumOfTradesABC = ABCtab.summarize("number of securities traded", sum)
				.by(ABCtab.dateTimeColumn("Date_Time").date());
		DoubleColumn sumPriceABC = sumABC.doubleColumn(5);
		DoubleColumn sumTradesABC = sumOfTradesABC.doubleColumn(1);
		DoubleColumn dailyTradeVolABC = sumPriceABC.multiply(sumTradesABC);
		sumABC.addColumns(dailyTradeVolABC);
		sumABC.column(6).setName("Daily trading Volume");
		sumABC.column(1).setName("Highest Price");
		sumABC.column(2).setName("Lowest Price");
		sumABC.column(3).setName("Open Price");
		sumABC.column(4).setName("Close Price");
		sumABC.removeColumns(5);
		return sumABC;
	}

}
