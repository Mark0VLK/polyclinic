alter table personal_doc
    alter column passport_series type varchar(10) using passport_series::varchar(10);

alter table personal_doc
    add identification_no varchar(30) not null;

alter table personal_doc
    add unique (identification_no);

