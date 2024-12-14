package bbdd;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import bbdd.model.Pasajero;
import bbdd.model.Entretenimiento;
import bbdd.model.Gasto;


public class Main {
    private static final String GASTOS_CSV = "src/main/resources/gastos.csv";

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();



        // @TODO Crear un nuevo pasajero llamado "Din Djarin" y un nuevo entretenimiento
        // llamado "Bounty Hunting" y guardarlos en la base de datos. Añade un gasto de
        // 100 a "Din Djarin" para "Bounty Hunting".

        session.beginTransaction();
        Pasajero dinDjarin = new Pasajero("Din Djarin");
        session.saveOrUpdate(dinDjarin);
        Entretenimiento bountyHunting = new Entretenimiento("Bounty Hunting");
        session.saveOrUpdate(bountyHunting);
        Gasto gasto = new Gasto(dinDjarin, bountyHunting, 100);
        session.saveOrUpdate(gasto);
        session.getTransaction().commit();


        // @TODO Leer el fichero CSV gastos.csv que se encuentra en el directorio "resources" y 
        // recorrerlo usando CSVParser para crear los pasajeros, entretenimientos y gastos que
        // en él se encuentran. Dichos gastos deberán ser asignados al pasajero/a y al entretenimiento 
        // correspondientes. Se deben guardar todos estos datos en la base de datos.
        //PRUEBA


        try (Reader reader = Files.newBufferedReader(Paths.get(GASTOS_CSV));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            session.beginTransaction();

            for (CSVRecord csvRecord : csvParser) {
                String pasajeroCSV = csvRecord.get("pasajero");
                String entretenimientoCSV = csvRecord.get("entretenimiento");
                String cantidadCSV_String = csvRecord.get("cantidad");
                int cantidadCSV = Integer.parseInt(cantidadCSV_String);

                // Crear y guardar objetos en la base de datos
                Pasajero pasajero = new Pasajero(pasajeroCSV);
                session.saveOrUpdate(pasajero);

                Entretenimiento entretenimiento = new Entretenimiento(entretenimientoCSV);
                session.saveOrUpdate(entretenimiento);

                Gasto nuevoGasto = new Gasto(pasajero, entretenimiento, cantidadCSV);
                session.saveOrUpdate(nuevoGasto);
            }

            session.getTransaction().commit();

        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}





