INSERT INTO Person (id, firstName, lastName, sex) VALUES (NULL, "John", "Madden", "Female");
SELECT * FROM Person;
UPDATE Person SET sex = "Male" WHERE id = 1;
DELETE FROM Person WHERE id = 1;

INSERT INTO ACTS_IN_EP (EpisodeID, PersonID) VALUES (1,1);
SELECT * FROM ACTS_IN_EP;
UPDATE ACTS_IN_EP SET firstName = "Bobby" WHERE id = 1;
DELETE FROM ACTS_IN_EP WHERE id = 1;