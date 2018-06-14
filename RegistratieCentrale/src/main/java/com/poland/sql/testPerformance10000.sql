DROP PROCEDURE IF EXISTS call_mypro_x10000;
DELIMITER $$
CREATE PROCEDURE call_mypro_x10000()
BEGIN
  SET @x = 1;
  WHILE @x <= 10000 DO
	CALL `registratie`.`insertLocationSP`(sysdate(),51.9358373 , 16.8920935, "test", @succeeded);
    SET @x := @x + 1;
  END WHILE;

END$$
DELIMITER ;