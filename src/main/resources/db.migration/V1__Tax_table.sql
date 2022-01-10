create table tax_tables (
    id bigserial primary key,
    zip text not null,
    local_tax_rate float not null
);