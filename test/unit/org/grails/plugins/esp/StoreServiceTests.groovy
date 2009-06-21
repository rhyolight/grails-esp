package org.grails.plugins.esp

import grails.test.*

class StoreServiceTests extends GrailsUnitTestCase {
    
    def service = new StoreService()
    
    protected void setUp() {
        super.setUp()
        service.metaClass.getLog = { ->
            [debug: {String s -> println s}]
        }
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testStoreAndGet() {
        service.store('group 1', 'type', { -> 'closure body 1'} )
        service.store('group 2', 'type', { -> 'closure body 2'} )
        
        assertEquals 'closure body 1', service.get('group 1', 'type')
        assertEquals 'closure body 2', service.get('group 2', 'type')    }

    void testClearCache() {
        service.store('group 1', 'type', { -> 'closure body 1'} )
        service.store('group 2', 'type', { -> 'closure body 2'} )
        
        assertEquals 'closure body 1', service.get('group 1', 'type')
        assertEquals 'closure body 2', service.get('group 2', 'type')
        
        service.clear('group 1', 'type')
        assertFalse service.has('group 1', 'type')
        assertTrue service.has('group 2', 'type')
        assertEquals 'closure body 2', service.get('group 2', 'type') 
    }

}
