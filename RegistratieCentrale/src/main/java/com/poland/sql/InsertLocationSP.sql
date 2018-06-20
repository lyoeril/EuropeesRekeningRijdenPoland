DROP PROCEDURE IF EXISTS insertLocationSP;
DELIMITER //
CREATE PROCEDURE `insertLocationSP` (
	in datetime_var datetime,
    in latitude double,
    in longitude double,
    in authorisationCode VARCHAR(255),
    out succeeded boolean)
BEGIN
    DECLARE vehicle_id bigint(20) DEFAULT 0;
    DECLARE ride_id bigint(20) DEFAULT 0;
    DECLARE lastLocation datetime DEFAULT NULL;
    DECLARE diff double;
    
	SET succeeded = false;
	SELECT v.id INTO vehicle_id FROM t_vehicle v WHERE v.authorisationCode = authorisationCode;
    IF vehicle_id = 0 THEN
		INSERT INTO t_vehicle (authorisationCode) VALUES (authorisationCode);
        SELECT v.id INTO vehicle_id FROM t_vehicle v WHERE v.authorisationCode = authorisationCode;
	END IF;
    SELECT r.id INTO ride_id FROM t_ride r WHERE datetime_var >= r.startDate AND(datetime_var <= (SELECT r2.startDate FROM t_ride r2 WHERE r2.endDate IS NULL and r2.vehicleId = vehicle_id) OR r.endDate IS NULL) and r.startDate IS NOT NULL and r.vehicleId = vehicle_id;
	IF ride_id = 0 THEN
		INSERT INTO t_ride (startDate, vehicleId) VALUES (datetime_var, vehicle_id);
        SELECT r.id INTO ride_id FROM t_ride r WHERE datetime_var >= r.startDate AND(datetime_var <= (SELECT r2.startDate FROM t_ride r2 WHERE r2.endDate IS NULL and r2.vehicleId = vehicle_id) OR r.endDate IS NULL) and r.startDate IS NOT NULL and r.vehicleId = vehicle_id;
    END IF;
    SELECT l.date INTO lastlocation FROM t_location l WHERE l.rideId=ride_id ORDER BY l.date desc LIMIT 1;
    IF lastlocation >= (SELECT r.endDate FROM t_ride r WHERE r.id = ride_id) THEN
		UPDATE t_ride r SET r.endDate=lastlocation WHERE r.id = ride_id;
    END IF;
    IF lastlocation IS NOT NULL THEN
		SET diff = (UNIX_TIMESTAMP(datetime_var)*1000 - UNIX_TIMESTAMP(lastlocation)*1000) / 3600000;	
        IF diff >= 1 THEN
			UPDATE t_ride r SET r.endDate=lastlocation WHERE r.id = ride_id;
			INSERT INTO t_ride (startDate, vehicleId) VALUES (datetime_var, vehicle_id);
			SELECT r.id INTO ride_id FROM t_ride r WHERE datetime_var >= r.startDate AND(datetime_var <= r.endDate OR r.endDate IS NULL) and r.startDate IS NOT NULL;
        END IF;
    END IF;
    INSERT INTO t_location (date, latitude, longitude, rideId) VALUES (datetime_var, latitude, longitude, ride_id);
    SET succeeded = true;
END

# CALL insertLocationSP(date_format("2018-04-21 23:36:25", "%Y-%m-%d %k:%i:%s"),90,91, "008402771a", @`succeeded`);