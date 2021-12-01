package io.juliodias.ze.exception

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class PartnerExceptionHandler {

    @ExceptionHandler(PartnerAlreadyExistsException::class)
    fun handle(exception: PartnerAlreadyExistsException): ResponseEntity<Void> {
        logger.error("Error to create duplicated partner: ", exception)
        return ResponseEntity.badRequest().build()
    }

    companion object {
        private val logger: Logger = LogManager.getLogger()
    }
}

class PartnerAlreadyExistsException(message: String) : RuntimeException(message)