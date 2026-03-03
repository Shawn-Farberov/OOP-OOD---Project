package ArthurAndShawn;

import java.util.ArrayList;
import java.util.LinkedHashMap;

class SaveListCommand implements Command {
    private final Manager manager;
    private final LinkedHashMap<String, Integer> nameMap;

    public SaveListCommand(Manager manager, LinkedHashMap<String, Integer> nameMap) {
        this.manager = manager;
        this.nameMap = nameMap;
    }

    @Override
    public void execute() {
        ArrayList<String> reversedList = manager.nameArray(nameMap);
        if (reversedList.isEmpty()) {
            System.out.println("Error: No reversed list available. Did you run option 102?");
            return;
        }

        manager.saveList(reversedList);
        System.out.println("Reversed list has been saved.");
    }
}

