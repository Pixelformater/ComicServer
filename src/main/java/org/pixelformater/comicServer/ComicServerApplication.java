package org.pixelformater.comicServer;

import org.pixelformater.comicServer.Services.InitializatorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("org.pixelformater.comicServer.Model")
@EnableJpaRepositories("org.pixelformater.comicServer.Repositories")
public class ComicServerApplication {



    public static void main(String[] args) {
        SpringApplication.run(ComicServerApplication.class, args);
        new InitializatorService().initializeDatabase();

    }
}

