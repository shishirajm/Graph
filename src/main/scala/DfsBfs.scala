import CommonTypes._

import scala.collection

object DfsBfs {

  def main(args: Array[String]) = {
    // Used for initial testing, this method can be deleted
    val g: NonWeightedGraph = Map(1 -> List(2, 3), 2 -> List(4), 3 -> List(4), 4 -> Nil)
    val start = 1

    val dfs = DFS(start, g)
    write(dfs)
    val bfs = BFS(start, g)
    write(bfs)
  }

  def write(vertices: List[Vertex]): Unit = {
    vertices.foreach(v => s"${print(v)} ")
    println()
  }

  def DFS(start: Vertex, g: NonWeightedGraph): List[Vertex] = {
    def DfsRec(v: Vertex, explored: List[Vertex]): List[Vertex] = {
      if (explored.contains(v))
        explored
      else {
        val neighbors: List[Vertex] = g(v).filterNot(explored.contains)
        neighbors.foldLeft(v :: explored)((exploredAcc, vertextToIterate) => DfsRec(vertextToIterate, exploredAcc))
      }
    }

    if (g.isEmpty || g.get(start).isEmpty) return List.empty
    DfsRec(start, List.empty).reverse
  }

  def BFS(start: Vertex, g: NonWeightedGraph): List[Vertex] = {
    def BfsRec(frontier: List[Vertex], explored: List[List[Vertex]]): List[List[Vertex]] = {
      val neighbors = frontier.flatMap(g(_)).filterNot(explored.flatten.contains).distinct
      if (neighbors.isEmpty)
        explored
      else
        BfsRec(neighbors, neighbors :: explored)
    }

    if (g.isEmpty || g.get(start).isEmpty) return List.empty
    BfsRec(List(start), List(List(start))).flatten.reverse
  }
}