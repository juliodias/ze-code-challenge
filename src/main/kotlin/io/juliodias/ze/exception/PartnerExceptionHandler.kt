package io.juliodias.ze.exception

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class PartnerExceptionHandler {

    @ExceptionHandler(PartnerAlreadyExistsException::class)
    fun handle(exception: PartnerAlreadyExistsException): ResponseEntity<PartnerErrorResponse> {
        logger.error("Error to create duplicated partner: $exception")
        val errorResponse = ErrorResponse.PARTNER_ALREADY_EXISTS
        val partnerErrorResponse = PartnerErrorResponse(errorResponse, errorResponse.description)
        return ResponseEntity.badRequest().body(partnerErrorResponse)
    }

    companion object {
        private val logger: Logger = LogManager.getLogger()
    }
}

enum class ErrorResponse(val description: String) {
    PARTNER_ALREADY_EXISTS("This partner already registed")
}

data class PartnerErrorResponse(val error: ErrorResponse, val description: String)

class PartnerAlreadyExistsException(message: String) : RuntimeException(message)