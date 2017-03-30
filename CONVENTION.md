# Proposition de convention pour travailler avec git

Dès que l'on veut faire un changement / ajouter une feature / corriger une feature :

1. Se mettre sur la branche master
```
	 git checkout master
```

2. Récupérer la dernière version du projet.
```
	git pull
```

3. Créer une nouvelle branche.
```
	git checkout -b feature/nom-explicite
```

4. Faire ses changements. Tester !

5. Commit une fois que ça fonctionne. Pour un commit mono-ligne : 
```
	git commit -m "le-nom-du-commit"
```
Pour quelque chose de plus long, faire `git commit` puis dans l'éditeur écrire le nom du du commit dans la première ligne et une description des modifications dans les lignes suivantes.

6. Push les changements sur github
```
	git push origin master
```

7. Aller sur les branches du projet, créer une pull request à partir de la branche en cours. Vérifier qu'il n'y a pas de conflits et merger.
