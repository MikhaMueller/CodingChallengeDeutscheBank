# CodingChallengeDeutscheBank
The Task was to calculate some daily aggregates for the provided csv-file. For every date in a log and for every traded ticker, daily values should be printed. The daily values are open price (price of the first deal in the day), close price (price of the last deal in the day), highest price, lowest price, daily traded volume (sum of price*number_traded). The daily values of the INDEX Ticker should be printed out aswell. The INDEX is a weighted sum of ticker prices at an instant. Th implementation of this coding challenge can be found in this repository.
## Description
I used the library tablesaw (version:0.43.1) to manage the Task.

### Reading CSV-File
At first the csv-file should be read. I created therefore a CSVReader class, which has a method that takes in a String(file) and reads the csv-file to create a table out of the data.

### Daily aggregates for the Ticker
I created an interface  Ticker, which gets implemented by the diffrent Ticker classes. All the Ticker classes have a method called createTickerTable, which takes in the table of the whole csv-file and calculates the daily aggregates for the Ticker and puts them in a table. Some Ticker don't have trades everyday and for them the missing values will be added in their Tables These will be printed out.

### Creation of Index
The Index will be created by the Index class. With the method createIndexColumn the columns for the Index Table will be created. Therfore it takes in the beforehand created Ticker tables and calculates the daily weighted sum of ticker prices. In the Runner class the columns are put together to create and print out the Index table. For the Tickers that have no trades in a day, the previous price is used.

### Test
Tests for the CSVReader can be found here /src/test/java/CodingChallengeDB/CSVReaderTest.java

Tests for the Ticker and his createTickerTable are here /src/test/java/CodingChallengeDB/TickerTest.java
The Index Creation was tested in /src/test/java/CodingChallengeDB/IndexTest.java
