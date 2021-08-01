import CommonTypes._

import scala.collection.parallel.CollectionConverters.IterableIsParallelizable
import scala.collection.parallel.ParIterable

// Couldn't get it working on the REPL
// Just leaving it in a separate file.

object DijkstraParallel {
  def dijkstraRec(active: Set[Int], res: Map[Int, Int], pred: Map[Int, Int], g: Graph): (Map[Int, Int], Map[Int, Int]) = {
    if (active.isEmpty) (res, pred)
    else {
      val vertex = active.minBy(res)
      val weight = res(vertex)
      val neighbours = for {
        (n, w) <- g(vertex) if
          weight + w < res.getOrElse(n, Int.MaxValue)
      } yield n -> (weight + w)
      val active1 = active - vertex ++ neighbours.keys
      val preds = neighbours.view.mapValues(_ => vertex).toMap
      dijkstraRec(active1, res ++ neighbours, pred ++ preds, g)
    }
  }

  private def getPath(v: Vertex, m: Map[Int, Int], p: List[Vertex], source: Vertex): Option[String] = {
    def pathRec(v: Vertex, m: Map[Int, Int], p: List[Vertex]): List[Vertex] = {
      val nextVertex = m.get(v)
      if (nextVertex.isEmpty)
        p
      else if (nextVertex.contains(source))
        nextVertex.get :: p
      else
        pathRec(nextVertex.get, m, nextVertex.get :: p)
    }

    val path = pathRec(v, m, p)
    val strPath = if (path.contains(source)) Some(path.mkString("->")) else None
    strPath
  }

  def shortestPath(g: Graph, source: Vertex, dest: Vertex): Option[(Int, String)] = {
    if (g.isEmpty || g.get(source).isEmpty || g.get(dest).isEmpty) return None
    val distanceDetails = dijkstraRec(Set(source), Map(source -> 0), Map.empty, g)
    distanceDetails._1.get(dest).map(t => (t, getPath(dest, distanceDetails._2, List(dest), source).get))
  }

  def eccentricity(g: Graph, source: Vertex): Int = {
    if (g.isEmpty || g.get(source).isEmpty) return Int.MaxValue
    val (distances, _) = dijkstraRec(Set(source), Map(source -> 0), Map.empty, g)
    if (distances.keys.toList.length == g.keys.toList.length) distances.values.max else Int.MaxValue
  }

  def eccentricityCalculator(g: Graph): Option[ParIterable[Int]] = {
    if (g.isEmpty) return None
    val eccOfVertices = g.keys.toList.par.map(v => eccentricity(g, v)).filterNot(_ == Int.MaxValue)
    if (eccOfVertices.toList.length == g.keys.toList.length) Some(eccOfVertices) else None
  }

  def radius(g: Graph): Int = {
    val ecc = eccentricityCalculator(g)
    if (ecc.isEmpty) Int.MaxValue
    else ecc.get.min
  }

  def diameter(g: Graph): Int = {
    val ecc = eccentricityCalculator(g)
    if (ecc.isEmpty) Int.MaxValue
    else ecc.get.max
  }
}