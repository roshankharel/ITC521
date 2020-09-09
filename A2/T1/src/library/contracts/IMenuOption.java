package library.contracts;

public interface IMenuOption {
    public interface IMenuOptionHandler {
        /**
         * Handles menu option selection event
         */
        public void dispatch();
    }

    /**
     * Get ID of the IMenuOption
     *
     * @return id of the IMenuOption
     */
    public int getID();

    /**
     * Get description of the IMenuOption
     *
     * @return description of the IMenuOption
     */
    public String getDescription();

    /**
     * Add event listener for menu option selection event
     *
     * @param dispatcher menu option selection event handler
     */
    public void addDispatcher(IMenuOptionHandler dispatcher);

    /**
     * Trigger menu option selection event
     */
    public void select();
}
