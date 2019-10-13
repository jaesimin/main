package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.cheatsheet.Cheatsheet;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class CheatsheetModelManager implements CheatsheetModel {
    private static final Logger logger = LogsCenter.getLogger(CheatsheetModelManager.class);

    private final CheatsheetBank cheatsheetBank;
    private final UserPrefs userPrefs;
    private final FilteredList<Cheatsheet> filteredCheatsheets;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public CheatsheetModelManager(ReadOnlyCheatsheetBank cheatsheetBank, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(cheatsheetBank, userPrefs);

        logger.fine("Initializing with address book: " + cheatsheetBank + " and user prefs " + userPrefs);

        this.cheatsheetBank = new CheatsheetBank(cheatsheetBank);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCheatsheets = new FilteredList<>(this.cheatsheetBank.getCheatsheetList());
    }

    public CheatsheetModelManager() {
        this(new CheatsheetBank(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== Cheatsheet Bank ================================================================================

    @Override
    public void setCheatsheetBank(ReadOnlyCheatsheetBank cheatsheetBank) {
        this.cheatsheetBank.resetData(cheatsheetBank);
    }

    @Override
    public ReadOnlyCheatsheetBank getCheatsheetBank() {
        return cheatsheetBank;
    }

    @Override
    public boolean hasCheatsheet(Cheatsheet cheatsheet) {
        requireNonNull(cheatsheet);
        return cheatsheetBank.hasCheatsheet(cheatsheet);
    }

    @Override
    public void deleteCheatsheet(Cheatsheet target) {
        cheatsheetBank.removeCheatsheet(target);
    }

    @Override
    public void addCheatsheet(Cheatsheet cheatsheet) {
        cheatsheetBank.addCheatsheet(cheatsheet);
        updateFilteredCheatsheetList(PREDICATE_SHOW_ALL_CHEATSHEETS);
    }

    @Override
    public void setCheatsheet(Cheatsheet target, Cheatsheet editedCheatsheet) {
        requireAllNonNull(target, editedCheatsheet);

        cheatsheetBank.setCheatsheet(target, editedCheatsheet);
    }

    //=========== Filtered Cheatsheet List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Cheatsheet} backed by the internal list of
     * {@code versionedCheatsheetBank}
     */
    @Override
    public ObservableList<Cheatsheet> getFilteredCheatsheetList() {
        return filteredCheatsheets;
    }

    @Override
    public void updateFilteredCheatsheetList(Predicate<Cheatsheet> predicate) {
        requireNonNull(predicate);
        filteredCheatsheets.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof CheatsheetModelManager)) {
            return false;
        }

        // state check
        CheatsheetModelManager other = (CheatsheetModelManager) obj;
        return cheatsheetBank.equals(other.cheatsheetBank)
                && userPrefs.equals(other.userPrefs)
                && filteredCheatsheets.equals(other.filteredCheatsheets);
    }

}
