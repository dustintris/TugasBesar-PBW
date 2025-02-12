PGDMP                      |         
   TugasBesar    17.2    17.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false                       1262    16404 
   TugasBesar    DATABASE     �   CREATE DATABASE "TugasBesar" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_Indonesia.1252';
    DROP DATABASE "TugasBesar";
                     postgres    false            �            1259    16465 
   activities    TABLE     0  CREATE TABLE public.activities (
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
    DROP TABLE public.activities;
       public         heap r       postgres    false            �            1259    16464    activities_id_seq    SEQUENCE     �   CREATE SEQUENCE public.activities_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.activities_id_seq;
       public               postgres    false    223                       0    0    activities_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.activities_id_seq OWNED BY public.activities.id;
          public               postgres    false    222            �            1259    16438    lomba    TABLE     [  CREATE TABLE public.lomba (
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
    DROP TABLE public.lomba;
       public         heap r       postgres    false            �            1259    16437    lomba_id_seq    SEQUENCE     �   CREATE SEQUENCE public.lomba_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.lomba_id_seq;
       public               postgres    false    220                       0    0    lomba_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.lomba_id_seq OWNED BY public.lomba.id;
          public               postgres    false    219            �            1259    16447    partisipasilomba    TABLE     �  CREATE TABLE public.partisipasilomba (
    user_id integer NOT NULL,
    event_id integer NOT NULL,
    status character varying(50) NOT NULL,
    joined_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT partisipasilomba_status_check CHECK (((status)::text = ANY ((ARRAY['Registered'::character varying, 'Completed'::character varying, 'Cancelled'::character varying])::text[])))
);
 $   DROP TABLE public.partisipasilomba;
       public         heap r       postgres    false            �            1259    16411    users    TABLE       CREATE TABLE public.users (
    id integer NOT NULL,
    email character varying(255) NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(50) DEFAULT 'user'::character varying NOT NULL
);
    DROP TABLE public.users;
       public         heap r       postgres    false            �            1259    16410    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public               postgres    false    218                       0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public               postgres    false    217            j           2604    16468 
   activities id    DEFAULT     n   ALTER TABLE ONLY public.activities ALTER COLUMN id SET DEFAULT nextval('public.activities_id_seq'::regclass);
 <   ALTER TABLE public.activities ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    223    222    223            g           2604    16441    lomba id    DEFAULT     d   ALTER TABLE ONLY public.lomba ALTER COLUMN id SET DEFAULT nextval('public.lomba_id_seq'::regclass);
 7   ALTER TABLE public.lomba ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    220    219    220            e           2604    16414    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    218    217    218                      0    16465 
   activities 
   TABLE DATA           w   COPY public.activities (id, userid, title, description, activitydate, duration, range, picture, createdat) FROM stdin;
    public               postgres    false    223   $                 0    16438    lomba 
   TABLE DATA           z   COPY public.lomba (id, title, start_date, regis_start, regis_end, description, location, range, time_created) FROM stdin;
    public               postgres    false    220   :$                 0    16447    partisipasilomba 
   TABLE DATA           P   COPY public.partisipasilomba (user_id, event_id, status, joined_at) FROM stdin;
    public               postgres    false    221   W$       	          0    16411    users 
   TABLE DATA           D   COPY public.users (id, email, username, password, role) FROM stdin;
    public               postgres    false    218   t$                  0    0    activities_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.activities_id_seq', 1, false);
          public               postgres    false    222                       0    0    lomba_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.lomba_id_seq', 1, false);
          public               postgres    false    219                       0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 7, true);
          public               postgres    false    217            t           2606    16473    activities activities_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.activities
    ADD CONSTRAINT activities_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.activities DROP CONSTRAINT activities_pkey;
       public                 postgres    false    223            p           2606    16446    lomba lomba_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.lomba
    ADD CONSTRAINT lomba_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.lomba DROP CONSTRAINT lomba_pkey;
       public                 postgres    false    220            r           2606    16453 &   partisipasilomba partisipasilomba_pkey 
   CONSTRAINT     s   ALTER TABLE ONLY public.partisipasilomba
    ADD CONSTRAINT partisipasilomba_pkey PRIMARY KEY (user_id, event_id);
 P   ALTER TABLE ONLY public.partisipasilomba DROP CONSTRAINT partisipasilomba_pkey;
       public                 postgres    false    221    221            n           2606    16418    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public                 postgres    false    218            u           2606    16459 /   partisipasilomba partisipasilomba_event_id_fkey 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.partisipasilomba
    ADD CONSTRAINT partisipasilomba_event_id_fkey FOREIGN KEY (event_id) REFERENCES public.lomba(id) ON DELETE CASCADE;
 Y   ALTER TABLE ONLY public.partisipasilomba DROP CONSTRAINT partisipasilomba_event_id_fkey;
       public               postgres    false    221    4720    220            v           2606    16454 .   partisipasilomba partisipasilomba_user_id_fkey 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.partisipasilomba
    ADD CONSTRAINT partisipasilomba_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;
 X   ALTER TABLE ONLY public.partisipasilomba DROP CONSTRAINT partisipasilomba_user_id_fkey;
       public               postgres    false    221    4718    218               
   x������ � �         
   x������ � �         
   x������ � �      	   �   x���MS�@ ��3|μ�y#AZ�f!�����.$�|�T�i:7���ߣq(�������	�iM��	*Y��=�4��9�!p��փPL5Õ�X^cYw���D�*m�ee�����u����c_�����\�ڎT]CZ�����n�o��أ�>��w_������� ��qKU��.Y�`�W���q�m���"Z�uY#*���q�����c���
��5^C/�<h#��	��fʢЎ���sX��z4�"��?��z�     