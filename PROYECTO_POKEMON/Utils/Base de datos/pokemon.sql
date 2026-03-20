-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-03-2026 a las 11:03:32
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
  `TIPO_ENTRENADOR` int(1) DEFAULT NULL
) ;

--
-- Volcado de datos para la tabla `entrenador`
--

INSERT INTO `entrenador` (`ID_ENTRENADOR`, `NOM_ENTRENADOR`, `PASSWORD`, `POKEDOLLARS`, `IMG_ENTRENADOR`, `TIPO_ENTRENADOR`) VALUES
(1, 'ASH', 'PIKA123', 5000, 'ASH.PNG', 1),
(2, 'MAY', 'TORCHIC99', 3000, 'MAY.PNG', 1),
(3, 'BRENDAN', 'SCEPTILE01', 4500, 'BRENDAN.PNG', 1),
(4, 'WALLY', 'RALTS_LOVE', 1200, 'WALLY.PNG', 2),
(5, 'STEVEN', 'METAGROSS_KING', 50000, 'STEVEN.PNG', 3),
(6, 'WALLACE', 'WATER_ART', 40000, 'WALLACE.PNG', 3),
(7, 'NORMAN', 'SLAKING_DAD', 15000, 'NORMAN.PNG', 2),
(8, 'CYNTHIA', 'GARCHOMP_0', 60000, 'CYNTHIA.PNG', 3),
(9, 'YOUNGSTER JOEY', 'TOP_RATATA', 100, 'JOEY.PNG', 2),
(10, 'TEAM AQUA GRUNT', 'KYOGRE_RULEZ', 500, 'AQUA.PNG', 2);

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
(1, 2, 5),
(2, 4, 1),
(5, 3, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimiento`
--

CREATE TABLE `movimiento` (
  `ID_MOVIMIENTO` int(6) NOT NULL,
  `NOM_MOVIMIENTO` varchar(20) NOT NULL,
  `TIPO` varchar(20) NOT NULL,
  `POTENCIA` int(6) DEFAULT NULL,
  `ESTADO` varchar(20) DEFAULT NULL,
  `NUM_TURNOS` int(2) DEFAULT NULL,
  `MEJORA` varchar(20) DEFAULT NULL,
  `DESC_MOVIMIENTO` varchar(50) NOT NULL,
  `CLASE_MOVIMIENTO` int(1) NOT NULL,
  `PP` int(2) NOT NULL,
  `NIVEL` int(3) DEFAULT NULL
) ;

--
-- Volcado de datos para la tabla `movimiento`
--

INSERT INTO `movimiento` (`ID_MOVIMIENTO`, `NOM_MOVIMIENTO`, `TIPO`, `POTENCIA`, `ESTADO`, `NUM_TURNOS`, `MEJORA`, `DESC_MOVIMIENTO`, `CLASE_MOVIMIENTO`, `PP`, `NIVEL`) VALUES
(1, 'TRITURAR', 'SINIESTRO', 80, 'DEF-BAJA', NULL, NULL, 'ATAQUE CON COLMILLOS AFILADOS', 1, 15, 38),
(2, 'AVALANCHA', 'ROCA', 75, 'RETROCESO', NULL, NULL, 'LANZA GRANDES ROCAS AL RIVAL', 1, 10, 32),
(3, 'TERREMOTO', 'TIERRA', 100, NULL, NULL, NULL, 'FUERTE SACUDIDA SISMICA', 1, 10, 45),
(4, 'MORDISCO', 'SINIESTRO', 60, 'RETROCESO', NULL, NULL, 'ATAQUE CON MANDIBULA AFILADA', 1, 25, 10),
(5, 'LANZALLAMAS', 'FUEGO', 90, 'QUEMADO', NULL, NULL, 'POTENTE CHORRO DE FUEGO', 1, 15, 35),
(6, 'HIDROBOMBA', 'AGUA', 110, NULL, NULL, NULL, 'GRAN VOLUMEN DE AGUA A PRESION', 1, 5, 42),
(7, 'HOJA AGUDA', 'PLANTA', 90, NULL, NULL, NULL, 'CORTA CON HOJAS MUY AFILADAS', 1, 15, 28),
(8, 'RAYO', 'ELECTRICO', 90, 'PARALIZADO', NULL, NULL, 'DESCARGA ELECTRICA POTENTE', 1, 15, 30),
(9, 'DANZA DRAGON', 'DRAGON', NULL, NULL, NULL, 'ATK_VEL_SUBE', 'SUBE ATAQUE Y VELOCIDAD', 2, 20, 40),
(10, 'GRUÑIDO', 'NORMAL', NULL, 'ATK-BAJA', 1, NULL, 'REDUCE EL ATAQUE DEL RIVAL', 3, 40, 1),
(11, 'TORMENTA ARENA', 'ROCA', NULL, 'DAÑO_CLIMA', 5, NULL, 'CREA UNA TORMENTA DE ARENA', 3, 10, 25),
(12, 'PROTECCION', 'NORMAL', NULL, 'EVADIR', 1, NULL, 'EVITA ATAQUES ESTE TURNO', 3, 10, 15);

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
(1, 3, 1, 15),
(2, 3, 1, 10),
(3, 3, 1, 10),
(7, 1, 1, 15),
(10, 1, 1, 40);

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

INSERT INTO `pokemon` (`ID_POKEMON`, `NUM_POKEDEX`, `ID_ENTRENADOR`, `MOTE`, `VITALIDAD`, `ATAQUE`, `DEFENSA`, `AT_ESP`, `DEF_ESP`, `VELOCIDAD`, `NIVEL`, `FERTILIDAD`, `SEXO`, `ESTADO`, `UBICACION`, `ID_OBJETO`) VALUES
(1, 252, 1, 'TREECKO', 45, 45, 35, 65, 55, 70, 5, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', 2),
(2, 255, 1, 'TORCHIC', 45, 60, 40, 70, 50, 45, 5, 5, 'HEMBRA', 'SALUDABLE', 'EQUIPO', NULL),
(3, 258, 1, 'MUDKIP', 50, 70, 50, 50, 50, 40, 5, 5, 'MACHO', 'SALUDABLE', 'EQUIPO', NULL),
(4, 376, 5, 'METAGROSS', 80, 135, 130, 95, 90, 70, 55, 3, 'SIN GENERO', 'SALUDABLE', 'EQUIPO', 3),
(5, 306, 5, 'AGGRON', 70, 110, 180, 60, 60, 50, 52, 3, 'MACHO', 'SALUDABLE', 'EQUIPO', 1),
(6, 246, 1, 'LARVITAR', 50, 64, 50, 45, 50, 41, 15, 5, 'MACHO', 'SALUDABLE', 'CAJA', NULL),
(7, 247, 1, 'PUPITAR', 70, 84, 70, 65, 70, 51, 35, 3, 'MACHO', 'SALUDABLE', 'CAJA', NULL),
(8, 248, 8, 'TYRANITAR', 100, 134, 110, 95, 100, 61, 60, 3, 'HEMBRA', 'SALUDABLE', 'CAJA', 1),
(9, 384, 5, 'RAYQUAZA', 105, 150, 90, 150, 90, 95, 70, 0, 'SIN GENERO', 'SALUDABLE', 'CAJA', NULL);

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
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `mochila`
--
ALTER TABLE `mochila`
  ADD CONSTRAINT `FK_MOCHILA_ENTRENADOR` FOREIGN KEY (`ID_ENTRENADOR`) REFERENCES `pokemon` (`ID_POKEMON`),
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
