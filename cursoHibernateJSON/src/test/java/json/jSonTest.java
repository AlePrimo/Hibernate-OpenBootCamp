package json;

import com.example.dao.EmployeeDAO;
import com.example.dao.EmployeeDAOimpl;
import com.example.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class jSonTest {


    EmployeeDAO dao;

    @BeforeEach
    void setUp() {
        dao = new EmployeeDAOimpl();
    }

    @Test
    void createWithJson() {
        Map<String, Object> json = new HashMap<>();
        json.put("color ", "azul");
        json.put("estado ", "activo");
        json.put("age ", 42);
        Employee employee5 = new Employee(null,"Albert","Camus","camu@mail.com",json);
        dao.create(employee5);

    }







}
