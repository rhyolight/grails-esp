package org.grails.plugins.esp

class StoreService {

    boolean transactional = false

    // This is likely inefficient and a concurrency problem, resolve it pls :) EhCache or similar
    Map lameCache = [:]
    
    /** 
     * Append the supplied content to the ESP resource identifed by the type and group supplied
     */
    void store(group, type, contentClosure) {
        // Need to be threadsafe here
        def key = "${group}|${type}"
        def cached = lameCache[key]
        if (!cached) {
            cached = new StringBuilder()
            lameCache[key] = cached
        }
        cached << contentClosure()
    }
    
    def get(group, type) {
        return lameCache["${group}|${type}"]
    }

    boolean has(group, type) {
        // Need to be threadsafe here
        return lameCache["${group}|${type}"] != null
    }
}
