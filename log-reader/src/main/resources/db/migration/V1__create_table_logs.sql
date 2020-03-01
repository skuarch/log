create table logs (
    log_id bigserial not null,
    log_date varchar(32),
    log_message text,
    constraint log_primary_key primary key (log_id)
);