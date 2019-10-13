package seedu.address.model;

import javafx.collections.ObservableList;

import java.nio.file.Path;
import java.util.function.Predicate;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.cheatsheet.Cheatsheet;

public interface CheatsheetModel {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Cheatsheet> PREDICATE_SHOW_ALL_CHEATSHEETS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setCheatsheetBank(ReadOnlyCheatsheetBank cheatsheetBank);

    /** Returns the AddressBook */
    ReadOnlyCheatsheetBank getCheatsheetBank();

    /**
     * Returns true if a cheatsheet with the same identity as {@code person} exists.
     */
    boolean hasCheatsheet(Cheatsheet cheatsheet);

    /**
     * Deletes the given cheatsheet.
     * The cheatsheet must exist.
     */
    void deleteCheatsheet(Cheatsheet target);

    /**
     * Adds the given cheatsheet.
     * {@code cheatsheet} must not already exist.
     */
    void addCheatsheet(Cheatsheet cheatsheet);

    /**
     * Replaces the given cheatsheet {@code target} with {@code editedCheatsheet}.
     * {@code target} must exist.
     * The cheatsheet identity of {@code editedCheatsheet} must not be the same as another existing cheatsheet.
     */
    void setCheatsheet(Cheatsheet target, Cheatsheet editedCheatsheet);

    /** Returns an unmodifiable view of the filtered cheatsheet list */
    ObservableList<Cheatsheet> getFilteredCheatsheetList();

    /**
     * Updates the filter of the filtered cheatsheet list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCheatsheetList(Predicate<Cheatsheet> predicate);
}
