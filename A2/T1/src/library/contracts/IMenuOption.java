package library.contracts;

public interface IMenuOption {
    /**
     * Get ID of the IMenuOption
     *
     * @return id of the IMenuOption
     */
    int getID();

    /**
     * Get description of the IMenuOption
     *
     * @return description of the IMenuOption
     */
    String getDescription();

    /**
     * Add event listener for menu option selection event
     *
     * @param dispatcher menu option selection event handler
     */
    void addDispatcher(IMenuOptionHandler dispatcher);

    /** Trigger menu option selection event */
    void select();

    interface IMenuOptionHandler {
        /** Handles menu option selection event */
        void dispatch();
    }
}
