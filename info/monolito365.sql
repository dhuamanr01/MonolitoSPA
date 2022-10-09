/*
 Navicat PostgreSQL Data Transfer

 Source Server         : lnvwinqa_13.2.1_5432
 Source Server Type    : PostgreSQL
 Source Server Version : 130002
 Source Host           : 192.168.1.76:5432
 Source Catalog        : postgres
 Source Schema         : monolito365

 Target Server Type    : PostgreSQL
 Target Server Version : 130002
 File Encoding         : 65001

 Date: 31/08/2021 01:22:00
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
INSERT INTO "monolito365"."archivos_proyectos" VALUES (5, '2021-08-30 22:49:02.655', 'TIT3', 'Test.pdf', NULL, 3);
INSERT INTO "monolito365"."archivos_proyectos" VALUES (6, '2021-08-30 22:51:06.472', 'TIT4', 'Test.docx', NULL, 3);
INSERT INTO "monolito365"."archivos_proyectos" VALUES (7, '2021-08-30 22:51:37.231', 'TIT5', 'Test.xlsx', NULL, 3);
INSERT INTO "monolito365"."archivos_proyectos" VALUES (10, '2021-08-31 01:16:50.137', 'TIT0099', 'Test9999.docx', NULL, 3);

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
INSERT INTO "monolito365"."clientes" VALUES (1, NULL, 'Mz H Lt 9 Pj 2 De Junio', 'servivega2002@gmail.com', '2020-03-04', '2020-03-04', 'Servi Vega E.I.R.L.', '20100003351', 'http://www.servivegaeirl.com', '043343714', '043343714', 2, 6);
INSERT INTO "monolito365"."clientes" VALUES (2, NULL, 'Metal Mecanica Mariategui S.A.C.', 'metalmariategui@hotmail.com', NULL, '2020-03-04', 'Metal Mecanica Mariategui S.A.C.', '20100003352', NULL, '043352099', NULL, 2, 7);
INSERT INTO "monolito365"."clientes" VALUES (3, NULL, 'Av. Brasil A-30 Urb. Los Álamos Nuevo Chimbote - Ancash - Perú', 'info@comet.com.pe', NULL, '2020-03-04', 'Comet S.R.L', '20100003353', NULL, '043318308', NULL, 2, NULL);
INSERT INTO "monolito365"."clientes" VALUES (6, NULL, 'Av. Industrial 1240, Chimbote', 'sales@copeinca.com.pe', NULL, '2020-03-04', 'Corporación Pesquera Inca S.A.C', '20100003356', NULL, '043365878', NULL, 2, NULL);
INSERT INTO "monolito365"."clientes" VALUES (4, NULL, 'Calle Chiclayo N° 157 Dpto. 401, Miraflores-Chimbote', 'informes@ifm.com.pe', NULL, '2020-03-04', 'Ingeniería, Fabricación y Montaje SAC', '20100003354', NULL, '043316688', NULL, 2, 3);
INSERT INTO "monolito365"."clientes" VALUES (5, NULL, 'Av. Los Pescadores s/n, Zona Industrial 27 de Octubre, Chimbote.', 'comunicaciones@tasa.com.pe', NULL, '2020-03-04', 'TASA', '20100003355', NULL, '043352160', NULL, 2, 3);

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
INSERT INTO "monolito365"."proyectos" VALUES (1, '2020-08-15 00:00:00', 'proyecto 1', 'f', '2020-10-01 00:00:00', 'proyecto 1', NULL, 2);
INSERT INTO "monolito365"."proyectos" VALUES (2, '2020-08-20 00:00:00', 'proyecto 2', 'f', '2020-12-20 00:00:00', 'proyecto 2', NULL, 3);
INSERT INTO "monolito365"."proyectos" VALUES (3, '2021-08-20 00:00:00', 'proyecto 3', 't', '2021-11-01 00:00:00', 'proyecto 3', NULL, 3);
INSERT INTO "monolito365"."proyectos" VALUES (4, '2021-08-29 13:59:56.256', 'test', 't', '2021-01-01 13:59:56.256', 'PROJECT 01', NULL, 2);
INSERT INTO "monolito365"."proyectos" VALUES (5, '2021-08-29 14:10:34.464', 'test update', 'f', '2021-08-30 14:41:45.305', 'PROJECT X02', '2021-08-29 14:42:15.42', 1);

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
INSERT INTO "monolito365"."reset_token" VALUES (1, '2021-08-27 20:13:38', 'XXXX', 3);
INSERT INTO "monolito365"."reset_token" VALUES (2, '2021-08-27 20:13:51', 'YYY', 3);

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
INSERT INTO "monolito365"."tipos_documentos" VALUES (1, '2020-03-04 00:00:00', 'DNI', '2020-03-04 00:00:00');
INSERT INTO "monolito365"."tipos_documentos" VALUES (2, '2020-03-04 00:00:00', 'RUC', '2020-03-04 00:00:00');

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
INSERT INTO "monolito365"."usuarios" VALUES (1, '2020-02-23 00:00:00', 't', '12345', '2020-02-23 00:00:00', 'mportilla2020', 1);
INSERT INTO "monolito365"."usuarios" VALUES (2, '2020-02-23 00:00:00', 't', '1234', '2020-02-23 00:00:00', 'copeinca2020', 2);
INSERT INTO "monolito365"."usuarios" VALUES (4, '2020-02-23 00:00:00', 't', '1234', '2020-02-23 00:00:00', 'mqtasa2020', 2);
INSERT INTO "monolito365"."usuarios" VALUES (5, '2021-08-19 00:00:00', 't', '0246', '2021-08-19 00:00:00', 'dhuamanr', 1);
INSERT INTO "monolito365"."usuarios" VALUES (6, '2021-08-19 00:00:00', 't', '1234', '2021-08-19 00:00:00', 'comet0120', 2);
INSERT INTO "monolito365"."usuarios" VALUES (7, '2021-08-19 00:00:00', 't', '1234', '2021-08-19 00:00:00', 'pesinca2018', 2);
INSERT INTO "monolito365"."usuarios" VALUES (9, '2021-08-28 22:30:49.08', 't', 'xxxx', NULL, 'Carlos', 2);
INSERT INTO "monolito365"."usuarios" VALUES (8, '2021-08-28 21:21:01.334', 'f', '999', '2021-08-28 22:36:20.117', 'Juancho', 1);
INSERT INTO "monolito365"."usuarios" VALUES (10, '2021-08-28 22:42:44.075', 'f', '999', '2021-08-28 22:44:07.684', 'Juancho', 1);
INSERT INTO "monolito365"."usuarios" VALUES (11, '2021-08-29 01:06:43.269', 'f', '999', '2021-08-29 01:07:50.257', 'Juancho', 1);
INSERT INTO "monolito365"."usuarios" VALUES (12, '2021-08-29 01:12:44.116', 't', 'xxxx', NULL, 'Juancho', 2);
INSERT INTO "monolito365"."usuarios" VALUES (13, '2021-08-29 01:30:19.123', 'f', '999', '2021-08-29 01:31:00.176', 'Juancho44', 1);
INSERT INTO "monolito365"."usuarios" VALUES (14, '2021-08-30 19:13:42.785', 'f', '555', '2021-08-30 19:19:19.188', 'YYYYYYY', 2);
INSERT INTO "monolito365"."usuarios" VALUES (15, '2021-08-30 21:56:50.211', 't', '999', NULL, 'Test01', 1);
INSERT INTO "monolito365"."usuarios" VALUES (3, '2020-02-23 00:00:00', 't', '555', '2021-08-30 21:57:55.927', 'Test0X', 2);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "monolito365"."archivos_proyectos_id_seq"
OWNED BY "monolito365"."archivos_proyectos"."id";
SELECT setval('"monolito365"."archivos_proyectos_id_seq"', 11, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "monolito365"."clientes_id_seq"
OWNED BY "monolito365"."clientes"."id";
SELECT setval('"monolito365"."clientes_id_seq"', 7, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "monolito365"."proyectos_id_seq"
OWNED BY "monolito365"."proyectos"."id";
SELECT setval('"monolito365"."proyectos_id_seq"', 6, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "monolito365"."reset_token_id_seq"
OWNED BY "monolito365"."reset_token"."id";
SELECT setval('"monolito365"."reset_token_id_seq"', 3, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "monolito365"."roles_id_seq"
OWNED BY "monolito365"."roles"."id";
SELECT setval('"monolito365"."roles_id_seq"', 3, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "monolito365"."tipos_documentos_id_seq"
OWNED BY "monolito365"."tipos_documentos"."id";
SELECT setval('"monolito365"."tipos_documentos_id_seq"', 3, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "monolito365"."usuarios_id_seq"
OWNED BY "monolito365"."usuarios"."id";
SELECT setval('"monolito365"."usuarios_id_seq"', 16, true);

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
