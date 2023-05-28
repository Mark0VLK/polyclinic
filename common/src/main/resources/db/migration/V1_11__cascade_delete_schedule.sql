alter table schedules
    drop constraint schedules_doctors_id_fk;

alter table schedules
    add constraint schedules_doctors_id_fk
        foreign key (id_doctor) references doctors
            on update cascade on delete cascade;
