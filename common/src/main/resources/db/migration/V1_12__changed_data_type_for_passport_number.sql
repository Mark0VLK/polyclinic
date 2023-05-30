alter table personal_doc
    alter column passport_number type varchar(10) using passport_number::varchar(10);
