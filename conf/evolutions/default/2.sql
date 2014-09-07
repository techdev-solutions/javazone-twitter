# --- !Ups

insert into account (id, username, firstname, lastname) values (1, 'alexhanschke', 'Alex', 'Hanschke');

# --- !Downs

delete from message;
