package org.pos.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.pos.model.Item;

public class ItemRepository implements IItemRepository {
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public ItemRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("pos_pu");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    /**
     * Saves an item to the database.
     * @param item The item to be saved to the database.
     * @return Returns the saved item.
     */
    @Override
    public Item saveItem(Item item) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(item);
        this.entityManager.getTransaction().commit();
        return item;
    }

    /**
     * Update an item on the database.
     * @param updatedItem New information to update the item with.
     * @return Returns the updated item
     */
    @Override
    public Item updateItem(Item updatedItem) {
        Item item = this.entityManager.find(Item.class, updatedItem.getId());
        this.entityManager.getTransaction().begin();
        item.setName(updatedItem.getName());
        item.setQuantity(updatedItem.getQuantity());
        item.setPrice(updatedItem.getPrice());
        this.entityManager.getTransaction().commit();
        return item;
    }

    /**
     * Item to delete from the database.
     * @param item The item to be deleted from the database.
     * @return True if deleted successfully.
     */
    @Override
    public boolean deleteItem(Item item) {
        this.entityManager.getTransaction().begin();
        Item itemToDelete = this.entityManager.find(Item.class, item.getId());
        this.entityManager.remove(itemToDelete);
        this.entityManager.getTransaction().commit();
        return true;
    }

    /**
     * Closes the connection to the database.
     */
    @Override
    public void close() {
        this.entityManager.close();
        this.entityManagerFactory.close();
    }
}
