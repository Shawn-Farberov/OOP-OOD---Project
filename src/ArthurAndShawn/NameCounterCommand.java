package ArthurAndShawn;

import java.util.Scanner;
import java.util.LinkedHashMap;

class NameCounterCommand implements Command {
    private final StoreFacade facade;
    private final Scanner scanner;
    private final LinkedHashMap<String, Integer> nameMap;

    public NameCounterCommand(StoreFacade facade, Scanner scanner, LinkedHashMap<String, Integer> nameMap) {
        this.facade = facade;
        this.scanner = scanner;
        this.nameMap = nameMap;
    }

    @Override
    public void execute() {
        facade.nameCounter(scanner, nameMap);
    }
}
