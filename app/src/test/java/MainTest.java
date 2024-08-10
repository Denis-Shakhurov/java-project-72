import hexlet.code.App;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    public void test() {
        int res = 5;
        assertEquals(App.getNum(), res);
    }
}
