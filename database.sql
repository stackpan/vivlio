create table books
(
    id          uuid         not null primary key,
    title       varchar(200) not null unique,
    description varchar(1000),
    language    varchar(50)  not null,
    year        int       not null,
    author      varchar(200) not null,
    total_pages  int          not null,
    width       float,
    height      float
);

create table reviews
(
    book_id uuid not null references books (id),
    body    text not null
);