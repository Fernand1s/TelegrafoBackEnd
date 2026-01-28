package com.telegrafo.backend;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.telegrafo.backend.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TelegrafoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegrafoApplication.class, args);
	}

    @Bean
    public CommandLineRunner demo(UsuarioRepository repository){
        return (args) ->{
            /*Usuario teste = new Usuario();

            teste.setNome("Fernandes");
            teste.setEmail("estevaonunnes@gmail.com");
            teste.setSenha("Red123");

            repository.save(teste);




            Usuario t1 = new Usuario();

            t1.setEmail("fe@gmail.com");
            t1.setNome("Esvo");
            t1.setSenha("Sssst123");
            repository.save(t1);
            */
        };
    }

}
