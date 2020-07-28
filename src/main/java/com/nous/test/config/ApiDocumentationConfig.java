package com.nous.test.config;

import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(
        info = @Info(
                description = "Leave Service API",
                version = "V12.0.12",
                title = "Leave module Resource API",
                contact = @Contact(
                   name = "Aman", 
                   email = "Aman@nousinfo.com", 
                   url = "http://www.nousinfo.com"
                ),
                license = @License(
                   name = "Apache 2.0", 
                   url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        consumes = {"application/json", "application/xml"},
        produces = {"application/json", "application/xml"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        externalDocs = @ExternalDocs(value = "Service Provider", url = "http://www.nousinfo.com")
)
public interface ApiDocumentationConfig {

}