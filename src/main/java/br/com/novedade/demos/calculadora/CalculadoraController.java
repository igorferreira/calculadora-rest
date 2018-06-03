package br.com.novedade.demos.calculadora;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS}, allowCredentials = "true", origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rest/v1/calculadora")
public class CalculadoraController {
	

    @GetMapping(value = "soma", produces = "application/json")
    public Map somar(@RequestParam("valor1") Integer valor1,@RequestParam("valor2")Integer valor2 ) {
    	
    	Map<String,Object> ret = new HashMap<String,Object>();
    	ret.put("sentenca",  valor1 + " + " + valor2);
    	ret.put("resultado", valor1+valor2);
    	ret.put("operacao", "soma");    			
        return ret;
    }

}
