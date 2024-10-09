-- commands.sql

-- open database
USE sfdevdb;

INSERT INTO command_lists (command, short_description, long_description)
VALUES
    ('echo', 'prints all text in argument list', NULL)
ON DUPLICATE KEY UPDATE
    short_description = VALUES(short_description),
    long_description = VALUES(long_description);
