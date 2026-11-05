ALTER TABLE cars
ALTER COLUMN cleared_customs TYPE boolean USING (false);