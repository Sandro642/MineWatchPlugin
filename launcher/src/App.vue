<template>
  <div id="app">
    <h1>Liste des joueurs :</h1>
    <table>
      <!-- Table header -->
      <thead>
      <tr>
        <th>Monde</th>
        <th>Joueur</th>
        <th>Action</th>
        <th>Heure</th>
      </tr>
      </thead>
      <!-- Table body -->
      <tbody>
      <tr v-for="(row, index) in displayedData" :key="index">
        <td>{{ row.World }}</td>
        <td>{{ row.Player }}</td>
        <td>{{ row.Action }}</td>
        <td>{{ row.Timestamp }}</td>
      </tr>
      </tbody>
    </table>
    <!-- Pagination -->
    <div class="pagination">
      <button @click="prevPage" :disabled="currentPage === 1">&lt; Précédent</button>
      <button v-for="pageNumber in totalPages" :key="pageNumber" @click="gotoPage(pageNumber)">
        {{ pageNumber }}
      </button>
      <button @click="nextPage" :disabled="currentPage === totalPages">Suivant &gt;</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      tableData: [],
      itemsPerPage: 50,
      currentPage: 1,
      latestDataTimestamp: null,
      intervalId: null,
    };
  },
  computed: {
    totalPages() {
      return Math.ceil(this.tableData.length / this.itemsPerPage);
    },
    displayedData() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      const end = this.currentPage * this.itemsPerPage;
      return this.tableData.slice(start, end);
    },
  },
  created() {
    this.startPolling(); // Démarrer la vérification périodique des nouvelles données
  },
  beforeUnmount() {
    this.stopPolling(); // Arrêter la vérification périodique avant que le composant soit détruit
  },
  methods: {
    fetchTableData() {
      const apiUrl = 'http://hungrycodes.alwaysdata.net/api.php';

      axios.get(apiUrl)
          .then((response) => {
            console.log('Nouvelles données reçues :', response.data);

            const newData = response.data;
            if (newData.length > 0) {
              // Vérifier si la dernière donnée reçue a le même timestamp que la dernière donnée existante
              const lastExistingData = this.tableData.length > 0 ? this.tableData[0] : null;
              const lastNewData = newData[0];

              if (!lastExistingData || lastNewData.Timestamp !== lastExistingData.Timestamp) {
                // Si les timestamps sont différents, remplacer les données actuelles par les nouvelles données
                this.tableData = newData;
              }
            } else {
              // Si l'API est vide, supprimer le contenu du tableau
              this.tableData = [];
              // Réinitialiser le numéro de page courant à 1
              this.currentPage = 1;
            }
            this.checkEmptyPages();
          })
          .catch((error) => {
            console.error('Erreur lors de la récupération des données :', error);
          });
    },
    checkEmptyPages() {
      const totalItems = this.tableData.length;
      const remainingItems = totalItems % this.itemsPerPage;

      if (remainingItems === 0 && totalItems > 0) {
        // Si toutes les pages sont pleines, supprimer la dernière page vide
        this.tableData.pop();
      }
    },
    startPolling() {
      this.fetchTableData(); // Appeler fetchTableData une fois pour récupérer les données au démarrage
      this.intervalId = setInterval(() => {
        this.fetchTableData(); // Appeler fetchTableData toutes les 5 secondes et mettre à jour le tableau
      }, 5000);
    },
    stopPolling() {
      clearInterval(this.intervalId); // Arrêter la vérification périodique lorsque le composant est détruit
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage += 1;
      }
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage -= 1;
      }
    },
    gotoPage(pageNumber) {
      this.currentPage = pageNumber;
    },
  },
};
</script>

<style>
/* Styles specific to the component go here */
table {
  border-collapse: collapse;
  width: 100%;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
}

th {
  background-color: #f2f2f2;
}

.pagination {
  margin-top: 20px;
}

.pagination button {
  margin: 0 5px;
  padding: 5px 10px;
  cursor: pointer;
}
</style>
