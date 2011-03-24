package cleaner

import groovy.util.GroovyTestCase

class PunctationCleanerTest extends GroovyTestCase{

    def INPUT = 'ěšč'
    def OUTPUT = 'esc'

    void testEngine() {
        temp = File.createTempFile("testfile", ".txt")
        temp.deleteOnExit()
        temp.write( INPUT )

        cleaner = new PunctationCleaner()
        cleaner.engine( temp )

        assert temp.text == OUTPUT
    }

}
