alter table schedules
    add id_doctor bigint default 2 not null;

alter table schedules
    add constraint schedules_doctors_id_fk
        foreign key (id_doctor) references doctors
            on delete cascade;