import CommonTypes._

import scala.util.Random
import scala.collection.immutable.LazyList

object RandomGraph {
  def generate(n: Int, s: Int): Option[Graph] = {
    if (n < 2) return None
    if (s < n - 1 || s > n * (n - 1)) return None
    val vertices: List[Vertex] = List.range(1, n + 1)

    def getVertexMap(vertex: Vertex, edgesForVertex: Int): Map[Vertex, Weight] = {
      val toTake = vertex + edgesForVertex
      val otherVerticesToConnect = LazyList.continually(vertices).flatten.take(toTake).toList.drop(vertex)
      otherVerticesToConnect.map(ov => ov -> Random.between(1, 10)).toMap
    }

    val emptyGraph = Map.empty[Vertex, Map[Vertex, Weight]]
    val graph = vertices.foldLeft(emptyGraph)((acc, vertex) => {
      val extraEdges = if (s % n >= vertex) 1 else 0
      val noOfEdgesForVertex = (s / n) + extraEdges
      acc + (vertex -> getVertexMap(vertex, noOfEdgesForVertex))
    })

    Some(graph)
  }
}