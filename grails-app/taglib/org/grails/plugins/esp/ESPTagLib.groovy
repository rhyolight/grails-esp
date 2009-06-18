package org.grails.plugins.esp

class ESPTagLib {

    static namespace = "esp"

    static RESOURCE_WRITERS = [
        js: { link ->
            "<script src=\"$link\" type=\"application/javascript\"/>"
        },
        css: { link -> 
            "<link href=\"$link\" type=\"text/css\" rel=\"stylesheet\"/>"
        }
    ]
    
    static REQUEST_VAR_LASTGROUP = 'org.grails.plugins.esp.last.group'
    
    def storeService
    
    /**
     * Include a resource such as CSS or JS file, that is built and cached using ESP
     */
    def resource = { attrs ->
        def group = attrs.group
        if (!group) {
            group = "${controllerName}.${actionName}"
        }
        
        def type = attrs.type
        assert type
        
        def args = [type:type, group:group]
        def template = attrs.template
        if (template) {
            args.template = template
        }
        def link = g.createLink(controller:'resource', action:'getCached', params:args)
        out << RESOURCE_WRITERS[type].call(link)
        request[REQUEST_VAR_LASTGROUP] = group
    }
    
    /** 
     * Store content in the ESP cache for this page, only if it has not been stored already
     */
    def store = { attrs, body ->
        if (!attrs.group) {
            attrs.group = request[REQUEST_VAR_LASTGROUP]
        }
        assert attrs.type
        
        storeService.store(attrs.group, attrs.type, body)
    }
}
