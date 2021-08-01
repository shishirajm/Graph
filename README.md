# Graph
Graph calculations in scala, Dijkstras algorithm, eccentricity, radius and diameter of a graph.

## Build and Run
Navigate to root folder ~/Graph

Dependency: 
* scala 2.13: https://www.scala-lang.org/download/
* sbt: https://www.scala-sbt.org/download.html

Run commands:

`sbt compile`

`sbt test`

## Test from REPL
Navigate on the terminal to scala folder: `src/main/scala`
Open scala REPL, by typing in `scala` on terminal: https://docs.scala-lang.org/overviews/repl/overview.html
Run the following command:

`scala> :load CommonTypes.scala`

`scala> :load RandomGraph.scala`

`scala> :load Dijkstra.scala`

`scala> val g = RandomGraph.generate(5, 5)`

`scala> val g1 = Map(1 -> (2 -> 1, 3 -> 1), 2 -> (3 -> 1), 3 -> (1 -> 1)) // Manual Graph creation`

`scala> Dijkstra.shortestPath(g.get, 1, 4)`

`scala> Dijkstra.eccentricity(g.get, 1)`

`scala> Dijkstra.radius(g.get)`

`scala> Dijkstra.diameter(g.get)`


