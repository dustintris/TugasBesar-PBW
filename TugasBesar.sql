--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

-- Started on 2024-12-12 10:12:23

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 223 (class 1259 OID 16465)
-- Name: activities; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.activities (
    id integer NOT NULL,
    userid integer,
    title character varying,
    description text,
    activitydate date,
    duration integer,
    range double precision,
    picture character varying,
    createdat timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.activities OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16464)
-- Name: activities_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.activities_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.activities_id_seq OWNER TO postgres;

--
-- TOC entry 4884 (class 0 OID 0)
-- Dependencies: 222
-- Name: activities_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.activities_id_seq OWNED BY public.activities.id;


--
-- TOC entry 220 (class 1259 OID 16438)
-- Name: lomba; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lomba (
    id integer NOT NULL,
    title character varying(255) NOT NULL,
    start_date date NOT NULL,
    regis_start date NOT NULL,
    regis_end date NOT NULL,
    description text,
    location character varying(255),
    range double precision,
    time_created timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.lomba OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16437)
-- Name: lomba_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.lomba_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.lomba_id_seq OWNER TO postgres;

--
-- TOC entry 4885 (class 0 OID 0)
-- Dependencies: 219
-- Name: lomba_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.lomba_id_seq OWNED BY public.lomba.id;


--
-- TOC entry 221 (class 1259 OID 16447)
-- Name: partisipasilomba; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.partisipasilomba (
    user_id integer NOT NULL,
    event_id integer NOT NULL,
    status character varying(50) NOT NULL,
    joined_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT partisipasilomba_status_check CHECK (((status)::text = ANY ((ARRAY['Registered'::character varying, 'Completed'::character varying, 'Cancelled'::character varying])::text[])))
);


ALTER TABLE public.partisipasilomba OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16411)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    email character varying(255) NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(50) DEFAULT 'user'::character varying NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16410)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 4886 (class 0 OID 0)
-- Dependencies: 217
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 4714 (class 2604 OID 16468)
-- Name: activities id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.activities ALTER COLUMN id SET DEFAULT nextval('public.activities_id_seq'::regclass);


--
-- TOC entry 4711 (class 2604 OID 16441)
-- Name: lomba id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lomba ALTER COLUMN id SET DEFAULT nextval('public.lomba_id_seq'::regclass);


--
-- TOC entry 4709 (class 2604 OID 16414)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 4878 (class 0 OID 16465)
-- Dependencies: 223
-- Data for Name: activities; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.activities (id, userid, title, description, activitydate, duration, range, picture, createdat) FROM stdin;
\.


--
-- TOC entry 4875 (class 0 OID 16438)
-- Dependencies: 220
-- Data for Name: lomba; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.lomba (id, title, start_date, regis_start, regis_end, description, location, range, time_created) FROM stdin;
\.


--
-- TOC entry 4876 (class 0 OID 16447)
-- Dependencies: 221
-- Data for Name: partisipasilomba; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.partisipasilomba (user_id, event_id, status, joined_at) FROM stdin;
\.


--
-- TOC entry 4873 (class 0 OID 16411)
-- Dependencies: 218
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, email, username, password, role) FROM stdin;
3	admin@example.com	admin	$2a$10$yJVFq5UcQmJ6zZOHF9zUIOO.c35F/rV0Ee04D/fWiS62/KP9n6MTu	admin
4	user@example.com	user	$2a$10$yJVFq5UcQmJ6zZOHF9zUIOO.c35F/rV0Ee04D/fWiS62/KP9n6MTu	user
6	dustintris3@gmail.com	dustintris	$2a$10$Cy.F84/wvh4hiKvDYGIdHOxzfrFj22teYXBHPEZ1Xco0Abb.xmsfe	user
7	samharatua@yahoo.com	samsam	$2a$10$QqVkVHJjSCOSvs5RQIXnIOCa5uvXuHj2LQd17h3kCBtvHuDuPDQ4S	user
\.


--
-- TOC entry 4887 (class 0 OID 0)
-- Dependencies: 222
-- Name: activities_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.activities_id_seq', 1, false);


--
-- TOC entry 4888 (class 0 OID 0)
-- Dependencies: 219
-- Name: lomba_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.lomba_id_seq', 1, false);


--
-- TOC entry 4889 (class 0 OID 0)
-- Dependencies: 217
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 7, true);


--
-- TOC entry 4724 (class 2606 OID 16473)
-- Name: activities activities_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.activities
    ADD CONSTRAINT activities_pkey PRIMARY KEY (id);


--
-- TOC entry 4720 (class 2606 OID 16446)
-- Name: lomba lomba_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lomba
    ADD CONSTRAINT lomba_pkey PRIMARY KEY (id);


--
-- TOC entry 4722 (class 2606 OID 16453)
-- Name: partisipasilomba partisipasilomba_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partisipasilomba
    ADD CONSTRAINT partisipasilomba_pkey PRIMARY KEY (user_id, event_id);


--
-- TOC entry 4718 (class 2606 OID 16418)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 4725 (class 2606 OID 16459)
-- Name: partisipasilomba partisipasilomba_event_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partisipasilomba
    ADD CONSTRAINT partisipasilomba_event_id_fkey FOREIGN KEY (event_id) REFERENCES public.lomba(id) ON DELETE CASCADE;


--
-- TOC entry 4726 (class 2606 OID 16454)
-- Name: partisipasilomba partisipasilomba_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.partisipasilomba
    ADD CONSTRAINT partisipasilomba_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


-- Completed on 2024-12-12 10:12:24

--
-- PostgreSQL database dump complete
--

