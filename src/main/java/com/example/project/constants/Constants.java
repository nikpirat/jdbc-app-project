package com.example.project.constants;

public class Constants {
    private static final String database = " `test`.";
    private static final String skillsTable = "`skills` ";
    public static final String SQL_CREATE_SKILL     = "INSERT INTO" + database + skillsTable + "(SKILL_NAME) VALUES (?);";
    public static final String SQL_DELETE_SKILL     = "DELETE FROM" + database + skillsTable + "WHERE `skill_id` = ?;";
    public static final String SQL_GET_SKILL_BY_ID  = "SELECT * FROM" + database + skillsTable + "WHERE `skill_id` = ?;";
    public static final String SQL_GET_ALL_SKILLS   = "SELECT * FROM" + database + skillsTable + ";";
    public static final String SQL_UPDATE_SKILL     = "UPDATE" + database + skillsTable + "SET `skill_name` = ? WHERE `skill_id` = ?;";


    public static final String SQL_CREATE_ACCOUNT           = "INSERT INTO `test`.`accounts`(`developer_id`, `username`) VALUES (?, ?);";
    public static final String SQL_DELETE_ACCOUNT           = "UPDATE `test`.`accounts` SET `account_status` = 'DELETED' WHERE `account_id` = ?;";
    public static final String SQL_DELETE_ACCOUNT_BY_DEV_ID = "UPDATE `test`.`accounts` SET `account_status` = 'DELETED' WHERE `developer_id` = ?;";
    public static final String SQL_GET_ACCOUNT_BY_ID        = "SELECT * FROM `test`.`accounts` WHERE `account_id` = ?;";
    public static final String SQL_GET_ALL_ACCOUNTS         = "SELECT * FROM `test`.`accounts`;";
    public static final String SQL_UPDATE_ACCOUNT           = "UPDATE `test`.`accounts` SET `developer_id` = ?, `username` = ?, `account_status` = ? WHERE `account_id` = ?";


    public static final String SQL_CREATE_DEVELOPER         = "INSERT INTO `test`.`developers`(name) VALUES (?);";
    public static final String SQL_GET_ALL_DEVELOPERS       =
            "SELECT d.developer_id, d.name, a.account_id, ds.skill_id, s.skill_name\n" +
            "FROM developers d\n" +
            "         LEFT JOIN developer_skills ds ON d.developer_id = ds.developer_id\n" +
            "         LEFT JOIN skills s ON ds.skill_id = s.skill_id\n" +
            "         LEFT JOIN accounts a ON d.developer_id = a.developer_id\n" +
            "ORDER BY developer_id, skill_id;";
    public static final String SQL_GET_LAST_ADDED_DEVELOPER     =
            "SELECT d.`developer_id`\n" +
            "FROM `developers` d\n" +
            "ORDER BY `developer_id` DESC\n" +
            "LIMIT 1;";
    public static final String SQL_GET_DEVELOPER_BY_ID          =
            "SELECT d.developer_id, d.name, a.account_id, ds.skill_id, s.skill_name\n" +
            "FROM developers d\n" +
            "         LEFT JOIN developer_skills ds ON d.developer_id = ds.developer_id\n" +
            "         LEFT JOIN skills s ON ds.skill_id = s.skill_id\n" +
            "         LEFT JOIN accounts a ON d.developer_id = a.developer_id\n" +
            "WHERE d.developer_id = ?\n" +
            "ORDER BY developer_id, skill_id;";
    public static final String SQL_DELETE_DEVELOPER             = "DELETE FROM `test`.`developers` WHERE `developer_id` = ?;";
    public static final String SQL_UPDATE_DEVELOPER             = "UPDATE `test`.`developers` SET `name` = ? WHERE `developer_id` =?;";


    public static final String SQL_CREATE_DEVELOPER_SKILLS      = "INSERT INTO `test`.`developer_skills`(DEVELOPER_ID, SKILL_ID) VALUES (?, ?);";
    public static final String SQL_DELETE_DEVELOPER_SKILLS      = "DELETE FROM `test`.`developer_skills` WHERE `developer_id` = ?;";


}
