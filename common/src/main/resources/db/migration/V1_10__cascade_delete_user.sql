alter table patients
    drop constraint patients_users_id_fk;

alter table patients
    add constraint patients_users_id_fk
        foreign key (id_user) references users
            on update cascade on delete cascade;
