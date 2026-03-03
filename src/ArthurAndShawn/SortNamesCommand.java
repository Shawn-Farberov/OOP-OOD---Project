package ArthurAndShawn;

class SortNamesCommand implements Command {
    private final StoreFacade facade;

    public SortNamesCommand(StoreFacade facade) {
        this.facade = facade;
    }

    @Override
    public void execute() {
        facade.sort();
    }
}
