<!DOCTYPE html>
<html>
<head>
    <title>MineWatch Launcher | Accueil</title>
    <link rel="stylesheet" href="style.css">
    <style>
        table {
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid black;
            padding: 5px;
        }
    </style>
</head>
<body>
    <table>
        <tr>
            <th>World</th>
            <th>Player</th>
            <th>Action</th>
        </tr>

        <?php
        // Informations de connexion à la base de données
        $host = 'localhost';
        $username = 'nom_utilisateur';
        $password = 'mot_de_passe';
        $database = 'nom_base_de_donnees';

        // Connexion à la base de données
        $conn = new mysqli($host, $username, $password, $database);

        // Vérification de la connexion
        if ($conn->connect_error) {
            die("La connexion à la base de données a échoué : " . $conn->connect_error);
        }

        // Requête SQL pour récupérer les données
        $sql = "SELECT Player, Action FROM table_joueurs";

        // Exécution de la requête
        $result = $conn->query($sql);

        // Vérification des résultats
        if ($result->num_rows > 0) {
            // Parcourir les lignes de résultats et afficher les données dans le tableau
            while ($row = $result->fetch_assoc()) {
                echo "<tr>";
                echo "<td>".$row['World']."</td>";
                echo "<td>".$row['Player']."</td>";
                echo "<td>".$row['Action']."</td>";
                echo "</tr>";
            }
        } else {
            echo "<tr><td colspan='2'>Aucune donnée trouvée</td></tr>";
        }

        // Fermer la connexion à la base de données
        $conn->close();
        ?>
    </table>
</body>
</html>
