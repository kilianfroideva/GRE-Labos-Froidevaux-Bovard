# GRE-Labos-GrQ

## Laboratoire 1 - Algorithmes de parcours de graphes

## Auteurs: Froidevaux Kilian & Bovard Nicolas

Ce laboratoire porte sur l'implémentation d'algorithmes de parcours de graphes pour la génération et la résolution de labyrinthes.

### Algorithme BFS (Breadth-First Search)

L'algorithme BFS (parcours en largeur) est utilisé pour résoudre un labyrinthe en trouvant le chemin le plus court entre un point de départ et un point d'arrivée. Notre implémentation utilise une file (Queue) pour explorer les sommets du graphe niveau par niveau. Pour chaque sommet visité, nous explorons tous ses voisins avant de passer aux sommets du niveau suivant.

Caractéristiques principales :
- Garantit le chemin le plus court dans un graphe non pondéré
- Utilise une structure de données FIFO (First In, First Out)
- Conserve une trace des sommets découverts pour reconstruire le chemin

### Algorithme DFS (Depth-First Search)

L'algorithme DFS (parcours en profondeur) est utilisé pour générer un labyrinthe. Notre approche a d'abord consisté à développer une version récursive du DFS, puis à l'adapter en version itérative pour éviter les problèmes de dépassement de pile avec de grands labyrinthes.

L'implémentation itérative utilise une pile (Stack) pour simuler la récursion, en explorant aussi loin que possible le long de chaque branche avant de revenir en arrière. Cela crée des labyrinthes avec de longs couloirs et moins de bifurcations.

Caractéristiques principales :
- Génère des labyrinthes avec des chemins plus longs et moins de carrefours
- Utilise une structure de données LIFO (Last In, First Out)
- Marque les murs à supprimer pour créer le labyrinthe

### Difficultés rencontrées

La principale difficulté de ce laboratoire a été de comprendre comment utiliser correctement les méthodes mises à disposition par l'API. Il a fallu bien saisir le fonctionnement des interfaces `MazeBuilder`, `MazeSolver` et `Graph` pour implémenter nos algorithmes de manière efficace.

L'adaptation de l'algorithme DFS de sa version récursive à sa version itérative a également représenté un défi, notamment pour maintenir correctement l'état de la progression et la gestion des murs du labyrinthe.

Ce laboratoire nous a permis de mieux comprendre les différences fondamentales entre les algorithmes BFS et DFS, ainsi que leurs applications pratiques dans le contexte des labyrinthes.
