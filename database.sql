create table books
(
    id          uuid         not null primary key,
    title       varchar(200) not null unique,
    description varchar(1000),
    language    varchar(50)  not null,
    year        int          not null,
    author      varchar(200) not null,
    total_pages int          not null,
    width       float,
    height      float
);

create table reviews
(
    id      uuid not null primary key,
    book_id uuid not null references books (id),
    body    text not null
);

create table users
(
    id       uuid          not null primary key,
    name     varchar(30)   not null unique,
    email    varchar(50)   not null unique,
    password varchar(1024) not null,
    role     varchar(255)  not null
);


