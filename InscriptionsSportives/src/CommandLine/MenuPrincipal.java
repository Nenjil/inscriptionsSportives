package CommandLine;
import inscriptions.*;
import commandLineMenus.*;
import commandLineMenus.rendering.examples.util.InOut;
public class MenuPrincipal {

	// Returns the root menu
	static Menu getMainMenu()
	{
		Menu mainMenu = new Menu("Main Menu");
		
		// Adds a submenu or an option is then done 
		// with a method call
		mainMenu.add(getCalculatorOption());
		mainMenu.add(getCompetMenu());
		mainMenu.addQuit("q");
		return mainMenu;
	}

	// Returns the calculator option
	static Option getCalculatorOption()
	{
		Option calculator = new Option("Calculator", "c", 
				getCalculatorAction());
		return calculator;
	}

	// It is advised to write each action in a separate method
	static Action getCalculatorAction()
	{
		return new Action()
		{
			public void optionSelected()
			{
				int a = InOut.getInt("Input the first operand : "),
						b = InOut.getInt("Input the second operand : ");
				System.out.println("" + a + " + " + b + " = " + (a+b));
			}
		};
	}

	static Action getSayHelloAction()
	{
		return new Action()
		{
			public void optionSelected()
			{
				System.out.println("Hello!");
			}
		};
	}

	static Option getSayHelloOption()
	{
		return new Option("Say Hello", "h", getSayHelloAction());
	}

	// It is also advised to create each menu in a separated method
	static Menu getCompetMenu()
	{
		Menu Competition = MenuCompet.createMenuCompet("Menu competition","Competition","C");
		
		return Competition;

	}

	public static void main(String[] args)
	{
		Menu menu = getMainMenu();
		menu.start();
	}
}
