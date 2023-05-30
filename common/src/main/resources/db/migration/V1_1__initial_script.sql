create table public.users
(
    id            bigserial
        primary key,
    login         varchar(30)           not null
        unique,
    user_password varchar(100)          not null
        constraint users_password_key
            unique,
    phone_number  varchar(15)           not null
        unique,
    email         varchar(100)          not null
        unique,
    created       timestamp(6)          not null,
    changed       timestamp(6)          not null,
    is_deleted    boolean default false not null
);

alter table public.users
    owner to dev;

create index users_changed_index
    on public.users (changed desc);

create index users_created_index
    on public.users (created desc);

create table public.regions
(
    id            bigserial
        primary key,
    number        bigint                not null
        unique,
    address_range varchar(100)          not null,
    created       timestamp(6)          not null,
    is_deleted    boolean default false not null
);

alter table public.regions
    owner to dev;

create index regions_created_index
    on public.regions (created desc);

create table public.patients
(
    card_number   bigserial
        primary key,
    name          varchar(20)                                           not null,
    surname       varchar(30)                                           not null,
    gender        varchar(20) default 'NOT_SELECTED'::character varying not null,
    birthday_data timestamp(6)                                          not null,
    address       varchar(50)                                           not null,
    created       timestamp(6)                                          not null,
    changed       timestamp(6)                                          not null,
    is_deleted    boolean     default false                             not null,
    id_user       bigint                                                not null
        constraint patients_users_id_fk
            references public.users
            on update cascade on delete cascade,
    region_number bigint                                                not null
        constraint patients_regions_number_fk
            references public.regions (number)
            on update cascade on delete cascade
);

alter table public.patients
    owner to dev;

create index patients_birthday_data_index
    on public.patients (birthday_data desc);

create index patients_changed_index
    on public.patients (changed desc);

create index patients_id_user_index
    on public.patients (id_user);

create index patients_name_index
    on public.patients (name);

create index patients_name_surname_index
    on public.patients (name, surname);

create index patients_region_number_index
    on public.patients (region_number);

create index patients_surname_index
    on public.patients (surname);

create table public.specializations
(
    id         bigserial
        primary key,
    name       varchar(30)           not null,
    created    timestamp(6)          not null,
    changed    timestamp(6)          not null,
    is_deleted boolean default false not null
);

alter table public.specializations
    owner to dev;

create index specializations_changed_index
    on public.specializations (changed desc);

create index specializations_name_index
    on public.specializations (name);

create table public.doctors
(
    id                bigserial
        primary key,
    name              varchar(20)           not null,
    surname           varchar(30)           not null,
    office            varchar(10)           not null,
    id_user           bigint                not null
        constraint doctors_users_id_fk
            references public.users
            on update cascade on delete cascade,
    id_specialization bigint                not null
        constraint doctors_specializations_id_fk
            references public.specializations
            on update cascade on delete cascade,
    created           timestamp(6)          not null,
    changed           timestamp(6)          not null,
    is_deleted        boolean default false not null,
    region_number     bigint                not null
        constraint doctors_regions_number_fk
            references public.regions (number)
            on update cascade on delete cascade
);

alter table public.doctors
    owner to dev;

create table public.schedules
(
    id          bigserial
        primary key,
    date        timestamp(6)          not null,
    time_start  timestamp(6)          not null,
    time_finish timestamp(6)          not null,
    created     timestamp(6)          not null,
    changed     timestamp             not null,
    is_deleted  boolean default false not null,
    id_doctor   bigint                not null
        constraint schedules_doctors_id_fk
            references public.doctors
            on update cascade on delete cascade
);

alter table public.schedules
    owner to dev;

create index schedules_changed_index
    on public.schedules (changed desc);

create index schedules_date_index
    on public.schedules (date desc);

create index schedules_date_time_start_index
    on public.schedules (date desc, time_start desc);

create index schedules_time_start_index
    on public.schedules (time_start desc);

create index doctors_changed_index
    on public.doctors (changed desc);

create index doctors_id_specialization_index
    on public.doctors (id_specialization);

create index doctors_id_user_index
    on public.doctors (id_user);

create index doctors_name_index
    on public.doctors (name);

create index doctors_name_surname_index
    on public.doctors (name, surname);

create index doctors_region_number_index
    on public.doctors (region_number);

create index doctors_surname_index
    on public.doctors (surname);

create table public.visits
(
    id                  bigserial
        primary key,
    status              boolean default false not null,
    note                varchar(100),
    price               real,
    patient_card_number bigint                not null
        constraint visits_patients_card_number_fk
            references public.patients
            on update cascade on delete cascade,
    created             timestamp(6)          not null,
    changed             timestamp(6)          not null,
    is_deleted          boolean default false not null,
    id_doctor           bigint                not null
        constraint visits_doctors_id_fk
            references public.doctors
            on update cascade on delete cascade
);

alter table public.visits
    owner to dev;

create index visits_changed_index
    on public.visits (changed desc);

create index visits_created_index
    on public.visits (created desc);

create index visits_id_doctor_index
    on public.visits (id_doctor);

create index visits_patient_card_number_index
    on public.visits (patient_card_number);

create index visits_price_index
    on public.visits (price desc);

create table public.roles
(
    id        bigserial
        primary key,
    role_name varchar(100) not null,
    user_id   bigint       not null
        constraint roles_users_id_fk
            references public.users
            on update cascade on delete cascade,
    created   timestamp(6) not null,
    changed   timestamp(6) not null
);

alter table public.roles
    owner to postgres;

create unique index roles_role_name_user_id_uindex
    on public.roles (role_name, user_id);

create index roles_changed_index
    on public.roles (changed desc);

create index roles_created_index
    on public.roles (created);

create table public.personal_doc
(
    id                bigserial
        primary key,
    passport_series   varchar(10)  not null,
    passport_number   varchar(10)  not null
        unique,
    created           timestamp(6) not null,
    changed           timestamp(6) not null,
    expiration_date   timestamp(6) not null,
    user_id           bigint       not null
        constraint personal_doc_users_id_fk
            references public.users
            on update cascade on delete cascade,
    identification_no varchar(30)  not null
        unique
);

alter table public.personal_doc
    owner to postgres;

create index personal_doc_created_index
    on public.personal_doc (created desc);

create index personal_doc_expiration_date_index
    on public.personal_doc (expiration_date desc);

create index personal_doc_user_id_index
    on public.personal_doc (user_id);

