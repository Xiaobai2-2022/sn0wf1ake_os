-- commands.sql

-- open database
USE sfdevdb;

INSERT INTO command_lists (command, short_description, long_description, ver)
VALUES
    ('echo', 'echo - display a line of text', 
'User Commands - "echo"\n\
NAME\n\
    echo - display a line of text\n\
SYNTAX\n\
    echo [ STRING ]\n\
    echo [ OPTION ]\n\
    echo [ OPTION ] [ STRING ]\n\
DESCRIPTION\n\
    echo the STRING(s) to standard output.\n\
    -n\n\
        do not output the trailing newline\n\
    -e\n\
        enable interpretation of backslash escapes\n\
    -E\n\
        disable interpretation of backslash escapes\n\
    --help\n\
        display this help and exit\n\
    --version\n\
        output version information and exit\n\
NOTE\n\
    Each shell may have its own version of echo, which usually supersedes\n\
    the version described here. Please refer to your shell\'s documentation\n\
    for details about the options it supports.\n\
AUTHOR\n\
    Written by Zhifan (Xiaobai2) Li\n\
REPORTING BUGS\n\
    Sn0wf1ake is a opensource project, if a bug is observed, please\n\
    directly email me at zhifan.li.2002@outlook.com.\n\
COPYRIGHT\n\
    Copyright by Fangxia Technology Ltd. Sn0wf1ake License.\n\
    This is free software: you are free to change and redistribute it.\n\
    However, you must make reference to Sn0wf1ake for redistribution.\n\
    There is NO WARRANTY, to the extent permitted by law.\n', 
    '2.0.2'),
    ('clear', 'clear - clear the terminal screen', 
'User Commands - "clear"\n\
NAME\n\
    clear - clear the terminal screen\n\
SYNTAX\n\
    clear [ OPTION ]\n\
DESCRIPTION\n\
    clear clears your screen if this is possible\n\
    --help\n\
	    display this help and exit\n\
    --version\n\
	    output version information and exit\n\
NOTE\n\
    Each shell may have its own version of echo, which usually supersedes\n\
    the version described here. Please refer to your shell\'s documentation\n\
    for details about the options it supports.\n\
AUTHOR\n\
    Written by Zhifan (Xiaobai2) Li\n\
REPORTING BUGS\n\
    Sn0wf1ake is a opensource project, if a bug is observed, please\n\
    directly email me at zhifan.li.2002@outlook.com.\n\
COPYRIGHT\n\
    Copyright by Fangxia Technology Ltd. Sn0wf1ake License.\n\
    This is free software: you are free to change and redistribute it.\n\
    However, you must make reference to Sn0wf1ake for redistribution.\n\
    There is NO WARRANTY, to the extent permitted by law.\n', 
    '1.0.1')
ON DUPLICATE KEY UPDATE
    short_description = VALUES(short_description),
    long_description = VALUES(long_description),
    ver = VALUES(ver);
