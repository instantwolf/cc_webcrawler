import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MarkDownGeneratorTest {
    private MarkDownGenerator generator;
    private String inputUrl;
    private int targetDepth;
    private List<Website> websites;

    @Before
    public void setUp() {
        generator = new MarkDownGenerator();
        inputUrl = "www.orf.at";
        targetDepth = 2;
        websites = new ArrayList<>();
        websites.add(new Website());
        websites.add(new Website());
    }

    @Test
    public void createMarkDownGeneratorTest() {
        assertNotNull(generator);
    }

    @Test
    public void generateMarkDownTest() {
        assertNotNull(generator.generateMarkDown(inputUrl, targetDepth, websites));
    }
}
