package org.careerseekers.apientrypoint.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GatewayConfig {
    @Value("\${services.uri.users-service}")
    private lateinit var userServiceURI: String

    @Value("\${services.uri.events-service}")
    private lateinit var eventsServiceURI: String

    @Value("\${services.uri.mail-service}")
    private lateinit var mailServiceURI: String

    @Bean
    fun customRouteLocator(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route("users-service") { r ->
                r.path("/user-service/**")
                    .uri(userServiceURI)
            }
            .route("events-service") { r ->
                r.path("/events-service/**")
                    .uri(eventsServiceURI)
            }
            .route("mail-service") { r ->
                r.path("/mail-service/**")
                    .uri(mailServiceURI)
            }
            .build()
    }
}