package CodingChallengeDB;

import static tech.tablesaw.aggregate.AggregateFunctions.first;
import static tech.tablesaw.aggregate.AggregateFunctions.last;
import static tech.tablesaw.aggregate.AggregateFunctions.max;
import static tech.tablesaw.aggregate.AggregateFunctions.min;
import static tech.tablesaw.aggregate.AggregateFunctions.sum;

import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

public class MEGA_Ticker implements Ticker {

	// Als erstes wird die Tablle nach den jeweiligen Ticker gefiltert. Und dann
	// wird eine zusammengefasste Tabelle erstellt(mit Maximum,Minimum
	// Erster,Letzter und Summe des Preises), welche nach Datum sortiert ist. Es
	// wird eine weitere Tablelle erstellt mit der Summe von number of
	// securities traded. Dessen Spalte wird mit einer Summe von Preisen Spalte
	// mutlipliziert um die Daily trading volume Spalte zu erhalten und einzufügen.
	@Override
	public Table createTickerTable(Table table) {
		Table MEGAtab = table.where(table.stringColumn("Company_Ticker").isEqualTo("MEGA"));
		Table sumMEGA = MEGAtab.summarize("Price", max, min, first, last, sum)
				.by(MEGAtab.dateTimeColumn("Date_Time").date());
		sumMEGA.setName("MEGA TABLE");

		Table sumOfTradesMEGA = MEGAtab.summarize("number of securities traded", sum)
				.by(MEGAtab.dateTimeColumn("Date_Time").date());
		DoubleColumn sumPriceMEGA = sumMEGA.doubleColumn(5);
		DoubleColumn sumTradesMEGA = sumOfTradesMEGA.doubleColumn(1);
		DoubleColumn dailyTradeVolMEGA = sumPriceMEGA.multiply(sumTradesMEGA);
		sumMEGA.addColumns(dailyTradeVolMEGA);
		sumMEGA.column(6).setName("Daily trading Volume");
		sumMEGA.column(1).setName("Highest Price");
		sumMEGA.column(2).setName("Lowest Price");
		sumMEGA.column(3).setName("Open Price");
		sumMEGA.column(4).setName("Close Price");
		sumMEGA.removeColumns(5);
		return sumMEGA;
	}

}
