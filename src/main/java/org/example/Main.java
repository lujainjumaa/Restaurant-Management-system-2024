package org.example;

import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Menu main_menu = new Menu();
        main_menu.loadMenu();
        Type.load_types();
        MenuItem mi1 = new MenuItem(2,"Pasta","Classic Italian pasta with tomato sauce and pesto",12.50,true);
        MenuView frame = new MenuView(main_menu);
//        main_menu.add_menu_item_to_file(mi1,2);
        main_menu.edit_menu_item(mi1);
    }
}