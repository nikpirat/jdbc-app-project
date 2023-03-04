package com.example.project;

import com.example.project.model.Developer;
import com.example.project.model.Skill;
import com.example.project.repository.SkillRepository;
import com.example.project.repository.impl.SkillRepositoryImpl;
import com.example.project.view.CommonView;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        CommonView commonView = new CommonView();
        commonView.run();
    }

//пофиксить метод update для девелопера, заменить логику удаления/сохранение на обновление существующего - ?
//доделать девелопер вьюху метод апдейт - 60%

}

/*
ACCOUNT MENU - ?
    FIND ALL - OK
    GET BY ID - OK
    CREATE - 85% (check for duplicate entry for creating account after successful delete - 0%)
    DELETE - OK
    UPDATE -
        CHANGE USER ID - OK
        CHANGE USERNAME - OK
        CHANGE STATUS - 70% OK BUT(пофиксить статус аккаунт метод, который при неправильном выборе кидает ошибку - 85%)

 */

/*
DEVELOPER MENU - ?
    GET ALL - OK
    GET BY ID - OK
    CREATE - OK
    DELETE - NOT OK(FOREIGN KEY FROM ACCOUNTS FORBID DELETE FROM DEVELOPERS)
    UPDATE -
        NAME - OK
        USERNAME - OK
        ACCOUNT STATUS -
        ADD SKILL -
        DELETE SKILL -

 */


/*
SKILL MENU - 100%
    FIND ALL - OK
    GET BY ID - OK
    CREATE - OK
    DELETE - OK
    UPDATE - OK
 */