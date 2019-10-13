package seedu.address.model.cheatsheet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.cheatsheet.exceptions.CheatsheetNotFoundException;
import seedu.address.model.cheatsheet.exceptions.DuplicateCheatsheetException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class UniqueCheatsheetList implements Iterable<Cheatsheet> {

    private final ObservableList<Cheatsheet> internalList = FXCollections.observableArrayList();
    private final ObservableList<Cheatsheet> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent cheatsheet as the given argument.
     */
    public boolean contains(Cheatsheet toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameCheatsheet);
    }

    /**
     * Adds a cheatsheet to the list.
     * The cheatsheet must not already exist in the list.
     */
    public void add(Cheatsheet toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateCheatsheetException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the cheat {@code target} in the list with {@code editedCheatsheet}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setCheatsheet(Cheatsheet target, Cheatsheet editedCheatsheet) {
        requireAllNonNull(target, editedCheatsheet);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new CheatsheetNotFoundException();
        }

        if (!target.isSameCheatsheet(editedCheatsheet) && contains(editedCheatsheet)) {
            throw new DuplicateCheatsheetException();
        }

        internalList.set(index, editedCheatsheet);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Cheatsheet toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new CheatsheetNotFoundException();
        }
    }

    public void setCheatsheets(seedu.address.model.cheatsheet.UniqueCheatsheetList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setCheatsheets(List<Cheatsheet> cheatsheets) {
        requireAllNonNull(cheatsheets);
        if (!cheatsheetsAreUnique(cheatsheets)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(cheatsheets);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Cheatsheet> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Cheatsheet> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.cheatsheet.UniqueCheatsheetList // instanceof handles nulls
                && internalList.equals(((seedu.address.model.cheatsheet.UniqueCheatsheetList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean cheatsheetsAreUnique(List<Cheatsheet> cheatsheets) {
        for (int i = 0; i < cheatsheets.size() - 1; i++) {
            for (int j = i + 1; j < cheatsheets.size(); j++) {
                if (cheatsheets.get(i).isSameCheatsheet(cheatsheets.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

