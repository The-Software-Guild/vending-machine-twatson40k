
public class VM_Controller {

    private UserIO io = new UserIOConsoleImpl();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {
			
            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    io.print("LIST ITEMS");
                    break;
                case 2:
                    io.print("PURCHASE ITEMS");
                    break;
                case 3:
                    io.print("RESUPPLY ITEM");
                    break;
                case 4:
                    io.print("CREATE & EDIT ITEMS");
                    break;
                case 5:
                    io.print("REMOVE ITEMS");
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }

        }
        io.print("GOOD BYE");
    }
	
	private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
}