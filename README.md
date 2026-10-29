# Backspin Records #

Vinyl marketplace - CIS2232 project

## Development Team ##

Business Client:  Isaac	<br/>
Lead Developer:  Misha	<br/>
Quality Control:  Lindsay	<br/>

## Description ##

Backspin Records is a marketplace for vinyl, where collectors and artists can list their records for sale, whether they are new (sealed) or used.  Records of different colours, genres, and sizes can be listed.  The seller enters the details of the record and the application automatically calculates the price of the record based on the information provided.

## Color ##

Main Color:  Old Rose (#A37774)	<br/>
Secondary Color:  Salmon (#E88873)	<br/>
Accent Color:  Melon (#E0AC9D)	<br/>
Dark Color:  Outer Space (#484A47)	<br/>
Neutral Color:  Payne's Gray (#5C6D70)	<br/>

## Required Fields ##

Table name:  vinyl_listing

id	int	Unique identifier for database table	<br/>
recordName	String	Name of the record	<br/>
sellerName	String	Name of the vinyl seller	<br/>
releaseYear	int	Year the vinyl was released	<br/>
vinylGenre	String	Genre of the record (Country, Pop, Rock, Jazz, Electronic)	<br/>
vinylColour	String	Colour of the record disc (Black, Red, White)	<br/>
recordSize	String	Size of the record (Seven-inch, Ten-inch, Twelve-inch)	<br/>
vinylIsSealed	boolean	Whether the vinyl is new/sealed (true) or used (false)	<br/>
vinylPrice	double	Calculated price of the record (including tax)	<br/>
createdDateTime	String	Date the listing was entered in the application	<br/>

## Calculation ##

The price of the vinyl is calculated when the listing is entered.  A base price of $10.00 is multiplied by each of the following factors and then 15% tax is added.

Condition:	<br/>
* Sealed (new):  x 2.00
* Used:  x 1.00

Colour:	<br/>
* Black:  x 1.00
* Red:  x 1.50
* White:  x 2.00

Size:	<br/>
* Seven-inch:  x 1.00
* Ten-inch:  x 2.00
* Twelve-inch:  x 4.00

vinylPrice = 10.00 x condition x colour x size x 1.15

Example:  A sealed, white, twelve-inch record = 10.00 x 2.00 x 2.00 x 4.00 = 160.00 + 15% tax = $184.00

## Report Details ##

### Seller Name Report ###

**Use Case:** Lets staff see every record a seller has listed, and what those listings are worth by genre.

**Input:**
* Seller name (full or partial, not case sensitive). Leaving it blank returns all listings.

**Processing:**
* A JDBC DAO selects all vinyl_listing rows where sellerName is LIKE the input, sorted by record name.

**Output (screen):**
* A table showing ID, Record Name, Seller Name, Release Year, Genre, Colour, Size, Condition (Sealed/Used) and Price
* A bar chart of the total listing value for each genre
* The message "No vinyl listings found for that seller" when there are no matches

**Output (file):**
* Saved to c:\cis2232\SellerNameReport<yyyyMMddHHmm>.txt (example: SellerNameReport202610251537.txt)
* One line per listing

**Test data:**
* "crate" returns Back in Black, Blue Train and Kind of Blue (chart: Jazz $138.00, Rock $46.00)
* "spin" returns Discovery, Random Access Memories and Thriller (chart: Electronic $253.00, Pop $69.00)
* "zzz" returns no results and shows the message
