import CommonTypes._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.contain
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}

class DijkstraSuite extends AnyFunSuite {
  val g: Graph = Map(
    1 -> Map(2 -> 3, 3 -> 5),
    2 -> Map(4 -> 10),
    3 -> Map(4 -> 7),
    4 -> Map(),
  )

  val g1: Graph = Map(
    1 -> Map(2 -> 3, 3 -> 5, 4 -> 15),
    2 -> Map(4 -> 10),
    3 -> Map(4 -> 7),
    4 -> Map(),
  )

  val g2: Graph = Map(
    1 -> Map(2 -> 3),
    2 -> Map(3 -> 10),
    3 -> Map(4 -> 7),
    4 -> Map(5 -> 11),
    5 -> Map(1 -> 1),
  )

  val disconnectedGraph: Graph = Map(
    1 -> Map(2 -> 3),
    2 -> Map(3 -> 10),
    3 -> Map(1 -> 7),
    4 -> Map(5 -> 11),
    5 -> Map(4 -> 1),
  )

  val connectedGraph: Graph = Map(
    1 -> Map(2 -> 1, 5 -> 1),
    2 -> Map(3 -> 1, 1 -> 1),
    3 -> Map(4 -> 1, 2 -> 1),
    4 -> Map(5 -> 1, 3 -> 1),
    5 -> Map(1 -> 1, 4 -> 1),
  )

  val connectedGraph2: Graph = Map(
    1 -> Map(2 -> 1),
    2 -> Map(1 -> 1, 3 -> 1, 4 -> 1, 5 -> 1),
    3 -> Map(4 -> 1, 2 -> 1),
    4 -> Map(5 -> 1, 3 -> 1),
    5 -> Map(2 -> 1, 4 -> 1),
  )

  test("ShortestPath with invalid path should return None") {
    val path = Dijkstra.shortestPath(Map.empty[Vertex, Map[Vertex, Weight]], 1, 4)
    path should equal (None)
  }

  test("ShortestPath with invalid destination should return None") {
    val path = Dijkstra.shortestPath(g, 1, 5)
    path should equal (None)
  }

  test("ShortestPath with invalid source should return None") {
    val path = Dijkstra.shortestPath(g, 0, 2)
    path should equal (None)
  }

  test("ShortestPath with invalid source and destination should return None") {
    val path = Dijkstra.shortestPath(g, 0, 5)
    path should equal (None)
  }

  test("ShortestPath on valid graph and source isnt connected should return None") {
    val path = Dijkstra.shortestPath(g, 4, 1)
    path should equal (None)
  }

  test("ShortestPath on valid graph and valid source and destination should find shortest weight and path") {
    val path = Dijkstra.shortestPath(g, 1, 4)
    path.map(_._1) should equal (Some(12))
    path.map(_._2) should equal (Some("1->3->4"))
  }

  test("ShortestPath on valid graph and valid source to same destination should be 0") {
    val path = Dijkstra.shortestPath(g, 1, 1)
    path.map(_._1) should equal (Some(0))
    path.map(_._2) should equal (Some("1"))
  }

  test("ShortestPath on valid graph and valid source and destination should find shortest weight and path even when there is heavier direct path") {
    val path = Dijkstra.shortestPath(g1, 1, 4)
    path.map(_._1) should equal (Some(12))
    path.map(_._2) should equal (Some("1->3->4"))
  }

  test("ShortestPath on valid graph and valid source and destination should have linear path when graph is linear") {
    val path = Dijkstra.shortestPath(g2, 1, 5)
    path.map(_._1) should equal (Some(31))
    path.map(_._2) should equal (Some("1->2->3->4->5"))
  }

  test("ShortestPath on valid graph and valid source and destination should have linear path when graph is linear2") {
    val path = Dijkstra.shortestPath(g2, 5, 4)
    path.map(_._1) should equal (Some(21))
    path.map(_._2) should equal (Some("5->1->2->3->4"))
  }

  test("Eccentricity with invalid path should return Infinite") {
    val e = Dijkstra.eccentricity(Map.empty[Vertex, Map[Vertex, Weight]], 1)
    e should equal (Int.MaxValue) // assumed a infinite
  }

  test("Eccentricity with invalid source should return Infinite") {
    val e = Dijkstra.eccentricity(g, 0)
    e should equal (Int.MaxValue)
  }

  test("Eccentricity with valid graph and a disconnected node should return infinite") {
    val e = Dijkstra.eccentricity(g, 4)
    e should equal (Int.MaxValue)
  }

  test("Eccentricity with disconnected graph and any node should return infinite") {
    val sources = connectedGraph.keys
    val es = sources.map(Dijkstra.eccentricity(disconnectedGraph, _))
    es should contain only (Int.MaxValue)
  }

  test("Eccentricity with valid graph and a valid source should return expected value") {
    val e = Dijkstra.eccentricity(g, 1)
    e should equal (12)
  }

  test("Eccentricity with connected graph and a any source should return expected value") {
    val sources = connectedGraph.keys
    val es = sources.map(Dijkstra.eccentricity(connectedGraph, _))
    es should contain only (2)
  }

  test("Radius with invalid path should return Infinite") {
    val r = Dijkstra.radius(Map.empty[Vertex, Map[Vertex, Weight]])
    r should equal (Int.MaxValue) // assumed a infinite
  }

  test("Radius with disconnected graph should return infinite") {
    val radius = Dijkstra.radius(disconnectedGraph)
    radius should equal (Int.MaxValue)
  }

  test("Radius with one lone node should return infinite") {
    val r = Dijkstra.radius(g)
    r should equal (Int.MaxValue)
  }

  test("Radius with connected graph should return expected value") {
    val r = Dijkstra.radius(connectedGraph)
    r should equal (2)
  }

  test("Radius with connected graph should return expected value2") {
    val r = Dijkstra.radius(connectedGraph2)
    r should equal (1)
  }

  test("Diameter with invalid path should return Infinite") {
    val r = Dijkstra.diameter(Map.empty[Vertex, Map[Vertex, Weight]])
    r should equal (Int.MaxValue) // assumed a infinite
  }

  test("Diameter with disconnected graph should return infinite") {
    val radius = Dijkstra.diameter(disconnectedGraph)
    radius should equal (Int.MaxValue)
  }

  test("Diameter with one lone node should return infinite") {
    val r = Dijkstra.diameter(g)
    r should equal (Int.MaxValue)
  }

  test("Diameter with connected graph should return expected value") {
    val r = Dijkstra.diameter(connectedGraph)
    r should equal (2)
  }

  test("Diameter with connected graph should return expected value2") {
    val r = Dijkstra.diameter(connectedGraph2)
    r should equal (3)
  }
}
