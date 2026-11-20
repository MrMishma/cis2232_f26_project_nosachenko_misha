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

## Sprint 3 - Vinyl Listing Maintenance (CRUD) ##

**Use Case:** Lets staff add, view, update and delete vinyl listings.  The price is calculated automatically every time a listing is saved.

**Navigation:**
* Vinyl Listings > View Listings  (/vinyllisting)
* Vinyl Listings > Add Listing  (/vinyllisting/add)
* Edit and Delete buttons are on each row of the list  (/vinyllisting/edit/{id} and /vinyllisting/delete/{id})

**Processing:**
* Spring Data JPA is used for all database access (VinylListingRepository extends CrudRepository).
* List uses findAll(), Edit uses findById(), Delete uses existsById() and deleteById().
* Add and Update both use save():  a new listing (no id) is inserted, an existing listing (has an id) is updated.
* The price is calculated in VinylListingBO.calculateVinylPrice() before saving (see Calculation above).
* The created date/time is set automatically when a new listing is added and is kept when a listing is edited.
* Delete asks for confirmation before removing the listing.

**Validation (add and update):**
* Record Name:  required, maximum 100 characters
* Seller Name:  required, maximum 50 characters
* Release Year:  required, between 1948 and 2026
* Genre:  required, must be Country, Pop, Rock, Jazz or Electronic
* Colour:  required, must be Black, Red or White
* Size:  required, must be Seven-inch, Ten-inch or Twelve-inch
* Condition:  required, Sealed or Used
* Price and created date are not entered by the user (calculated / set by the application)
* When validation fails, the form is shown again with the user's values and a message under each field with an error.  Nothing is saved.

**Test data:**

Before testing, run createDatabase.sql to load the 8 sample listings.

List:
* Vinyl Listings > View Listings shows all 8 sample listings with their prices (example: Kind of Blue - Jazz, Black, Twelve-inch, Sealed - $92.00)

Add:
* Add a Jazz, Black, Twelve-inch, Sealed record - price is $92.00 and today's date is shown as Created
* Add a Pop, White, Seven-inch, Used record - price is $23.00
* Add a Rock, Red, Ten-inch, Sealed record - price is $69.00

Update:
* Edit the Jazz record added above and change it to Used - price changes to $46.00, the id and Created date stay the same and no new row is added
* Edit the same record and change the size to Seven-inch - price changes to $11.50

Delete:
* Click Delete on a listing and choose Cancel - nothing is removed
* Click Delete on a listing and choose OK - the listing is removed and "Listing X deleted" is shown
* Go to /vinyllisting/delete/999 - "Listing 999 was not found" is shown

Validation:
* Submit the Add form with every field empty - error messages are shown for Record Name, Seller Name, Release Year, Genre, Colour and Size, and nothing is saved
* Enter only spaces for Record Name - "Record name is required"
* Enter 2099 for Release Year - "Release year cannot be in the future"
* Enter 1900 for Release Year - "Release year must be 1948 or later"
* Edit a listing, clear the Seller Name and submit - the error is shown and the page still says Edit Vinyl Listing; fix it and submit - the listing is updated (not duplicated)

Reports:
* After adding a listing, the Seller Name Report finds it (JPA and the JDBC report use the same table)
