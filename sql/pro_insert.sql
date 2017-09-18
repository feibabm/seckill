DROP PROCEDURE
IF EXISTS pro_insert;

CREATE PROCEDURE pro_insert ()
BEGIN

DECLARE number INT DEFAULT 0;


REPEAT

SET number = number + 1;

INSERT INTO success_killed
VALUES
	(1000, number, 0, NOW());

UNTIL number >= 10000
END
REPEAT
;


END