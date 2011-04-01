echo "--------------------------------------------------------------------------------"
echo "                          INSTALANDO LA APLICACIÓN"
echo "--------------------------------------------------------------------------------"
mvn clean install
if [ $? -ne 0 ]
then
	echo "Ocurrió un error al generar los paquetes."
	exit 1
fi

echo "--------------------------------------------------------------------------------"
echo "                        CARGANDO LOS DATOS DE PRUEBA"
echo "--------------------------------------------------------------------------------"
cd criadero-model
mvn exec:java -Dexec.mainClass=ar.uba.fi.criaderoxp.RunInitialLoad -e
if [ $? -ne 0 ]
then
	echo "Ocurrió un error al cargar los datos."
	exit 1
fi

echo "--------------------------------------------------------------------------------"
echo "                              LEVANTANDO EL SERVIDOR"
echo "--------------------------------------------------------------------------------"
cd ../criadero-webapp
mvn jetty:run
if [ $? -ne 0 ]
then
	echo "Ocurrió un error al iniciar el servidor."
	exit 1
fi

