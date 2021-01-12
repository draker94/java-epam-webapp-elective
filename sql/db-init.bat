@echo on
"c:\Program Files\MySQL\MySQL Server 8.0\bin\mysql" -u root -pT***** --default-character-set=utf8 -e "source init.sql"
pause