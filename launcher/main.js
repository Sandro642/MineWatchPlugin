// main.js

// Modules de controle du cycle de vie de l'application et de création 
// de fenêtre native de navigateur
const { app, BrowserWindow } = require('electron')

const path = require('path')

const createWindow = () => {
  // Création de la fenêtre de navigateur.
  const mainWindow = new BrowserWindow({
    width: 1280,
    height: 720,
    autoHideMenuBar: true,
    show : false,
    icon: "logo.ico"
  })

  var splash = new BrowserWindow({
    width: 400, 
    height: 500, 
    transparent: true, 
    frame: false, 
    alwaysOnTop: true,
    icon: "logo.ico"
})

    splash.loadFile('loader.html'); 
    splash.center();

    setTimeout(function () { 
        splash.close(); 
        mainWindow.show(); 
      }, 6000);

  // et chargement de l'index.html de l'application.
  mainWindow.loadFile('index.html');
  mainWindow.center();

  // Ouvrir les outils de développement.
  // mainWindow.webContents.openDevTools()
}

// Cette méthode sera appelée quand Electron aura fini
// de s'initialiser et sera prêt à créer des fenêtres de navigation.
// Certaines APIs peuvent être utilisées uniquement quant cet événement est émit.
app.whenReady().then(() => {
  createWindow()

  app.on('activate', () => {
    // Sur macOS il est commun de re-créer une fenêtre  lors 
    // du click sur l'icone du dock et qu'il n'y a pas d'autre fenêtre ouverte.
    if (BrowserWindow.getAllWindows().length === 0) createWindow()
  })
})

// Quitter quand toutes les fenêtres sont fermées, sauf sur macOS. Dans ce cas il est courant
// que les applications et barre de menu restents actives jusqu'à ce que l'utilisateur quitte 
// de manière explicite par Cmd + Q.
app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') app.quit()
})