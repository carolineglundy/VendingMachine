package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.VendingMachine;

import java.io.File;
import java.util.Scanner;

public class VendingMachineCLI {

	private Menu menu;
	private File inventoryFile = new File("C:\\Users\\Student\\Workspace\\mod1-capstone-blue-t10\\java\\vendingmachine.csv");
	private VendingMachine vm = new VendingMachine(inventoryFile);

	private static Scanner userInput = new Scanner(System.in);

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private static final String OPTIONS_MENU_FEED_MONEY = "Feed Money";
	private static final String OPTIONS_MENU_SELECT_PRODUCT = "Select Product";
	private static final String OPTIONS_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] OPTIONS_MENU_OPTIONS = { OPTIONS_MENU_FEED_MONEY, OPTIONS_MENU_SELECT_PRODUCT, OPTIONS_MENU_FINISH_TRANSACTION };

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				System.out.println();
				System.out.print(vm.displayItems());
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				displayVendingMachineOptions();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println();
				System.out.println("Thank you for stopping by! Don't forget your change.");
				break;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

	private void displayVendingMachineOptions() {
		while (true) {
			System.out.println();
			System.out.printf("Current Balance: $%.2f", vm.getCurrentAmount());
			System.out.println();

			String choice = (String) menu.getChoiceFromOptions(OPTIONS_MENU_OPTIONS);

			if (choice.equals(OPTIONS_MENU_FEED_MONEY)) {
				insertMoney();
			} else if (choice.equals(OPTIONS_MENU_SELECT_PRODUCT)) {
				System.out.println();
				selectItem();
			} else if (choice.equals(OPTIONS_MENU_FINISH_TRANSACTION)) {
				System.out.println();
				System.out.println(vm.spitOutChange());
				vm.resetToZeroBalance();
				break;
			}
		}
	}

	private void insertMoney() {
		System.out.println();
		System.out.print("How much money would you like to deposit: ");
		String userInputMoneyDeposited = userInput.nextLine();

		String result = vm.depositMoney(userInputMoneyDeposited);

		if (!result.equals("")) {
			System.out.println(result);
		}
	}

	private void selectItem() {
		System.out.print(vm.displayItems());

		System.out.println();
		System.out.print("Please enter an item code: ");
		String userSelection = userInput.nextLine();
		try {
			System.out.println();
			System.out.println(vm.itemSelection(userSelection));

		} catch (Exception e) {

		}
	}

}
