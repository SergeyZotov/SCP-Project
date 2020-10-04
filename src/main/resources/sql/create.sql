CREATE TABLE public.achievement
(
    id integer NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    description character varying COLLATE pg_catalog."default",
    CONSTRAINT achievement_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.achievement
    OWNER to postgres;

-----------------------------------
CREATE TABLE public.role
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    role_name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (id),
    CONSTRAINT "roleUnique" UNIQUE (role_name)
)

TABLESPACE pg_default;

ALTER TABLE public.role
    OWNER to postgres;

----------------------------------
CREATE TABLE public.status
(
    id integer NOT NULL,
    status_name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT status_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.status
    OWNER to postgres;

----------------------------------
CREATE TABLE public."user"
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    email character varying COLLATE pg_catalog."default" NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    nickname character varying COLLATE pg_catalog."default" NOT NULL,
    role_id integer NOT NULL,
    status_id integer NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT "nickNameUnique" UNIQUE (nickname),
    CONSTRAINT "userUnique" UNIQUE (email),
    CONSTRAINT fk_user_role FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_user_status FOREIGN KEY (status_id)
        REFERENCES public.status (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public."user"
    OWNER to postgres;

----------------------------------
CREATE TABLE public.user_achievement
(
    user_id integer NOT NULL,
    achievement_id integer NOT NULL,
    CONSTRAINT user_achievement_pkey PRIMARY KEY (user_id, achievement_id),
    CONSTRAINT fk_achievement_achievement FOREIGN KEY (achievement_id)
        REFERENCES public.achievement (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_user_achievement FOREIGN KEY (user_id)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.user_achievement
    OWNER to postgres;