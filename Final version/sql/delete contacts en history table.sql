delete from contacts;
ALTER TABLE contacts ALTER COLUMN id RESTART WITH 1;

delete from history;
ALTER TABLE history ALTER COLUMN id RESTART WITH 1;