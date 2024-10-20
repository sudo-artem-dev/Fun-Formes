# FunFormes

Le Fun Formes est une application web éducative qui permet d'observer un dessin contenant des figures géométriques et remplir un tableau en indiquant le nombre de chaque type de figure. 

L'application permettra au joueur de remplir un tableau en comptant les figures géométriques présentes sur un dessin à côté . Pour cela :

- Niveaux de difficulté : L'application propose deux niveaux de difficulté (facile ou difficile). Les niveaux plus élevés présentent des dessins plus complexes et demandent une observation plus attentive.
- Regarder le dessin : Le joueur visualise un dessin affiché à l'écran contenant diverses figures géométriques.
- Reconnaître les figures : Le joueur identifie les différents types de figures géométriques présentes dans le dessin.
- Déterminer les quantités : Le joueur compte le nombre de chaque type de figure visible dans le dessin.
- Saisir les quantités : Le joueur entre les quantités comptées dans un tableau prévu à cet effet.
- Recevoir un retour : Le joueur reçoit une rétroaction sur la précision des réponses fournies.
- Barre de progression : Une barre de progression visible à l'écran indique l'avancement du joueur dans la partie actuelle. Elle se remplit à mesure que le joueur progresse dans le jeu, permettant ainsi de savoir combien de dessins ont déjà été dénombrés et combien il en reste à traiter pour terminer la partie.
- Gagner des points : En fonction de la précision des réponses et du niveau de difficulté choisi , le joueur accumule un score.

# FrontFunFormes

Ce projet a été généré avec [Angular CLI](https://github.com/angular/angular-cli) version 17.3.10.

## Serveur de développement

Exécutez `ng serve` pour démarrer un serveur de développement. Rendez-vous ensuite sur `http://localhost:4200/`. L'application se rechargera automatiquement si vous modifiez un fichier source.


## Aide supplémentaire

Pour obtenir plus d'aide sur Angular CLI, exécutez `ng help` ou consultez la page [Angular CLI Overview and Command Reference](https://angular.io/cli).

# BackFunFormes

## Configurez la base de données

Dans le fichier `src/main/resources/application.properties`, configurez votre base de données,
Modifiez ces valeurs en fonction de votre environnement. Vérifiez que votre base de données est bien en cours d'exécution.

## Construisez le projet avec Maven

Exécutez la commande suivante pour télécharger les dépendances et compiler le projet `mvn clean install`

## Démarrez l'application Spring Boot

Lancez l'application avec la commande suivante `mvn spring-boot:run`

## Accédez à l'API

Ouvrez votre navigateur et accédez à l'URL suivante : `http://localhost:8080/api/dessins`








