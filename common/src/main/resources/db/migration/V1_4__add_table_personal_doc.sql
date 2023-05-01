create table if not exists personal_doc
(
    id              bigserial
        primary key
        unique,
    personal_id     varchar(100) not null
        unique,
    document_type   varchar(20)  not null,
    doc_number      varchar(20)  not null
        unique,
    "create"        timestamp(6) not null,
    changed         timestamp(6) not null,
    expiration_date timestamp(6) not null,
    user_id         bigint       not null
        constraint personal_doc_users_id_fk
            references users
);

create index personal_doc_user_id_personal_id_doc_number_index
    on personal_doc (user_id, personal_id, doc_number);

create unique index personal_doc_user_id_uindex
    on personal_doc (user_id);

