//@@author jaesimin-reused
//Reused from https://github.com/nus-cs2103-AY1920S1/addressbook-level3/commit/b7a47c50c8e5f0430d343a23d2863446b6ce9298#diff-af2f075d24dfcd333876f0fbce321f25 with minor modifications

package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Remark {
    public final String value;

    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

//@@author