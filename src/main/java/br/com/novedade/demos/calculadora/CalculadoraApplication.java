package br.com.novedade.demos.calculadora;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CalculadoraApplication {
	
    @GetMapping(value = "/", produces = "application/json")
    public Map root() {
    	
    	Map<String,Object> ret = new HashMap<String,Object>();
    	ret.put("status",  "UP");
        return ret;
    }

	public static void main(String[] args) {
		SpringApplication.run(CalculadoraApplication.class, args);
	}
}
