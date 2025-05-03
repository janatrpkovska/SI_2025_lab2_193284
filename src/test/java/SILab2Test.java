import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SILab2Test {

    @Test
    public void testEveryStatement() {
        // Тест 1: allItems == null
        RuntimeException ex1 = assertThrows(RuntimeException.class, () ->
            SILab2.checkCart(null, "1234567890123456")
        );
        assertTrue(ex1.getMessage().contains("allItems list can't be null"));

        // Тест 2: невалидно име
        List<Item> items2 = List.of(new Item(null, 1, 100, 0));
        RuntimeException ex2 = assertThrows(RuntimeException.class, () ->
            SILab2.checkCart(items2, "1234567890123456")
        );
        assertTrue(ex2.getMessage().contains("Invalid item"));

        // Тест 3: важечка картичка, попуст
        List<Item> items3 = List.of(new Item("A", 1, 100, 0.2));
        assertEquals(80.0, SILab2.checkCart(items3, "1234567890123456"));

        // Тест 4: невалиден карактер во картичка
        RuntimeException ex3 = assertThrows(RuntimeException.class, () ->
            SILab2.checkCart(items3, "123x567890123456")
        );
        assertTrue(ex3.getMessage().contains("Invalid character in card number"));
    }

    @Test
    public void testMultipleCondition() {
        // Без попуст, без казна
        assertEquals(100.0, SILab2.checkCart(List.of(new Item("A", 1, 100, 0)), "1234567890123456"));

        // Казна - цена > 300
        assertEquals(370.0, SILab2.checkCart(List.of(new Item("B", 1, 400, 0)), "1234567890123456"));

        // Попуст
        assertEquals(70.0, SILab2.checkCart(List.of(new Item("C", 1, 100, 0.3)), "1234567890123456"));

        // Казна - количина > 10
        assertEquals(80.0, SILab2.checkCart(List.of(new Item("D", 11, 10, 0)), "1234567890123456"));
    }
}
