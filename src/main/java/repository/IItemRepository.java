package repository;

import model.Item;

public interface IItemRepository {

    /**
     * Saves an item to the database.
     * @param item The item to be saved to the database.
     * @return Returns the saved item.
     */
    public Item saveItem(Item item);

    /**
     * Update an item on the database.
     * @param updatedItem New information to update the item with.
     * @return Returns the updated item
     */
    public Item updateItem(Item updatedItem);

    /**
     * Item to delete from the database.
     * @param item The item to be deleted from the database.
     * @return True if deleted successfully.
     */
    public boolean deleteItem(Item item);

    /**
     * Closes the connection to the database.
     */
    public void close();
}
