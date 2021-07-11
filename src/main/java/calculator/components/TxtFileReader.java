package calculator.components;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class TxtFileReader {
    public List<Pair<Operations, BigDecimal>> getOperations(String path) throws IOException {
        return FileUtils.readLines(new File(path), StandardCharsets.UTF_8)
                .stream()
                .map(content -> content.split("\\s"))
                .map(tab -> Pair.of(tab[0], tab[1]))
                .map(pair -> Pair.of(Operations.valueOf(pair.left.toUpperCase()), new BigDecimal(pair.right)))
                .collect(Collectors.toList());
    }
}