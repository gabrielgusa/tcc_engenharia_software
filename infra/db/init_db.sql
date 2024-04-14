
CREATE DATABASE findfoodtosave;

create user 'findfoodtosave'@'%' identified by '12345';

grant all on findfoodtosave.* to 'findfoodtosave'@'%';
