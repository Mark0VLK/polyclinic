create table public.users
(
    id                         bigserial
        primary key,
    login                      varchar(30)           not null
        unique,
    password                   varchar(20)           not null
        unique,
    phone_number               varchar(15)           not null
        unique,
    email                      varchar(60)           not null
        unique,
    passport_series_and_number varchar(30)           not null
        unique,
    created                    timestamp(6)          not null,
    changed                    timestamp(6)          not null,
    is_deleted                 boolean default false not null
);

alter table public.users
    owner to dev;

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

create table public.patients
(
    card_number   bigserial
        primary key,
    name          varchar(20)                                           not null,
    surname       varchar(30)                                           not null,
    gender        varchar(20) default 'NOT_SELECTED'::character varying not null,
    birthday_data timestamp(6)                                          not null,
    address       varchar(50)                                           not null,
    region_number bigint                                                not null
        constraint patients_regions_number_fk
            references public.regions (number),
    created       timestamp(6)                                          not null,
    changed       timestamp(6)                                          not null,
    is_deleted    boolean     default false                             not null,
    id_user       bigint                                                not null
        constraint patients_users_id_fk
            references public.users
);

alter table public.patients
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
    is_deleted  boolean default false not null
);

alter table public.schedules
    owner to dev;

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
    id_schedule       bigint                not null
        constraint doctors_schedules_id_fk
            references public.schedules
            on update cascade on delete cascade,
    id_region         bigint                not null
        constraint doctors_regions_id_fk
            references public.regions
            on update cascade on delete cascade,
    created           timestamp(6)          not null,
    changed           timestamp(6)          not null,
    is_deleted        boolean default false not null
);

alter table public.doctors
    owner to dev;

create table public.appointments
(
    id            bigserial
        primary key,
    id_doctor     bigint                not null
        constraint appointments_doctors_id_fk
            references public.doctors,
    date_and_time timestamp(6)          not null,
    created       timestamp(6)          not null,
    changed       timestamp(6)          not null,
    is_deleted    boolean default false not null
);

alter table public.appointments
    owner to dev;

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
    id_appointment      bigint                not null
        constraint visits_appointments_id_fk
            references public.appointments
            on update cascade on delete cascade,
    created             timestamp(6)          not null,
    changed             timestamp(6)          not null,
    is_deleted          boolean default false not null
);

alter table public.visits
    owner to dev;

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

