# GRE-Labos-GrQ

## Laboratoire 1 - Algorithmes de parcours de graphes

### Auteurs
- Froidevaux Kilian
- Bovard Nicolas

## Description du projet
Ce laboratoire porte sur l'implémentation d'algorithmes de parcours de graphes pour la génération et la résolution de labyrinthes. Nous avons implémenté deux algorithmes fondamentaux:
- **BFS** (Breadth-First Search) pour la résolution de labyrinthes
- **DFS** (Depth-First Search) pour la génération de labyrinthes

## Algorithmes implémentés

### Algorithme BFS (Breadth-First Search)

L'algorithme BFS (parcours en largeur) est utilisé pour résoudre un labyrinthe en trouvant le chemin le plus court entre un point de départ et un point d'arrivée. Notre implémentation utilise une file (Queue) pour explorer les sommets du graphe niveau par niveau. Pour chaque sommet visité, nous explorons tous ses voisins avant de passer aux sommets du niveau suivant.

#### Caractéristiques principales
- Garantit le chemin le plus court dans un graphe non pondéré
- Utilise une structure de données FIFO (First In, First Out)
- Conserve une trace des sommets découverts pour reconstruire le chemin

#### Détails d'implémentation
- Utilisation d'une `Queue<Integer>` pour stocker les sommets à visiter
- Tableau `discoveredFrom[]` pour tracer le chemin parcouru
- Marquage des sommets traités avec `treatments.setLabel()`
- Reconstruction du chemin par backtracking depuis la destination

### Algorithme DFS (Depth-First Search)

L'algorithme DFS (parcours en profondeur) est utilisé pour générer un labyrinthe. Notre approche a d'abord consisté à développer une version récursive du DFS, puis à l'adapter en version itérative pour éviter les problèmes de dépassement de pile avec de grands labyrinthes.

#### Caractéristiques principales
- Génère des labyrinthes avec des chemins plus longs et moins de carrefours
- Utilise une structure de données LIFO (Last In, First Out)
- Marque les murs à supprimer pour créer le labyrinthe

#### Détails d'implémentation
Nous avons implémenté deux variantes du DFS:

1. **DFS_neighbors**: 
   - Ajoute directement tous les voisins non-visités à la pile
   - Utilise un `BitSet` pour suivre les sommets découverts
   - Mélange aléatoirement les voisins pour créer des labyrinthes différents à chaque exécution

2. **DFS_backtrack**:
   - Traite un seul voisin à la fois avant de revenir aux autres
   - Utilise un `HashSet` pour suivre les sommets visités
   - Permet une exploration plus méthodique du graphe

## Difficultés rencontrées

La principale difficulté de ce laboratoire a été de comprendre comment utiliser correctement les méthodes mises à disposition par l'API. Il a fallu bien saisir le fonctionnement des interfaces `MazeBuilder`, `MazeSolver` et `Graph` pour implémenter nos algorithmes de manière efficace.

L'adaptation de l'algorithme DFS de sa version récursive à sa version itérative a également représenté un défi, notamment pour maintenir correctement l'état de la progression et la gestion des murs du labyrinthe.

## Points intéressants

Ayant chacun fait le laboratoire de notre côté, nous avons développé deux versions du DFS:
- La version retenue (`DFS_neighbors`) ajoute directement tous les voisins non-rencontrés à la pile
- L'autre version (`DFS_backtrack`) ne traite qu'un seul voisin à la fois avant de revenir aux autres non encore traités

L'avantage de la version retenue est qu'elle évite de traiter des sommets dont on sait qu'ils ne seront pas visités ultérieurement. En ajoutant directement tous les voisins non-visités à la pile, l'algorithme peut "sauter" à la dernière bifurcation lors du backtracking, ce qui améliore l'efficacité (mais ne change pas la complexité de celui-ci).

On peut visualiser cette différence en choisissant l'algorithme à utiliser dans le code (`src/main/java/ch/heig/gre/groupQ/DfsGenerator.java`).

## Conclusion

Ce laboratoire nous a permis de mieux comprendre les différences fondamentales entre les algorithmes BFS et DFS, ainsi que leurs applications pratiques dans le contexte des labyrinthes. Nous avons pu observer comment ces algorithmes classiques de la théorie des graphes peuvent être adaptés pour résoudre des problèmes concrets comme la génération et la résolution de labyrinthes.
