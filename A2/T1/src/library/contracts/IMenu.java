package library.contracts;

public interface IMenu {
    /**
     * Set heading for the console menu
     *
     * @param heading The heading for the console menu
     */
    void setHeading(String heading);

    /**
     * Add console menu options in bulk
     *
     * @param options array of IMenuOption
     */
    void addOptions(IMenuOption[] options);

    /**
     * Add console menu option one-by-one
     *
     * @param option IMenuOption
     */
    void addOption(IMenuOption option);

    /**
     * Finds the corresponding IMenuOption for the given id
     *
     * @param id identifier of menu option
     * @return IMenuOption
     */
    IMenuOption getMenuOption(int id);

    /**
     * Extracts, Sorts, and Returns ids of all IMenuOption
     *
     * @return sorted array of ids of IMenuOption
     */
    int[] getOrderedOptionIds();

    /** Displays the menu */
    IMenu display();

    /**
     * Selects the menu option that matches supplied id of IMenuOption
     *
     * @param id id of IMenuOption
     */
    void selectOption(int id);

    /** Ask user to chose any option from the menu */
    void awaitOptionSelection();
}
