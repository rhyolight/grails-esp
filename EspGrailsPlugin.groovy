class EspGrailsPlugin {
    def version = 0.1
    def dependsOn = [core:"1.1 > *"]

    // TODO Fill in these fields
    def author = "Marc Palmer"
    def authorEmail = "marc@anyware.co.uk"
    def title = "Imbues your application with the power of Extra Sensory Perception"
    def description = '''\\
Allows tags and pages to magically inject code into CSS and JS files without 
filling your HTML content with junk.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/ESP+Plugin"

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
