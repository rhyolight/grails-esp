package org.grails.plugins.esp

class StoreService {

    boolean transactional = false

    // This is likely inefficient and a concurrency problem, resolve it pls :) EhCache or similar
    Map lameCache = [:]
    
    /** 
     * Append the supplied content to the ESP resource identifed by the type and group supplied
     */
    void store(group, type, contentClosure) {
        def content = contentClosure()        
        log.debug "esp service:store (group:$group) (type:$type) Content:"
        log.debug content
        // Need to be threadsafe here
        def key = "${group}|${type}"
        log.debug "caching into \"$key\""
        def cached = lameCache[key]
        if (!cached) {
            cached = new StringBuilder()
            lameCache[key] = cached
        }
        cached << content
    }
    
    def get(group, type) {
        return lameCache["${group}|${type}"].toString()
    }

    boolean has(group, type) {
        log.debug "esp:service has (group:$group) (type:$type)?"
        log.debug "Current cache: ${lameCache}"
        log.debug "For key ${group}|${type} found:" + lameCache["${group}|${type}"]
        // Need to be threadsafe here
        return lameCache["${group}|${type}"] != null
    }
    
    def clear(group, type) {
        lameCache["${group}|${type}"] = null
    }
}
