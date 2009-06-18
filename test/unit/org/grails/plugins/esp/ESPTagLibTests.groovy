package org.grails.plugins.esp

import grails.test.*

class ESPTagLibTests extends TagLibUnitTestCase {
    

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testJsResourceOutputsProperTag() {
        tagLib.metaClass.getControllerName = { -> "myController" }
        tagLib.metaClass.getActionName = { -> "myAction" }
        def gCalled = false
        tagLib.metaClass.getG = { ->
            [
                createLink:{ params ->
                    gCalled = true
                    assertEquals 'resource', params.controller
                    assertEquals 'getCached', params.action
                    assertEquals 'js', params.params.type
                    assertEquals 'myController.myAction', params.params.group
                    "frank"
                }
            ]
        }
        tagLib.resource(type:'js')
        assertEquals "<script src=\"frank\" type=\"application/javascript\"/>", tagLib.out.toString()
    }
}
