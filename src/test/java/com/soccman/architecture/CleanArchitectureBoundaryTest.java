package com.soccman.architecture;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class CleanArchitectureBoundaryTest {

    @Test
    void domainAndApplicationLayersDoNotDependOnSpringOrInfrastructure() throws IOException {
        List<Path> protectedLayers;
        try (var files = Files.walk(Path.of("src/main/java/com/soccman"))) {
            protectedLayers = files
                    .filter(path -> path.toString().endsWith(".java"))
                    .filter(path -> path.toString().contains("domain")
                            || path.toString().contains("application"))
                    .toList();
        }

        for (Path sourceFile : protectedLayers) {
            String source = Files.readString(sourceFile);
            assertFalse(
                    source.contains("import org.springframework"),
                    () -> sourceFile + " must not depend on Spring"
            );
            assertFalse(
                    source.contains(".infrastructure."),
                    () -> sourceFile + " must not depend on infrastructure"
            );
        }
    }
}

