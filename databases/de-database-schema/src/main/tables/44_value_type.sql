SET search_path = public, pg_catalog;

--
-- A table to store value types associated with various parameter types.
--
CREATE TABLE value_type (
    id uuid NOT NULL,
    name character varying(40) NOT NULL,
    description character varying(255) NOT NULL
);
