package seedu.address.model.cheatsheet.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateCheatsheetException extends RuntimeException {
    public DuplicateCheatsheetException() {
        super("Operation would result in duplicate cheatsheets");
    }
}
