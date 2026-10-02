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

### Seller name report ###

(To be confirmed by BA)  Enter a seller name and the report will return any listings that have that name 'like' the seller name.
