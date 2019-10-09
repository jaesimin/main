package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CheatsheetModel;
import seedu.address.model.Model;
import seedu.address.model.cheatsheet.Cheatsheet;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class AddCheatsheetCommand extends CheatsheetCommand {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a cheatsheet. "
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "midterm quiz "
            + PREFIX_TAG + "cs2103t ";

    public static final String MESSAGE_SUCCESS = "New cheatsheet added: %1$s";
    public static final String MESSAGE_DUPLICATE_CHEATSHEET = "This cheatsheet already exists";

    private final Cheatsheet toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCheatsheetCommand(Cheatsheet cheatsheet) {
        requireNonNull(cheatsheet);
        toAdd = cheatsheet;
    }

    @Override
    public CommandResult execute(CheatsheetModel model) throws CommandException {
        requireNonNull(model);

        if (model.hasCheatsheet(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CHEATSHEET);
        }

        model.addCheatsheet(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCheatsheetCommand // instanceof handles nulls
                && toAdd.equals(((AddCheatsheetCommand) other).toAdd));
    }
}
