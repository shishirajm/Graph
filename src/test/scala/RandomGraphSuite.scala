import CommonTypes.Vertex
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.contain
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, equal}

class RandomGraphSuite extends AnyFunSuite {
  test("RandomGraph on generating with size less than 2 nodes should not generate a graph") {
    val graph = RandomGraph.generate(1, 10)
    graph.isDefined should equal (false)
  }

  test("RandomGraph on generating size with less than 2 nodes should not generate a graph2") {
    val graph = RandomGraph.generate(0, 10)
    graph.isDefined should equal (false)
  }

  test("RandomGraph on generating size with less than 2 nodes should not generate a graph3") {
    val graph = RandomGraph.generate(-1, 10)
    graph.isDefined should equal (false)
  }

  test("RandomGraph on generating with sparseness less than n - 1 should not generate a graph") {
    val graph = RandomGraph.generate(3, 1)
    graph.isDefined should equal (false)
  }

  test("RandomGraph on generating with sparseness less than n - 1 should not generate a graph2") {
    val graph = RandomGraph.generate(3, 0)
    graph.isDefined should equal (false)
  }

  test("RandomGraph on generating with sparseness less than n - 1 should not generate a graph3") {
    val graph = RandomGraph.generate(100, 98)
    graph.isDefined should equal (false)
  }

  test("RandomGraph on generating with sparseness more than n * (n - 1) should not generate a graph") {
    val graph = RandomGraph.generate(3, 7)
    graph.isDefined should equal (false)
  }

  test("RandomGraph on generating with sparseness more than n * (n - 1) should not generate a graph2") {
    val graph = RandomGraph.generate(100, 9901)
    graph.isDefined should equal (false)
  }

  test("RandomGraph on generating with valid inputs should not generate a graph2") {
    val graph = RandomGraph.generate(100, 9901)
    graph.isDefined should equal (false)
  }

  test("RandomGraph on generating with valid inputs should not generate a connected graph") {
    val n = 3
    val s = 3
    val graph = RandomGraph.generate(n, s)
    graph.get.keys.toList.length should equal (n)
    graph.get.keys should contain theSameElementsAs (List.range(1, n + 1))
    graph.get.flatMap(x => x._2.keys).toList.length should equal (s)
  }

  test("RandomGraph on generating with valid inputs should not generate a connected graph2") {
    val n = 3
    val s = 6
    val graph = RandomGraph.generate(n, s)
    graph.get.keys.toList.length should equal (n)
    graph.get.keys should contain theSameElementsAs (List.range(1, n + 1))
    graph.get.flatMap(x => x._2.keys).toList.length should equal (s)
  }

  test("RandomGraph on generating with valid inputs should not generate a connected graph3") {
    val n = 4
    val s = 12
    val graph = RandomGraph.generate(n, s)
    graph.get.keys.toList.length should equal (n)
    graph.get.keys should contain theSameElementsAs (List.range(1, n + 1))
    graph.get.flatMap(x => x._2.keys).toList.length should equal (s)
  }

  test("RandomGraph on generating with valid inputs should not generate a connected graph4") {
    val n = 5
    val s = 4
    val graph = RandomGraph.generate(n, s)
    graph.get.keys.toList.length should equal (n)
    graph.get.keys should contain theSameElementsAs (List.range(1, n + 1))
    graph.get.flatMap(x => x._2.keys).toList.length should equal (s)
  }

  test("RandomGraph on generating with valid inputs should not generate a connected graph5") {
    val n = 1000
    val s = 999
    val graph = RandomGraph.generate(n, s)
    graph.get.keys.toList.length should equal (n)
    graph.get.keys should contain theSameElementsAs (List.range(1, n + 1))
    graph.get.flatMap(x => x._2.keys).toList.length should equal (s)
  }

  test("RandomGraph on generating with valid inputs should not generate a connected graph6") {
    val n = 1000
    val s = 999000
    val graph = RandomGraph.generate(n, s)
    graph.get.keys.toList.length should equal (n)
    graph.get.keys should contain theSameElementsAs (List.range(1, n + 1))
    graph.get.flatMap(x => x._2.keys).toList.length should equal (s)
  }
}
