import CommonTypes._
import DfsBfsWeighted.{bfs, dfs}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.not
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}

class DfsBfsWeightedSuite extends AnyFunSuite {
  val g: Graph = Map(
    1 -> Map(2 -> 1, 3 -> 3),
    2 -> Map(4 -> 4),
    3 -> Map(4 -> 2),
    4 -> Map.empty
  )
  val g1: Graph = Map(
    1 -> Map(2 -> 1, 3 -> 3),
    2 -> Map(3 -> 4),
    3 -> Map(2 -> 2),
    4 -> Map(5 -> 7),
    5 -> Map(4 -> 6)
  )
  val g2: Graph = Map(
    1 -> Map(2 -> 1, 3 -> 3),
    2 -> Map(4 -> 4),
    3 -> Map(4 -> 2),
    4 -> Map(5 -> 7),
    5 -> Map.empty
  )

  test("DFS on empty graph result should be empty") {
    val res = dfs(1, Map.empty[Vertex, Map[Vertex, Weight]])
    res should equal (List.empty[Vertex])
  }

  test("DFS on unknown key result should be empty") {
    val res = dfs(-1, g)
    res should equal (List.empty[Vertex])
  }

  test("DFS on valid graph should have expected result") {
    val res = dfs(1, g)
    res should (equal(List(1, 2, 4, 3)) or equal(List(1, 3, 4, 2)))
  }

  test("DFS on valid graph should not have BFS result") {
    val res = dfs(1, g)
    res should not equal (List(1, 2, 3, 4))
  }

  test("DFS in case of disconnected graph should have expected result") {
    val res = dfs(1, g1)
    res should equal (List(1, 2, 3))
  }

  test("DFS in case of disconnected graph should have expected result2") {
    val res = dfs(5, g1)
    res should equal (List(5, 4))
  }

  test("DFS in case of connected linear graph should have expected result") {
    val res = dfs(2, g2)
    res should equal (List(2, 4, 5))
  }

  test("BFS on empty graph result should be empty") {
    val res = bfs(1, Map.empty[Vertex, Map[Vertex, Weight]])
    res should equal (List.empty[Vertex])
  }

  test("BFS on unknown key result should be empty") {
    val res = bfs(-1, g)
    res should equal (List.empty[Vertex])
  }

  test("BFS on valid graph should have expected result") {
    val res = bfs(1, g)
    res should (equal(List(1, 2, 3, 4)) or equal(List(1, 3, 2, 4)))
  }

  test("BFS on valid graph should not have res result") {
    val res = bfs(1, g)
    res should not equal (List(1, 2, 4, 3))
  }

  test("BFS in case of disconnected graph should have expected result") {
    val res = bfs(1, g1)
    res should (equal(List(1, 2, 3)) or equal(List(1, 3, 2)))
  }

  test("BFS in case of disconnected graph should have expected result2") {
    val res = bfs(5, g1)
    res should equal (List(5, 4))
  }

  test("BFS in case of connected linear graph should have expected result") {
    val res = bfs(2, g2)
    res should equal (List(2, 4, 5))
  }
}
