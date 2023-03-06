package com.example.project;

import com.example.project.view.CommonView;

public class Main {
    public static void main(String[] args) {
        CommonView commonView = new CommonView();
        commonView.run();
    }
}

/**
ACCOUNT MENU - 90%
    FIND ALL - OK
    GET BY ID - OK
    CREATE -    85% НА ОДНОГО ЮЗЕРА ТОЛЬКО 1 АККАУНТ(ПОСЛЕ УДАЛЕНИЯ АККАУНТ ОСТАЕТСЯ И НОВЫЙ СОЗДАТЬ НЕ ПОЛУЧИТСЯ НА ТОГО ЖЕ ЮЗЕРА)
                ВЫБИВАЕТ ERROR DUPLICATE ENTRY
    DELETE - OK
    UPDATE - OK
        CHANGE USER ID - OK
        CHANGE USERNAME - OK
        CHANGE STATUS - OK

 */
/**
DEVELOPER MENU - 90%
    GET ALL - OK
    GET BY ID - OK
    CREATE - OK
    DELETE - OK
    UPDATE - OK
        NAME - OK
        USERNAME - OK
        ACCOUNT STATUS - OK
        ADD SKILL - OK
        DELETE SKILL - OK

 */
/**
SKILL MENU - 100%
    FIND ALL - OK
    GET BY ID - OK
    CREATE - OK
    DELETE - OK
    UPDATE - OK
 */