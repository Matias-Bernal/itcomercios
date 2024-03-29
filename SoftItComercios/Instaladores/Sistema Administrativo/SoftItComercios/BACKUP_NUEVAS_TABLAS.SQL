-- MySQL dump 9.09
--
-- Host: localhost    Database: softcomerciodb
---------------------------------------------------------
-- Server version	4.0.15-nt

--
-- Table structure for table `jdo_table`
--

CREATE TABLE jdo_table (
  TABLE_ID int(11) NOT NULL default '0',
  NEXT_ID int(11) NOT NULL default '0',
  JAVA_NAME varchar(128) binary NOT NULL default '',
  TABLE_NAME varchar(64) binary NOT NULL default '',
  PRIMARY KEY  (TABLE_ID),
  UNIQUE KEY JAVA_NAME (JAVA_NAME),
  UNIQUE KEY TABLE_NAME (TABLE_NAME)
) TYPE=InnoDB;

--
-- Dumping data for table `jdo_table`
--

INSERT INTO jdo_table VALUES (1,2,'persistencia.domain.Oid','OID');
INSERT INTO jdo_table VALUES (2,1,'persistencia.domain.Localidad','LOCALIDAD');
INSERT INTO jdo_table VALUES (3,1,'persistencia.domain.Provincia','PROVINCIA');
INSERT INTO jdo_table VALUES (4,1,'persistencia.domain.Distribuidora','DISTRIBUIDORA');
INSERT INTO jdo_table VALUES (5,1,'persistencia.domain.Cliente','CLIENTE');
INSERT INTO jdo_table VALUES (6,1,'persistencia.domain.Producto','PRODUCTO');
INSERT INTO jdo_table VALUES (7,1,'persistencia.domain.Proveedor','PROVEEDOR');
INSERT INTO jdo_table VALUES (8,1,'persistencia.domain.PlanillaES','PLANILLA_ES');
INSERT INTO jdo_table VALUES (9,1,'persistencia.domain.MovimientoCaja','MOVIMIENTO_CAJA');
INSERT INTO jdo_table VALUES (10,1,'persistencia.domain.ItemFactura','ITEM_FACTURA');
INSERT INTO jdo_table VALUES (11,1,'persistencia.domain.Factura','FACTURA');
INSERT INTO jdo_table VALUES (12,1,'persistencia.domain.FacturaCliente','FACTURA_CLIENTE');
INSERT INTO jdo_table VALUES (13,1,'persistencia.domain.FacturaProveedor','FACTURA_PROVEEDOR');

--
-- Table structure for table `oid`
--

CREATE TABLE oid (
  OID_ID bigint(20) NOT NULL default '0',
  OID bigint(20) default NULL,
  PRIMARY KEY  (OID_ID)
) TYPE=InnoDB;

--
-- Dumping data for table `oid`
--

INSERT INTO oid VALUES (35184372088832,2);

--
-- Table structure for table `provincia`
--

CREATE TABLE provincia (
  PROVINCIA_ID bigint(20) NOT NULL default '0',
  ID bigint(20) default NULL,
  NOMBRE varchar(20) binary default NULL,
  PRIMARY KEY  (PROVINCIA_ID)
) TYPE=InnoDB;

--
-- Table structure for table `localidad`
--

CREATE TABLE localidad (
  LOCALIDAD_ID bigint(20) NOT NULL default '0',
  COD_POSTAL int(11) NOT NULL default '0',
  ID bigint(20) default NULL,
  NOMBRE varchar(255) binary default NULL,
  PROVINCIA_ID bigint(20) default NULL,
  PRIMARY KEY  (LOCALIDAD_ID),
  KEY LOCALIDAD_N1 (PROVINCIA_ID),
  CONSTRAINT `0_1750` FOREIGN KEY (`PROVINCIA_ID`) REFERENCES `provincia` (`PROVINCIA_ID`)
) TYPE=InnoDB;

--
-- Table structure for table `cliente`
--

CREATE TABLE cliente (
  CLIENTE_ID bigint(20) NOT NULL default '0',
  CUIT varchar(255) binary default NULL,
  DIRECCION varchar(255) binary default NULL,
  ID bigint(20) default NULL,
  ING_BRUTOS_CL varchar(255) binary default NULL,
  IVA_CL varchar(255) binary default NULL,
  LOCALIDAD_ID bigint(20) default NULL,
  NOMBRE varchar(255) binary default NULL,
  TELEFONO varchar(255) binary default NULL,
  PRIMARY KEY  (CLIENTE_ID),
  KEY CLIENTE_N1 (LOCALIDAD_ID),
  CONSTRAINT `0_1752` FOREIGN KEY (`LOCALIDAD_ID`) REFERENCES `localidad` (`LOCALIDAD_ID`)
) TYPE=InnoDB;

--
-- Table structure for table `proveedor`
--

CREATE TABLE proveedor (
  PROVEEDOR_ID bigint(20) NOT NULL default '0',
  CODIGO int(11) NOT NULL default '0',
  DIRECCION varchar(255) binary default NULL,
  ID bigint(20) default NULL,
  LOCALIDAD_ID bigint(20) default NULL,
  NOMBRE varchar(255) binary default NULL,
  TELEFONO varchar(255) binary default NULL,
  PRIMARY KEY  (PROVEEDOR_ID),
  KEY PROVEEDOR_N1 (LOCALIDAD_ID),
  CONSTRAINT `0_1756` FOREIGN KEY (`LOCALIDAD_ID`) REFERENCES `localidad` (`LOCALIDAD_ID`)
) TYPE=InnoDB;

--
-- Table structure for table `producto`
--

CREATE TABLE producto (
  PRODUCTO_ID bigint(20) NOT NULL default '0',
  CODIGO bigint(20) default '0',
  ID bigint(20) default NULL,
  NOMBRE varchar(255) binary default NULL,
  PRECIO_ENTRADA double NOT NULL default '0',
  PRECIO_KILOS tinyint(1) NOT NULL default '0',
  PRECIO_VENTA_SIN_IVA double NOT NULL default '0',
  PROVEEDOR_ID bigint(20) default NULL,
  STOCK_ACTUAL int(11) NOT NULL default '0',
  STOCK_MINIMO int(11) NOT NULL default '0',
  GANANCIA int(11) NOT NULL default '0',
  PRECIO_ENT_CIVA tinyint(1) NOT NULL default '0',
  PRECIO_VENTA_CON_IVA double NOT NULL default '0',
  STOCK_KILOS_ACT double NOT NULL default '0',
  STOCK_KILOS_MIN double NOT NULL default '0',
  PRIMARY KEY  (PRODUCTO_ID),
  KEY PRODUCTO_N1 (PROVEEDOR_ID),
  CONSTRAINT `0_2023` FOREIGN KEY (`PROVEEDOR_ID`) REFERENCES `proveedor` (`PROVEEDOR_ID`)
) TYPE=InnoDB;

--
-- Table structure for table `factura`
--

CREATE TABLE factura (
  FACTURA_ID bigint(20) NOT NULL default '0',
  ID bigint(20) default NULL,
  IMPORTE_AUX_IVA double NOT NULL default '0',
  IMPORTE_TOTAL double NOT NULL default '0',
  NRO_FACTURA bigint(20) default NULL,
  LUGAR varchar(255) default '',
  ANULADA tinyint(1) NOT NULL default '0',
  TIPO_FACTURA varchar(255) binary default NULL,
  PERIODO varchar(255) binary default NULL,
  PRIMARY KEY  (FACTURA_ID)
) TYPE=InnoDB;

--
-- Table structure for table `factura_cliente`
--

CREATE TABLE factura_cliente (
  FACTURA_CLIENTE_ID bigint(20) NOT NULL default '0',
  CLIENTE_ID bigint(20) default NULL,
  COND_VENTA varchar(255) binary default NULL,
  FECHA_IMPRESION date default NULL,
  FECHA_PAGO date default NULL,
  IMPORTE_ABONADO double NOT NULL default '0',
  INGR_BRUTOS varchar(255) binary default NULL,
  IVA_F varchar(255) binary default NULL,
  REMITO_NRO varchar(255) binary default NULL,
  PRIMARY KEY  (FACTURA_CLIENTE_ID),
  KEY FACTURA_CLIENTE_N1 (CLIENTE_ID),
  CONSTRAINT `0_1761` FOREIGN KEY (`CLIENTE_ID`) REFERENCES `cliente` (`CLIENTE_ID`),
  CONSTRAINT `0_1762` FOREIGN KEY (`FACTURA_CLIENTE_ID`) REFERENCES `factura` (`FACTURA_ID`)
) TYPE=InnoDB;

--
-- Table structure for table `factura_proveedor`
--

CREATE TABLE factura_proveedor (
  FACTURA_PROVEEDOR_ID bigint(20) NOT NULL default '0',
  FECHA date default NULL,
  PROVEEDOR_ID bigint(20) default NULL,
  PRIMARY KEY  (FACTURA_PROVEEDOR_ID),
  KEY FACTURA_PROVEEDOR_N1 (PROVEEDOR_ID),
  CONSTRAINT `0_1764` FOREIGN KEY (`FACTURA_PROVEEDOR_ID`) REFERENCES `factura` (`FACTURA_ID`),
  CONSTRAINT `0_1765` FOREIGN KEY (`PROVEEDOR_ID`) REFERENCES `proveedor` (`PROVEEDOR_ID`)
) TYPE=InnoDB;

--
-- Table structure for table `planilla_es`
--

CREATE TABLE planilla_es (
  PLANILLA_ES_ID bigint(20) NOT NULL default '0',
  FECHA_P date default NULL,
  ID bigint(20) default NULL,
  NRO_PLANILLA int(11) NOT NULL default '0',
  SALDO double NOT NULL default '0',
  PERIODO varchar(255) binary default NULL,
  PRIMARY KEY  (PLANILLA_ES_ID)
) TYPE=InnoDB;

--
-- Table structure for table `movimiento_caja`
--

CREATE TABLE movimiento_caja (
  MOVIMIENTO_CAJA_ID bigint(20) NOT NULL default '0',
  CODIGO int(11) NOT NULL default '0',
  CON_FACTURA tinyint(1) NOT NULL default '0',
  DESCRIPCION varchar(255) binary default NULL,
  FACTURA_ID bigint(20) default NULL,
  FECHA_MC date default NULL,
  FORMA_PAGO varchar(255) binary default NULL,
  ID bigint(20) default NULL,
  IMPORTE double NOT NULL default '0',
  NRO_CHEQUE bigint(20) default NULL,
  PLANILLA_ID bigint(20) default NULL,
  TIPO_FACTURA varchar(255) binary default NULL,
  TIPO_MOVIMIENTO int(11) NOT NULL default '0',
  PERIODO varchar(255) binary default NULL,
  PRIMARY KEY  (MOVIMIENTO_CAJA_ID),
  KEY MOVIMIENTO_CAJA_N2 (PLANILLA_ID),
  KEY MOVIMIENTO_CAJA_N1 (FACTURA_ID),
  CONSTRAINT `0_1768` FOREIGN KEY (`PLANILLA_ID`) REFERENCES `planilla_es` (`PLANILLA_ES_ID`),
  CONSTRAINT `0_1769` FOREIGN KEY (`FACTURA_ID`) REFERENCES `factura` (`FACTURA_ID`)
) TYPE=InnoDB;

--
-- Table structure for table `item_factura`
--

CREATE TABLE item_factura (
  ITEM_FACTURA_ID bigint(20) NOT NULL default '0',
  CANTIDAD int(11) NOT NULL default '0',
  DESCUENTO int(11) NOT NULL default '0',
  FACTURA_ID bigint(20) default NULL,
  ID bigint(20) default NULL,
  KILOS double NOT NULL default '0',
  PR_TOTAL double NOT NULL default '0',
  PR_UNIT double NOT NULL default '0',
  PRODUCTO_ID bigint(20) default NULL,
  PRIMARY KEY  (ITEM_FACTURA_ID),
  KEY ITEM_FACTURA_N1 (PRODUCTO_ID),
  KEY ITEM_FACTURA_N2 (FACTURA_ID),
  CONSTRAINT `0_1771` FOREIGN KEY (`PRODUCTO_ID`) REFERENCES `producto` (`PRODUCTO_ID`),
  CONSTRAINT `0_1772` FOREIGN KEY (`FACTURA_ID`) REFERENCES `factura` (`FACTURA_ID`)
) TYPE=InnoDB;


--
-- Table structure for table `comercio`
--

CREATE TABLE comercio (
  COMERCIO_ID bigint(20) NOT NULL default '0',
  CATEGORIA varchar(255) binary default NULL,
  CUIT varchar(255) binary default NULL,
  DIRECCION varchar(255) binary default NULL,
  ID bigint(20) default NULL,
  INICIO_ACT date default NULL,
  LOCALIDAD_ID bigint(20) default NULL,
  NOMBRE varchar(255) binary default NULL,
  NRO_FACT_A bigint(20) default NULL,
  NRO_FACT_B bigint(20) default NULL,
  NRO_REMITO bigint(20) default NULL,
  TELEFONO varchar(255) binary default NULL,
  PRIMARY KEY  (COMERCIO_ID),
  KEY COMERCIO_N1 (LOCALIDAD_ID),
  CONSTRAINT `0_1776` FOREIGN KEY (`LOCALIDAD_ID`) REFERENCES `localidad` (`LOCALIDAD_ID`)
) TYPE=InnoDB;



