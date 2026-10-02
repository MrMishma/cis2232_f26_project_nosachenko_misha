#For localhost
DROP DATABASE IF EXISTS cis2232_backspin_records;
CREATE DATABASE cis2232_backspin_records;
use cis2232_backspin_records;

-- ------------------------------------------------------------------------------
-- Backspin Records - vinyl marketplace
-- This table holds the vinyl records listed for sale by sellers.
--
-- Price calculation (done by the application when the listing is entered):
--   vinylPrice = 10.00 (base) x condition x colour x size x 1.15 (tax)
--   condition:  sealed 2.00, used 1.00
--   colour:     Black 1.00, Red 1.50, White 2.00
--   size:       Seven-inch 1.00, Ten-inch 2.00, Twelve-inch 4.00
-- ------------------------------------------------------------------------------

CREATE TABLE vinyl_listing (
                               id              INT             NOT NULL AUTO_INCREMENT COMMENT 'This is the primary key',
                               recordName      VARCHAR(100)    NOT NULL COMMENT 'Name of the record',
                               sellerName      VARCHAR(50)     NOT NULL COMMENT 'Name of the vinyl seller',
                               releaseYear     INT(4)          NOT NULL COMMENT 'Year the vinyl was released',
                               vinylGenre      VARCHAR(20)     NOT NULL COMMENT 'Country, Pop, Rock, Jazz, Electronic',
                               vinylColour     VARCHAR(10)     NOT NULL COMMENT 'Black, Red, White',
                               recordSize      VARCHAR(15)     NOT NULL COMMENT 'Seven-inch, Ten-inch, Twelve-inch',
                               vinylIsSealed   BOOLEAN         NOT NULL DEFAULT FALSE COMMENT 'True if new/sealed, false if used',
                               vinylPrice      DECIMAL(8,2)    NOT NULL DEFAULT 0 COMMENT 'Calculated price including tax',
                               createdDateTime VARCHAR(20)     NOT NULL COMMENT 'yyyy-MM-dd HH:mm:ss',
                               PRIMARY KEY (id)
) COMMENT 'This table holds the vinyl records listed for sale';

INSERT INTO vinyl_listing
(recordName, sellerName, releaseYear, vinylGenre, vinylColour, recordSize, vinylIsSealed, vinylPrice, createdDateTime)
VALUES
-- 10 x 2.00 x 1.00 x 4.00 = 80.00 + tax
('Kind of Blue',            'Crate Diggers PEI',  1959, 'Jazz',       'Black', 'Twelve-inch', TRUE,   92.00, '2026-09-20 10:15:00'),
-- 10 x 1.00 x 1.50 x 4.00 = 60.00 + tax
('Thriller',                'Spin Cycle Records', 1982, 'Pop',        'Red',   'Twelve-inch', FALSE,  69.00, '2026-09-21 13:40:00'),
-- 10 x 1.00 x 1.00 x 1.00 = 10.00 + tax
('Jolene',                  'Groove Island',      1973, 'Country',    'Black', 'Seven-inch',  FALSE,  11.50, '2026-09-22 09:05:00'),
-- 10 x 2.00 x 2.00 x 4.00 = 160.00 + tax
('Discovery',               'Spin Cycle Records', 2001, 'Electronic', 'White', 'Twelve-inch', TRUE,  184.00, '2026-09-23 16:30:00'),
-- 10 x 1.00 x 1.00 x 4.00 = 40.00 + tax
('Back in Black',           'Crate Diggers PEI',  1980, 'Rock',       'Black', 'Twelve-inch', FALSE,  46.00, '2026-09-24 11:20:00'),
-- 10 x 2.00 x 1.50 x 1.00 = 30.00 + tax
('Heart-Shaped Box',        'Groove Island',      1993, 'Rock',       'Red',   'Seven-inch',  TRUE,   34.50, '2026-09-25 14:45:00'),
-- 10 x 1.00 x 2.00 x 2.00 = 40.00 + tax
('Blue Train',              'Crate Diggers PEI',  1957, 'Jazz',       'White', 'Ten-inch',    FALSE,  46.00, '2026-09-26 12:00:00'),
-- 10 x 2.00 x 1.50 x 2.00 = 60.00 + tax
('Random Access Memories',  'Spin Cycle Records', 2013, 'Electronic', 'Red',   'Ten-inch',    TRUE,   69.00, '2026-09-27 17:10:00');


-- ------------------------------------------------------------------------------
-- Code tables - hold the lookup values used for the drop downs in the application.
-- ------------------------------------------------------------------------------

CREATE TABLE CodeType (codeTypeId int(3) COMMENT 'This is the primary key for code types',
                       englishDescription varchar(100) NOT NULL COMMENT 'English description',
                       frenchDescription varchar(100) DEFAULT NULL COMMENT 'French description',
                       createdDateTime datetime DEFAULT NULL,
                       createdUserId varchar(20) DEFAULT NULL,
                       updatedDateTime datetime DEFAULT NULL,
                       updatedUserId varchar(20) DEFAULT NULL
) COMMENT 'This tables holds the code types that are available for the application';

ALTER TABLE CodeType
    ADD PRIMARY KEY (CodeTypeId);

INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (1, 'User Types', 'User Types FR', sysdate(), '', CURRENT_TIMESTAMP, ''),
    (2, 'Vinyl Genres', 'Genres de vinyle', sysdate(), '', CURRENT_TIMESTAMP, ''),
    (3, 'Vinyl Colours', 'Couleurs de vinyle', sysdate(), '', CURRENT_TIMESTAMP, ''),
    (4, 'Record Sizes', 'Tailles de disque', sysdate(), '', CURRENT_TIMESTAMP, '');


CREATE TABLE CodeValue (
                           codeTypeId int(3) NOT NULL COMMENT 'see code_type table',
                           codeValueSequence int(3) NOT NULL,
                           englishDescription varchar(100) NOT NULL COMMENT 'English description',
                           englishDescriptionShort varchar(20) NOT NULL COMMENT 'English abbreviation for description',
                           frenchDescription varchar(100) DEFAULT NULL COMMENT 'French description',
                           frenchDescriptionShort varchar(20) DEFAULT NULL COMMENT 'French abbreviation for description',
                           sortOrder int(3) DEFAULT NULL COMMENT 'Sort order if applicable',
                           createdDateTime datetime DEFAULT NULL,
                           createdUserId varchar(20) DEFAULT NULL,
                           updatedDateTime datetime DEFAULT NULL,
                           updatedUserId varchar(20) DEFAULT NULL
) COMMENT='This will hold code values for the application.';

ALTER TABLE CodeValue
    ADD PRIMARY KEY (CodeTypeId, codeValueSequence);

INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, sortOrder, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
-- User types
    (1, 1, 'General', 'General', 'GeneralFR', 'GeneralFR', 1, sysdate(), 'admin', sysdate(), 'admin'),
    (1, 2, 'Admin', 'Admin', 'Admin', 'Admin', 2, sysdate(), 'admin', sysdate(), 'admin'),
-- Vinyl genres
    (2, 1, 'Country', 'Country', 'Country', 'Country', 1, sysdate(), 'admin', sysdate(), 'admin'),
    (2, 2, 'Pop', 'Pop', 'Pop', 'Pop', 2, sysdate(), 'admin', sysdate(), 'admin'),
    (2, 3, 'Rock', 'Rock', 'Rock', 'Rock', 3, sysdate(), 'admin', sysdate(), 'admin'),
    (2, 4, 'Jazz', 'Jazz', 'Jazz', 'Jazz', 4, sysdate(), 'admin', sysdate(), 'admin'),
    (2, 5, 'Electronic', 'Electronic', 'Electronique', 'Electronique', 5, sysdate(), 'admin', sysdate(), 'admin'),
-- Vinyl colours
    (3, 1, 'Black', 'Black', 'Noir', 'Noir', 1, sysdate(), 'admin', sysdate(), 'admin'),
    (3, 2, 'Red', 'Red', 'Rouge', 'Rouge', 2, sysdate(), 'admin', sysdate(), 'admin'),
    (3, 3, 'White', 'White', 'Blanc', 'Blanc', 3, sysdate(), 'admin', sysdate(), 'admin'),
-- Record sizes
    (4, 1, 'Seven-inch', '7"', 'Sept pouces', '7"', 1, sysdate(), 'admin', sysdate(), 'admin'),
    (4, 2, 'Ten-inch', '10"', 'Dix pouces', '10"', 2, sysdate(), 'admin', sysdate(), 'admin'),
    (4, 3, 'Twelve-inch', '12"', 'Douze pouces', '12"', 3, sysdate(), 'admin', sysdate(), 'admin');
