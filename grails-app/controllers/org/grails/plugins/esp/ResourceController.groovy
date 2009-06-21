package org.grails.plugins.esp

class ResourceController {

    def storeService
    
    // This is also lame, not thread safe and not optimal
    static Map contentCache = [:]
    
    static TYPES_TO_MIME = [
        js: 'application/javascript',
        css: 'text/css'
    ]

    
    def getCached = { 
        def group = params.group

        assert group
        assert params.type
        
        // If a template was supplied, key on that also
        def key = params.template ? "${params.template}|${group}|${params.type}" : "${group}|${params.type}"

        if (log.debugEnabled) log.debug "ESP controller using cache key ${key}"
        
        def cachedContent = contentCache.get(key)
        if (!cachedContent) {
            if (log.debugEnabled) log.debug "ESP controller using cache key ${key} has no cached content, generating"
            // Get the builder and make it a string now
            cachedContent = storeService.get(group, params.type)?.toString()
            if (!cachedContent) {
                response.sendError(404, "No ESP content for group ${group} and type ${params.type}")
                return null
            }
            // @todo Implement template rendering, passing in the cachedContent to the template to decorate it with static CSS/JS
            contentCache[key] = cachedContent
        } else {
            if (log.debugEnabled) log.debug "ESP controller using cache key ${key} has ached content: ${cachedContent}"
        }
        
        // @todo set cache headers
        render(text:cachedContent, contentType:TYPES_TO_MIME[params.type])
        
        return null
    }
}
