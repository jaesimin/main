package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.cheatsheet.Cheatsheet;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyCheatsheetBank {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Cheatsheet> getCheatsheetList();
}
