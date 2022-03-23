package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.dish.Dish;
import seedu.address.model.item.Name;
import seedu.address.model.item.Person;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedDish {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Dish's %s field is missing!";

    private final String name;

    /**
     * Constructs a {@code JsonAdaptedDish} with the given dish details.
     */
    @JsonCreator
    public JsonAdaptedDish(@JsonProperty("name") String name) {
        this.name = name;
    }

    /**
     * Converts a given {@code Dish} into this class for Jackson use.
     */
    public JsonAdaptedDish(Dish source) {
        name = source.getName().fullName;
    }

    /**
     * Converts this Jackson-friendly adapted dish object into the model's {@code Dish} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted dish.
     */
    public Dish toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        return new Dish(modelName);
    }

}

