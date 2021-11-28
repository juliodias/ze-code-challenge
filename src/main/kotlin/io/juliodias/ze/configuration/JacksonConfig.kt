package io.juliodias.ze.configuration

import com.bedatadriven.jackson.datatype.jts.JtsModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfig {

    @Bean
    fun jtsModule() = JtsModule()
}