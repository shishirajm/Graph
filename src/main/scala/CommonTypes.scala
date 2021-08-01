object CommonTypes {
  type Vertex = Int
  type NonWeightedGraph = Map[Vertex, List[Vertex]]

  type Weight = Int
  type Graph = Map[Vertex, Map[Vertex, Weight]]

  type TotalWeightAndPath = (Int, String)
}
