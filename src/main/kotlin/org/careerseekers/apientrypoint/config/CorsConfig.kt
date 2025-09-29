package org.careerseekers.apientrypoint.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource


@Configuration
class CorsConfig {

    @Value("\${services.frontend.local}")
    private lateinit var frontendLocalHost: String

    @Value("\${services.frontend.production}")
    private lateinit var frontendProductionHost: String

    @Value("\${services.frontend.productionPattern}")
    private lateinit var frontendProductionHostPattern: String

    @Bean
    fun corsWebFilter(): CorsWebFilter {
        val corsConfig = CorsConfiguration().apply {
//            addAllowedOrigin(frontendLocalHost)
//            addAllowedOrigin(frontendProductionHost)
//            addAllowedOriginPattern(frontendProductionHostPattern)
            addAllowedOriginPattern("*")

            allowedMethods = listOf("*")
            allowedHeaders = listOf("Content-Type", "Authorization", "Accept", "Origin", "X-Requested-With")
            allowCredentials = true
        }

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfig)

        return CorsWebFilter(source)
    }
}