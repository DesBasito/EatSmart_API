package kg.manurov.eatsmartapi.services.impl;

import kg.manurov.eatsmartapi.exception.ErrorResponseBody;
import kg.manurov.eatsmartapi.services.interfaces.ErrorService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ErrorServiceImpl implements ErrorService {
    @Override
    public ErrorResponseBody makeResponse(Exception ex){
        String msg = ex.getMessage();
        return ErrorResponseBody.builder()
                .title(ex.getMessage())
                .response(Map.of("errors", List.of(msg)))
                .build();
    }

    @Override
    public ErrorResponseBody makeResponse(BindingResult result){
        Map<String,List<String>> reasons = new HashMap<>();
        result.getFieldErrors().stream()
                .filter(err -> err.getDefaultMessage() != null)
                .forEach(e -> {
                    List<String> errors = new ArrayList<>();
                    errors.add(e.getDefaultMessage());
                    if (!reasons.containsKey(e.getField())){
                        reasons.put(e.getField(),errors);
                    }
                });
        return ErrorResponseBody.builder()
                .title("Valid errors")
                .response(reasons)
                .build();
    }
}
