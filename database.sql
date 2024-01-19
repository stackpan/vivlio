create table books
(
    id       bigserial    not null primary key,
    title    varchar(200) not null unique,
    language varchar(50)  not null,
    year     bigint       not null,
    author   varchar(200) not null
);

create table reviews
(
    book_id bigint not null references books (id),
    body    text   not null
);