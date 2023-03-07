package com.example.project.constants;

public class Constants {
    private static final String database = " `test`.";
    private static final String skillsTable = "`skills` ";
    private static final String accountsTable = "`accounts` ";
    private static final String developersTable = "`developers` ";
    private static final String developerSkillsTable = "`developer_skills` ";

    public static final String SQL_CREATE_SKILL     = "INSERT INTO" + database + skillsTable + "(SKILL_NAME) VALUES (?);";
    public static final String SQL_DELETE_SKILL     = "DELETE FROM" + database + skillsTable + "WHERE `skill_id` = ?;";
    public static final String SQL_GET_SKILL_BY_ID  = "SELECT * FROM" + database + skillsTable + "WHERE `skill_id` = ?;";
    public static final String SQL_GET_ALL_SKILLS   = "SELECT * FROM" + database + skillsTable + ";";
    public static final String SQL_UPDATE_SKILL     = "UPDATE" + database + skillsTable + "SET `skill_name` = ? WHERE `skill_id` = ?;";


    public static final String SQL_CREATE_ACCOUNT           = "INSERT INTO" + database + accountsTable + "(`developer_id`, `username`) VALUES (?, ?);";
    public static final String SQL_DELETE_ACCOUNT           = "UPDATE" + database + accountsTable + "SET `account_status` = 'DELETED' WHERE `account_id` = ?;";
    public static final String SQL_DELETE_ACCOUNT_BY_DEV_ID = "UPDATE" + database + accountsTable + "SET `account_status` = 'DELETED' WHERE `developer_id` = ?;";
    public static final String SQL_GET_ACCOUNT_BY_ID        = "SELECT * FROM" + database + accountsTable + "WHERE `account_id` = ?;";
    public static final String SQL_GET_ALL_ACCOUNTS         = "SELECT * FROM" + database + accountsTable + ";";
    public static final String SQL_UPDATE_ACCOUNT           = "UPDATE" + database + accountsTable + "SET `developer_id` = ?, `username` = ?, `account_status` = ? WHERE `account_id` = ?";


    public static final String SQL_CREATE_DEVELOPER         = "INSERT INTO" + database + developersTable + "(name) VALUES (?);";
    public static final String SQL_DELETE_DEVELOPER         = "DELETE FROM" + database + developersTable + "WHERE `developer_id` = ?;";
    public static final String SQL_UPDATE_DEVELOPER         = "UPDATE" + database + developersTable + "SET `name` = ? WHERE `developer_id` =?;";
    public static final String SQL_GET_ALL_DEVELOPERS       =
            "SELECT d.`developer_id`, d.`name`, a.`account_id`, ds.`skill_id`, s.`skill_name`\n" +
            "FROM" + database + developersTable + "d\n" +
            "         LEFT JOIN" + database + developerSkillsTable + "ds ON d.`developer_id` = ds.`developer_id`\n" +
            "         LEFT JOIN" + database + skillsTable + "s ON ds.`skill_id` = s.`skill_id`\n" +
            "         LEFT JOIN" + database + accountsTable + "a ON d.`developer_id` = a.`developer_id`\n" +
            "ORDER BY `developer_id`, `skill_id`;";
    public static final String SQL_GET_LAST_ADDED_DEVELOPER =
            "SELECT d.`developer_id`\n" +
            "FROM" + database + developersTable + "d\n" +
            "ORDER BY `developer_id` DESC\n" +
            "LIMIT 1;";
    public static final String SQL_GET_DEVELOPER_BY_ID      =
            "SELECT d.`developer_id`, d.`name`, a.`account_id`, ds.`skill_id`, s.`skill_name`\n" +
            "FROM" + database + developersTable + "d\n" +
            "         LEFT JOIN" + database + developerSkillsTable + "ds ON d.`developer_id` = ds.`developer_id`\n" +
            "         LEFT JOIN" + database + skillsTable + "s ON ds.`skill_id` = s.`skill_id`\n" +
            "         LEFT JOIN" + database + accountsTable + "a ON d.`developer_id` = a.`developer_id`\n" +
            "WHERE d.`developer_id` = ?\n" +
            "ORDER BY `developer_id`, `skill_id`;";

    public static final String SQL_CREATE_DEVELOPER_SKILLS  = "INSERT INTO" + database + developerSkillsTable + "(DEVELOPER_ID, SKILL_ID) VALUES (?, ?);";
    public static final String SQL_DELETE_DEVELOPER_SKILLS  = "DELETE FROM" + database + developerSkillsTable + "WHERE `developer_id` = ?;";


}
