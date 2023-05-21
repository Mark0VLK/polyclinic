alter table users
    rename column password to user_password;

alter table users
    alter column user_password type varchar(100) using user_password::varchar(100);

alter table users
    alter column email type varchar(100) using email::varchar(100);

