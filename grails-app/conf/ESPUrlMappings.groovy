class ESPUrlMappings {
    static mappings = {
      "/resource/$group/$type/$template?" {
          controller = "resource"
          action = "getCached"
      }

      // @todo only in dev mode
      "/test"(controller:'test', action:'index')

	  "500"(view:'/error')
	}
}
