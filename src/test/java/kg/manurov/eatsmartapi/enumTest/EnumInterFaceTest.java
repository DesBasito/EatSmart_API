package kg.manurov.eatsmartapi.enumTest;

import kg.manurov.eatsmartapi.enums.ActivityLevel;
import kg.manurov.eatsmartapi.enums.EnumInterface;
import kg.manurov.eatsmartapi.enums.Gender;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class EnumInterFaceTest {
    @Test
    void checkForTrueTypeOfEnum() {
        Double factor = ActivityLevel.getFactor("VERY_ACTIVE");
        assertEquals(1.9, factor, "The factor for VERY_ACTIVE should be 1.9");
    }

    @Test
    void testInvalidType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EnumInterface.getType(Gender.class,"INVALID");
        });

        assertEquals("Тип INVALID не найден", exception.getMessage());
    }

    @Test
    void testIsExists() {
        Boolean exists = EnumInterface.isExists(ActivityLevel.class, "Легкая");
        assertTrue(exists, "Description 'Легкая' should exist in ActivityLevel enum");
    }

    @Test
    void testIsExistsForInvalidDescription() {
        Boolean exists = EnumInterface.isExists(ActivityLevel.class, "NonExistent");
        assertFalse(exists, "Description 'NonExistent' should not exist in ActivityLevel enum");
    }
}
