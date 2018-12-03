package CommandLine;

import commandLineMenus.*;
import commandLineMenus.rendering.examples.util.InOut;

public class MainMenuConsole{
	
	
	public static void main(String[] args)
	{
		 Creates the root menu of the application
		Menu rootMenu = new Menu("Root Menu");
		 
	
		// Creates options
		//Gestion + Selection competition dans classe plus tard
		Option calculator = new Option("Calculator", "ca");
	
		
		
		// Adds an option to the rootMenu 
		rootMenu.add(calculator);
		rootMenu.add(Team);
		
		
		//rootMenu.add(Equipe);
	//	rootMenu.add(Personne);
		// Adds the sub-menu sayHelloMenu to the rootMenu
		// Please notice that since Menu extends Option, polymorphism allows us to pass the Menu sayHelloMenu where an Option was expected.
		rootMenu.add(Competition);
		
		rootMenu.add(Person);
		// Adds the quit option
		rootMenu.addQuit("q");
		
		
		// Defines the action that will be triggered if the calculator is selected.
		calculator.setAction(new Action()
		{
			// Method triggered if the calculatorOption is selected 
			public void optionSelected()
			{
				int a = InOut.getInt("Input the first operand : "),
						b = InOut.getInt("Input the second operand : ");
				System.out.println("" + a + " + " + b + " = " + (a+b));
			}
		});
		
		// Please notice that the action can be passed to the constructor of Option 
		Competition.add(				
				new Option("Say Hello", "h", new Action()
				{
					public void optionSelected()
					{
						System.out.println("Hello!");
					}
				}));
		
		// Adds an option to go back to the rootMenu
		Competition.addBack("r");
		
		// Once an option has been selected in sayHelloMenu, and the associated action is done, we will automatically go back to the rootMenu. 
		Competition.setAutoBack(true);
		
		rootMenu.start();
	}
	
	
}


