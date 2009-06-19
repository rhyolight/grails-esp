package org.grails.plugins.esp

import grails.test.*

class ESPTagLibTests extends TagLibUnitTestCase {
    
    def storeService

    protected void setUp() {
        super.setUp()
        storeService = new StoreService()
        tagLib.metaClass.getControllerName = { -> "myController" }
        tagLib.metaClass.getActionName = { -> "myAction" }
        tagLib.metaClass.getG = { -> [ createLink:{ params -> "frank" } ] }
        tagLib.metaClass.getStoreService = { -> storeService }
        def mocklog = [ debug: { String s -> println s } ]
        tagLib.metaClass.getLog = { -> mocklog }
        storeService.metaClass.getLog = { -> mocklog }
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testJsResourceOutputsProperTag_typeOnly() {
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
    
    /*
    void testJsResourceOutputsProperTag_typeAndTemplate() {
        tagLib.resource(type:'js', template:'myTemplate')
        fail('not implemented')
    }
    */
    
    void testStoreOneJs() {
        tagLib.resource(type:'js')
        tagLib.store(type:'js') { 'javascript up in here' }
        assertTrue storeService.has('myController.myAction', 'js')
        assertEquals 'javascript up in here', storeService.get('myController.myAction', 'js')
    }
    
    void testStoreMultiple() {
        tagLib.resource(type:'js')
        10.times { i ->
            tagLib.store(type:'js') { "s${i};" }
        }
        assertTrue storeService.has('myController.myAction', 'js')
        assertEquals 's0;s1;s2;s3;s4;s5;s6;s7;s8;s9;', storeService.get('myController.myAction', 'js')
    }
    
}
