-- Prepara SQL Server para que el proyecto de la pizzeria se pueda conectar.
-- Cada integrante lo ejecuta UNA sola vez en su propio SQL Server (localhost).
--
-- La clave es la misma que usa el codigo en SqlServerDAOFactory (constante CLAVE).
--
-- Crea:
--   1. La base de datos Pizzeria (si no existe).
--   2. El login pizzeriaUser, para entrar al servidor.
--   3. El usuario pizzeriaUser dentro de la base Pizzeria, con permisos completos sobre ella.

IF DB_ID('Pizzeria') IS NULL
    CREATE DATABASE Pizzeria;
GO

IF NOT EXISTS (SELECT 1 FROM sys.server_principals WHERE name = 'pizzeriaUser')
    CREATE LOGIN pizzeriaUser WITH PASSWORD = 'Pizza2026*', DEFAULT_DATABASE = Pizzeria, CHECK_POLICY = ON;
GO

USE Pizzeria;
GO

IF NOT EXISTS (SELECT 1 FROM sys.database_principals WHERE name = 'pizzeriaUser')
    CREATE USER pizzeriaUser FOR LOGIN pizzeriaUser;
GO

ALTER ROLE db_owner ADD MEMBER pizzeriaUser;
GO
