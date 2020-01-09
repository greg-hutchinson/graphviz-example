package ca.fcc.graphexplorer

import ca.attractors.dot.DefaultNodeAttributes
import ca.attractors.dot.Graph
import ca.attractors.dot.attribute.type.NodeShapeType
import ca.attractors.dot.attribute.type.RankDirType
import groovy.json.JsonSlurper

class DefinitionParser {
    File file
    def graph = new Graph()

    public DefinitionParser(File aRoot) {
        file = aRoot
    }
    def parse() {
        println ("******\n\n\n\n")
        def graph1 = getGraphFromFile(file)
        println ("******\n\n\n\n")
        println (graph1.toDotString())
    }

    Graph getGraphFromFile (File aFile) {
        def object = new JsonSlurper().parseText(aFile.text)
        DefaultNodeAttributes nodeAttributes = graph.newDefaultNodeAttributes()
        nodeAttributes.setShape(NodeShapeType.BOX_3D)
      //  graph.setConcentrate(true)
        graph.setRankDir(RankDirType.TB)

        def isFirstNode = true
        def firstNode = null
        def properties = object.properties
        def definition = properties.definition
        def actions = definition.actions
        actions.each { item ->
            String nodeName = item.key
            def runAfter = item.value.runAfter
            String proceedingNode
            runAfter.keySet().each {
                proceedingNode = it
            }
            graph.newEdge(proceedingNode, nodeName)
        }
        return graph
    }

}
