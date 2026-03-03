package ArthurAndShawn;

public class PrintNamesCommand implements Command {
    private final StoreFacade facade;

    public PrintNamesCommand(StoreFacade facade){
        this.facade = facade;
    }
    @Override
    public void execute(){
        facade.printNames();
    }
}
