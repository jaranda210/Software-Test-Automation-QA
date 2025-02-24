import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContactServiceTest {
    private ContactService service;

    @BeforeEach
    void setUp() {
        service = new ContactService();
    }

    @Test
    void testAddContactSuccess() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Elm Street");
        service.addContact(contact);
        assertEquals(contact, service.getContact("12345"));
    }

    @Test
    void testAddDuplicateContact() {
        Contact contact1 = new Contact("12345", "John", "Doe", "1234567890", "123 Elm Street");
        Contact contact2 = new Contact("12345", "Jane", "Smith", "0987654321", "456 Oak Avenue");

        service.addContact(contact1);
        assertThrows(IllegalArgumentException.class, () -> service.addContact(contact2));
    }

    @Test
    void testDeleteContactSuccess() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Elm Street");
        service.addContact(contact);
        service.deleteContact("12345");
        assertNull(service.getContact("12345"));
    }

    @Test
    void testUpdateContactSuccess() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Elm Street");
        service.addContact(contact);
        service.updateContact("12345", "Jane", "Smith", "0987654321", "456 Oak Avenue");

        Contact updated = service.getContact("12345");
        assertEquals("Jane", updated.getFirstName());
        assertEquals("Smith", updated.getLastName());
        assertEquals("0987654321", updated.getPhone());
        assertEquals("456 Oak Avenue", updated.getAddress());
    }
}