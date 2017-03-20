# Proposition de convention pour travailler avec git

Dès que l'on veut faire un changement / ajouter une feature / corriger une feature :

1. Se mettre sur la branche master
	git checkout master

2. Récupérer la dernière version du projet.
	git pull

3. Créer une nouvelle branche.
	git checkout -b le-nom-de-ma-branche

4. Faire ses changements. Tester !

5. Commit une fois que ça fonctionne.
	git commit "le-nom-du-commit"

6. Push les changements sur github
	git push origin master

7. Aller sur les branches du projet, créer une pull request à partir de la branche en cours. Vérifier qu'il n'y a pas de conflits et merger.
