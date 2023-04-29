//package com.example.contestApp;
//
//import com.example.contestApp.service.AppService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.springframework.test.util.AssertionErrors.assertEquals;
//
//@SpringBootTest
//class AppServiceTest {
//    @Autowired
//    private AppService appService;
//
//    @Test
//    void testGetAge() {
//        assertEquals(27, appService.getAge("Андрей"));
//        assertEquals(54, appService.getAge("Дмитрий"));
//        assertTrue(appService.getAge("John") > 0);
//    }
//
//    @Test
//    void testGetStats() {
//        Map<String, Integer> stats = appService.getStats();
//        assertEquals(2, stats.get("Андрей").intValue());
//        assertEquals(1, stats.get("Дмитрий").intValue());
//    }
//
//    @Test
//    void testGetMax() {
//        assertEquals("Дмитрий", ageController.getMax());
//    }
//}
