package ArthurAndShawn;

import java.util.LinkedHashMap;

class ReverseArrayCommand implements Command {
    private final StoreFacade facade;
    private final LinkedHashMap<String, Integer> nameMap;

    public ReverseArrayCommand(StoreFacade facade, LinkedHashMap<String, Integer> nameMap) {
        this.facade = facade;
        this.nameMap = nameMap;
    }

    @Override
    public void execute() {
        facade.reverseArray(nameMap);
    }
}
