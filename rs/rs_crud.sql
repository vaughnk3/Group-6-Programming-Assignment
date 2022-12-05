INSERT INTO Person (id, firstName, lastName, sex) VALUES (NULL, "John", "Madden", "Female");
SELECT * FROM Person;
UPDATE Person SET sex = "Male" WHERE id = 1;
DELETE FROM Person WHERE id = 1;