package CodingChallengeDB;

import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

public class Index {

	// Hier werden die Spalten erstellt aus der Summe der gewichteten Prices.
	public DoubleColumn createIndexColumn(Table sumTRX, Table sumABC, Table sumNGL, Table sumMEGA, int Colnum) {
		double[] column = {
				sumTRX.doubleColumn(Colnum).getDouble(0) * 0.2 + sumABC.doubleColumn(Colnum).getDouble(0) * 0.1
						+ sumNGL.doubleColumn(Colnum).getDouble(0) * 0.4
						+ sumMEGA.doubleColumn(Colnum).getDouble(0) * 0.3,
				sumTRX.doubleColumn(Colnum).getDouble(0) * 0.2 + sumABC.doubleColumn(Colnum).getDouble(1) * 0.1
						+ sumNGL.doubleColumn(Colnum).getDouble(0) * 0.4
						+ sumMEGA.doubleColumn(Colnum).getDouble(1) * 0.3,
				sumTRX.doubleColumn(Colnum).getDouble(1) * 0.2 + sumABC.doubleColumn(Colnum).getDouble(2) * 0.1
						+ sumNGL.doubleColumn(Colnum).getDouble(1) * 0.4
						+ sumMEGA.doubleColumn(Colnum).getDouble(2) * 0.3,
				sumTRX.doubleColumn(Colnum).getDouble(2) * 0.2 + sumABC.doubleColumn(Colnum).getDouble(3) * 0.1
						+ sumNGL.doubleColumn(Colnum).getDouble(2) * 0.4
						+ sumMEGA.doubleColumn(Colnum).getDouble(3) * 0.3,
				sumTRX.doubleColumn(Colnum).getDouble(3) * 0.2 + sumABC.doubleColumn(Colnum).getDouble(4) * 0.1
						+ sumNGL.doubleColumn(Colnum).getDouble(3) * 0.4
						+ sumMEGA.doubleColumn(Colnum).getDouble(4) * 0.3,
				sumTRX.doubleColumn(Colnum).getDouble(4) * 0.2 + sumABC.doubleColumn(Colnum).getDouble(5) * 0.1
						+ sumNGL.doubleColumn(Colnum).getDouble(4) * 0.4
						+ sumMEGA.doubleColumn(Colnum).getDouble(5) * 0.3,
				sumTRX.doubleColumn(Colnum).getDouble(4) * 0.2 + sumABC.doubleColumn(Colnum).getDouble(6) * 0.1
						+ sumNGL.doubleColumn(Colnum).getDouble(5) * 0.4
						+ sumMEGA.doubleColumn(Colnum).getDouble(6) * 0.3 };

		if (Colnum == 1) {
			DoubleColumn doublecolumn = DoubleColumn.create("Highest Price", column);
			return doublecolumn;
		} else if (Colnum == 2) {
			DoubleColumn doublecolumn = DoubleColumn.create("Lowest Price", column);
			return doublecolumn;
		} else if (Colnum == 3) {
			DoubleColumn doublecolumn = DoubleColumn.create("Open Price", column);
			return doublecolumn;
		} else if (Colnum == 4) {
			DoubleColumn doublecolumn = DoubleColumn.create("Close Price", column);
			return doublecolumn;
		} else if (Colnum == 5) {
			DoubleColumn doublecolumn = DoubleColumn.create("Daily Trading Volume", column);
			return doublecolumn;
		} else
			System.out.println("wrong column Number");
		return null;

	}

}
