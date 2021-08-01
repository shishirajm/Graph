import CommonTypes._

import scala.collection

object DfsBfsWeighted {
  def main(args: Array[String]) = {
    // Used for initial testing, this method can be deleted
    val g: Graph = Map(
      1 -> Map(2 -> 1, 3 -> 3),
      2 -> Map(4 -> 4),
      3 -> Map(4 -> 2),
      4 -> Map.empty
    )
    val start = 1

    val dfs = DFS(start, g)
    write(dfs)
    val bfs = BFS(start, g)
    write(bfs)
  }

  def write(vertices: List[Vertex]) = {
    vertices.foreach(v => s"${print(v)} ")
    println()
  }

  def DFS(start: Vertex, g: Graph): List[Vertex] = {
    def DfsRec(v: Vertex, explored: List[Vertex]): List[Vertex] = {
      if (explored.contains(v))
        explored
      else {
        val neighbors = g(v).keys.filterNot(explored.contains)
        neighbors.foldLeft(v :: explored)((exploredAcc, vertextToIterate) => DfsRec(vertextToIterate, exploredAcc))
      }
    }

    if (g.isEmpty || g.get(start).isEmpty) return List.empty
    DfsRec(start, List.empty).reverse
  }

  def BFS(start: Vertex, g: Graph): List[Vertex] = {
    def BfsRec(frontier: List[Vertex], explored: List[List[Vertex]]): List[List[Vertex]] = {
      val neighbors = frontier.flatMap(g(_).keys).filterNot(explored.flatten.contains).distinct
      if (neighbors.isEmpty)
        explored
      else
        BfsRec(neighbors, neighbors :: explored)
    }

    if (g.isEmpty || g.get(start).isEmpty) return List.empty
    BfsRec(List(start), List(List(start))).flatten.reverse
  }
}