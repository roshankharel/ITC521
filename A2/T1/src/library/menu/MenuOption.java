package library.menu;

import library.contracts.IMenuOption;

public class MenuOption implements IMenuOption {
    protected int id;
    protected String description;
    protected IMenuOptionHandler dispatcher;

    /**
     * @param id of the IMenuOption
     * @param description description of the IMenuOption
     */
    public MenuOption(int id, String description) {
        this.id = id;
        this.description = description;
    }

    /**
     * @param id of the IMenuOption
     * @param description description of the IMenuOption
     * @param dispatcher menu option selection event handler
     */
    public MenuOption(int id, String description, IMenuOptionHandler dispatcher) {
        this(id, description);
        addDispatcher(dispatcher);
    }

    /**
     * Get ID of the IMenuOption
     *
     * @return id of the IMenuOption
     */
    @Override
    public int getID() {
        return id;
    }

    /**
     * Get description of the IMenuOption
     *
     * @return description of the IMenuOption
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Add event listener for menu option selection event
     *
     * @param dispatcher menu option selection event handler
     */
    @Override
    public void addDispatcher(IMenuOptionHandler dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * Trigger menu option selection event
     */
    @Override
    public void select() {
        this.dispatcher.dispatch();
    }
}
