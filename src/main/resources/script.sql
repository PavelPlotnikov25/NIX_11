CREATE DATABASE "Nix_11"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Ukraine.1251'
    LC_CTYPE = 'Russian_Ukraine.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION postgres;

COMMENT ON SCHEMA public
    IS 'standard public schema';

GRANT ALL ON SCHEMA public TO PUBLIC;

GRANT ALL ON SCHEMA public TO postgres;

CREATE TABLE IF NOT EXISTS public."Computer"
(
    id character varying COLLATE pg_catalog."default" NOT NULL,
    title character varying COLLATE pg_catalog."default",
    model character varying COLLATE pg_catalog."default",
    count integer,
    price double precision,
    manufacturer character varying COLLATE pg_catalog."default",
    "invoiceID" character varying COLLATE pg_catalog."default",
    CONSTRAINT "Computer_pkey" PRIMARY KEY (id),
    CONSTRAINT "invoiceID" FOREIGN KEY ("invoiceID")
        REFERENCES public."Invoice" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Computer"
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public."Phone"
(
    id character varying COLLATE pg_catalog."default" NOT NULL,
    title character varying COLLATE pg_catalog."default",
    count integer,
    price double precision,
    manufacturer character varying COLLATE pg_catalog."default",
    model character varying COLLATE pg_catalog."default",
    "invoiceID" character varying COLLATE pg_catalog."default",
    CONSTRAINT phone_pk PRIMARY KEY (id),
    CONSTRAINT "invoiceID" FOREIGN KEY ("invoiceID")
        REFERENCES public."Invoice" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Phone"
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public."Television"
(
    id character varying COLLATE pg_catalog."default" NOT NULL,
    title character varying COLLATE pg_catalog."default",
    model character varying COLLATE pg_catalog."default",
    count integer,
    price double precision,
    diagonal integer,
    manufacturer character varying COLLATE pg_catalog."default",
    "invoiceID" character varying COLLATE pg_catalog."default",
    CONSTRAINT "Television_pkey" PRIMARY KEY (id),
    CONSTRAINT "invoiceID" FOREIGN KEY ("invoiceID")
        REFERENCES public."Invoice" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Television"
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public."Invoice"
(
    id character varying COLLATE pg_catalog."default" NOT NULL,
    sum double precision,
    "time" character varying COLLATE pg_catalog."default",
    CONSTRAINT "Invoice_pkey" PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Invoice"
    OWNER to postgres;