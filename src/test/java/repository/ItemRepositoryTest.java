package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    private ItemRepository itemRepository;
    private EntityManagerFactory emf;
    private EntityManager entityManager;

    private void saveItems(int numberOfItems) {
        for (int i=0; i < numberOfItems; i++) {
            this.entityManager.getTransaction().begin();
            Item item = new Item("item"+i, i+10, i+10);
            this.entityManager.persist(item);
            this.entityManager.getTransaction().commit();
        }
    }

    @BeforeEach
    void setUp() {
        this.itemRepository = new ItemRepository();
        this.emf = Persistence.createEntityManagerFactory("pos_pu");
        this.entityManager = this.emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        this.entityManager.close();
        this.emf.close();
    }

    @Test
    void saveItem() {
        Item item = itemRepository.saveItem(new Item("Test item", 10, 10));

        assertNotNull(item.getId());
    }

    @Test
    void updateItem() {
        saveItems(1);
        Item item = entityManager.find(Item.class, 1);
        item.setName("Updated item");
        this.entityManager.close();

        this.itemRepository.updateItem(item);

        this.entityManager = this.emf.createEntityManager();
        Item updatedItem = entityManager.find(Item.class, 1);
        assertEquals("Updated item", updatedItem.getName());
    }

    @Test
    void deleteItem() {
        saveItems(2);
        List<Item> items = this.entityManager.createQuery("SELECT i FROM Item i").getResultList();
        assertEquals(2, items.size());

        Item item = this.entityManager.find(Item.class, 1);
        this.itemRepository.deleteItem(item);

        items = this.entityManager.createQuery("SELECT i FROM Item i").getResultList();
        assertEquals(1, items.size());
    }
}