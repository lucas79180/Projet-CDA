# Projet CDA N°1 : Enchère



## Développeurs

- [@Mathis](https://github.com/MathisDeschamps)
- [@Lucas](https://github.com/lucas79180)
- [@Gabin](https://github.com/GabinBrochardDev)


## Exécution local (en mode projet)
Ce guide est fait en utilisant InteliJ, certain étape doivent être adapter dans le cas de l'utilisation d'un autre IDE.

### Prérequis 
- JDK-17
- Node-JS 20.3.1


### Récupération et initialisation du projet
Clonner le projet

```bash
  git clone https://github.com/lucas79180/Projet-CDA.git
```

Ouvrir InteliJ


Pour la base de donnée il y a deux option possible :
1. SQL Server  
    Si vous utilisez SQL Server, on part du principe que la configuration de base a été effectuée et que le TCP/IP est activé.  
    Dans ce cas, utilisez le script SQL suivant pour créer la structure de la base : `.\Projet-CDA\install\script-creation-structure-bdd.sql`
    Modifier ensuite le fichier `.\Projet-CDA\Back-end\src\main\ressources\application.properties` pour y renseigner les informations d'accès et d'identification de votre base de données.

2. H2  
    Si vous souhaiter utiliser une base H2, remplacé le fichier `.\Projet-CDA\Back-end\setting.gradle` par celui présent dans `.\Projet-CDA\install\H2`.  
    Puis remplacer le fichier `.\Projet-CDA\Back-end\src\main\ressources\application.properties` par celui présent dans `.\Projet-CDA\install\H2`.


Ensuite faire un clic droit sur le fichier `.\Back-end\build.gradle` puis cliquer sur "Link Gradle Project" pour initaliser Gradle.  

Faire un  clic droit sur `.\Projet-CDA\back-end\src\back-end\BackEndApplication` puis cliquer sur "Run 'BackEndApplica...main()' "

Ce rendre dans le répertoire du projet

```bash
  cd .\Projet-CDA\Front-end\
```


Installer les dépenances

```bash
  npm install
```

Lancer le serveur

```bash
  npm run dev
```


