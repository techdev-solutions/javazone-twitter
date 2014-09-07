# --- !Ups

create table account (
  id                        bigint not null,
  username                  varchar(255),
  firstname                 varchar(255),
  lastname                  varchar(255),
  constraint pk_account primary key (id))
;

create table message (
  id                        bigint not null,
  content                   varchar(255),
  timestamp                 timestamp,
  account_id                bigint,
  constraint pk_message primary key (id))
;

create sequence account_seq start with 1000;

create sequence message_seq start with 1000;

alter table message add constraint fk_message_account_1 foreign key (account_id) references account (id) on delete restrict on update restrict;
create index ix_message_account_1 on message (account_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists account;

drop table if exists message;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists account_seq;

drop sequence if exists message_seq;
