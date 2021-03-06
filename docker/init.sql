--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.24
-- Dumped by pg_dump version 12.3

-- Started on 2021-05-08 22:30:07

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 8 (class 2615 OID 44042)
-- Name: app; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA app;


SET default_tablespace = '';

--
-- TOC entry 171 (class 1259 OID 44045)
-- Name: pessoa; Type: TABLE; Schema: app; Owner: -
--

CREATE TABLE app.pessoa (
    id integer NOT NULL,
    cpf character varying(11) NOT NULL,
    criadoem timestamp without time zone NOT NULL,
    datanascimento date NOT NULL,
    email character varying(80),
    nacionalidade character varying(30),
    naturalidade character varying(30),
    nome character varying(50) NOT NULL,
    sexo character varying(255),
    ultimamodificacao timestamp without time zone
);


--
-- TOC entry 170 (class 1259 OID 44043)
-- Name: pessoa_id_seq; Type: SEQUENCE; Schema: app; Owner: -
--

CREATE SEQUENCE app.pessoa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2863 (class 0 OID 0)
-- Dependencies: 170
-- Name: pessoa_id_seq; Type: SEQUENCE OWNED BY; Schema: app; Owner: -
--

ALTER SEQUENCE app.pessoa_id_seq OWNED BY app.pessoa.id;


--
-- TOC entry 173 (class 1259 OID 44053)
-- Name: usuario; Type: TABLE; Schema: app; Owner: -
--

CREATE TABLE app.usuario (
    id integer NOT NULL,
    nome character varying(255) NOT NULL,
    senha character varying(255) NOT NULL
);


--
-- TOC entry 172 (class 1259 OID 44051)
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: app; Owner: -
--

CREATE SEQUENCE app.usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2864 (class 0 OID 0)
-- Dependencies: 172
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: app; Owner: -
--

ALTER SEQUENCE app.usuario_id_seq OWNED BY app.usuario.id;


--
-- TOC entry 2740 (class 2604 OID 44048)
-- Name: pessoa id; Type: DEFAULT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.pessoa ALTER COLUMN id SET DEFAULT nextval('app.pessoa_id_seq'::regclass);


--
-- TOC entry 2741 (class 2604 OID 44056)
-- Name: usuario id; Type: DEFAULT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.usuario ALTER COLUMN id SET DEFAULT nextval('app.usuario_id_seq'::regclass);


--
-- TOC entry 2857 (class 0 OID 44053)
-- Dependencies: 173
-- Data for Name: usuario; Type: TABLE DATA; Schema: app; Owner: -
--

INSERT INTO app.usuario VALUES (1, 'soft', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');


--
-- TOC entry 2865 (class 0 OID 0)
-- Dependencies: 170
-- Name: pessoa_id_seq; Type: SEQUENCE SET; Schema: app; Owner: -
--

SELECT pg_catalog.setval('app.pessoa_id_seq', 1, true);


--
-- TOC entry 2866 (class 0 OID 0)
-- Dependencies: 172
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: app; Owner: -
--

SELECT pg_catalog.setval('app.usuario_id_seq', 2, true);


--
-- TOC entry 2743 (class 2606 OID 44065)
-- Name: pessoa pessoa_cpf_un; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.pessoa
    ADD CONSTRAINT pessoa_cpf_un UNIQUE (cpf);


--
-- TOC entry 2745 (class 2606 OID 44050)
-- Name: pessoa pessoa_pkey; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.pessoa
    ADD CONSTRAINT pessoa_pkey PRIMARY KEY (id);


--
-- TOC entry 2747 (class 2606 OID 44058)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: app; Owner: -
--

ALTER TABLE ONLY app.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


-- Completed on 2021-05-08 22:30:09

--
-- PostgreSQL database dump complete
--

