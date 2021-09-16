alter session set "_oracle_script"=true;
drop user funfun CASCADE;

create user funfun identified by 1234;
grant connect, resource, unlimited tablespace to funfun;
conn funfun/1234;