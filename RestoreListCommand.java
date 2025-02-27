package ArthurAndShawn;

import java.util.ArrayList;

class RestoreListCommand implements Command {
    private final Manager manager;

    public RestoreListCommand(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        ArrayList<String> restoredList = manager.restoreList();
        if (restoredList == null || restoredList.isEmpty()) {
            System.out.println("Error: No saved list found! Did you run option 104?");
        } else {
            System.out.println("Restored List: " + restoredList);
        }
    }
}
