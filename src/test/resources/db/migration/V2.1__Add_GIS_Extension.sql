CREATE ALIAS IF NOT EXISTS H2GIS_SPATIAL FOR "org.h2gis.functions.factory.H2GISFunctions.load";
CALL H2GIS_SPATIAL();

UPDATE partners SET address = (ST_GeomFromText('POINT(10.809003 54.097834)',4326));