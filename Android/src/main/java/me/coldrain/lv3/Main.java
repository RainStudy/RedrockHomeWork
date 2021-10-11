package me.coldrain.lv3;

import java.util.*;

/**
 * me.coldrain.lv4.Main
 * Lv4
 * 预习作业
 *
 * @author 寒雨
 * @since 2021/10/1 15:07
 **/
public class Main {

    static String[][] studentData = new String[2][2];

    public static void main(String[] args) {
        start();
    }

    static void start() {
        System.out.println("学生管理系统");
        System.out.println("数据库内容:");
        System.out.println("-------------------------");
        Arrays.stream(studentData).sorted(Comparator.comparing(strs -> Integer.parseInt(Optional.ofNullable(Optional.ofNullable(strs).orElse(new String[2])[0]).orElse("0"))))
                .filter(Objects::nonNull)
                .forEach(strings -> {
                    if (strings[0] != null && strings[1] != null) {
                    System.out.println("学号: " + strings[0] + " 姓名: " + strings[1]);
                }
            });
        System.out.println("-------------------------");
        System.out.println("操作类型: 1-增,2-删,3-查,4-改");
        System.out.println("输入你想要执行的操作类型");
        Scanner scan = new Scanner(System.in);
        if (scan.hasNext()) {
            String input = scan.next();
            switch (input) {
                case "1": {
                    // 增
                    System.out.println("输入学号和姓名(用,分隔开):");
                    if (scan.hasNext()) {
                        String[] strings = scan.next().split(",");
                        if (strings.length != 2) {
                            System.out.println("格式错误");
                        } else if (!strings[0].matches("^[0-9]+$")) {
                            System.out.println("学号中含有非数字，学号非法");
                        } else {
                            add(strings[0], strings[1]);
                            System.out.println("操作成功!");
                        }
                        start();
                        break;
                    }
                }
                case "2": {
                    // 删
                    System.out.println("输入要删掉学生的学号或姓名");
                    if (scan.hasNext()) {
                        String str = scan.next();
                        remove(str);
                        start();
                        break;
                    }
                }
                case "3": {
                    // 查
                    System.out.println("输入要检索学生的学号或姓名");
                    if (scan.hasNext()) {
                        System.out.println("正在搜索...");
                        String str = scan.next();
                        search(str);
                        start();
                        break;
                    }
                }
                case "4": {
                    // 改
                    System.out.println("输入要修改的学生学号或姓名和要修改的内容(逗号隔开)");
                    System.out.println("输入学号则修改姓名，输入姓名则修改学号:");
                    if (scan.hasNext()) {
                        String[] strings = scan.next().split(",");
                        if (strings.length != 2) {
                            System.out.println("格式错误");
                        } else {
                            edit(strings[0], strings[1]);
                        }
                        start();
                        break;
                    }
                }
                default: {
                    System.out.println("不存在的操作方式，程序退出...");
                }
            }
        }
        scan.close();
    }

    /**
     * 增
     *
     * @param id 学号
     * @param name 名字
     */
    static void add(String id, String name) {
        // 数组扩容操作
        String[][] newData = Arrays.copyOf(studentData, studentData.length + 1);
        newData[newData.length - 1] = new String[]{id, name};
        studentData = newData;
    }

    /**
     * 删
     *
     * @param input 输入
     */
    static void remove(String input) {
        boolean find = false;
        if (!input.matches("^[0-9]+$")) {
            // 从姓名中检索
            for (int i = 0; i < studentData.length; i++) {
                if (studentData[i] != null && Optional.ofNullable(studentData[i][1]).orElse("").equals(input)) {
                    studentData[i] = null;
                    find = true;
                    break;
                }
            }
            if (find) {
                System.out.println("已删除学生: " + input + " 的数据");
            } else {
                System.out.println("未找到学生: " + input + " 的数据");
            }
        } else {
            // 从学号中检索
            for (int i = 0; i < studentData.length; i++) {
                if (studentData[i] != null && Optional.ofNullable(studentData[i][0]).orElse("").equals(input)) {
                    studentData[i] = null;
                    find = true;
                    break;
                }
            }
            if (find) {
                System.out.println("已删除学号为: " + input + " 的学生的数据");
            } else {
                System.out.println("未找到学号为: " + input + " 的学生的数据");
            }
        }
    }

    /**
     * 查
     *
     * @param input 输入
     */
    static void search(String input) {
        if (!input.matches("^[0-9]+$")) {
            // 从姓名中检索
            for (String[] studentDatum : studentData) {
                if (studentDatum != null && Optional.ofNullable(studentDatum[1]).orElse("").equals(input)) {
                    System.out.println(Arrays.toString(studentDatum));
                }
            }
        } else {
            // 从学号中检索
            for (String[] studentDatum : studentData) {
                if (studentDatum != null && Optional.ofNullable(studentDatum[0]).orElse("").equals(input)) {
                    System.out.println(Arrays.toString(studentDatum));
                }
            }
        }
    }

    /**
     * 改
     *
     * @param nameOrId 名称或学号
     * @param edit 修改内容
     */
    static void edit(String nameOrId, String edit) {
        boolean find = false;
        if (!nameOrId.matches("^[0-9]+$")) {
            // 从姓名中检索
            for (int i = 0; i < studentData.length; i++) {
                if (studentData[i] != null && Optional.ofNullable(studentData[i][1]).orElse("").equals(nameOrId)) {
                    if (!edit.matches("^[0-9]+$")) {
                        System.out.println("输入格式有误，学号中不能含有非数字字符");
                        return;
                    }
                    String[] data = studentData[i];
                    data[0] = edit;
                    studentData[i] = data;
                    find = true;
                    System.out.println("检索到姓名为: " + nameOrId + "的学生,并为其修改学号为: " + edit);
                    break;
                }
            }
            if (!find) System.out.println("未能检索到姓名为 :" + nameOrId + " 的学生数据");
        } else {
            // 从学号中检索
            for (int i = 0; i < studentData.length; i++) {
                if (studentData[i] != null && Optional.ofNullable(studentData[i][0]).orElse("").equals(nameOrId)) {
                    String[] data = studentData[i];
                    data[1] = edit;
                    studentData[i] = data;
                    find = true;
                    System.out.println("检索到学号为: " + nameOrId + "的学生,并为其修改姓名为: " + edit);
                    break;
                }
            }
            if (!find) System.out.println("未能检索到学号为 :" + nameOrId + " 的学生数据");
        }
    }

}
