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

IntelliJ:

Open the sbt project in intellij and use the UI way of running tests.

## Test from REPL
Navigate on the terminal to scala folder: `src/main/scala`

Open scala REPL, by typing in `scala` on terminal: https://docs.scala-lang.org/overviews/repl/overview.html

Run the following command:

`scala> :load CommonTypes.scala`

`scala> :load RandomGraph.scala`

`scala> :load Dijkstra.scala`

`scala> val g = RandomGraph.generate(5, 5)`

`scala> val g1 = Map(1 -> Map(2 -> 1, 3 -> 1), 2 -> Map(3 -> 1), 3 -> Map(1 -> 1)) // Manual Graph creation`

`scala> Dijkstra.shortestPath(g.get, 1, 4)`

`scala> Dijkstra.eccentricity(g.get, 1)`

`scala> Dijkstra.radius(g.get)`

`scala> Dijkstra.diameter(g.get)`

For testing Dfs and Bfs

`scala> :load DfsBfsWeighted.scala`

`scala> DfsBfsWeighted.dfs(1, g.get)`

`scala> DfsBfsWeighted.bfs(1, g.get)`

## Decisions
* I could have written lot more unit tests, but I have written to check things would work. Some cases might have been missed out.
* Random weight choosen be between 1 and 10, so that easy to manually calculate.
* Initially tried writing clojure, but I felt it's not easy for a rookie to write best clojure in one shot. So had to fall back to Scala.
