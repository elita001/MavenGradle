# Manufacturers
USE repair_shop;
INSERT INTO Manufacturers(name)
VALUES('Ford'),
('Audi'),
('Lada'),
('Opel'),
('Renault');

# Roles in system
INSERT INTO Roles(name)
VALUES('Customer'),
('Worker'),
('Administrator');

# Services test data
INSERT INTO Services(name, curPrice)
VALUES('Oil change', 500),
('General technical inspection', 1600),
('Collapse and convergence', 1200),
('Bulkhead chassis', 5150),
('Belt Kit replacement', 700),
('Engine cleaning', 8400),
('Injector washing', 12000),
('Repainting', 8100),
('Washing', 300),
('Cleaning interior', 1400);

DROP PROCEDURE IF EXISTS repair_shop.GenTestValues;

# Generate test data
DELIMITER //
CREATE PROCEDURE GenTestValues(IN numVal INT)
BEGIN
	DECLARE i INT DEFAULT 1;
	IF (numVal > 0) THEN
		WHILE (i <= numVal) DO
			# Customer
			SET @roleID = (SELECT roleID FROM Roles WHERE Roles.name='Customer');
			SET @firstName = CONCAT('Name', i);
			SET @lastName = CONCAT('Surname', i);
			SET @phoneNumber = 11111 + i;
			SET @email = CONCAT('email', i, '@mail.ru');
			INSERT INTO Users(`roleID`, `firstName`, `lastName`, `phoneNumber`, `email`) 
					VALUES(@roleID, @firstName, @lastName, @phoneNumber, @email);
			
			SET @newCustomerID = last_insert_id();

			# Car
			SET @manufacturerSize = (SELECT COUNT(*) FROM Manufacturers);
			SET @manufacturerID = FLOOR(RAND() * (@manufacturerSize)) + 1;
			SET @number = CONCAT(i, '');
			SET @model = CONCAT('Model', i);
			SET @mileage = i * 2;
			INSERT INTO Cars(`customerID`, `manufacturerID`, `number`, `model`, `mileage`)
				VALUES(@newCustomerID, @manufacturerID, @number, @model, @mileage);

			SET @newCarID = last_insert_id();

			# Order
			SET @statusIndex = FLOOR(RAND() * 3) + 1; # Rand between 1..3
			SET @status = ELT(@statusIndex, 'open', 'processing', 'done');
			INSERT INTO Orders(`carID`, `status`) VALUES(@newCarID, @status);

			SET @newOrderID = last_insert_id();

			# Order info
			SET @curOrderInfo = 0;
			SET @orderInfoCount = FLOOR(RAND() * 8) + 1;
			WHILE (@curOrderInfo < @orderInfoCount) DO
				SET @serviceCount = (SELECT COUNT(*) FROM Services);
				SET @serviceID = FLOOR(RAND() * @serviceCount) + 1;
				INSERT INTO OrdersInfo(`orderID`, `serviceID`, `price`)
					VALUES(@newOrderID, @serviceID, (SELECT curPrice FROM Services WHERE serviceID=@serviceID));
				SET @curOrderInfo = @curOrderInfo + 1;
			END WHILE;

			SET i = i + 1;
		END WHILE;
	ELSE
		SELECT 'Error: parameter less than zero';
	END IF;
END
//

call GenTestValues(100);