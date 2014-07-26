SET search_path = public, pg_catalog;

--
-- Updates columns in the existing jobs table.
--
ALTER TABLE ONLY jobs RENAME COLUMN user_id TO user_id_v187;
ALTER TABLE ONLY jobs ALTER COLUMN user_id_v187 DROP NOT NULL;
ALTER TABLE ONLY jobs ALTER COLUMN id SET DEFAULT uuid_generate_v1();
ALTER TABLE ONLY jobs ADD COLUMN user_id UUID;

