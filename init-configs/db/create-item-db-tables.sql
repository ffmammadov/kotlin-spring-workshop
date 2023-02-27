create table item_db.items
(
    id int auto_increment,
    name varchar(255) not null,
    price int not null,
    available boolean default true not null,
    created_at datetime not null,
    updated_at datetime not null,
    unavailability_reason varchar(255) null,
    constraint items_pk
        primary key (id)
);

create unique index items_name_uindex
    on item_db.items (name);

