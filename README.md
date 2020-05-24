# Logiciel de dessin

### 1. Commandes :

   Pour dessiner des formes (en anglais) :
   
   ``` nom de l'objet a dessiner = Forme ((coordonnée x, coordonnée y), longueur si nécéssaire)```.

   Exemples :
   
   `triangle1 = Triangle ((1,1), (2,2), (3,3))`
   
   `Carre3 = square((1,1), 6)`
   
   `bob = Circle((56, 85), 280)`
   
   `r1 = rectangle((45, 215), (65,15))`
   
   Pour déplacer des formes : Le déplacement s'effectue en ajoutant ou retirant une valeur aux coordonnées, ce n'est pas un déplacement absolu. Le déplacement fonctionne également sur les groupes d'objets.
   
   `move(nomDeLObjet, (quantité horizontale, quantité veticale))`
   
   Exemple :
   `move(r1, (1,2))`
   
   Pour regrouper des formes :
   
   ` batch(nomDuGroupe , (nomForme1, nomForme2, ...)) `
   
   Exemples :
   
   ` batch(test, (tirangle1, bob))`
   
   Pour charger et sauvegarder :
   
   `save(nomDeLaSauvegarde)`
   
   `load(nomDeLaSauvegarde)`
   
   Pour quitter : 
   
   `Quit()`
    
### 2. Spécificités :
   Les fonctionnalités ont été testées (avec des tests unitaires).
   
   Les objets sont sauvegardés dans la DB avec Save, leur nom devient alors réservé jusqu'a la reinitialisation de la BD.
   Pour réinitialiser la BD il suffit d'executer la batterie de tests dans AppTest.
   Si la BD à déjà tournée avant les tests, des messages d'erreurs peuvent survenir après une deuxième execution des tests il n'y a plus de messages d'erreurs (la BD à été réinitialisée).
   

### 3. Bugs :
   Le load, fonctionnel dans les tests mais non fonctionnel dans l'application. -> Problème avec le chargement des nouvelles formes dans le dessin courant.
   
   Le delete, optionnel, il est impossible de supprimer des objets existants sauvegardés, sauf en faisant tourner le test, puisque la BD est réinitialisée.