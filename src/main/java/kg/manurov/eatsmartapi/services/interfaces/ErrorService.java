package kg.manurov.eatsmartapi.services.interfaces;

import kg.manurov.eatsmartapi.exception.ErrorResponseBody;
import org.springframework.validation.BindingResult;

public interface ErrorService {
    ErrorResponseBody makeResponse(Exception ex);

    ErrorResponseBody makeResponse(BindingResult result);
}
