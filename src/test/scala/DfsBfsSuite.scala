import CommonTypes._
import DfsBfs.{bfs, dfs}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.not
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}

class DfsBfsSuite extends AnyFunSuite {
  val g: NonWeightedGraph = Map(1 -> List(2, 3), 2 -> List(4), 3 -> List(4), 4 -> Nil)
  val g1: NonWeightedGraph = Map(1 -> List(2, 3), 2 -> List(), 3 -> List(2), 4 -> List(5), 5 -> List(4))
  val g2: NonWeightedGraph = Map(1 -> List(2, 3), 2 -> List(4), 3 -> List(2), 4 -> List(5), 5 -> List())

  test("DFS on empty graph result should be empty") {
    val res = dfs(1, Map.empty[Vertex, List[Vertex]])
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
    val res = bfs(1, Map.empty[Vertex, List[Vertex]])
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
