-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-04-2026 a las 18:15:11
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pokemon`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrenador`
--

CREATE TABLE `entrenador` (
  `ID_ENTRENADOR` int(6) NOT NULL,
  `NOM_ENTRENADOR` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `POKEDOLLARS` int(10) NOT NULL,
  `IMG_ENTRENADOR` varchar(20) DEFAULT NULL,
  `TIPO_ENTRENADOR` int(1) DEFAULT NULL,
  `CAMPEON` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `entrenador`
--

INSERT INTO `entrenador` (`ID_ENTRENADOR`, `NOM_ENTRENADOR`, `PASSWORD`, `POKEDOLLARS`, `IMG_ENTRENADOR`, `TIPO_ENTRENADOR`, `CAMPEON`) VALUES
(1, 'Joven Chano', '1234', 150, NULL, 2, 0),
(2, 'Cazabichos Pedro', '1234', 120, NULL, 2, 0),
(3, 'Dominguera Marta', '1234', 200, NULL, 2, 0),
(4, 'Campista Carlos', '1234', 250, NULL, 2, 0),
(5, 'Chica Laura', '1234', 180, NULL, 2, 0),
(6, 'Escolar Luis', '1234', 140, NULL, 2, 0),
(7, 'Pescador Ramón', '1234', 300, NULL, 2, 0),
(8, 'Marinero Blas', '1234', 280, NULL, 2, 0),
(9, 'Montañero Paco', '1234', 350, NULL, 2, 0),
(10, 'Preescolar Ana', '1234', 100, NULL, 2, 0),
(11, 'Guille', '1234', 915, NULL, 1, 0),
(12, 'GUILLERMO', 'guillermo123', 80000, 'GuilleSprite.png', 3, 0),
(13, 'LUISRE', 'luisre123', 80000, 'LuisReSprite.png', 3, 0),
(14, 'ALVARO', 'alvaro123', 80000, 'AlvaroSprite.png', 3, 0),
(15, 'JULIO', 'julio123', 80000, 'JulioSprite.png', 3, 0),
(16, 'JESUCRISTO', 'amen', 999999, 'JesucristoSprite.png', 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mochila`
--

CREATE TABLE `mochila` (
  `ID_ENTRENADOR` int(6) NOT NULL,
  `ID_OBJETO` int(6) NOT NULL,
  `CANTIDAD` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mochila`
--

INSERT INTO `mochila` (`ID_ENTRENADOR`, `ID_OBJETO`, `CANTIDAD`) VALUES
(1, 1, 2),
(1, 2, 3),
(2, 3, 1),
(2, 5, 2),
(3, 2, 4),
(3, 4, 1),
(4, 1, 1),
(4, 3, 2),
(5, 4, 3),
(5, 5, 1),
(6, 2, 2),
(6, 3, 1),
(7, 1, 5),
(7, 4, 2),
(8, 3, 3),
(8, 5, 2),
(9, 1, 4),
(9, 3, 3),
(10, 2, 5),
(10, 5, 1),
(11, 1, 1),
(11, 2, 3),
(11, 3, 3),
(11, 4, 3),
(11, 5, 3),
(12, 1, 10),
(12, 3, 5),
(13, 2, 10),
(13, 5, 5),
(14, 1, 5),
(14, 4, 10),
(15, 2, 5),
(15, 3, 10),
(16, 1, 99),
(16, 2, 99),
(16, 3, 99),
(16, 4, 99),
(16, 5, 99);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimiento`
--

CREATE TABLE `movimiento` (
  `ID_MOVIMIENTO` int(11) NOT NULL,
  `NOMBRE` varchar(50) NOT NULL,
  `NIVEL` int(11) DEFAULT 1,
  `TIPO` varchar(20) NOT NULL,
  `CATEGORIA` varchar(15) NOT NULL,
  `POTENCIA` int(11) DEFAULT NULL,
  `PRECISION_MOV` int(11) DEFAULT NULL,
  `PP` int(11) NOT NULL,
  `PRIORIDAD` tinyint(4) DEFAULT 0,
  `BLANCO` varchar(15) DEFAULT 'RIVAL',
  `EFECTO` varchar(25) DEFAULT NULL,
  `PROBABILIDAD_EFECTO` tinyint(4) DEFAULT NULL,
  `STAT_MODIFICADO` varchar(15) DEFAULT NULL,
  `CANTIDAD_MODIFICACION` tinyint(4) DEFAULT 0,
  `EFECTO_ESPECIAL` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `movimiento`
--

INSERT INTO `movimiento` (`ID_MOVIMIENTO`, `NOMBRE`, `NIVEL`, `TIPO`, `CATEGORIA`, `POTENCIA`, `PRECISION_MOV`, `PP`, `PRIORIDAD`, `BLANCO`, `EFECTO`, `PROBABILIDAD_EFECTO`, `STAT_MODIFICADO`, `CANTIDAD_MODIFICACION`, `EFECTO_ESPECIAL`) VALUES
(1, 'Placaje', 1, 'NORMAL', 'FÍSICO', 40, 100, 35, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(2, 'Látigo', 5, 'NORMAL', 'STAT', NULL, 100, 30, 0, 'RIVAL', NULL, NULL, 'DEFENSA', -1, NULL),
(3, 'Danza Espada', 10, 'NORMAL', 'STAT', NULL, 999, 20, 0, 'USUARIO', NULL, NULL, 'ATAQUE', 2, NULL),
(5, 'Rapidez', 20, 'NORMAL', 'ESPECIAL', 60, 999, 20, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(6, 'Golpe Cuerpo', 25, 'NORMAL', 'FÍSICO', 85, 100, 15, 0, 'RIVAL', 'PARALIZADO', 30, NULL, 0, NULL),
(8, 'Vozarrón', 35, 'NORMAL', 'ESPECIAL', 90, 100, 10, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(12, 'Ascuas', 5, 'FUEGO', 'ESPECIAL', 40, 100, 25, 0, 'RIVAL', 'QUEMADO', 10, NULL, 0, NULL),
(13, 'Pantalla Humo', 10, 'FUEGO', 'STAT', NULL, 100, 20, 0, 'RIVAL', NULL, NULL, 'PRECISION', -1, NULL),
(14, 'Fuego Fatuo', 15, 'FUEGO', 'ESTADO', NULL, 85, 15, 0, 'RIVAL', 'QUEMADO', 100, NULL, 0, NULL),
(15, 'Rueda Fuego', 20, 'FUEGO', 'FÍSICO', 60, 100, 25, 0, 'RIVAL', 'QUEMADO', 10, NULL, 0, NULL),
(17, 'Colmillo Ígneo', 30, 'FUEGO', 'FÍSICO', 65, 95, 15, 0, 'RIVAL', 'QUEMADO', 10, NULL, 0, NULL),
(18, 'Lanzallamas', 35, 'FUEGO', 'ESPECIAL', 90, 100, 15, 0, 'RIVAL', 'QUEMADO', 10, NULL, 0, NULL),
(21, 'Llamarada', 50, 'FUEGO', 'ESPECIAL', 110, 85, 5, 0, 'RIVAL', 'QUEMADO', 10, NULL, 0, NULL),
(22, 'Pistola Agua', 5, 'AGUA', 'ESPECIAL', 40, 100, 25, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(25, 'Acua Jet', 20, 'AGUA', 'FÍSICO', 40, 100, 20, 1, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(27, 'Hidropulso', 30, 'AGUA', 'ESPECIAL', 60, 100, 20, 0, 'RIVAL', 'CONFUSO', 20, NULL, 0, NULL),
(28, 'Surf', 35, 'AGUA', 'ESPECIAL', 90, 100, 15, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(29, 'Escaldar', 40, 'AGUA', 'ESPECIAL', 80, 100, 15, 0, 'RIVAL', 'QUEMADO', 30, NULL, 0, NULL),
(30, 'Acua Cola', 45, 'AGUA', 'FÍSICO', 90, 90, 10, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(31, 'Hidrobomba', 50, 'AGUA', 'ESPECIAL', 110, 80, 5, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(32, 'Látigo Cepa', 5, 'PLANTA', 'FÍSICO', 45, 100, 25, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(34, 'Somnífero', 15, 'PLANTA', 'ESTADO', NULL, 75, 15, 0, 'RIVAL', 'DORMIDO', 100, NULL, 0, NULL),
(35, 'Hoja Mágica', 20, 'PLANTA', 'ESPECIAL', 60, 999, 20, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(43, 'Ataque Arena', 10, 'TIERRA', 'STAT', NULL, 100, 15, 0, 'RIVAL', NULL, NULL, 'PRECISION', -1, NULL),
(50, 'Terremoto', 45, 'TIERRA', 'FÍSICO', 100, 100, 10, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(52, 'Lanzarrocas', 5, 'ROCA', 'FÍSICO', 50, 90, 15, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(53, 'Pulimento', 10, 'ROCA', 'STAT', NULL, 999, 20, 0, 'USUARIO', NULL, NULL, 'VELOCIDAD', 2, NULL),
(62, 'Picotazo Venenoso', 5, 'VENENO', 'FÍSICO', 15, 100, 35, 0, 'RIVAL', 'ENVENENADO', 30, NULL, 0, NULL),
(63, 'Gas Venenoso', 10, 'VENENO', 'ESTADO', NULL, 90, 40, 0, 'RIVAL', 'ENVENENADO', 100, NULL, 0, NULL),
(66, 'Tóxico', 25, 'VENENO', 'ESTADO', NULL, 90, 10, 0, 'RIVAL', 'GRAVEMENTE_ENVENENADO', 100, NULL, 0, NULL),
(67, 'Bomba Lodo', 30, 'VENENO', 'ESPECIAL', 90, 100, 10, 0, 'RIVAL', 'ENVENENADO', 30, NULL, 0, NULL),
(68, 'Puya Nociva', 35, 'VENENO', 'FÍSICO', 80, 100, 20, 0, 'RIVAL', 'ENVENENADO', 30, NULL, 0, NULL),
(69, 'Armadura Ácida', 40, 'VENENO', 'STAT', NULL, 999, 20, 0, 'USUARIO', NULL, NULL, 'DEFENSA', 2, NULL),
(70, 'Onda Tóxica', 45, 'VENENO', 'ESPECIAL', 95, 100, 10, 0, 'RIVAL', 'ENVENENADO', 10, NULL, 0, NULL),
(71, 'Lanza Mugre', 50, 'VENENO', 'FÍSICO', 120, 80, 5, 0, 'RIVAL', 'ENVENENADO', 30, NULL, 0, NULL),
(72, 'Impactrueno', 5, 'ELECTRICO', 'ESPECIAL', 40, 100, 30, 0, 'RIVAL', 'PARALIZADO', 10, NULL, 0, NULL),
(73, 'Onda Trueno', 10, 'ELECTRICO', 'ESTADO', NULL, 90, 20, 0, 'RIVAL', 'PARALIZADO', 100, NULL, 0, NULL),
(74, 'Chispa', 15, 'ELECTRICO', 'FÍSICO', 65, 100, 20, 0, 'RIVAL', 'PARALIZADO', 30, NULL, 0, NULL),
(75, 'Onda Voltio', 20, 'ELECTRICO', 'ESPECIAL', 60, 999, 20, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(76, 'Rayo', 25, 'ELECTRICO', 'ESPECIAL', 90, 100, 15, 0, 'RIVAL', 'PARALIZADO', 10, NULL, 0, NULL),
(78, 'Chispazo', 35, 'ELECTRICO', 'ESPECIAL', 80, 100, 15, 0, 'RIVAL', 'PARALIZADO', 30, NULL, 0, NULL),
(81, 'Electrocañón', 50, 'ELECTRICO', 'ESPECIAL', 120, 50, 5, 0, 'RIVAL', 'PARALIZADO', 100, NULL, 0, NULL),
(85, 'Tiro Vital', 20, 'LUCHA', 'FÍSICO', 70, 999, 10, -1, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(91, 'Puño Dinámico', 50, 'LUCHA', 'FÍSICO', 100, 50, 5, 0, 'RIVAL', 'CONFUSO', 100, NULL, 0, NULL),
(92, 'Confusión', 5, 'PSIQUICO', 'ESPECIAL', 50, 100, 25, 0, 'RIVAL', 'CONFUSO', 10, NULL, 0, NULL),
(94, 'Psicorrayo', 15, 'PSIQUICO', 'ESPECIAL', 65, 100, 20, 0, 'RIVAL', 'CONFUSO', 10, NULL, 0, NULL),
(104, 'Finta', 15, 'SINIESTRO', 'FÍSICO', 60, 999, 20, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(105, 'Llanto Falso', 20, 'SINIESTRO', 'STAT', NULL, 100, 20, 0, 'RIVAL', NULL, NULL, 'DEFENSA_ESP', -2, NULL),
(108, 'Maquinación', 35, 'SINIESTRO', 'STAT', NULL, 999, 20, 0, 'USUARIO', NULL, NULL, 'ATAQUE_ESP', 2, NULL),
(112, 'Picotazo', 5, 'VOLADOR', 'FÍSICO', 35, 100, 35, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(114, 'Tornado', 15, 'VOLADOR', 'ESPECIAL', 40, 100, 35, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(115, 'Golpe Aéreo', 20, 'VOLADOR', 'FÍSICO', 60, 999, 20, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(117, 'Danza Pluma', 30, 'VOLADOR', 'STAT', NULL, 100, 15, 0, 'RIVAL', NULL, NULL, 'ATAQUE', -2, NULL),
(118, 'Pico Taladro', 35, 'VOLADOR', 'FÍSICO', 80, 100, 15, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(123, 'Disparo Demora', 10, 'BICHO', 'STAT', NULL, 95, 40, 0, 'RIVAL', NULL, NULL, 'VELOCIDAD', -1, NULL),
(125, 'Doble Rayo', 20, 'BICHO', 'ESPECIAL', 75, 100, 15, 0, 'RIVAL', 'CONFUSO', 10, NULL, 0, NULL),
(126, 'Tijera X', 25, 'BICHO', 'FÍSICO', 80, 100, 15, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(131, 'Megacuerno', 50, 'BICHO', 'FÍSICO', 120, 85, 10, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(133, 'Rayo Confuso', 10, 'FANTASMA', 'ESTADO', NULL, 100, 10, 0, 'RIVAL', 'CONFUSO', 100, NULL, 0, NULL),
(134, 'Sombra Vil', 15, 'FANTASMA', 'FÍSICO', 40, 100, 30, 1, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(139, 'Manto Espectral', 40, 'FANTASMA', 'STAT', NULL, 999, 15, 0, 'USUARIO', NULL, NULL, 'EVASION', 2, NULL),
(143, 'Defensa Férrea', 10, 'ACERO', 'STAT', NULL, 999, 15, 0, 'USUARIO', NULL, NULL, 'DEFENSA', 2, NULL),
(145, 'Puño Bala', 20, 'ACERO', 'FÍSICO', 40, 100, 30, 1, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(152, 'Nieve Polvo', 5, 'HIELO', 'ESPECIAL', 40, 100, 25, 0, 'RIVAL', 'CONGELADO', 10, NULL, 0, NULL),
(154, 'Canto Helado', 15, 'HIELO', 'FÍSICO', 40, 100, 30, 1, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(158, 'Rayo Hielo', 35, 'HIELO', 'ESPECIAL', 90, 100, 10, 0, 'RIVAL', 'CONGELADO', 10, NULL, 0, NULL),
(165, 'Dragoaliento', 20, 'DRAGON', 'ESPECIAL', 60, 100, 20, 0, 'RIVAL', 'PARALIZADO', 30, NULL, 0, NULL),
(166, 'Garra Dragón', 25, 'DRAGON', 'FÍSICO', 80, 100, 15, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(167, 'Pulso Dragón', 30, 'DRAGON', 'ESPECIAL', 85, 100, 10, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(172, 'Viento Feérico', 5, 'HADA', 'ESPECIAL', 40, 100, 30, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL),
(173, 'Encanto', 10, 'HADA', 'STAT', NULL, 100, 20, 0, 'RIVAL', NULL, NULL, 'ATAQUE', -2, NULL),
(175, 'Brillo Mágico', 20, 'HADA', 'ESPECIAL', 80, 100, 10, 0, 'RIVAL', NULL, NULL, NULL, 0, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `objeto`
--

CREATE TABLE `objeto` (
  `ID_OBJETO` int(6) NOT NULL,
  `NOM_OBJETO` varchar(20) NOT NULL,
  `ATAQUE` int(4) DEFAULT NULL,
  `DEFENSA` int(4) DEFAULT NULL,
  `ATA_ESP` int(4) DEFAULT NULL,
  `DEF_ESP` int(4) DEFAULT NULL,
  `VELOCIDAD` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `objeto`
--

INSERT INTO `objeto` (`ID_OBJETO`, `NOM_OBJETO`, `ATAQUE`, `DEFENSA`, `ATA_ESP`, `DEF_ESP`, `VELOCIDAD`) VALUES
(1, 'PESA', 20, 20, 0, 0, -20),
(2, 'PLUMA', 0, -20, 0, -20, 30),
(3, 'CHALECO', -15, 20, 0, 20, -15),
(4, 'BASTON', 0, 0, 20, 0, -15),
(5, 'PILAS', 0, 0, 50, -30, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pkmnmovimiento`
--

CREATE TABLE `pkmnmovimiento` (
  `ID_MOVIMIENTO` int(6) NOT NULL,
  `ID_POKEMON` int(6) NOT NULL,
  `ACTIVO` tinyint(1) DEFAULT NULL,
  `PP` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pkmnmovimiento`
--

INSERT INTO `pkmnmovimiento` (`ID_MOVIMIENTO`, `ID_POKEMON`, `ACTIVO`, `PP`) VALUES
(1, 10, 1, 35),
(1, 13, 1, 35),
(1, 14, 1, 35),
(1, 40, 1, 15),
(1, 42, 1, 15),
(1, 46, 1, 15),
(1, 47, 1, 15),
(1, 52, 1, 15),
(1, 55, 1, 15),
(1, 59, 1, 15),
(1, 101, 1, 35),
(1, 103, 1, 35),
(1, 104, 1, 35),
(1, 105, 1, 35),
(1, 106, 1, 35),
(1, 107, 1, 35),
(1, 108, 1, 35),
(1, 109, 1, 35),
(1, 110, 1, 35),
(1, 111, 1, 35),
(1, 112, 1, 35),
(1, 114, 1, 35),
(1, 115, 1, 35),
(1, 119, 1, 35),
(1, 120, 1, 35),
(1, 123, 1, 35),
(1, 124, 1, 35),
(1, 126, 1, 35),
(1, 127, 1, 35),
(1, 128, 1, 35),
(1, 129, 1, 35),
(1, 130, 1, 35),
(1, 131, 1, 35),
(1, 132, 1, 35),
(1, 133, 1, 35),
(1, 137, 1, 35),
(1, 138, 1, 35),
(1, 139, 1, 35),
(1, 140, 1, 35),
(1, 141, 1, 35),
(1, 142, 1, 35),
(1, 144, 1, 35),
(1, 145, 1, 35),
(1, 146, 1, 35),
(1, 148, 1, 35),
(1, 149, 1, 35),
(1, 150, 1, 35),
(1, 151, 1, 35),
(1, 154, 1, 35),
(1, 155, 1, 35),
(1, 156, 1, 35),
(1, 158, 1, 35),
(1, 160, 1, 35),
(1, 161, 1, 35),
(1, 162, 1, 35),
(1, 163, 1, 35),
(1, 164, 1, 35),
(1, 165, 1, 35),
(1, 166, 1, 35),
(2, 10, 1, 30),
(2, 38, 1, 10),
(2, 41, 1, 10),
(2, 44, 1, 10),
(2, 45, 1, 10),
(2, 48, 1, 10),
(2, 55, 1, 10),
(2, 56, 1, 10),
(2, 57, 1, 10),
(2, 60, 1, 10),
(2, 61, 1, 10),
(2, 63, 1, 10),
(2, 101, 1, 30),
(2, 102, 1, 30),
(2, 105, 1, 30),
(2, 113, 1, 30),
(2, 114, 1, 30),
(2, 117, 1, 30),
(2, 121, 1, 30),
(2, 126, 1, 30),
(2, 135, 1, 30),
(2, 136, 1, 30),
(2, 158, 1, 30),
(2, 159, 1, 30),
(3, 37, 1, 10),
(3, 41, 1, 10),
(3, 43, 1, 10),
(3, 44, 1, 10),
(3, 45, 1, 10),
(3, 46, 1, 10),
(3, 49, 1, 10),
(3, 53, 1, 10),
(3, 55, 1, 10),
(3, 57, 1, 10),
(3, 58, 1, 10),
(3, 60, 1, 10),
(3, 61, 1, 10),
(3, 63, 1, 10),
(3, 64, 1, 10),
(5, 10, 1, 20),
(5, 49, 1, 15),
(5, 53, 1, 15),
(5, 54, 1, 15),
(5, 61, 1, 15),
(5, 63, 1, 15),
(5, 66, 1, 15),
(6, 42, 1, 5),
(6, 43, 1, 5),
(6, 48, 1, 5),
(6, 51, 1, 5),
(6, 62, 1, 5),
(6, 65, 1, 5),
(8, 39, 1, 15),
(8, 50, 1, 15),
(8, 58, 1, 15),
(8, 62, 1, 15),
(8, 64, 1, 15),
(8, 65, 1, 15),
(12, 37, 1, 10),
(12, 38, 1, 10),
(12, 47, 1, 10),
(12, 50, 1, 10),
(12, 51, 1, 10),
(12, 62, 1, 10),
(12, 64, 1, 10),
(12, 66, 1, 10),
(12, 120, 1, 25),
(12, 150, 1, 25),
(12, 151, 1, 25),
(14, 10, 1, 15),
(22, 113, 1, 25),
(22, 117, 1, 25),
(22, 118, 1, 25),
(22, 130, 1, 25),
(22, 143, 1, 25),
(22, 144, 1, 25),
(22, 145, 1, 25),
(22, 146, 1, 25),
(22, 147, 1, 25),
(22, 148, 1, 25),
(22, 155, 1, 25),
(22, 159, 1, 25),
(32, 115, 1, 25),
(32, 116, 1, 25),
(62, 116, 1, 35),
(72, 127, 1, 30),
(72, 128, 1, 30),
(92, 106, 1, 25),
(92, 122, 1, 25),
(92, 125, 1, 25),
(92, 129, 1, 25),
(92, 131, 1, 25),
(92, 132, 1, 25),
(92, 134, 1, 25),
(92, 152, 1, 25),
(92, 153, 1, 25),
(92, 157, 1, 25),
(112, 102, 1, 35),
(152, 160, 1, 25),
(172, 157, 1, 30);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pokedex`
--

CREATE TABLE `pokedex` (
  `NUM_POKEDEX` int(6) NOT NULL,
  `NOM_POKEMON` varchar(20) NOT NULL,
  `TIPO1` varchar(20) NOT NULL,
  `TIPO2` varchar(20) DEFAULT NULL,
  `IMG_FRONTAL` varchar(15) NOT NULL,
  `IMG_BACK` varchar(15) NOT NULL,
  `SONIDO` varchar(15) DEFAULT NULL,
  `NIVEL_EVOLUCION` int(3) DEFAULT NULL,
  `POKEMON_EVOLUCION` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pokedex`
--

INSERT INTO `pokedex` (`NUM_POKEDEX`, `NOM_POKEMON`, `TIPO1`, `TIPO2`, `IMG_FRONTAL`, `IMG_BACK`, `SONIDO`, `NIVEL_EVOLUCION`, `POKEMON_EVOLUCION`) VALUES
(246, 'Larvitar', 'Roca', 'Tierra', '246f.png', '246b.png', '246.mp3', 30, 247),
(247, 'Pupitar', 'Roca', 'Tierra', '247f.png', '247b.png', '247.mp3', 55, 248),
(248, 'Tyranitar', 'Roca', 'Siniestro', '248f.png', '248b.png', '248.mp3', NULL, NULL),
(252, 'Treecko', 'Planta', NULL, '252f.png', '252b.png', '252.mp3', 16, 253),
(253, 'Grovyle', 'Planta', NULL, '253f.png', '253b.png', '253.mp3', 36, 254),
(254, 'Sceptile', 'Planta', NULL, '254f.png', '254b.png', '254.mp3', NULL, NULL),
(255, 'Torchic', 'Fuego', NULL, '255f.png', '255b.png', '255.mp3', 16, 256),
(256, 'Combusken', 'Fuego', 'Lucha', '256f.png', '256b.png', '256.mp3', 36, 257),
(257, 'Blaziken', 'Fuego', 'Lucha', '257f.png', '257b.png', '257.mp3', NULL, NULL),
(258, 'Mudkip', 'Agua', NULL, '258f.png', '258b.png', '258.mp3', 16, 259),
(259, 'Marshtomp', 'Agua', 'Tierra', '259f.png', '259b.png', '259.mp3', 36, 260),
(260, 'Swampert', 'Agua', 'Tierra', '260f.png', '260b.png', '260.mp3', NULL, NULL),
(261, 'Poochyena', 'Siniestro', NULL, '261f.png', '261b.png', '261.mp3', 18, 262),
(262, 'Mightyena', 'Siniestro', NULL, '262f.png', '262b.png', '262.mp3', NULL, NULL),
(263, 'Zigzagoon', 'Normal', NULL, '263f.png', '263b.png', '263.mp3', 20, 264),
(264, 'Linoone', 'Normal', NULL, '264f.png', '264b.png', '264.mp3', NULL, NULL),
(265, 'Wurmple', 'Bicho', NULL, '265f.png', '265b.png', '265.mp3', 7, 268),
(266, 'Silcoon', 'Bicho', NULL, '266f.png', '266b.png', '266.mp3', 10, 267),
(267, 'Beautifly', 'Bicho', 'Volador', '267f.png', '267b.png', '267.mp3', NULL, NULL),
(268, 'Cascoon', 'Bicho', NULL, '268f.png', '268b.png', '268.mp3', 10, 269),
(269, 'Dustox', 'Bicho', 'Veneno', '269f.png', '269b.png', '269.mp3', NULL, NULL),
(270, 'Lotad', 'Agua', 'Planta', '270f.png', '270b.png', '270.mp3', 14, 271),
(271, 'Lombre', 'Agua', 'Planta', '271f.png', '271b.png', '271.mp3', 30, 272),
(272, 'Ludicolo', 'Agua', 'Planta', '272f.png', '272b.png', '272.mp3', NULL, NULL),
(273, 'Seedot', 'Planta', NULL, '273f.png', '273b.png', '273.mp3', 14, 274),
(274, 'Nuzleaf', 'Planta', 'Siniestro', '274f.png', '274b.png', '274.mp3', 30, 275),
(275, 'Shiftry', 'Planta', 'Siniestro', '275f.png', '275b.png', '275.mp3', NULL, NULL),
(276, 'Taillow', 'Normal', 'Volador', '276f.png', '276b.png', '276.mp3', 22, 277),
(277, 'Swellow', 'Normal', 'Volador', '277f.png', '277b.png', '277.mp3', NULL, NULL),
(278, 'Wingull', 'Agua', 'Volador', '278f.png', '278b.png', '278.mp3', 25, 279),
(279, 'Pelipper', 'Agua', 'Volador', '279f.png', '279b.png', '279.mp3', NULL, NULL),
(280, 'Ralts', 'Psíquico', 'Hada', '280f.png', '280b.png', '280.mp3', 20, 281),
(281, 'Kirlia', 'Psíquico', 'Hada', '281f.png', '281b.png', '281.mp3', 30, 282),
(282, 'Gardevoir', 'Psíquico', 'Hada', '282f.png', '282b.png', '282.mp3', NULL, NULL),
(283, 'Surskit', 'Bicho', 'Agua', '283f.png', '283b.png', '283.mp3', 22, 284),
(284, 'Masquerain', 'Bicho', 'Volador', '284f.png', '284b.png', '284.mp3', NULL, NULL),
(285, 'Shroomish', 'Planta', NULL, '285f.png', '285b.png', '285.mp3', 23, 286),
(286, 'Breloom', 'Planta', 'Lucha', '286f.png', '286b.png', '286.mp3', NULL, NULL),
(287, 'Slakoth', 'Normal', NULL, '287f.png', '287b.png', '287.mp3', 18, 288),
(288, 'Vigoroth', 'Normal', NULL, '288f.png', '288b.png', '288.mp3', 36, 289),
(289, 'Slaking', 'Normal', NULL, '289f.png', '289b.png', '289.mp3', NULL, NULL),
(290, 'Nincada', 'Bicho', 'Tierra', '290f.png', '290b.png', '290.mp3', 20, 291),
(291, 'Ninjask', 'Bicho', 'Volador', '291f.png', '291b.png', '291.mp3', NULL, NULL),
(292, 'Shedinja', 'Bicho', 'Fantasma', '292f.png', '292b.png', '292.mp3', NULL, NULL),
(293, 'Whismur', 'Normal', NULL, '293f.png', '293b.png', '293.mp3', 20, 294),
(294, 'Loudred', 'Normal', NULL, '294f.png', '294b.png', '294.mp3', 40, 295),
(295, 'Exploud', 'Normal', NULL, '295f.png', '295b.png', '295.mp3', NULL, NULL),
(296, 'Makuhita', 'Lucha', NULL, '296f.png', '296b.png', '296.mp3', 24, 297),
(297, 'Hariyama', 'Lucha', NULL, '297f.png', '297b.png', '297.mp3', NULL, NULL),
(298, 'Azurill', 'Normal', 'Hada', '298f.png', '298b.png', '298.mp3', NULL, NULL),
(299, 'Nosepass', 'Roca', NULL, '299f.png', '299b.png', '299.mp3', NULL, NULL),
(300, 'Skitty', 'Normal', NULL, '300f.png', '300b.png', '300.mp3', 22, 301),
(301, 'Delcatty', 'Normal', NULL, '301f.png', '301b.png', '301.mp3', NULL, NULL),
(302, 'Sableye', 'Siniestro', 'Fantasma', '302f.png', '302b.png', '302.mp3', NULL, NULL),
(303, 'Mawile', 'Acero', 'Hada', '303f.png', '303b.png', '303.mp3', NULL, NULL),
(304, 'Aron', 'Acero', 'Roca', '304f.png', '304b.png', '304.mp3', 32, 305),
(305, 'Lairon', 'Acero', 'Roca', '305f.png', '305b.png', '305.mp3', 42, 306),
(306, 'Aggron', 'Acero', 'Roca', '306f.png', '306b.png', '306.mp3', NULL, NULL),
(307, 'Meditite', 'Lucha', 'Psíquico', '307f.png', '307b.png', '307.mp3', 37, 308),
(308, 'Medicham', 'Lucha', 'Psíquico', '308f.png', '308b.png', '308.mp3', NULL, NULL),
(309, 'Electrike', 'Eléctrico', NULL, '309f.png', '309b.png', '309.mp3', 26, 310),
(310, 'Manectric', 'Eléctrico', NULL, '310f.png', '310b.png', '310.mp3', NULL, NULL),
(311, 'Plusle', 'Eléctrico', NULL, '311f.png', '311b.png', '311.mp3', NULL, NULL),
(312, 'Minun', 'Eléctrico', NULL, '312f.png', '312b.png', '312.mp3', NULL, NULL),
(313, 'Volbeat', 'Bicho', NULL, '313f.png', '313b.png', '313.mp3', NULL, NULL),
(314, 'Illumise', 'Bicho', NULL, '314f.png', '314b.png', '314.mp3', NULL, NULL),
(315, 'Roselia', 'Planta', 'Veneno', '315f.png', '315b.png', '315.mp3', NULL, NULL),
(316, 'Gulpin', 'Veneno', NULL, '316f.png', '316b.png', '316.mp3', 26, 317),
(317, 'Swalot', 'Veneno', NULL, '317f.png', '317b.png', '317.mp3', NULL, NULL),
(318, 'Carvanha', 'Agua', 'Siniestro', '318f.png', '318b.png', '318.mp3', 30, 319),
(319, 'Sharpedo', 'Agua', 'Siniestro', '319f.png', '319b.png', '319.mp3', NULL, NULL),
(320, 'Wailmer', 'Agua', NULL, '320f.png', '320b.png', '320.mp3', 40, 321),
(321, 'Wailord', 'Agua', NULL, '321f.png', '321b.png', '321.mp3', NULL, NULL),
(322, 'Numel', 'Fuego', 'Tierra', '322f.png', '322b.png', '322.mp3', 33, 323),
(323, 'Camerupt', 'Fuego', 'Tierra', '323f.png', '323b.png', '323.mp3', NULL, NULL),
(324, 'Torkoal', 'Fuego', NULL, '324f.png', '324b.png', '324.mp3', NULL, NULL),
(325, 'Spoink', 'Psíquico', NULL, '325f.png', '325b.png', '325.mp3', 32, 326),
(326, 'Grumpig', 'Psíquico', NULL, '326f.png', '326b.png', '326.mp3', NULL, NULL),
(327, 'Spinda', 'Normal', NULL, '327f.png', '327b.png', '327.mp3', NULL, NULL),
(328, 'Trapinch', 'Tierra', NULL, '328f.png', '328b.png', '328.mp3', 35, 329),
(329, 'Vibrava', 'Tierra', 'Dragón', '329f.png', '329b.png', '329.mp3', 45, 330),
(330, 'Flygon', 'Tierra', 'Dragón', '330f.png', '330b.png', '330.mp3', NULL, NULL),
(331, 'Cacnea', 'Planta', NULL, '331f.png', '331b.png', '331.mp3', 32, 332),
(332, 'Cacturne', 'Planta', 'Siniestro', '332f.png', '332b.png', '332.mp3', NULL, NULL),
(333, 'Swablu', 'Normal', 'Volador', '333f.png', '333b.png', '333.mp3', 35, 334),
(334, 'Altaria', 'Dragón', 'Volador', '334f.png', '334b.png', '334.mp3', NULL, NULL),
(335, 'Zangoose', 'Normal', NULL, '335f.png', '335b.png', '335.mp3', NULL, NULL),
(336, 'Seviper', 'Veneno', NULL, '336f.png', '336b.png', '336.mp3', NULL, NULL),
(337, 'Lunatone', 'Roca', 'Psíquico', '337f.png', '337b.png', '337.mp3', NULL, NULL),
(338, 'Solrock', 'Roca', 'Psíquico', '338f.png', '338b.png', '338.mp3', NULL, NULL),
(339, 'Barboach', 'Agua', 'Tierra', '339f.png', '339b.png', '339.mp3', 30, 340),
(340, 'Whiscash', 'Agua', 'Tierra', '340f.png', '340b.png', '340.mp3', NULL, NULL),
(341, 'Corphish', 'Agua', NULL, '341f.png', '341b.png', '341.mp3', 30, 342),
(342, 'Crawdaunt', 'Agua', 'Siniestro', '342f.png', '342b.png', '342.mp3', NULL, NULL),
(343, 'Baltoy', 'Tierra', 'Psíquico', '343f.png', '343b.png', '343.mp3', 36, 344),
(344, 'Claydol', 'Tierra', 'Psíquico', '344f.png', '344b.png', '344.mp3', NULL, NULL),
(345, 'Lileep', 'Roca', 'Planta', '345f.png', '345b.png', '345.mp3', 40, 346),
(346, 'Cradily', 'Roca', 'Planta', '346f.png', '346b.png', '346.mp3', NULL, NULL),
(347, 'Anorith', 'Roca', 'Bicho', '347f.png', '347b.png', '347.mp3', 40, 348),
(348, 'Armaldo', 'Roca', 'Bicho', '348f.png', '348b.png', '348.mp3', NULL, NULL),
(349, 'Feebas', 'Agua', NULL, '349f.png', '349b.png', '349.mp3', 40, 350),
(350, 'Milotic', 'Agua', NULL, '350f.png', '350b.png', '350.mp3', NULL, NULL),
(351, 'Castform', 'Normal', NULL, '351f.png', '351b.png', '351.mp3', NULL, NULL),
(352, 'Kecleon', 'Normal', NULL, '352f.png', '352b.png', '352.mp3', NULL, NULL),
(353, 'Shuppet', 'Fantasma', NULL, '353f.png', '353b.png', '353.mp3', 37, 354),
(354, 'Banette', 'Fantasma', NULL, '354f.png', '354b.png', '354.mp3', NULL, NULL),
(355, 'Duskull', 'Fantasma', NULL, '355f.png', '355b.png', '355.mp3', 37, 356),
(356, 'Dusclops', 'Fantasma', NULL, '356f.png', '356b.png', '356.mp3', NULL, NULL),
(357, 'Tropius', 'Planta', 'Volador', '357f.png', '357b.png', '357.mp3', NULL, NULL),
(358, 'Chimecho', 'Psíquico', NULL, '358f.png', '358b.png', '358.mp3', NULL, NULL),
(359, 'Absol', 'Siniestro', NULL, '359f.png', '359b.png', '359.mp3', NULL, NULL),
(360, 'Wynaut', 'Psíquico', NULL, '360f.png', '360b.png', '360.mp3', NULL, NULL),
(361, 'Snorunt', 'Hielo', NULL, '361f.png', '361b.png', '361.mp3', 42, 362),
(362, 'Glalie', 'Hielo', NULL, '362f.png', '362b.png', '362.mp3', NULL, NULL),
(363, 'Spheal', 'Hielo', 'Agua', '363f.png', '363b.png', '363.mp3', 32, 364),
(364, 'Sealeo', 'Hielo', 'Agua', '364f.png', '364b.png', '364.mp3', 44, 365),
(365, 'Walrein', 'Hielo', 'Agua', '365f.png', '365b.png', '365.mp3', NULL, NULL),
(366, 'Clamperl', 'Agua', NULL, '366f.png', '366b.png', '366.mp3', 20, 367),
(367, 'Huntail', 'Agua', NULL, '367f.png', '367b.png', '367.mp3', NULL, NULL),
(368, 'Gorebyss', 'Agua', NULL, '368f.png', '368b.png', '368.mp3', NULL, NULL),
(369, 'Relicanth', 'Agua', 'Roca', '369f.png', '369b.png', '369.mp3', NULL, NULL),
(370, 'Luvdisc', 'Agua', NULL, '370f.png', '370b.png', '370.mp3', NULL, NULL),
(371, 'Bagon', 'Dragón', NULL, '371f.png', '371b.png', '371.mp3', 30, 372),
(372, 'Shelgon', 'Dragón', NULL, '372f.png', '372b.png', '372.mp3', 50, 373),
(373, 'Salamence', 'Dragón', 'Volador', '373f.png', '373b.png', '373.mp3', NULL, NULL),
(374, 'Beldum', 'Acero', 'Psíquico', '374f.png', '374b.png', '374.mp3', 20, 375),
(375, 'Metang', 'Acero', 'Psíquico', '375f.png', '375b.png', '375.mp3', 45, 376),
(376, 'Metagross', 'Acero', 'Psíquico', '376f.png', '376b.png', '376.mp3', NULL, NULL),
(377, 'Regirock', 'Roca', NULL, '377f.png', '377b.png', '377.mp3', NULL, NULL),
(378, 'Regice', 'Hielo', NULL, '378f.png', '378b.png', '378.mp3', NULL, NULL),
(379, 'Registeel', 'Acero', NULL, '379f.png', '379b.png', '379.mp3', NULL, NULL),
(380, 'Latias', 'Dragón', 'Psíquico', '380f.png', '380b.png', '380.mp3', NULL, NULL),
(381, 'Latios', 'Dragón', 'Psíquico', '381f.png', '381b.png', '381.mp3', NULL, NULL),
(382, 'Kyogre', 'Agua', NULL, '382f.png', '382b.png', '382.mp3', NULL, NULL),
(383, 'Groudon', 'Tierra', NULL, '383f.png', '383b.png', '383.mp3', NULL, NULL),
(384, 'Rayquaza', 'Dragón', 'Volador', '384f.png', '384b.png', '384.mp3', NULL, NULL),
(385, 'Jirachi', 'Acero', 'Psíquico', '385f.png', '385b.png', '385.mp3', NULL, NULL),
(386, 'Deoxys', 'Psíquico', NULL, '386f.png', '386b.png', '386.mp3', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pokemon`
--

CREATE TABLE `pokemon` (
  `ID_POKEMON` int(6) NOT NULL,
  `NUM_POKEDEX` int(6) NOT NULL,
  `ID_ENTRENADOR` int(6) NOT NULL,
  `MOTE` varchar(20) DEFAULT NULL,
  `VITALIDAD` int(4) DEFAULT NULL,
  `ATAQUE` int(4) DEFAULT NULL,
  `DEFENSA` int(4) DEFAULT NULL,
  `AT_ESP` int(4) DEFAULT NULL,
  `DEF_ESP` int(4) DEFAULT NULL,
  `VELOCIDAD` int(4) DEFAULT NULL,
  `EXPERIENCIA` int(10) DEFAULT 0,
  `NIVEL` int(3) NOT NULL,
  `FERTILIDAD` int(1) NOT NULL,
  `SEXO` varchar(20) NOT NULL,
  `ESTADO` varchar(20) DEFAULT NULL,
  `UBICACION` varchar(20) NOT NULL,
  `ID_OBJETO` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pokemon`
--

INSERT INTO `pokemon` (`ID_POKEMON`, `NUM_POKEDEX`, `ID_ENTRENADOR`, `MOTE`, `VITALIDAD`, `ATAQUE`, `DEFENSA`, `AT_ESP`, `DEF_ESP`, `VELOCIDAD`, `EXPERIENCIA`, `NIVEL`, `FERTILIDAD`, `SEXO`, `ESTADO`, `UBICACION`, `ID_OBJETO`) VALUES
(10, 274, 11, 'ELXOKAS', 222, 230, 228, 229, 226, 232, 603, 80, 5, 'MACHO', NULL, 'EQUIPO', 1),
(13, 254, 11, 'Lerolero', 5, 2, 3, 5, 1, 5, 0, 1, 5, 'MACHO', NULL, 'CAJA', NULL),
(14, 320, 11, 'Gordi', 3, 5, 4, 4, 1, 2, 0, 1, 5, 'MACHO', NULL, 'EQUIPO', NULL),
(37, 254, 12, 'Sceptile', 145, 105, 85, 125, 105, 140, 0, 50, 0, 'MACHO', NULL, 'EQUIPO', NULL),
(38, 277, 12, 'Swellow', 135, 105, 80, 75, 70, 145, 0, 50, 0, 'MACHO', NULL, 'EQUIPO', NULL),
(39, 310, 12, 'Manectric', 145, 95, 80, 125, 80, 125, 0, 50, 0, 'HEMBRA', NULL, 'EQUIPO', NULL),
(40, 359, 12, 'Absol', 140, 150, 80, 95, 80, 95, 0, 50, 0, 'MACHO', NULL, 'EQUIPO', NULL),
(41, 330, 12, 'Flygon', 155, 120, 100, 100, 100, 120, 0, 50, 0, 'HEMBRA', NULL, 'EQUIPO', NULL),
(42, 342, 12, 'Crawdaunt', 138, 140, 105, 110, 75, 75, 0, 50, 0, 'MACHO', NULL, 'EQUIPO', NULL),
(43, 260, 13, 'Swampert', 175, 130, 110, 105, 110, 80, 0, 50, 0, 'MACHO', NULL, 'EQUIPO', NULL),
(44, 306, 13, 'Aggron', 145, 130, 200, 80, 80, 70, 0, 50, 0, 'MACHO', NULL, 'EQUIPO', NULL),
(45, 297, 13, 'Hariyama', 219, 140, 80, 60, 80, 70, 0, 50, 0, 'MACHO', NULL, 'EQUIPO', NULL),
(46, 289, 13, 'Slaking', 225, 180, 120, 115, 85, 120, 0, 50, 0, 'HEMBRA', NULL, 'EQUIPO', NULL),
(47, 356, 13, 'Dusclops', 115, 90, 150, 80, 150, 45, 0, 50, 0, 'MACHO', NULL, 'EQUIPO', NULL),
(48, 365, 13, 'Walrein', 185, 100, 110, 115, 110, 85, 0, 50, 0, 'HEMBRA', NULL, 'EQUIPO', NULL),
(49, 257, 14, 'Blaziken', 155, 140, 90, 130, 90, 100, 0, 50, 0, 'MACHO', NULL, 'EQUIPO', NULL),
(50, 282, 14, 'Gardevoir', 143, 85, 85, 145, 135, 100, 0, 50, 0, 'HEMBRA', NULL, 'EQUIPO', NULL),
(51, 350, 14, 'Milotic', 170, 80, 99, 120, 145, 101, 0, 50, 0, 'HEMBRA', NULL, 'EQUIPO', NULL),
(52, 275, 14, 'Shiftry', 165, 120, 80, 110, 80, 100, 0, 50, 0, 'MACHO', NULL, 'EQUIPO', NULL),
(53, 323, 14, 'Camerupt', 145, 120, 90, 125, 95, 60, 0, 50, 0, 'HEMBRA', NULL, 'EQUIPO', NULL),
(54, 373, 14, 'Salamence', 170, 155, 100, 130, 100, 120, 0, 50, 0, 'MACHO', NULL, 'EQUIPO', NULL),
(55, 248, 15, 'Tyranitar', 175, 154, 130, 115, 120, 81, 0, 50, 0, 'MACHO', NULL, 'EQUIPO', NULL),
(56, 346, 15, 'Cradily', 161, 101, 117, 101, 127, 63, 0, 50, 0, 'HEMBRA', NULL, 'EQUIPO', NULL),
(57, 348, 15, 'Armaldo', 150, 145, 120, 90, 100, 65, 0, 50, 0, 'MACHO', NULL, 'EQUIPO', NULL),
(58, 344, 15, 'Claydol', 135, 90, 125, 90, 140, 95, 0, 50, 0, 'SIN GENERO', NULL, 'EQUIPO', NULL),
(59, 332, 15, 'Cacturne', 145, 135, 80, 135, 80, 75, 0, 50, 0, 'MACHO', NULL, 'EQUIPO', NULL),
(60, 376, 15, 'Metagross', 155, 155, 150, 115, 110, 90, 0, 50, 0, 'SIN GENERO', NULL, 'EQUIPO', NULL),
(61, 384, 16, 'Rayquaza', 180, 170, 110, 170, 110, 115, 0, 50, 0, 'SIN GENERO', NULL, 'EQUIPO', NULL),
(62, 382, 16, 'Kyogre', 175, 120, 110, 170, 160, 110, 0, 50, 0, 'SIN GENERO', NULL, 'EQUIPO', NULL),
(63, 383, 16, 'Groudon', 175, 170, 160, 120, 110, 110, 0, 50, 0, 'SIN GENERO', NULL, 'EQUIPO', NULL),
(64, 385, 16, 'Jirachi', 175, 120, 120, 120, 120, 120, 0, 50, 0, 'SIN GENERO', NULL, 'EQUIPO', NULL),
(65, 381, 16, 'Latios', 155, 110, 100, 150, 130, 130, 0, 50, 0, 'MACHO', NULL, 'EQUIPO', NULL),
(66, 380, 16, 'Latias', 155, 100, 110, 130, 150, 130, 0, 50, 0, 'HEMBRA', NULL, 'EQUIPO', NULL),
(101, 263, 1, 'ZIGZAGOON', 20, 15, 15, 10, 10, 15, 0, 5, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(102, 276, 1, 'TAILLOW', 18, 16, 12, 10, 10, 18, 0, 4, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(103, 293, 1, 'WHISMUR', 22, 14, 12, 14, 12, 10, 0, 5, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(104, 261, 1, 'POOCHYENA', 18, 16, 12, 10, 10, 14, 0, 4, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(105, 300, 1, 'SKITTY', 20, 14, 14, 12, 12, 16, 0, 5, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(106, 327, 1, 'SPINDA', 22, 15, 15, 15, 15, 15, 0, 6, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(107, 265, 2, 'WURMPLE', 18, 12, 12, 10, 10, 10, 0, 4, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(108, 266, 2, 'SILCOON', 20, 10, 18, 10, 10, 8, 0, 5, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(109, 268, 2, 'CASCOON', 20, 10, 18, 10, 10, 8, 0, 5, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(110, 290, 2, 'NINCADA', 18, 16, 18, 10, 12, 14, 0, 5, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(111, 313, 2, 'VOLBEAT', 22, 16, 14, 12, 14, 16, 0, 6, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(112, 314, 2, 'ILLUMISE', 22, 12, 14, 16, 14, 16, 0, 6, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(113, 270, 3, 'LOTAD', 18, 12, 12, 14, 14, 10, 0, 4, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(114, 273, 3, 'SEEDOT', 18, 14, 14, 12, 12, 10, 0, 4, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(115, 285, 3, 'SHROOMISH', 20, 14, 16, 14, 16, 10, 0, 5, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(116, 315, 3, 'ROSELIA', 20, 14, 14, 20, 18, 16, 0, 6, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(117, 298, 3, 'AZURILL', 18, 10, 12, 10, 12, 10, 0, 4, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(118, 283, 3, 'SURSKIT', 18, 12, 12, 14, 14, 16, 0, 5, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(119, 304, 4, 'ARON', 20, 16, 20, 10, 10, 10, 0, 6, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(120, 322, 4, 'NUMEL', 22, 16, 14, 16, 14, 10, 0, 6, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(121, 328, 4, 'TRAPINCH', 20, 18, 14, 14, 14, 10, 0, 6, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(122, 343, 4, 'BALTOY', 18, 12, 16, 12, 16, 14, 0, 5, 5, 'SIN GENERO', 'SALUDABLE', 'EQUIPO', NULL),
(123, 299, 4, 'NOSEPASS', 18, 12, 22, 12, 18, 10, 0, 6, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(124, 296, 4, 'MAKUHITA', 24, 16, 12, 10, 10, 10, 0, 5, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(125, 280, 5, 'RALTS', 16, 10, 10, 16, 14, 12, 0, 4, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(126, 300, 5, 'SKITTY', 20, 14, 14, 12, 12, 16, 0, 5, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(127, 311, 5, 'PLUSLE', 20, 12, 12, 16, 14, 18, 0, 6, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(128, 312, 5, 'MINUN', 20, 12, 14, 14, 16, 18, 0, 6, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(129, 325, 5, 'SPOINK', 20, 10, 12, 16, 16, 14, 0, 5, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(130, 351, 5, 'CASTFORM', 22, 16, 16, 16, 16, 16, 0, 6, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(131, 280, 6, 'RALTS', 16, 10, 10, 16, 14, 12, 0, 5, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(132, 325, 6, 'SPOINK', 20, 10, 12, 16, 16, 14, 0, 5, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(133, 374, 6, 'BELDUM', 18, 14, 16, 12, 14, 10, 0, 5, 5, 'SIN GENERO', 'SALUDABLE', 'EQUIPO', NULL),
(134, 343, 6, 'BALTOY', 18, 12, 16, 12, 16, 14, 0, 5, 5, 'SIN GENERO', 'SALUDABLE', 'EQUIPO', NULL),
(135, 355, 6, 'DUSKULL', 16, 12, 18, 12, 18, 10, 0, 5, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(136, 353, 6, 'SHUPPET', 18, 16, 12, 14, 12, 14, 0, 5, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(137, 349, 7, 'FEEBAS', 16, 10, 10, 10, 12, 16, 0, 5, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(138, 349, 7, 'FEEBAS', 16, 10, 10, 10, 12, 16, 0, 5, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(139, 349, 7, 'FEEBAS', 18, 10, 10, 10, 12, 16, 0, 6, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(140, 349, 7, 'FEEBAS', 18, 10, 10, 10, 12, 16, 0, 6, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(141, 349, 7, 'FEEBAS', 20, 10, 10, 10, 12, 16, 0, 7, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(142, 349, 7, 'FEEBAS', 22, 10, 10, 10, 12, 16, 0, 8, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(143, 318, 8, 'CARVANHA', 18, 18, 10, 16, 10, 16, 0, 6, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(144, 320, 8, 'WAILMER', 26, 16, 12, 16, 12, 14, 0, 6, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(145, 341, 8, 'CORPHISH', 20, 18, 16, 12, 12, 12, 0, 6, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(146, 339, 8, 'BARBOACH', 22, 14, 14, 14, 14, 14, 0, 6, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(147, 369, 8, 'RELICANTH', 24, 16, 20, 12, 16, 12, 0, 7, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(148, 366, 8, 'CLAMPERL', 18, 14, 18, 16, 14, 10, 0, 6, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(149, 304, 9, 'ARON', 20, 16, 20, 10, 10, 10, 0, 6, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(150, 324, 9, 'TORKOAL', 22, 16, 22, 16, 16, 8, 0, 7, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(151, 322, 9, 'NUMEL', 22, 16, 14, 16, 14, 10, 0, 6, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(152, 337, 9, 'LUNATONE', 22, 14, 16, 18, 16, 16, 0, 7, 5, 'SIN GENERO', 'SALUDABLE', 'EQUIPO', NULL),
(153, 338, 9, 'SOLROCK', 22, 18, 16, 14, 16, 16, 0, 7, 5, 'SIN GENERO', 'SALUDABLE', 'EQUIPO', NULL),
(154, 328, 9, 'TRAPINCH', 20, 18, 14, 14, 14, 10, 0, 6, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(155, 298, 10, 'AZURILL', 18, 10, 12, 10, 12, 10, 0, 4, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(156, 360, 10, 'WYNAUT', 22, 10, 12, 10, 12, 10, 0, 4, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(157, 280, 10, 'RALTS', 16, 10, 10, 16, 14, 12, 0, 4, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(158, 273, 10, 'SEEDOT', 18, 14, 14, 12, 12, 10, 0, 4, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(159, 270, 10, 'LOTAD', 18, 12, 12, 14, 14, 10, 0, 4, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(160, 361, 10, 'SNORUNT', 20, 14, 14, 14, 14, 14, 0, 5, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(161, 255, 11, 'Pollo', 3, 1, 3, 1, 1, 4, 0, 1, 5, 'HEMBRA', NULL, 'EQUIPO', NULL),
(162, 287, 11, 'Mono', 4, 5, 2, 1, 3, 4, 0, 1, 5, 'HEMBRA', NULL, 'EQUIPO', NULL),
(163, 315, 11, 'MARI', 2, 5, 4, 5, 1, 1, 0, 1, 5, 'HEMBRA', NULL, 'CAJA', NULL),
(164, 384, 11, 'BOMBA', 2, 1, 3, 2, 2, 3, 0, 1, 5, 'HEMBRA', NULL, 'EQUIPO', 1),
(165, 255, 11, 'PoELX', 2, 1, 4, 1, 3, 5, 0, 1, 5, 'MACHO', NULL, 'CAJA', NULL),
(166, 350, 11, 'MLADY', 4, 4, 2, 4, 1, 1, 0, 1, 5, 'MACHO', NULL, 'EQUIPO', NULL),
(167, 386, 11, 'Alien', 4, 3, 3, 3, 4, 5, 0, 1, 5, 'MACHO', NULL, 'CAJA', NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `entrenador`
--
ALTER TABLE `entrenador`
  ADD PRIMARY KEY (`ID_ENTRENADOR`);

--
-- Indices de la tabla `mochila`
--
ALTER TABLE `mochila`
  ADD PRIMARY KEY (`ID_ENTRENADOR`,`ID_OBJETO`),
  ADD KEY `FK_MOCHILA_OBJETO` (`ID_OBJETO`);

--
-- Indices de la tabla `movimiento`
--
ALTER TABLE `movimiento`
  ADD PRIMARY KEY (`ID_MOVIMIENTO`);

--
-- Indices de la tabla `objeto`
--
ALTER TABLE `objeto`
  ADD PRIMARY KEY (`ID_OBJETO`);

--
-- Indices de la tabla `pkmnmovimiento`
--
ALTER TABLE `pkmnmovimiento`
  ADD PRIMARY KEY (`ID_MOVIMIENTO`,`ID_POKEMON`),
  ADD KEY `FK_PKMNMOVIMIENTO_POKEMON` (`ID_POKEMON`);

--
-- Indices de la tabla `pokedex`
--
ALTER TABLE `pokedex`
  ADD PRIMARY KEY (`NUM_POKEDEX`);

--
-- Indices de la tabla `pokemon`
--
ALTER TABLE `pokemon`
  ADD PRIMARY KEY (`ID_POKEMON`),
  ADD KEY `FK_POKEMON_POKEDEX` (`NUM_POKEDEX`),
  ADD KEY `FK_POKEMON_ENTRENADOR` (`ID_ENTRENADOR`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `movimiento`
--
ALTER TABLE `movimiento`
  MODIFY `ID_MOVIMIENTO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=182;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `mochila`
--
ALTER TABLE `mochila`
  ADD CONSTRAINT `FK_MOCHILA_ENTRENADOR` FOREIGN KEY (`ID_ENTRENADOR`) REFERENCES `entrenador` (`ID_ENTRENADOR`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_MOCHILA_OBJETO` FOREIGN KEY (`ID_OBJETO`) REFERENCES `objeto` (`ID_OBJETO`);

--
-- Filtros para la tabla `pkmnmovimiento`
--
ALTER TABLE `pkmnmovimiento`
  ADD CONSTRAINT `FK_PKMNMOVIMIENTO_MOVIMIENTO` FOREIGN KEY (`ID_MOVIMIENTO`) REFERENCES `movimiento` (`ID_MOVIMIENTO`),
  ADD CONSTRAINT `FK_PKMNMOVIMIENTO_POKEMON` FOREIGN KEY (`ID_POKEMON`) REFERENCES `pokemon` (`ID_POKEMON`);

--
-- Filtros para la tabla `pokemon`
--
ALTER TABLE `pokemon`
  ADD CONSTRAINT `FK_POKEMON_ENTRENADOR` FOREIGN KEY (`ID_ENTRENADOR`) REFERENCES `entrenador` (`ID_ENTRENADOR`),
  ADD CONSTRAINT `FK_POKEMON_POKEDEX` FOREIGN KEY (`NUM_POKEDEX`) REFERENCES `pokedex` (`NUM_POKEDEX`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
