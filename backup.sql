--
-- PostgreSQL database dump
--

-- Dumped from database version 13.16 (Debian 13.16-1.pgdg120+1)
-- Dumped by pg_dump version 13.16 (Debian 13.16-1.pgdg120+1)

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
-- Name: church_cms; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA church_cms;


ALTER SCHEMA church_cms OWNER TO postgres;

--
-- Name: tbl_biodata_bdt_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_biodata_bdt_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_biodata_bdt_id_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: tbl_biodata; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_biodata (
    bdt_id bigint DEFAULT nextval('church_cms.tbl_biodata_bdt_id_seq'::regclass) NOT NULL,
    gnd_id bigint NOT NULL,
    first_name character varying NOT NULL,
    middle_name character varying,
    last_name character varying NOT NULL,
    id_number character varying,
    phone1 character varying,
    phone2 character varying,
    dob date,
    date_created timestamp without time zone,
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_biodata OWNER TO postgres;

--
-- Name: tbl_branch; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_branch (
    brn_id bigint NOT NULL,
    cty_id bigint NOT NULL,
    area character varying,
    branch_name character varying NOT NULL,
    description character varying,
    date_created timestamp without time zone DEFAULT now(),
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_branch OWNER TO postgres;

--
-- Name: tbl_branch_brn_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_branch_brn_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_branch_brn_id_seq OWNER TO postgres;

--
-- Name: tbl_branch_brn_id_seq; Type: SEQUENCE OWNED BY; Schema: church_cms; Owner: postgres
--

ALTER SEQUENCE church_cms.tbl_branch_brn_id_seq OWNED BY church_cms.tbl_branch.brn_id;


--
-- Name: tbl_campaign; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_campaign (
    cmp_id bigint NOT NULL,
    name character varying NOT NULL,
    description character varying NOT NULL,
    message character varying NOT NULL,
    dispatch_date date NOT NULL,
    date_created timestamp without time zone DEFAULT now(),
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_campaign OWNER TO postgres;

--
-- Name: tbl_campaign_cmp_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_campaign_cmp_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_campaign_cmp_id_seq OWNER TO postgres;

--
-- Name: tbl_campaign_cmp_id_seq; Type: SEQUENCE OWNED BY; Schema: church_cms; Owner: postgres
--

ALTER SEQUENCE church_cms.tbl_campaign_cmp_id_seq OWNED BY church_cms.tbl_campaign.cmp_id;


--
-- Name: tbl_campaign_group; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_campaign_group (
    cmg_id bigint NOT NULL,
    cmp_id bigint,
    grp_id bigint,
    date_created timestamp without time zone DEFAULT now(),
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_campaign_group OWNER TO postgres;

--
-- Name: tbl_campaign_group_cmg_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_campaign_group_cmg_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_campaign_group_cmg_id_seq OWNER TO postgres;

--
-- Name: tbl_campaign_group_cmg_id_seq; Type: SEQUENCE OWNED BY; Schema: church_cms; Owner: postgres
--

ALTER SEQUENCE church_cms.tbl_campaign_group_cmg_id_seq OWNED BY church_cms.tbl_campaign_group.cmg_id;


--
-- Name: tbl_county; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_county (
    cty_id bigint NOT NULL,
    name character varying NOT NULL,
    date_created timestamp without time zone DEFAULT now(),
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_county OWNER TO postgres;

--
-- Name: tbl_county_cty_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_county_cty_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_county_cty_id_seq OWNER TO postgres;

--
-- Name: tbl_county_cty_id_seq; Type: SEQUENCE OWNED BY; Schema: church_cms; Owner: postgres
--

ALTER SEQUENCE church_cms.tbl_county_cty_id_seq OWNED BY church_cms.tbl_county.cty_id;


--
-- Name: tbl_dependant; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_dependant (
    dep_id bigint NOT NULL,
    name character varying NOT NULL,
    description character varying,
    date_created timestamp without time zone,
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_dependant OWNER TO postgres;

--
-- Name: tbl_dependant_dep_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_dependant_dep_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_dependant_dep_id_seq OWNER TO postgres;

--
-- Name: tbl_dependant_dep_id_seq; Type: SEQUENCE OWNED BY; Schema: church_cms; Owner: postgres
--

ALTER SEQUENCE church_cms.tbl_dependant_dep_id_seq OWNED BY church_cms.tbl_dependant.dep_id;


--
-- Name: tbl_event_evn_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_event_evn_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_event_evn_id_seq OWNER TO postgres;

--
-- Name: tbl_event; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_event (
    evn_id bigint DEFAULT nextval('church_cms.tbl_event_evn_id_seq'::regclass) NOT NULL,
    brn_id bigint NOT NULL,
    ety_id bigint NOT NULL,
    name character varying NOT NULL,
    description character varying NOT NULL,
    from_date date NOT NULL,
    to_date date NOT NULL,
    is_pledgeable bigint DEFAULT 0,
    date_created timestamp without time zone DEFAULT now() NOT NULL,
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_event OWNER TO postgres;

--
-- Name: tbl_event_group; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_event_group (
    evm_id bigint NOT NULL,
    evn_id bigint NOT NULL,
    mem_id bigint NOT NULL,
    is_deleted bigint DEFAULT 0,
    date_created timestamp without time zone DEFAULT now(),
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_event_group OWNER TO postgres;

--
-- Name: tbl_event_group_evm_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_event_group_evm_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_event_group_evm_id_seq OWNER TO postgres;

--
-- Name: tbl_event_group_evm_id_seq; Type: SEQUENCE OWNED BY; Schema: church_cms; Owner: postgres
--

ALTER SEQUENCE church_cms.tbl_event_group_evm_id_seq OWNED BY church_cms.tbl_event_group.evm_id;


--
-- Name: tbl_event_type; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_event_type (
    ety_id bigint NOT NULL,
    name character varying NOT NULL,
    date_created timestamp without time zone DEFAULT now() NOT NULL,
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_event_type OWNER TO postgres;

--
-- Name: tbl_event_type_ety_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_event_type_ety_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_event_type_ety_id_seq OWNER TO postgres;

--
-- Name: tbl_event_type_ety_id_seq; Type: SEQUENCE OWNED BY; Schema: church_cms; Owner: postgres
--

ALTER SEQUENCE church_cms.tbl_event_type_ety_id_seq OWNED BY church_cms.tbl_event_type.ety_id;


--
-- Name: tbl_family; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_family (
    fml_id bigint NOT NULL,
    primary_mem_id bigint NOT NULL,
    name character varying NOT NULL,
    is_active bigint,
    date_created timestamp without time zone DEFAULT now(),
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_family OWNER TO postgres;

--
-- Name: tbl_family_fml_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_family_fml_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_family_fml_id_seq OWNER TO postgres;

--
-- Name: tbl_family_fml_id_seq; Type: SEQUENCE OWNED BY; Schema: church_cms; Owner: postgres
--

ALTER SEQUENCE church_cms.tbl_family_fml_id_seq OWNED BY church_cms.tbl_family.fml_id;


--
-- Name: tbl_gender; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_gender (
    gnd_id bigint NOT NULL,
    name character varying,
    date_created timestamp without time zone DEFAULT now(),
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_gender OWNER TO postgres;

--
-- Name: tbl_gender_gnd_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_gender_gnd_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_gender_gnd_id_seq OWNER TO postgres;

--
-- Name: tbl_gender_gnd_id_seq; Type: SEQUENCE OWNED BY; Schema: church_cms; Owner: postgres
--

ALTER SEQUENCE church_cms.tbl_gender_gnd_id_seq OWNED BY church_cms.tbl_gender.gnd_id;


--
-- Name: tbl_group_grp_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_group_grp_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_group_grp_id_seq OWNER TO postgres;

--
-- Name: tbl_group; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_group (
    grp_id bigint DEFAULT nextval('church_cms.tbl_group_grp_id_seq'::regclass) NOT NULL,
    brn_id bigint NOT NULL,
    email character varying,
    name character varying NOT NULL,
    description character varying,
    is_active bigint DEFAULT 1 NOT NULL,
    date_created timestamp without time zone DEFAULT now(),
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_group OWNER TO postgres;

--
-- Name: tbl_group_member; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_group_member (
    grm_id bigint NOT NULL,
    grp_id bigint NOT NULL,
    mem_id bigint NOT NULL,
    is_active bigint DEFAULT 1,
    date_created timestamp without time zone,
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_group_member OWNER TO postgres;

--
-- Name: tbl_group_member_grm_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_group_member_grm_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_group_member_grm_id_seq OWNER TO postgres;

--
-- Name: tbl_group_member_grm_id_seq; Type: SEQUENCE OWNED BY; Schema: church_cms; Owner: postgres
--

ALTER SEQUENCE church_cms.tbl_group_member_grm_id_seq OWNED BY church_cms.tbl_group_member.grm_id;


--
-- Name: tbl_group_social_media_link; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_group_social_media_link (
    gsm_id bigint NOT NULL,
    grp_id bigint NOT NULL,
    smd_id bigint NOT NULL,
    name character varying NOT NULL,
    link character varying NOT NULL,
    key character varying,
    token character varying,
    is_active bigint,
    date_created timestamp without time zone DEFAULT now(),
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_group_social_media_link OWNER TO postgres;

--
-- Name: tbl_group_social_media_links_gsm_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_group_social_media_links_gsm_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_group_social_media_links_gsm_id_seq OWNER TO postgres;

--
-- Name: tbl_group_social_media_links_gsm_id_seq; Type: SEQUENCE OWNED BY; Schema: church_cms; Owner: postgres
--

ALTER SEQUENCE church_cms.tbl_group_social_media_links_gsm_id_seq OWNED BY church_cms.tbl_group_social_media_link.gsm_id;


--
-- Name: tbl_group_type; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_group_type (
    gty_id bigint NOT NULL,
    name character varying NOT NULL,
    date_created timestamp without time zone DEFAULT now() NOT NULL,
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_group_type OWNER TO postgres;

--
-- Name: tbl_group_type_gty_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_group_type_gty_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_group_type_gty_id_seq OWNER TO postgres;

--
-- Name: tbl_group_type_gty_id_seq; Type: SEQUENCE OWNED BY; Schema: church_cms; Owner: postgres
--

ALTER SEQUENCE church_cms.tbl_group_type_gty_id_seq OWNED BY church_cms.tbl_group_type.gty_id;


--
-- Name: tbl_member_mem_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_member_mem_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_member_mem_id_seq OWNER TO postgres;

--
-- Name: tbl_member; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_member (
    mem_id bigint DEFAULT nextval('church_cms.tbl_member_mem_id_seq'::regclass) NOT NULL,
    bdt_id bigint,
    dep_id bigint,
    dob date,
    is_primary bigint DEFAULT 0,
    is_dependant bigint DEFAULT 0,
    has_dependant bigint DEFAULT 0,
    photo_link character varying,
    is_fullmember bigint DEFAULT 0,
    is_baptized bigint DEFAULT 0,
    is_active bigint DEFAULT 1,
    date_created timestamp without time zone DEFAULT now(),
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_member OWNER TO postgres;

--
-- Name: tbl_member_branch; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_member_branch (
    mbr_id bigint NOT NULL,
    mem_id bigint NOT NULL,
    brn_id bigint NOT NULL,
    date_created timestamp without time zone DEFAULT now(),
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_member_branch OWNER TO postgres;

--
-- Name: tbl_member_branch_mbr_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_member_branch_mbr_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_member_branch_mbr_id_seq OWNER TO postgres;

--
-- Name: tbl_member_branch_mbr_id_seq; Type: SEQUENCE OWNED BY; Schema: church_cms; Owner: postgres
--

ALTER SEQUENCE church_cms.tbl_member_branch_mbr_id_seq OWNED BY church_cms.tbl_member_branch.mbr_id;


--
-- Name: tbl_member_dependant; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_member_dependant (
    mdp_id bigint NOT NULL,
    fml_id bigint NOT NULL,
    mem_id bigint NOT NULL,
    date_created timestamp without time zone DEFAULT now(),
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_member_dependant OWNER TO postgres;

--
-- Name: tbl_member_dependant_mdp_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_member_dependant_mdp_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_member_dependant_mdp_id_seq OWNER TO postgres;

--
-- Name: tbl_member_dependant_mdp_id_seq; Type: SEQUENCE OWNED BY; Schema: church_cms; Owner: postgres
--

ALTER SEQUENCE church_cms.tbl_member_dependant_mdp_id_seq OWNED BY church_cms.tbl_member_dependant.mdp_id;


--
-- Name: tbl_message; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_message (
    msg_id bigint NOT NULL,
    cmp_id bigint,
    usr_id bigint,
    phone character varying,
    hashmessage character varying,
    sent_status bigint,
    recv_status bigint,
    ack_status bigint,
    date_created timestamp without time zone DEFAULT now(),
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_message OWNER TO postgres;

--
-- Name: tbl_messages_msg_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_messages_msg_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_messages_msg_id_seq OWNER TO postgres;

--
-- Name: tbl_messages_msg_id_seq; Type: SEQUENCE OWNED BY; Schema: church_cms; Owner: postgres
--

ALTER SEQUENCE church_cms.tbl_messages_msg_id_seq OWNED BY church_cms.tbl_message.msg_id;


--
-- Name: tbl_role; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_role (
    role_id bigint NOT NULL,
    name character varying NOT NULL,
    description character varying,
    is_active bigint DEFAULT 1,
    date_created timestamp without time zone DEFAULT now(),
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_role OWNER TO postgres;

--
-- Name: tbl_role_role_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_role_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_role_role_id_seq OWNER TO postgres;

--
-- Name: tbl_role_role_id_seq; Type: SEQUENCE OWNED BY; Schema: church_cms; Owner: postgres
--

ALTER SEQUENCE church_cms.tbl_role_role_id_seq OWNED BY church_cms.tbl_role.role_id;


--
-- Name: tbl_sermon_srm_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_sermon_srm_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_sermon_srm_id_seq OWNER TO postgres;

--
-- Name: tbl_sermon; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_sermon (
    srm_id bigint DEFAULT nextval('church_cms.tbl_sermon_srm_id_seq'::regclass) NOT NULL,
    brn_id bigint NOT NULL,
    title character varying NOT NULL,
    description character varying,
    sermon_date date NOT NULL,
    date_created timestamp without time zone DEFAULT now(),
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_sermon OWNER TO postgres;

--
-- Name: tbl_sermon_link; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_sermon_link (
    sml_id bigint NOT NULL,
    srm_id bigint,
    link character varying,
    date_created timestamp without time zone DEFAULT now(),
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_sermon_link OWNER TO postgres;

--
-- Name: tbl_sermon_links_sml_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_sermon_links_sml_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_sermon_links_sml_id_seq OWNER TO postgres;

--
-- Name: tbl_sermon_links_sml_id_seq; Type: SEQUENCE OWNED BY; Schema: church_cms; Owner: postgres
--

ALTER SEQUENCE church_cms.tbl_sermon_links_sml_id_seq OWNED BY church_cms.tbl_sermon_link.sml_id;


--
-- Name: tbl_social_media; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_social_media (
    scm_id bigint NOT NULL,
    name character varying NOT NULL,
    description character varying,
    date_created timestamp without time zone DEFAULT now(),
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_social_media OWNER TO postgres;

--
-- Name: tbl_social_media_scm_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_social_media_scm_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_social_media_scm_id_seq OWNER TO postgres;

--
-- Name: tbl_social_media_scm_id_seq; Type: SEQUENCE OWNED BY; Schema: church_cms; Owner: postgres
--

ALTER SEQUENCE church_cms.tbl_social_media_scm_id_seq OWNED BY church_cms.tbl_social_media.scm_id;


--
-- Name: tbl_user_usr_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_user_usr_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_user_usr_id_seq OWNER TO postgres;

--
-- Name: tbl_user; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_user (
    usr_id bigint DEFAULT nextval('church_cms.tbl_user_usr_id_seq'::regclass) NOT NULL,
    mem_id bigint NOT NULL,
    username character varying NOT NULL,
    password character varying NOT NULL,
    is_reset_password character varying,
    otp character varying,
    token character varying,
    date_created timestamp without time zone DEFAULT now(),
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_user OWNER TO postgres;

--
-- Name: tbl_user_role; Type: TABLE; Schema: church_cms; Owner: postgres
--

CREATE TABLE church_cms.tbl_user_role (
    usl_id bigint NOT NULL,
    user_id bigint,
    role_id bigint,
    is_active bigint DEFAULT 1,
    date_created timestamp without time zone DEFAULT now(),
    last_update timestamp without time zone
);


ALTER TABLE church_cms.tbl_user_role OWNER TO postgres;

--
-- Name: tbl_user_role_usl_id_seq; Type: SEQUENCE; Schema: church_cms; Owner: postgres
--

CREATE SEQUENCE church_cms.tbl_user_role_usl_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE church_cms.tbl_user_role_usl_id_seq OWNER TO postgres;

--
-- Name: tbl_user_role_usl_id_seq; Type: SEQUENCE OWNED BY; Schema: church_cms; Owner: postgres
--

ALTER SEQUENCE church_cms.tbl_user_role_usl_id_seq OWNED BY church_cms.tbl_user_role.usl_id;


--
-- Name: tbl_branch brn_id; Type: DEFAULT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_branch ALTER COLUMN brn_id SET DEFAULT nextval('church_cms.tbl_branch_brn_id_seq'::regclass);


--
-- Name: tbl_campaign cmp_id; Type: DEFAULT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_campaign ALTER COLUMN cmp_id SET DEFAULT nextval('church_cms.tbl_campaign_cmp_id_seq'::regclass);


--
-- Name: tbl_campaign_group cmg_id; Type: DEFAULT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_campaign_group ALTER COLUMN cmg_id SET DEFAULT nextval('church_cms.tbl_campaign_group_cmg_id_seq'::regclass);


--
-- Name: tbl_county cty_id; Type: DEFAULT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_county ALTER COLUMN cty_id SET DEFAULT nextval('church_cms.tbl_county_cty_id_seq'::regclass);


--
-- Name: tbl_dependant dep_id; Type: DEFAULT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_dependant ALTER COLUMN dep_id SET DEFAULT nextval('church_cms.tbl_dependant_dep_id_seq'::regclass);


--
-- Name: tbl_event_group evm_id; Type: DEFAULT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_event_group ALTER COLUMN evm_id SET DEFAULT nextval('church_cms.tbl_event_group_evm_id_seq'::regclass);


--
-- Name: tbl_event_type ety_id; Type: DEFAULT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_event_type ALTER COLUMN ety_id SET DEFAULT nextval('church_cms.tbl_event_type_ety_id_seq'::regclass);


--
-- Name: tbl_family fml_id; Type: DEFAULT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_family ALTER COLUMN fml_id SET DEFAULT nextval('church_cms.tbl_family_fml_id_seq'::regclass);


--
-- Name: tbl_gender gnd_id; Type: DEFAULT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_gender ALTER COLUMN gnd_id SET DEFAULT nextval('church_cms.tbl_gender_gnd_id_seq'::regclass);


--
-- Name: tbl_group_member grm_id; Type: DEFAULT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_group_member ALTER COLUMN grm_id SET DEFAULT nextval('church_cms.tbl_group_member_grm_id_seq'::regclass);


--
-- Name: tbl_group_social_media_link gsm_id; Type: DEFAULT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_group_social_media_link ALTER COLUMN gsm_id SET DEFAULT nextval('church_cms.tbl_group_social_media_links_gsm_id_seq'::regclass);


--
-- Name: tbl_group_type gty_id; Type: DEFAULT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_group_type ALTER COLUMN gty_id SET DEFAULT nextval('church_cms.tbl_group_type_gty_id_seq'::regclass);


--
-- Name: tbl_member_branch mbr_id; Type: DEFAULT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_member_branch ALTER COLUMN mbr_id SET DEFAULT nextval('church_cms.tbl_member_branch_mbr_id_seq'::regclass);


--
-- Name: tbl_member_dependant mdp_id; Type: DEFAULT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_member_dependant ALTER COLUMN mdp_id SET DEFAULT nextval('church_cms.tbl_member_dependant_mdp_id_seq'::regclass);


--
-- Name: tbl_message msg_id; Type: DEFAULT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_message ALTER COLUMN msg_id SET DEFAULT nextval('church_cms.tbl_messages_msg_id_seq'::regclass);


--
-- Name: tbl_role role_id; Type: DEFAULT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_role ALTER COLUMN role_id SET DEFAULT nextval('church_cms.tbl_role_role_id_seq'::regclass);


--
-- Name: tbl_sermon_link sml_id; Type: DEFAULT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_sermon_link ALTER COLUMN sml_id SET DEFAULT nextval('church_cms.tbl_sermon_links_sml_id_seq'::regclass);


--
-- Name: tbl_social_media scm_id; Type: DEFAULT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_social_media ALTER COLUMN scm_id SET DEFAULT nextval('church_cms.tbl_social_media_scm_id_seq'::regclass);


--
-- Name: tbl_user_role usl_id; Type: DEFAULT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_user_role ALTER COLUMN usl_id SET DEFAULT nextval('church_cms.tbl_user_role_usl_id_seq'::regclass);


--
-- Data for Name: tbl_biodata; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_biodata (bdt_id, gnd_id, first_name, middle_name, last_name, id_number, phone1, phone2, dob, date_created, last_update) FROM stdin;
1	1	Simon		Mwaura	26573462	0714812921	0722345678	1989-01-01	2025-01-09 16:35:54.374	2025-01-09 16:35:54.375
2	1	Simon		Kimani	26583462	0714812921	0722345678	1989-01-01	2025-01-09 16:51:27.546	2025-01-09 16:51:27.546
\.


--
-- Data for Name: tbl_branch; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_branch (brn_id, cty_id, area, branch_name, description, date_created, last_update) FROM stdin;
1	1	Kinamba	Kinamba FCGK	Full Gospel Churches of kenya	2025-01-09 13:52:00.635	2025-01-09 13:52:00.635
\.


--
-- Data for Name: tbl_campaign; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_campaign (cmp_id, name, description, message, dispatch_date, date_created, last_update) FROM stdin;
1	Prayer group meeting	meeting for prayers	meeting for prayers	2025-01-31	2025-01-09 17:46:36.723	2025-01-09 17:46:36.723
\.


--
-- Data for Name: tbl_campaign_group; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_campaign_group (cmg_id, cmp_id, grp_id, date_created, last_update) FROM stdin;
\.


--
-- Data for Name: tbl_county; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_county (cty_id, name, date_created, last_update) FROM stdin;
\.


--
-- Data for Name: tbl_dependant; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_dependant (dep_id, name, description, date_created, last_update) FROM stdin;
\.


--
-- Data for Name: tbl_event; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_event (evn_id, brn_id, ety_id, name, description, from_date, to_date, is_pledgeable, date_created, last_update) FROM stdin;
\.


--
-- Data for Name: tbl_event_group; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_event_group (evm_id, evn_id, mem_id, is_deleted, date_created, last_update) FROM stdin;
\.


--
-- Data for Name: tbl_event_type; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_event_type (ety_id, name, date_created, last_update) FROM stdin;
\.


--
-- Data for Name: tbl_family; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_family (fml_id, primary_mem_id, name, is_active, date_created, last_update) FROM stdin;
\.


--
-- Data for Name: tbl_gender; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_gender (gnd_id, name, date_created, last_update) FROM stdin;
\.


--
-- Data for Name: tbl_group; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_group (grp_id, brn_id, email, name, description, is_active, date_created, last_update) FROM stdin;
\.


--
-- Data for Name: tbl_group_member; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_group_member (grm_id, grp_id, mem_id, is_active, date_created, last_update) FROM stdin;
\.


--
-- Data for Name: tbl_group_social_media_link; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_group_social_media_link (gsm_id, grp_id, smd_id, name, link, key, token, is_active, date_created, last_update) FROM stdin;
\.


--
-- Data for Name: tbl_group_type; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_group_type (gty_id, name, date_created, last_update) FROM stdin;
\.


--
-- Data for Name: tbl_member; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_member (mem_id, bdt_id, dep_id, dob, is_primary, is_dependant, has_dependant, photo_link, is_fullmember, is_baptized, is_active, date_created, last_update) FROM stdin;
\.


--
-- Data for Name: tbl_member_branch; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_member_branch (mbr_id, mem_id, brn_id, date_created, last_update) FROM stdin;
\.


--
-- Data for Name: tbl_member_dependant; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_member_dependant (mdp_id, fml_id, mem_id, date_created, last_update) FROM stdin;
\.


--
-- Data for Name: tbl_message; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_message (msg_id, cmp_id, usr_id, phone, hashmessage, sent_status, recv_status, ack_status, date_created, last_update) FROM stdin;
\.


--
-- Data for Name: tbl_role; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_role (role_id, name, description, is_active, date_created, last_update) FROM stdin;
\.


--
-- Data for Name: tbl_sermon; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_sermon (srm_id, brn_id, title, description, sermon_date, date_created, last_update) FROM stdin;
1	1	Sermon on the Mountain - 2025	Sermon on the Mountain	2025-02-05	2025-01-09 18:11:29.467	2025-01-09 18:11:29.467
\.


--
-- Data for Name: tbl_sermon_link; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_sermon_link (sml_id, srm_id, link, date_created, last_update) FROM stdin;
\.


--
-- Data for Name: tbl_social_media; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_social_media (scm_id, name, description, date_created, last_update) FROM stdin;
\.


--
-- Data for Name: tbl_user; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_user (usr_id, mem_id, username, password, is_reset_password, otp, token, date_created, last_update) FROM stdin;
\.


--
-- Data for Name: tbl_user_role; Type: TABLE DATA; Schema: church_cms; Owner: postgres
--

COPY church_cms.tbl_user_role (usl_id, user_id, role_id, is_active, date_created, last_update) FROM stdin;
\.


--
-- Name: tbl_biodata_bdt_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_biodata_bdt_id_seq', 2, true);


--
-- Name: tbl_branch_brn_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_branch_brn_id_seq', 1, true);


--
-- Name: tbl_campaign_cmp_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_campaign_cmp_id_seq', 1, true);


--
-- Name: tbl_campaign_group_cmg_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_campaign_group_cmg_id_seq', 1, false);


--
-- Name: tbl_county_cty_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_county_cty_id_seq', 1, false);


--
-- Name: tbl_dependant_dep_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_dependant_dep_id_seq', 1, false);


--
-- Name: tbl_event_evn_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_event_evn_id_seq', 1, false);


--
-- Name: tbl_event_group_evm_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_event_group_evm_id_seq', 1, false);


--
-- Name: tbl_event_type_ety_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_event_type_ety_id_seq', 1, false);


--
-- Name: tbl_family_fml_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_family_fml_id_seq', 1, false);


--
-- Name: tbl_gender_gnd_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_gender_gnd_id_seq', 1, false);


--
-- Name: tbl_group_grp_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_group_grp_id_seq', 1, false);


--
-- Name: tbl_group_member_grm_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_group_member_grm_id_seq', 1, false);


--
-- Name: tbl_group_social_media_links_gsm_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_group_social_media_links_gsm_id_seq', 1, false);


--
-- Name: tbl_group_type_gty_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_group_type_gty_id_seq', 1, false);


--
-- Name: tbl_member_branch_mbr_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_member_branch_mbr_id_seq', 1, false);


--
-- Name: tbl_member_dependant_mdp_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_member_dependant_mdp_id_seq', 1, false);


--
-- Name: tbl_member_mem_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_member_mem_id_seq', 1, false);


--
-- Name: tbl_messages_msg_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_messages_msg_id_seq', 1, false);


--
-- Name: tbl_role_role_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_role_role_id_seq', 1, false);


--
-- Name: tbl_sermon_links_sml_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_sermon_links_sml_id_seq', 1, false);


--
-- Name: tbl_sermon_srm_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_sermon_srm_id_seq', 1, true);


--
-- Name: tbl_social_media_scm_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_social_media_scm_id_seq', 1, false);


--
-- Name: tbl_user_role_usl_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_user_role_usl_id_seq', 1, false);


--
-- Name: tbl_user_usr_id_seq; Type: SEQUENCE SET; Schema: church_cms; Owner: postgres
--

SELECT pg_catalog.setval('church_cms.tbl_user_usr_id_seq', 1, false);


--
-- Name: tbl_event_type event_type_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_event_type
    ADD CONSTRAINT event_type_pkey PRIMARY KEY (ety_id);


--
-- Name: tbl_biodata tbl_biodata_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_biodata
    ADD CONSTRAINT tbl_biodata_pkey PRIMARY KEY (bdt_id);


--
-- Name: tbl_branch tbl_branch_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_branch
    ADD CONSTRAINT tbl_branch_pkey PRIMARY KEY (brn_id, cty_id);


--
-- Name: tbl_campaign tbl_campaign_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_campaign
    ADD CONSTRAINT tbl_campaign_pkey PRIMARY KEY (cmp_id);


--
-- Name: tbl_county tbl_county_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_county
    ADD CONSTRAINT tbl_county_pkey PRIMARY KEY (cty_id);


--
-- Name: tbl_dependant tbl_dependant_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_dependant
    ADD CONSTRAINT tbl_dependant_pkey PRIMARY KEY (dep_id);


--
-- Name: tbl_event_group tbl_event_group_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_event_group
    ADD CONSTRAINT tbl_event_group_pkey PRIMARY KEY (evm_id);


--
-- Name: tbl_event tbl_event_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_event
    ADD CONSTRAINT tbl_event_pkey PRIMARY KEY (evn_id);


--
-- Name: tbl_family tbl_family_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_family
    ADD CONSTRAINT tbl_family_pkey PRIMARY KEY (fml_id);


--
-- Name: tbl_group_member tbl_group_member_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_group_member
    ADD CONSTRAINT tbl_group_member_pkey PRIMARY KEY (grm_id);


--
-- Name: tbl_group tbl_group_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_group
    ADD CONSTRAINT tbl_group_pkey PRIMARY KEY (grp_id);


--
-- Name: tbl_group_type tbl_group_type_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_group_type
    ADD CONSTRAINT tbl_group_type_pkey PRIMARY KEY (gty_id);


--
-- Name: tbl_member_branch tbl_member_branch_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_member_branch
    ADD CONSTRAINT tbl_member_branch_pkey PRIMARY KEY (mbr_id, mem_id, brn_id);


--
-- Name: tbl_member_dependant tbl_member_dependant_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_member_dependant
    ADD CONSTRAINT tbl_member_dependant_pkey PRIMARY KEY (mdp_id);


--
-- Name: tbl_member tbl_member_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_member
    ADD CONSTRAINT tbl_member_pkey PRIMARY KEY (mem_id);


--
-- Name: tbl_message tbl_messages_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_message
    ADD CONSTRAINT tbl_messages_pkey PRIMARY KEY (msg_id);


--
-- Name: tbl_role tbl_role_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_role
    ADD CONSTRAINT tbl_role_pkey PRIMARY KEY (role_id);


--
-- Name: tbl_sermon tbl_sermon_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_sermon
    ADD CONSTRAINT tbl_sermon_pkey PRIMARY KEY (srm_id);


--
-- Name: tbl_social_media tbl_social_media_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_social_media
    ADD CONSTRAINT tbl_social_media_pkey PRIMARY KEY (scm_id);


--
-- Name: tbl_user tbl_user_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_user
    ADD CONSTRAINT tbl_user_pkey PRIMARY KEY (usr_id);


--
-- Name: tbl_user_role user_role_pkey; Type: CONSTRAINT; Schema: church_cms; Owner: postgres
--

ALTER TABLE ONLY church_cms.tbl_user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (usl_id);


--
-- PostgreSQL database dump complete
--

