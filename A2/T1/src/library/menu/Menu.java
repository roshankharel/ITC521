package library.menu;

import library.KeyboardInput;
import library.contracts.IMenu;
import library.contracts.IMenuOption;

import java.util.Arrays;
import java.util.HashMap;

public class Menu implements IMenu {
    protected String heading;
    protected int[] menuOptionIdsCache = null;
    protected HashMap<Integer, IMenuOption> options = new HashMap<>();

    /**
     * @param heading The heading for the console menu
     */
    public Menu(String heading) {
        setHeading(heading);
    }

    /**
     * Set heading for the console menu
     *
     * @param heading The heading for the console menu
     */
    @Override
    public void setHeading(String heading) {
        this.heading = heading;
    }

    /**
     * Add console menu options in bulk
     *
     * @param options array of IMenuOption
     */
    @Override
    public void addOptions(IMenuOption[] options) {
        for (IMenuOption option : options) {
            addOption(option);
        }
    }

    /**
     * Add console menu option one-by-one
     *
     * @param option IMenuOption
     */
    @Override
    public void addOption(IMenuOption option) {
        options.put(option.getID(), option);
    }

    /**
     * Finds the corresponding IMenuOption for the given id
     *
     * @param id identifier of menu option
     * @return IMenuOption
     */
    @Override
    public IMenuOption getMenuOption(int id) {
        return options.get(id);
    }

    /**
     * Extracts, Sorts, and Returns ids of all IMenuOption
     *
     * @return sorted array of ids of IMenuOption
     */
    @Override
    public int[] getOrderedOptionIds() {
        if (menuOptionIdsCache == null) {
            // map from @prop options ArrayList<Integer> to int[]
            menuOptionIdsCache = options.keySet().stream().mapToInt(i -> i).toArray();
            Arrays.sort(menuOptionIdsCache);
        }

        return menuOptionIdsCache;
    }

    /** Displays the menu */
    @Override
    public IMenu display() {
        int[] optionIDs = getOrderedOptionIds();

        System.out.printf("%s: \n", heading);

        for (int id : optionIDs) {
            IMenuOption option = options.get(id);
            System.out.printf("%d. %s\n", option.getID(), option.getDescription());
        }

        return this;
    }

    /**
     * Selects the menu option that matches supplied id of IMenuOption
     *
     * @param id id of IMenuOption
     */
    @Override
    public void selectOption(int id) {
        options.get(id).select();
    }

    /** Ask user to chose any option from the menu */
    @Override
    public void awaitOptionSelection() {
        int[] choices = getOrderedOptionIds();
        int selectedOptionId = KeyboardInput.getMenuOptionId(choices);
        IMenuOption option = getMenuOption(selectedOptionId);

        System.out.printf("\nSelected Option: #%d. %s\n\n", option.getID(), option.getDescription());

        option.select();
    }
}
