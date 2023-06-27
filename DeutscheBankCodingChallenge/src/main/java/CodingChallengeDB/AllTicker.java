package CodingChallengeDB;

import static tech.tablesaw.aggregate.AggregateFunctions.first;
import static tech.tablesaw.aggregate.AggregateFunctions.last;
import static tech.tablesaw.aggregate.AggregateFunctions.max;
import static tech.tablesaw.aggregate.AggregateFunctions.min;
import static tech.tablesaw.aggregate.AggregateFunctions.sum;

import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

public class AllTicker implements Ticker {

	@Override
	public Table createTickerTable(Table table) {

		Table sumall = table.summarize("Price", max, min, first, last, sum)
				.by(table.dateTimeColumn("Date_Time").date());
		sumall.setName("All TABLE");

		Table sumOfTrades = table.summarize("number of securities traded", sum)
				.by(table.dateTimeColumn("Date_Time").date());
		DoubleColumn sumPrice = sumall.doubleColumn(5);
		DoubleColumn sumTrades = sumOfTrades.doubleColumn(1);
		DoubleColumn dailyTradeVol = sumPrice.multiply(sumTrades);
		sumall.addColumns(dailyTradeVol);
		sumall.column(6).setName("Daily trading Volume");
		sumall.column(1).setName("Highest Price");
		sumall.column(2).setName("Lowest Price");
		sumall.column(3).setName("Open Price");
		sumall.column(4).setName("Close Price");
		sumall.removeColumns(5);
		return sumall;
	}

}
