package dndStuff;

import dndStuffGUI.GUI;
import dndStuffRandom.DnDrand;

public class main {

	public static void main(String[] args) {
		DnDrand rand = new DnDrand();
		GUI gui = new GUI(rand);
	}

}
