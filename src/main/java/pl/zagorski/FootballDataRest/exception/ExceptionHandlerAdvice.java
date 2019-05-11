package pl.zagorski.FootballDataRest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.zagorski.FootballDataRest.dto.ErrorDto;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * @ControllerAdvice korzystajac z tego musze utworzyc metode handle, ktora obsluzy blad.
 * @ResponseBody powoduje, ze informacja o bledzie wyswietli sie w ciele(body).
 * @ExceptionHandler ta adnotacja powoduje, ze ta metoda wykona sie jesli wystapi wyjatek oraz
 * zostanie spelniony warunek zawarty w if.
 * <p>
 * UUID.randomUUID().toString() - GENERATOR LICZB LOSOWYCH
 */


@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler
    @ResponseBody
    ResponseEntity<ErrorDto> handle(Exception e, HttpServletRequest httpServletRequest) {
        ErrorDto errorDto = new ErrorDto();
        if (e instanceof NotFoundException) {
            errorDto.setMessage(e.getMessage());
            errorDto.setEvent(UUID.randomUUID().toString());
        }else if( e instanceof NoSuchElementException){
            errorDto.setMessage(e.getMessage());
            errorDto.setEvent(UUID.randomUUID().toString());
        }
        return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
