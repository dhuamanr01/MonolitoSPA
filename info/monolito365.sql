/*
 Navicat Premium Data Transfer

 Source Server         : BDPOSTGRES
 Source Server Type    : PostgreSQL
 Source Server Version : 120007
 Source Host           : localhost:5432
 Source Catalog        : postgres
 Source Schema         : monolito365

 Target Server Type    : PostgreSQL
 Target Server Version : 120007
 File Encoding         : 65001

 Date: 16/10/2022 22:11:13
*/


-- ----------------------------
-- Sequence structure for archivos_proyectos_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "monolito365"."archivos_proyectos_id_seq";
CREATE SEQUENCE "monolito365"."archivos_proyectos_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for clientes_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "monolito365"."clientes_id_seq";
CREATE SEQUENCE "monolito365"."clientes_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for proyectos_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "monolito365"."proyectos_id_seq";
CREATE SEQUENCE "monolito365"."proyectos_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for reset_token_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "monolito365"."reset_token_id_seq";
CREATE SEQUENCE "monolito365"."reset_token_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for roles_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "monolito365"."roles_id_seq";
CREATE SEQUENCE "monolito365"."roles_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for tipos_documentos_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "monolito365"."tipos_documentos_id_seq";
CREATE SEQUENCE "monolito365"."tipos_documentos_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for usuarios_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "monolito365"."usuarios_id_seq";
CREATE SEQUENCE "monolito365"."usuarios_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Table structure for archivos_proyectos
-- ----------------------------
DROP TABLE IF EXISTS "monolito365"."archivos_proyectos";
CREATE TABLE "monolito365"."archivos_proyectos" (
  "id" int4 NOT NULL DEFAULT nextval('"monolito365".archivos_proyectos_id_seq'::regclass),
  "created_at" timestamp(6),
  "nombre" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "ruta" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "updated_at" timestamp(6),
  "proyecto_id" int4 NOT NULL
)
;

-- ----------------------------
-- Records of archivos_proyectos
-- ----------------------------
INSERT INTO "monolito365"."archivos_proyectos" VALUES (1, '2022-08-18 21:55:14.859', 'AUDITORIA DE SISTEMA DE GESTION SST.PDF', 'Auditoria de Sistema de Gestion SST.pdf', NULL, 1);
INSERT INTO "monolito365"."archivos_proyectos" VALUES (2, '2022-08-28 22:46:15.11', 'HIGIENE INDUSTRIAL.PDF', 'Higiene Industrial.pdf', NULL, 2);
INSERT INTO "monolito365"."archivos_proyectos" VALUES (3, '2022-08-28 22:46:35.03', 'MONITOREO DE AGENTES OCUPACIONALES.PDF', 'Monitoreo de Agentes Ocupacionales.pdf', NULL, 3);
INSERT INTO "monolito365"."archivos_proyectos" VALUES (4, '2022-08-28 22:46:47.73', 'PLAN COVID-19.PDF', 'Plan COVID-19.pdf', NULL, 4);
INSERT INTO "monolito365"."archivos_proyectos" VALUES (5, '2022-09-05 19:40:55.504', 'SENIALIZACION MARITIMA.PDF', 'Senializacion Maritima.pdf', NULL, 7);

-- ----------------------------
-- Table structure for auth_servicio
-- ----------------------------
DROP TABLE IF EXISTS "monolito365"."auth_servicio";
CREATE TABLE "monolito365"."auth_servicio" (
  "id" int4 NOT NULL,
  "method" varchar(10) COLLATE "pg_catalog"."default" NOT NULL,
  "orden" int4 NOT NULL,
  "url" varchar(100) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of auth_servicio
-- ----------------------------
INSERT INTO "monolito365"."auth_servicio" VALUES (100, 'POST', 1, '/login/**');
INSERT INTO "monolito365"."auth_servicio" VALUES (200, 'GET', 1, '/usuario/list');
INSERT INTO "monolito365"."auth_servicio" VALUES (201, 'GET', 2, '/usuario/page/**');
INSERT INTO "monolito365"."auth_servicio" VALUES (202, 'GET', 3, '/usuario/findbyid/**');
INSERT INTO "monolito365"."auth_servicio" VALUES (240, 'PUT', 1, '/usuario');
INSERT INTO "monolito365"."auth_servicio" VALUES (260, 'DELETE', 1, '/usuario/**');
INSERT INTO "monolito365"."auth_servicio" VALUES (220, 'POST', 2, '/usuario');
INSERT INTO "monolito365"."auth_servicio" VALUES (320, 'POST', 4, '/proyecto');
INSERT INTO "monolito365"."auth_servicio" VALUES (400, 'GET', 4, '/proyecto/archivo/**');
INSERT INTO "monolito365"."auth_servicio" VALUES (300, 'GET', 5, '/proyecto/**');
INSERT INTO "monolito365"."auth_servicio" VALUES (340, 'PUT', 3, '/proyecto');
INSERT INTO "monolito365"."auth_servicio" VALUES (460, 'DELETE', 2, '/proyecto/archivo/**');
INSERT INTO "monolito365"."auth_servicio" VALUES (360, 'DELETE', 3, '/proyecto/**');
INSERT INTO "monolito365"."auth_servicio" VALUES (420, 'POST', 3, '/proyecto/archivo/**');
INSERT INTO "monolito365"."auth_servicio" VALUES (440, 'PUT', 2, '/proyecto/archivo/**');
INSERT INTO "monolito365"."auth_servicio" VALUES (203, 'GET', 4, '/usuario/findByUserName/**');

-- ----------------------------
-- Table structure for auth_servicio_role
-- ----------------------------
DROP TABLE IF EXISTS "monolito365"."auth_servicio_role";
CREATE TABLE "monolito365"."auth_servicio_role" (
  "id" int4 NOT NULL,
  "servicio_id" int4 NOT NULL,
  "role_id" int4
)
;

-- ----------------------------
-- Records of auth_servicio_role
-- ----------------------------
INSERT INTO "monolito365"."auth_servicio_role" VALUES (1, 100, NULL);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (2, 200, 1);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (3, 201, 1);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (4, 202, 1);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (5, 220, 1);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (6, 240, 1);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (7, 260, 1);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (8, 200, 2);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (9, 201, 2);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (10, 202, 2);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (11, 220, 2);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (12, 240, 2);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (13, 260, 2);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (14, 300, 1);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (15, 320, 1);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (16, 340, 1);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (17, 360, 1);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (18, 300, 2);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (19, 320, 2);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (20, 340, 2);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (21, 360, 2);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (22, 400, 1);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (23, 420, 1);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (24, 440, 1);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (25, 460, 1);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (26, 400, 2);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (27, 420, 2);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (28, 440, 2);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (29, 460, 2);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (30, 203, 1);
INSERT INTO "monolito365"."auth_servicio_role" VALUES (31, 203, 2);

-- ----------------------------
-- Table structure for clientes
-- ----------------------------
DROP TABLE IF EXISTS "monolito365"."clientes";
CREATE TABLE "monolito365"."clientes" (
  "id" int4 NOT NULL DEFAULT nextval('"monolito365".clientes_id_seq'::regclass),
  "apellidos" varchar(50) COLLATE "pg_catalog"."default",
  "direccion" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "email" varchar(60) COLLATE "pg_catalog"."default" NOT NULL,
  "fecha_modificacion" date,
  "fecha_registro" date NOT NULL,
  "nombres" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "numero_doc" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "pagina_web" varchar(60) COLLATE "pg_catalog"."default",
  "telefono1" varchar(12) COLLATE "pg_catalog"."default" NOT NULL,
  "telefono2" varchar(12) COLLATE "pg_catalog"."default",
  "tipo_documento_id" int4 NOT NULL,
  "usuario_id" int4
)
;

-- ----------------------------
-- Records of clientes
-- ----------------------------

-- ----------------------------
-- Table structure for proyectos
-- ----------------------------
DROP TABLE IF EXISTS "monolito365"."proyectos";
CREATE TABLE "monolito365"."proyectos" (
  "id" int4 NOT NULL DEFAULT nextval('"monolito365".proyectos_id_seq'::regclass),
  "created_at" timestamp(6),
  "descripcion" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "estado" bool NOT NULL,
  "fecha_expiracion" timestamp(6),
  "proyecto" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "updated_at" timestamp(6),
  "usuario_id" int4 NOT NULL
)
;

-- ----------------------------
-- Records of proyectos
-- ----------------------------
INSERT INTO "monolito365"."proyectos" VALUES (2, '2022-08-21 21:33:37.441', 'Higiene Industrial', 't', '2022-10-13 21:33:37.441', 'Higiene Industrial', NULL, 2);
INSERT INTO "monolito365"."proyectos" VALUES (3, '2022-08-28 22:38:05.933', 'Monitoreo de Agentes Ocupacionales.', 't', '2023-01-28 22:38:05.932', 'Monitoreo de Agentes Ocupacionales.', NULL, 5);
INSERT INTO "monolito365"."proyectos" VALUES (4, '2022-08-28 22:38:58.42', 'Plan COVID-19', 't', '2022-12-28 22:38:58.42', 'Plan COVID-19', NULL, 6);
INSERT INTO "monolito365"."proyectos" VALUES (5, '2022-08-28 22:39:29.493', 'Sistema de alarma contra incendios.', 't', '2023-01-20 22:39:29.492', 'Sistema de alarma contra incendios.', NULL, 6);
INSERT INTO "monolito365"."proyectos" VALUES (6, '2022-08-28 22:40:16.192', 'Señalizacion Industrial.', 't', '2023-02-27 22:45:11.396', 'Señalizacion Industrial', '2022-08-28 22:45:11.396', 4);
INSERT INTO "monolito365"."proyectos" VALUES (7, '2022-09-05 19:40:42.147', 'Senializacion Maritima', 't', '2023-03-05 19:40:42.147', 'Senializacion Maritima', NULL, 5);
INSERT INTO "monolito365"."proyectos" VALUES (1, '2022-08-18 21:27:24.635', 'Auditoria de Sistema de Gestion SST.', 't', '2022-10-18 19:55:57.25', 'Auditoria de Sistema de Gestion SSTT', '2022-09-05 19:55:57.25', 1);

-- ----------------------------
-- Table structure for reset_token
-- ----------------------------
DROP TABLE IF EXISTS "monolito365"."reset_token";
CREATE TABLE "monolito365"."reset_token" (
  "id" int8 NOT NULL DEFAULT nextval('"monolito365".reset_token_id_seq'::regclass),
  "expiracion" timestamp(6) NOT NULL,
  "token" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "id_usuario" int4 NOT NULL
)
;

-- ----------------------------
-- Records of reset_token
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS "monolito365"."roles";
CREATE TABLE "monolito365"."roles" (
  "id" int4 NOT NULL DEFAULT nextval('"monolito365".roles_id_seq'::regclass),
  "created_at" timestamp(6),
  "nombre" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "updated_at" timestamp(6),
  "auth" varchar(30) COLLATE "pg_catalog"."default" NOT NULL DEFAULT ''::character varying
)
;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO "monolito365"."roles" VALUES (1, '2021-08-19 00:00:00', 'ADMIN', NULL, 'ROLE_ADMIN');
INSERT INTO "monolito365"."roles" VALUES (2, '2021-08-19 00:00:00', 'USER', NULL, 'ROLE_USER');

-- ----------------------------
-- Table structure for tipos_documentos
-- ----------------------------
DROP TABLE IF EXISTS "monolito365"."tipos_documentos";
CREATE TABLE "monolito365"."tipos_documentos" (
  "id" int4 NOT NULL DEFAULT nextval('"monolito365".tipos_documentos_id_seq'::regclass),
  "created_at" timestamp(6),
  "descripcion" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "updated_at" timestamp(6)
)
;

-- ----------------------------
-- Records of tipos_documentos
-- ----------------------------
INSERT INTO "monolito365"."tipos_documentos" VALUES (1, '2020-03-04 00:00:00', 'DNI', '2021-03-04 00:00:00');
INSERT INTO "monolito365"."tipos_documentos" VALUES (2, '2020-03-04 00:00:00', 'RUC', '2021-03-04 00:00:00');

-- ----------------------------
-- Table structure for usuarios
-- ----------------------------
DROP TABLE IF EXISTS "monolito365"."usuarios";
CREATE TABLE "monolito365"."usuarios" (
  "id" int4 NOT NULL DEFAULT nextval('"monolito365".usuarios_id_seq'::regclass),
  "created_at" timestamp(6),
  "estado" bool NOT NULL,
  "password" varchar(60) COLLATE "pg_catalog"."default" NOT NULL,
  "updated_at" timestamp(6),
  "username" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "rol_id" int4 NOT NULL
)
;

-- ----------------------------
-- Records of usuarios
-- ----------------------------
INSERT INTO "monolito365"."usuarios" VALUES (1, '2022-08-14 22:38:10', 't', '1234', '2022-08-18 22:16:24.996', 'mportilla', 1);
INSERT INTO "monolito365"."usuarios" VALUES (2, '2022-08-18 21:25:34.701', 't', '1234', '2022-08-18 22:13:24.294', 'equenhua', 1);
INSERT INTO "monolito365"."usuarios" VALUES (4, '2022-08-21 21:36:17.296', 't', '1234', NULL, 'copeinca2022', 2);
INSERT INTO "monolito365"."usuarios" VALUES (5, '2022-08-21 21:36:34.914', 't', '1234', NULL, 'jpsima2022', 2);
INSERT INTO "monolito365"."usuarios" VALUES (6, '2022-08-21 21:37:13.937', 't', '1234', NULL, 'mqtasa2022', 2);
INSERT INTO "monolito365"."usuarios" VALUES (7, '2022-08-21 21:37:27.886', 't', '1234', NULL, 'comet2022', 2);
INSERT INTO "monolito365"."usuarios" VALUES (8, '2022-08-21 21:37:40.444', 't', '12234', NULL, 'cfginv2022', 2);
INSERT INTO "monolito365"."usuarios" VALUES (9, '2022-08-21 21:38:07.158', 't', '1234', NULL, 'exalmar2022', 2);
INSERT INTO "monolito365"."usuarios" VALUES (10, '2022-08-21 21:38:46.691', 't', '1234', NULL, 'pcentinela2022', 2);
INSERT INTO "monolito365"."usuarios" VALUES (11, '2022-08-21 21:39:04.967', 't', '1234', NULL, 'pgomez2022', 2);
INSERT INTO "monolito365"."usuarios" VALUES (12, '2022-09-30 23:22:50.711', 't', '1234', NULL, 'test3009', 1);
INSERT INTO "monolito365"."usuarios" VALUES (13, '2022-10-02 18:00:10.914', 't', '1234', NULL, 'test0210', 2);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "monolito365"."archivos_proyectos_id_seq"
OWNED BY "monolito365"."archivos_proyectos"."id";
SELECT setval('"monolito365"."archivos_proyectos_id_seq"', 6, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "monolito365"."clientes_id_seq"
OWNED BY "monolito365"."clientes"."id";
SELECT setval('"monolito365"."clientes_id_seq"', 8, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "monolito365"."proyectos_id_seq"
OWNED BY "monolito365"."proyectos"."id";
SELECT setval('"monolito365"."proyectos_id_seq"', 8, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "monolito365"."reset_token_id_seq"
OWNED BY "monolito365"."reset_token"."id";
SELECT setval('"monolito365"."reset_token_id_seq"', 4, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "monolito365"."roles_id_seq"
OWNED BY "monolito365"."roles"."id";
SELECT setval('"monolito365"."roles_id_seq"', 4, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "monolito365"."tipos_documentos_id_seq"
OWNED BY "monolito365"."tipos_documentos"."id";
SELECT setval('"monolito365"."tipos_documentos_id_seq"', 4, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "monolito365"."usuarios_id_seq"
OWNED BY "monolito365"."usuarios"."id";
SELECT setval('"monolito365"."usuarios_id_seq"', 14, true);

-- ----------------------------
-- Indexes structure for table archivos_proyectos
-- ----------------------------
CREATE INDEX "archivos_proyectos_proyecto_id_idx" ON "monolito365"."archivos_proyectos" USING btree (
  "proyecto_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Uniques structure for table archivos_proyectos
-- ----------------------------
ALTER TABLE "monolito365"."archivos_proyectos" ADD CONSTRAINT "uk_4s0wu8u6o0ufvsniv68k0r2sc" UNIQUE ("nombre");

-- ----------------------------
-- Primary Key structure for table archivos_proyectos
-- ----------------------------
ALTER TABLE "monolito365"."archivos_proyectos" ADD CONSTRAINT "archivos_proyectos_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table auth_servicio
-- ----------------------------
ALTER TABLE "monolito365"."auth_servicio" ADD CONSTRAINT "auth_servicio_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table auth_servicio_role
-- ----------------------------
CREATE INDEX "auth_servicio_role_role_id_idx" ON "monolito365"."auth_servicio_role" USING btree (
  "role_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);
CREATE INDEX "auth_servicio_role_servicio_id_idx" ON "monolito365"."auth_servicio_role" USING btree (
  "servicio_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table auth_servicio_role
-- ----------------------------
ALTER TABLE "monolito365"."auth_servicio_role" ADD CONSTRAINT "auth_servicio_role_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table clientes
-- ----------------------------
CREATE INDEX "clientes_tipo_documento_id_idx" ON "monolito365"."clientes" USING btree (
  "tipo_documento_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);
CREATE INDEX "clientes_usuario_id_idx" ON "monolito365"."clientes" USING btree (
  "usuario_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table clientes
-- ----------------------------
ALTER TABLE "monolito365"."clientes" ADD CONSTRAINT "clientes_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table proyectos
-- ----------------------------
CREATE INDEX "proyectos_usuario_id_idx" ON "monolito365"."proyectos" USING btree (
  "usuario_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table proyectos
-- ----------------------------
ALTER TABLE "monolito365"."proyectos" ADD CONSTRAINT "proyectos_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table reset_token
-- ----------------------------
CREATE INDEX "reset_token_id_usuario_idx" ON "monolito365"."reset_token" USING btree (
  "id_usuario" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Uniques structure for table reset_token
-- ----------------------------
ALTER TABLE "monolito365"."reset_token" ADD CONSTRAINT "uk_shiutqgqq3m7hdrlmckbk4am6" UNIQUE ("token");

-- ----------------------------
-- Primary Key structure for table reset_token
-- ----------------------------
ALTER TABLE "monolito365"."reset_token" ADD CONSTRAINT "reset_token_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table roles
-- ----------------------------
ALTER TABLE "monolito365"."roles" ADD CONSTRAINT "roles_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table tipos_documentos
-- ----------------------------
ALTER TABLE "monolito365"."tipos_documentos" ADD CONSTRAINT "tipos_documentos_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table usuarios
-- ----------------------------
CREATE INDEX "usuarios_rol_id_idx" ON "monolito365"."usuarios" USING btree (
  "rol_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table usuarios
-- ----------------------------
ALTER TABLE "monolito365"."usuarios" ADD CONSTRAINT "usuarios_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table archivos_proyectos
-- ----------------------------
ALTER TABLE "monolito365"."archivos_proyectos" ADD CONSTRAINT "fk7vr9v72kx8ky2vn0hmc9y46t3" FOREIGN KEY ("proyecto_id") REFERENCES "monolito365"."proyectos" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table auth_servicio_role
-- ----------------------------
ALTER TABLE "monolito365"."auth_servicio_role" ADD CONSTRAINT "auth_servicio_role_role_id_fkey" FOREIGN KEY ("role_id") REFERENCES "monolito365"."roles" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "monolito365"."auth_servicio_role" ADD CONSTRAINT "auth_servicio_role_servicio_id_fkey" FOREIGN KEY ("servicio_id") REFERENCES "monolito365"."auth_servicio" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table clientes
-- ----------------------------
ALTER TABLE "monolito365"."clientes" ADD CONSTRAINT "fkk6iwsq3kts1bblivkjy6epajx" FOREIGN KEY ("usuario_id") REFERENCES "monolito365"."usuarios" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "monolito365"."clientes" ADD CONSTRAINT "fkqerxdg5bvo6941uruyn1ointf" FOREIGN KEY ("tipo_documento_id") REFERENCES "monolito365"."tipos_documentos" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table proyectos
-- ----------------------------
ALTER TABLE "monolito365"."proyectos" ADD CONSTRAINT "fkjxx999dwi209rsxmvqmjb2sen" FOREIGN KEY ("usuario_id") REFERENCES "monolito365"."usuarios" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table reset_token
-- ----------------------------
ALTER TABLE "monolito365"."reset_token" ADD CONSTRAINT "fkbm0dfy24tj36rv1u6p2x1x61w" FOREIGN KEY ("id_usuario") REFERENCES "monolito365"."usuarios" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table usuarios
-- ----------------------------
ALTER TABLE "monolito365"."usuarios" ADD CONSTRAINT "fkqf5elo4jcq7qrt83oi0qmenjo" FOREIGN KEY ("rol_id") REFERENCES "monolito365"."roles" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
