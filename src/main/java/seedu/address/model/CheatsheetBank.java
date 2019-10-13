package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.cheatsheet.Cheatsheet;
import seedu.address.model.cheatsheet.UniqueCheatsheetList;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class CheatsheetBank implements ReadOnlyCheatsheetBank {
    private final UniqueCheatsheetList cheatsheets;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        cheatsheets = new UniqueCheatsheetList();
    }

    public CheatsheetBank() {}

    /**
     * Creates a CheatsheetBank using the Cheatsheets in the {@code toBeCopied}
     */
    public CheatsheetBank(ReadOnlyCheatsheetBank toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the cheatsheet list with {@code cheatsheets}.
     * {@code persons} must not contain duplicate cheatsheets.
     */
    public void setCheatsheets(List<Cheatsheet> cheatsheets) {
        this.cheatsheets.setCheatsheets(cheatsheets);
    }

    /**
     * Resets the existing data of this {@code CheatsheetBank} with {@code newData}.
     */
    public void resetData(ReadOnlyCheatsheetBank newData) {
        requireNonNull(newData);

        setCheatsheets(newData.getCheatsheetList());
    }

    //// person-level operations

    /**
     * Returns true if a cheatsheet with the same identity as {@code cheatsheet} exists in the cheatsheet bank.
     */
    public boolean hasCheatsheet(Cheatsheet cheatsheet) {
        requireNonNull(cheatsheet);
        return cheatsheets.contains(cheatsheet);
    }

    /**
     * Adds a cheatsheet to the cheatsheet bank.
     * The cheatsheet must not already exist in the cheatsheet bank.
     */
    public void addCheatsheet(Cheatsheet c) {
        cheatsheets.add(c);
    }

    /**
     * Replaces the given cheatsheet {@code target} in the list with {@code editedCheatsheet}.
     * {@code target} must exist in the cheatsheet bank.
     * The cheatsheet identity of {@code editedCheatsheet} must not be the same
     * as another existing cheatsheet in the cheatsheet bank.
     */
    public void setCheatsheet(Cheatsheet target, Cheatsheet editedCheatsheet) {
        requireNonNull(editedCheatsheet);

        cheatsheets.setCheatsheet(target, editedCheatsheet);
    }

    /**
     * Removes {@code key} from this {@code CheatsheetBank}.
     * {@code key} must exist in the cheatsheet bank.
     */
    public void removeCheatsheet(Cheatsheet key) {
        cheatsheets.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return cheatsheets.asUnmodifiableObservableList().size() + " cheatsheets";
        // TODO: refine later
    }

    @Override
    public ObservableList<Cheatsheet> getCheatsheetList() {
        return cheatsheets.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CheatsheetBank // instanceof handles nulls
                && cheatsheets.equals(((CheatsheetBank) other).cheatsheets));
    }

    @Override
    public int hashCode() {
        return cheatsheets.hashCode();
    }
}
