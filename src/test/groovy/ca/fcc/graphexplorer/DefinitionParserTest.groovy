package ca.fcc.graphexplorer

import org.junit.Test

class DefinitionParserTest {
    static final File DEFINITION = new File(DefinitionParserTest.class.getResource("/definition.json").toURI())

    @Test
    void testParser() {
        new DefinitionParser(DEFINITION).parse()
    }

}