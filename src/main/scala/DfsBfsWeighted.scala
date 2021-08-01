import CommonTypes._

import scala.collection

object DfsBfsWeighted {

  def dfs(start: Vertex, g: Graph): List[Vertex] = {
    def dfsRec(v: Vertex, explored: List[Vertex]): List[Vertex] = {
      if (explored.contains(v))
        explored
      else {
        val neighbors = g(v).keys.filterNot(explored.contains)
        neighbors.foldLeft(v :: explored)((exploredAcc, vertextToIterate) => dfsRec(vertextToIterate, exploredAcc))
      }
    }

    if (g.isEmpty || g.get(start).isEmpty) return List.empty
    dfsRec(start, List.empty).reverse
  }

  def bfs(start: Vertex, g: Graph): List[Vertex] = {
    def bfsRec(frontier: List[Vertex], explored: List[List[Vertex]]): List[List[Vertex]] = {
      val neighbors = frontier.flatMap(g(_).keys).filterNot(explored.flatten.contains).distinct
      if (neighbors.isEmpty)
        explored
      else
        bfsRec(neighbors, neighbors :: explored)
    }

    if (g.isEmpty || g.get(start).isEmpty) return List.empty
    bfsRec(List(start), List(List(start))).flatten.reverse
  }
}