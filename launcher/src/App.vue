<template>
  <div id="app">
    <h1>Liste des joueurs :</h1>
    <table>
      <thead>
        <tr>
          <th>Monde</th>
          <th>Joueur</th>
          <th>Action</th>
          <th>Heure</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(row, index) in tableData" :key="index">
          <td>{{ row.World }}</td>
          <td>{{ row.Player }}</td>
          <td>{{ row.Action }}</td>
          <td>{{ row.Timestamp }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import urlapi from './config.js';

import axios from 'axios';

export default {
  data() {
    return {
      tableData: [],
      pollingInterval: null,
    };
  },
  created() {
    this.fetchTableData(); // Appel initial pour récupérer les données au démarrage
    this.startPolling(); // Démarrer la vérification périodique des nouvelles données
  },
  beforeUnmount() {
    this.stopPolling(); // Arrêter la vérification périodique avant que le composant soit détruit
  },
  methods: {
    fetchTableData() {
      const apiUrl = urlapi;

      axios.get(apiUrl)
        .then((response) => {
          this.tableData = response.data;
        })
        .catch((error) => {
          console.error('Erreur lors de la récupération des données :', error);
        });
    },
    startPolling() {
      this.pollingInterval = setInterval(this.fetchTableData, 5000); // Vérifier toutes les 5 secondes (vous pouvez ajuster l'intervalle selon vos besoins)
    },
    stopPolling() {
      clearInterval(this.pollingInterval); // Arrêter la vérification périodique lorsque le composant est détruit
    },
  },
};
</script>

<style>
/* Styles spécifiques au composant vont ici */
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
</style>
@/config.js