package fr.sandro642.github.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import fr.sandro642.github.MineWatch;
import org.bson.Document;

public class DatabaseManager {

    public static DatabaseManager instance = new DatabaseManager();

    public final String DB_URI = "mongodb+srv://Sandro642:Sandro3345_@minewatch.aqatpfv.mongodb.net/?retryWrites=true&w=majority";
    public final String DB_NAME = "MineWatch";
    public final String DB_COLLECTION = "joueurs";

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public void connect() {
        try {
            // Étape 1: Création d'un client MongoDB en utilisant l'URI de connexion
            mongoClient = MongoClients.create(DB_URI);

            // Étape 2: Obtention de la référence à la base de données
            database = mongoClient.getDatabase(DB_NAME);

            // Étape 3: Obtention de la référence à la collection
            collection = database.getCollection(DB_COLLECTION);

            System.out.println("Connexion à MongoDB réussie.");
        } catch (Exception e) {
            System.err.println("Erreur lors de la connexion à MongoDB.");
        }
    }

    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Connexion à MongoDB fermée.");
        }
    }

    public void fetchDataFromCollection() {
        try {
            // Étape 4: Récupération des données depuis la collection
            FindIterable<Document> documents = collection.find();
            MongoCursor<Document> cursor = documents.iterator();

            // Étape 5: Traitement des résultats
            while (cursor.hasNext()) {
                Document document = cursor.next();
                // Traitez ici les données récupérées depuis MongoDB
                // Exemple: String name = document.getString("name");
                //         int age = document.getInteger("age");
                //         ...
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des données depuis MongoDB.");
        }
    }
}
