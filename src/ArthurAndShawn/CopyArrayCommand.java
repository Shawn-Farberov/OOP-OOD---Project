package ArthurAndShawn;

import java.util.LinkedHashMap;

class CopyArrayCommand implements Command {
    private final StoreFacade facade;
    private LinkedHashMap<String, Integer> nameMap;

    public CopyArrayCommand(StoreFacade facade) {
        this.facade = facade;
    }

    @Override
    public void execute() {
        nameMap = facade.copyArrayToHashMap();
    }

    public LinkedHashMap<String, Integer> getNameMap() {
        return nameMap;
    }
}
